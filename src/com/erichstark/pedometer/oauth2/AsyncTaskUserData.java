package com.erichstark.pedometer.oauth2;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import com.erichstark.pedometer.MainActivity;
import com.erichstark.pedometer.sqlite.helper.DatabaseHelper;
import com.erichstark.pedometer.sqlite.model.Login;
import com.erichstark.pedometer.sqlite.model.User;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class AsyncTaskUserData extends AsyncTask<String, Void, User> {

	private Context context;
	private DatabaseHelper db;

	public AsyncTaskUserData(Context context) {
		this.context = context;
	}

	@Override
	protected User doInBackground(String... arg0) {
		// Creating service handler class instance
		ServiceHandler sh = new ServiceHandler();

		// Making a request to url and getting response
		String jsonStr = sh.makeServiceCall(arg0[0], ServiceHandler.GET);

		Log.d("Response: ", "> " + jsonStr);

		// my code

		String[] str = { "", "" };
		User user = new User();
		if (jsonStr != null) {
			try {
				JSONObject jObjUser = new JSONObject(jsonStr);
				
				user.setDateOfBirth(jObjUser.getString("dateofbirth"));
				user.setGender(jObjUser.getString("gender"));
				user.setHeight((float) jObjUser.getDouble("height"));
				user.setHeightUnit(jObjUser.getInt("HeightUnit"));
				user.setWeight((float) jObjUser.getDouble("weight"));
				user.setWeightUnit(jObjUser.getInt("WeightUnit"));
				user.setLogo(jObjUser.getString("logo"));
				user.setNickname(jObjUser.getString("nickname"));
				user.setUserid(jObjUser.getString("userid"));
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		//return login;
		return user;
	}

	@Override
	protected void onPostExecute(User result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

		
		
		db = new DatabaseHelper(context);
		
		long logee = db.createUser(result);
		
		// test activity report
		String activityUrl = "https://api.ihealthlabs.com:8443/openapiv2/user/"
				+ db.getLogin(1).getUser_id()
				+ "/activity.json/?client_id="
				+ MainActivity.CLIENT_ID
				+ "&client_secret="
				+ MainActivity.CLIENT_SECRET
				+ "&redirect_uri=http://erichstark.com&access_token="
				+ db.getLogin(1).getAccess_token()
				+ "&page_index=1&sc=17979dfde8cb4c30813ad612d0b974e9&sv=e9495e71db784657a16edfadf6f06754";
		db.close();

		AsyncTaskActivityData getActivityData = new AsyncTaskActivityData(context);
		AsyncTask<String, Void, Void> asyncGetActivityData = getActivityData
				.execute(activityUrl);
		
		db.close();


	}

	public String getCurrentTimeStamp() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
	}

}
