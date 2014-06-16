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

import android.util.Log;

public class LogsHelper {

	private static final String LOG_TAG = "Android UX Library";
	
	public static void log(String message) { Log.d(LOG_TAG, message); } 
	
	public static void error(String message) { Log.e(LOG_TAG, message); }
}
