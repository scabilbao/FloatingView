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
package com.gruposca.android.floatingview.helpers;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

public class ExceptionsHelper {

	public static void manage(View context, Exception e) { manage(context.getContext(), e); }
	
	public static void manage(Fragment context, Exception e) { manage(context.getActivity(), e); }
	
	public static void manage(Context context, Exception e) {
		if (e != null) {
			e.printStackTrace();
			
			LogsHelper.log(e.toString());
			
			if (context != null) {
				Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
			}
		}
	}
}
