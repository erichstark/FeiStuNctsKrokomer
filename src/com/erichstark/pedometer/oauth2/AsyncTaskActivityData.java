package com.erichstark.pedometer.oauth2;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
	private String accessToken;
	private String userID;

	public AsyncTaskActivityData(Context context) {
		this.context = context;
	}

	@Override
	protected ActivityReport doInBackground(String... arg0) {
		// Creating service handler class instance
		ServiceHandler sh = new ServiceHandler();
		
		

		db = new DatabaseHelper(context);
		accessToken = db.getLogin(1).getAccess_token();
		userID = db.getLogin(1).getUser_id();
		
		// Making a request to url and getting response
		String jsonStr = sh.makeServiceCall(arg0[0], ServiceHandler.GET);
		
		Log.d("Response: ", "> " + jsonStr);
		
		 String dataUrl = "https://api.ihealthlabs.com:8443/openapiv2/user/"
		 + userID
		 + "/activity.json/?client_id="
		 + MainActivity.CLIENT_ID
		 + "&client_secret="
		 + MainActivity.CLIENT_SECRET
		 + "&redirect_uri=http://erichstark.com&access_token="
		 + accessToken
		 + "&page_index=1&sc=17979dfde8cb4c30813ad612d0b974e9&sv=e9495e71db784657a16edfadf6f06754";
		
		

		// my code
		ActivityReport activity = new ActivityReport();
		if (jsonStr != null) {
			try {
				JSONObject jObjLogin = new JSONObject(jsonStr);
				
				
//				login.setAccess_token(jObjLogin.getString("AccessToken"));
//				login.setRefresh_token(jObjLogin.getString("RefreshToken"));
//				login.setExpires(jObjLogin.getInt("Expires"));
//				login.setUser_id(jObjLogin.getString("UserID"));
//				login.setUser_para(jObjLogin.getString("client_para"));
//				login.setTimestamp(getCurrentTimeStamp());
				

				
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
