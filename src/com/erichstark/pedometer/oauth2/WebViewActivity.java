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
	
}
