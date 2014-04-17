package com.erichstark.pedometer.oauth2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import com.erichstark.pedometer.R;
import com.erichstark.pedometer.sqlite.helper.DatabaseHelper;
import com.erichstark.pedometer.sqlite.model.Login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class WebViewActivity extends Activity {

	private WebView webView;
	public static final String appId = "417483e552844d0a8bd37fb7166401a0";
	public static final String appSecret = "4f6afba3cf624833807e9f64ca2638d6";

	private String strtoken;
	private String userID;

	private long unixTime;
	
	private TextView calories;
	private TextView distance;
	private TextView steps;
	
	private String str_calories;
	private String str_distance;
	private String str_steps;
	
	private DatabaseHelper db;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_view);

		db = new DatabaseHelper(getApplicationContext());
		
		unixTime = System.currentTimeMillis() / 1000L;
		Log.d("unix", ">" + unixTime);

		webView = (WebView) findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setUseWideViewPort(true);
		

		Bundle extras = getIntent().getExtras();
		String addOn = extras.getString("Key");

		webView.loadUrl(addOn);

		webView.setWebViewClient(new WebViewClient() {

			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			public void onLoadResource(WebView view, String url) {

				if (url.toLowerCase().contains(
						"http://erichstark.com/?code=".toLowerCase())) {

					String[] arr = url.split("code=");
					url = "https://api.ihealthlabs.com:8443/OpenApiV2/OAuthv2/userauthorization/?"
							+ "client_id="
							+ appId
							+ "&client_secret="
							+ appSecret
							+ "&grant_type=authorization_code"
							+ "&redirect_uri=http://erichstark.com&code="
							+ arr[1] + "&client_para=xxx";

					//GetContacts initialize = new GetContacts();// retrieve
																// callbackID &
																// OAuthTokenSecret
					//AsyncTask<String, Void, Void> initializeOAuthResultSet = initialize
					//		.execute(url);
					
					Intent returnIntent = new Intent();
					returnIntent.putExtra("result", url);
					setResult(RESULT_OK,returnIntent);

					finish();

				}
			}
		});

	}

	private class GetContacts extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... arg0) {
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

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
//			String dataUrl = "https://api.ihealthlabs.com:8443/openapiv2/user/"
//					+ userID
//					+ "/activity.json/?client_id="
//					+ appId
//					+ "&client_secret="
//					+ appSecret
//					+ "&redirect_uri=http://erichstark.com&access_token="
//					+ strtoken
//					+ "&page_index=1&sc=17979dfde8cb4c30813ad612d0b974e9&sv=e9495e71db784657a16edfadf6f06754";
//			
//			GetContacts2 getData = new GetContacts2();
//			AsyncTask<String, String, Void> asyncGetData = getData.execute(dataUrl);
			
//			Login login = new Login();
//			login.setAccess_token(strtoken);
//			login.setUser_id(userID);
//			db.createLogin(login);
//			
//			db.close();
			
			
			Intent returnIntent = new Intent();
			returnIntent.putExtra("result", "muhehe");
			setResult(RESULT_OK,returnIntent);

			finish();
		}
		
		
	}

	private class GetContacts2 extends AsyncTask<String, String, Void> {

		@Override
		protected Void doInBackground(String... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(arg0[0], ServiceHandler.GET);

			Log.d("Response: ", "> " + jsonStr);

			// my code

			try {
				
				JSONObject jObject = new JSONObject(jsonStr);
				JSONArray array = jObject.getJSONArray("ARDataList");
				str_calories = array.getJSONObject(0).getString("Calories");
				str_distance = array.getJSONObject(0).getString("DistanceTraveled");
				str_steps = array.getJSONObject(0).getString("Steps");
				
				/*
				calories = (TextView) findViewById(R.id.tv_calories);
				distance = (TextView) findViewById(R.id.tv_distance);
				steps = (TextView) findViewById(R.id.tv_steps);

				calories.setText(str_calories);
				distance.setText(str_distance);
				steps.setText(str_steps);
*/
				
				//Toast.makeText(getApplicationContext(), "Posledne kalorie: " + str_calories, Toast.LENGTH_LONG).show();
				Log.d("Response: ", "> " + str_calories + str_distance + str_steps);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}


		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			finish();
		}
		
		
	}
	
	
}
