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

package com.gruposca.android.floatingview.ui.activities;

import android.os.Bundle;

import com.gruposca.android.floatingview.R;
import com.gruposca.android.floatingview.helpers.ExceptionsHelper;
import com.gruposca.android.floatingview.ui.views.FloatingView;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.support.v7.app.ActionBarActivity;


public class MainActivity extends ActionBarActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        try {
        	
        	setContentView(R.layout.activity_main);
        	
        	final FloatingView floatinView = (FloatingView)findViewById(R.id.FloatingView);
        	if (floatinView != null) {
        		floatinView.setScrollableViewId(R.id.List);
        		floatinView.setOnClickListener(this);
        	}
        	
        } catch (Exception e) {
        	ExceptionsHelper.manage(this, e);
        }
    }

	@Override
	public void onClick(View view) {
		try {
		
			Toast.makeText(this, "Tap!!!", Toast.LENGTH_SHORT).show();
			
		} catch (Exception e) {
        	ExceptionsHelper.manage(this, e);
        }
	}
}
