package com.erichstark.pedometer.oauth2;

import org.json.JSONException;
import org.json.JSONObject;

import com.erichstark.pedometer.sqlite.helper.DatabaseHelper;
import com.erichstark.pedometer.sqlite.model.Login;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class AsyncTaskLoginData extends AsyncTask<String, Void, String[]> {
	
	private Context context;
	private DatabaseHelper db;
	private String strtoken = null;
	private String userID = null;
	private Object str;

	public AsyncTaskLoginData(Context context) {
		this.context = context;
	}
	
	@Override
	protected String[] doInBackground(String... arg0) {
		// Creating service handler class instance
		ServiceHandler sh = new ServiceHandler();
		
		

		// Making a request to url and getting response
		String jsonStr = sh.makeServiceCall(arg0[0], ServiceHandler.GET);

		Log.d("Response: ", "> " + jsonStr);

		// my code

		try {
			JSONObject jObject = new JSONObject(jsonStr);
			strtoken = jObject.getString("AccessToken");
			userID = jObject.getString("UserID");
			//Log.d("Response: ", "> " + strtoken);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Login login = new Login();
//		login.setAccess_token(strtoken);
//		login.setUser_id(userID);
//		db.createLogin(login);
//		
//		db.close();
		String[] str = {"", ""};
		str[0] = strtoken;
		str[1] = userID;

		return str;
	}

	@Override
	protected void onPostExecute(String[] result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
//		String dataUrl = "https://api.ihealthlabs.com:8443/openapiv2/user/"
//				+ userID
//				+ "/activity.json/?client_id="
//				+ appId
//				+ "&client_secret="
//				+ appSecret
//				+ "&redirect_uri=http://erichstark.com&access_token="
//				+ strtoken
//				+ "&page_index=1&sc=17979dfde8cb4c30813ad612d0b974e9&sv=e9495e71db784657a16edfadf6f06754";
//		
//		GetContacts2 getData = new GetContacts2();
//		AsyncTask<String, String, Void> asyncGetData = getData.execute(dataUrl);
		db = new DatabaseHelper(context);
		Login login = new Login();
		login.setAccess_token(result[0]);
		login.setUser_id(result[1]);
		long logee = db.createLogin(login);
		
		Log.d("asyncDB......: ", " " +db.getLogin(logee).getId());
		
		db.close();
		
//		
//		Intent returnIntent = new Intent();
//		returnIntent.putExtra("result", 1);
//		setResult(RESULT_OK,returnIntent);
////		
//		finish();
	}

}
