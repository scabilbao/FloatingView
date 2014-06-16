/*
   Copyright Grupo SCA 2014 (@SCA_Consultores)

   Licensed under the LGPL Lesser General Public License, Version 3.0 (the "License"),  
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.gnu.org/licenses/lgpl.html

   Unless required by applicable law or agreed to in writing, software 
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
   
   Website: http://gruposca.com
   Contact: hello@gruposca.com
*/
package com.gruposca.android.floatingview.ui.views;

import com.gruposca.android.floatingview.R;
import com.gruposca.android.floatingview.helpers.ExceptionsHelper;
import com.gruposca.android.floatingview.helpers.LogsHelper;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class FloatingView extends FrameLayout implements OnScrollListener {

	private int         mLastShowedItem = 0;
	private int         mScrollableViewId;
	private AbsListView mScrollableView;
	private boolean     mShoweb = false;
	
	public FloatingView(Context context) { super(context); }
	public FloatingView(Context context, AttributeSet attrs) { super(context, attrs); initializeAttributes(attrs); }
	public FloatingView(Context context, AttributeSet attrs, int defStyle) { super(context, attrs, defStyle); initializeAttributes(attrs); }
	
	/**
	 * Set the scrollable view linked to the FloatingView.
	 */
	public void setScrollableView(AbsListView scrollableView) { mScrollableView = scrollableView; }
	
	/**
	 * Set the scrollable view ID linked to the FloatingView.
	 */
	public void setScrollableViewId(int id) { mScrollableViewId = id; }
	
	/**
	 * Returns true or false if the view is showed.
	 */
	public boolean isShown() { return mShoweb; }
	
	
	@Override
	protected void onAttachedToWindow() {
		initializeView();
		showView(); //Display the View.
	}
	
	@Override
	protected Parcelable onSaveInstanceState() {
		
		Bundle savedState = new Bundle();
		
		try {
			
			savedState.putParcelable("instanceState", super.onSaveInstanceState());
			if (mScrollableView != null)
				savedState.putInt("scrollabeViewId", mScrollableView.getId());
			else
				savedState.putInt("scrollabeViewId", 0);
			
		} catch (Exception e) {
			ExceptionsHelper.manage(getContext(), e);
		}
		
		return savedState;
	}
	
	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		try {
			
			if (state instanceof Bundle) {
				Bundle bundle = (Bundle)state;
				
				mScrollableViewId = bundle.getInt("scrollabeViewId"); 
				state = bundle.getParcelable("instanceState");
				
				configureScrollabeView();
			}
			
			super.onRestoreInstanceState(state);
			
		} catch (Exception e) {
			ExceptionsHelper.manage(getContext(), e);
		}
	}
	
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		if (firstVisibleItem > mLastShowedItem) {
			hideView();
		} else if (firstVisibleItem < mLastShowedItem) {
			showView();
		}
		
		mLastShowedItem = firstVisibleItem;
	}
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) { }
	
	/**
	 * Initialize view properties.
	 */
	private void initializeView() {
		if (getParent() instanceof RelativeLayout) {

			RelativeLayout.LayoutParams paramns = (RelativeLayout.LayoutParams)getLayoutParams();
			paramns.addRule(RelativeLayout.CENTER_HORIZONTAL);
			paramns.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

			setClickable(true);
			
			configureScrollabeView();
			
		} else {
			LogsHelper.log("The parent view of FloatingView not is a RelativeLayout, please put this view inside of RelativeLayout.");
		}
	}
	
	/**
	 * Configure the listeners of the linked Scrollable view.
	 */
	private void configureScrollabeView() {
		if (mScrollableView == null) {
			if (mScrollableViewId != 0) {
				View scrollableView = ((RelativeLayout)getParent()).findViewById(mScrollableViewId);
				if (scrollableView instanceof AbsListView) {
					mScrollableView = (AbsListView)scrollableView;
				} else {
					LogsHelper.log("The ScrollableView linked to this view it's not instance of AbsListView.");
				}
			}
		}
		
		if (mScrollableView != null) {
			if (mScrollableView.getId() == View.NO_ID)
				LogsHelper.log("The ScrollableView linked to this view does not have set the ID attribute.");
			
			mScrollableView.setOnScrollListener(this);
		}
	}
	
	/**
	 * Initialize custom attributes.
	 */
	private void initializeAttributes(AttributeSet attrs) {
		if (attrs != null) {
			TypedArray customParameters = getContext().obtainStyledAttributes(attrs, R.styleable.FloatingView);
			if (customParameters != null) {
				mScrollableViewId = customParameters.getInt(R.styleable.FloatingView_scrollableView, 0);
				
				customParameters.recycle();
			}
		}
	}
	
	/**
	 * Execute the animation and show the view on the screen.
	 */
	public void showView() {
		if (!isShown()) {
			mShoweb = true;
			AnimatorSet animatorSet = (AnimatorSet)AnimatorInflater.loadAnimator(getContext(), R.anim.floating_view_show);
			animatorSet.setTarget(this);
			animatorSet.start();
		}
	}
	
	/**
	 * Execute the animation and hide the view.
	 */
	public void hideView() {
		if (isShown()) {
			mShoweb = false;
			AnimatorSet animatorSet = (AnimatorSet)AnimatorInflater.loadAnimator(getContext(), R.anim.floating_view_hide);
			animatorSet.setTarget(this);
			animatorSet.start();
		}
	}

	
}
