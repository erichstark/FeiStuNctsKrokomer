package com.erichstark.pedometer.oauth2;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.erichstark.pedometer.MainActivity;
import com.erichstark.pedometer.sqlite.helper.DatabaseHelper;
import com.erichstark.pedometer.sqlite.model.ActivityReport;
import com.erichstark.pedometer.sqlite.model.Login;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class AsyncTaskActivityData extends AsyncTask<String, Void, ActivityReport> {

	private Context context;
	private DatabaseHelper db;

	public AsyncTaskActivityData(Context context) {
		this.context = context;
	}

	@Override
	protected ActivityReport doInBackground(String... arg0) {
		// Creating service handler class instance
		ServiceHandler sh = new ServiceHandler();
		
		

//		db = new DatabaseHelper(context);

		
		// Making a request to url and getting response
		String jsonStr = sh.makeServiceCall(arg0[0], ServiceHandler.GET);
		
		Log.d("Response ACTIVITTY: ", "> " + jsonStr);
		
//		 String dataUrl = "https://api.ihealthlabs.com:8443/openapiv2/user/"
//		 + db.getLogin(1).getUser_id()
//		 + "/activity.json/?client_id="
//		 + MainActivity.CLIENT_ID
//		 + "&client_secret="
//		 + MainActivity.CLIENT_SECRET
//		 + "&redirect_uri=http://erichstark.com&access_token="
//		 + db.getLogin(1).getAccess_token()
//		 + "&page_index=1&sc=17979dfde8cb4c30813ad612d0b974e9&sv=e9495e71db784657a16edfadf6f06754";
		
		

		// my code
		ActivityReport activity = new ActivityReport();
		if (jsonStr != null) {
			try {
				JSONObject jObjLogin = new JSONObject(jsonStr);
				
				JSONArray jData = jObjLogin.getJSONArray("ARDataList");
				
//				login.setAccess_token(jObjLogin.getString("AccessToken"));
//				login.setRefresh_token(jObjLogin.getString("RefreshToken"));
//				login.setExpires(jObjLogin.getInt("Expires"));
//				login.setUser_id(jObjLogin.getString("UserID"));
//				login.setUser_para(jObjLogin.getString("client_para"));
//				login.setTimestamp(getCurrentTimeStamp());
				
				db = new DatabaseHelper(context);
				// looping through All Contacts
                for (int i = 0; i < jData.length(); i++) {
                    JSONObject c = jData.getJSONObject(i);
                    
                    ActivityReport report = new ActivityReport();
                    report.setCalories(c.getInt("Calories"));
                    report.setDistanceTraveled((float) c.getDouble("DistanceTraveled"));
                    report.setMdate(c.getString("MDate"));
                    report.setSteps(c.getString("Steps"));

                    long juju = db.createActivityReport(report);
                    // adding contact to contact list
                    //contactList.add(contact);
                    //Log.d("activityReport vypis: ",""+ db.getActivityReport(juju).getCalories() +" : " +db.getActivityReport(juju).getId());
                }
                
				db.close();

				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return activity;
	}

	@Override
	protected void onPostExecute(ActivityReport result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

//		 String dataUrl = "https://api.ihealthlabs.com:8443/openapiv2/user/"
//		 + userID
//		 + "/activity.json/?client_id="
//		 + appId
//		 + "&client_secret="
//		 + appSecret
//		 + "&redirect_uri=http://erichstark.com&access_token="
//		 + strtoken
//		 +
//		 "&page_index=1&sc=17979dfde8cb4c30813ad612d0b974e9&sv=e9495e71db784657a16edfadf6f06754";
//		
		// GetContacts2 getData = new GetContacts2();
		// AsyncTask<String, String, Void> asyncGetData =
		// getData.execute(dataUrl);
//		db = new DatabaseHelper(context);
//		long logee = db.createLogin(result);
//
//		
//
//		db.close();

		//
		// Intent returnIntent = new Intent();
		// returnIntent.putExtra("result", 1);
		// setResult(RESULT_OK,returnIntent);
		// //
		// finish();
	}

	public String getCurrentTimeStamp() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
	}

}
