package com.erichstark.pedometer;

import java.util.ArrayList;

import com.erichstark.pedometer.drawer.NavigationDrawerItem;
import com.erichstark.pedometer.drawer.NavigationDrawerListAdapter;
import com.erichstark.pedometer.oauth2.AsyncTaskLoginData;
import com.erichstark.pedometer.oauth2.WebViewActivity;
import com.erichstark.pedometer.sqlite.helper.DatabaseHelper;
import com.erichstark.pedometer.sqlite.model.Login;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public static final String appId = "417483e552844d0a8bd37fb7166401a0";
	public static final String appSecret = "4f6afba3cf624833807e9f64ca2638d6";
	public static final String API_NAME = 
			"&APIName=OpenApiActivity+OpenApiBG+OpenApiSleep+OpenApiUserInfo+OpenApiWeight&RequiredAPIName=OpenApiActivity+OpenApiBG+OpenApiSleep+OpenApiUserInfo+OpenApiWeight";

	private DrawerLayout drawerLayout;
	private ListView drawerListView;
	private ActionBarDrawerToggle abDrawerToggle;

	// navigation drawer title
	private CharSequence navDrawerTitle;

	// store app title
	private CharSequence appTitle;

	// navigation drawer menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavigationDrawerItem> navDrawerItems;
	private NavigationDrawerListAdapter navDrawerAdapter;
	
	// DATABASE
	private DatabaseHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		db = new DatabaseHelper(getApplicationContext());
		

		Intent intent = new Intent(this, WebViewActivity.class);
		intent.putExtra(
				"Key",
				"https://api.ihealthlabs.com:8443/OpenApiV2/OAuthv2/userauthorization/?"
						+ "client_id="
						+ appId
						+ "&response_type=code&redirect_uri=http://erichstark.com/"
						+ API_NAME);
		
		startActivityForResult(intent, 1);
		
		
		//intent.get
		//onActivityResult(int requestCode, intent., intent.getExtras());
		
		// new db
		//db = new DatabaseHelper(getApplicationContext());
		
		//Login login = new Login("clientID",	"clientsecret", "accesstoken", "refreshtoken", 157856, "userid", "userpara", "timestampt");
		//long log1 = db.createLogin(login);
		
		Log.d("show login1", db.getLogin(1).getAccess_token() + "c " + db.getLogin(1).getId());
		//Log.d("show login5", db.getLogin(5).getAccess_token() + "c " + db.getLogin(5).getId());
		//Log.d("show login6", db.getLogin(6).getAccess_token() + "c " + db.getLogin(6).getId());
		//Log.d("show login7", db.getLogin(7).getAccess_token() + "c " + db.getLogin(7).getId());
		
//		 Tag tag4 = new Tag("Androidhive");
//		 
//	        // Inserting tags in db
//	        long tag1_id = db.createTag(tag1);
		
		
		
		
		
		
		
		
		
		db.close();
		// set title
		appTitle = navDrawerTitle = getTitle();

		// load slide menu items from string array
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// load slide menu icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerListView = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavigationDrawerItem>();

		// adding items from array to navigation drawer
		// prvy ---- zistit preco -1
		navDrawerItems.add(new NavigationDrawerItem(navMenuIcons.getResourceId(
				0, -1), navMenuTitles[0]));
		// druhy
		navDrawerItems.add(new NavigationDrawerItem(navMenuIcons.getResourceId(
				1, -1), navMenuTitles[1]));
		// treti
		navDrawerItems.add(new NavigationDrawerItem(navMenuIcons.getResourceId(
				2, -1), navMenuTitles[2]));
		// stvrty , prida sa aj counter
		navDrawerItems.add(new NavigationDrawerItem(navMenuIcons.getResourceId(
				3, -1), navMenuTitles[3], "10", true));
		// piaty
		navDrawerItems.add(new NavigationDrawerItem(navMenuIcons.getResourceId(
				4, -1), navMenuTitles[4]));
		// siesty, prida sa aj counter
		navDrawerItems.add(new NavigationDrawerItem(navMenuIcons.getResourceId(
				5, -1), navMenuTitles[5], "15", true));

		// free icons from memory
		navMenuIcons.recycle();

		// set navigation drawer list adapter
		navDrawerAdapter = new NavigationDrawerListAdapter(
				getApplicationContext(), navDrawerItems);
		drawerListView.setAdapter(navDrawerAdapter);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		abDrawerToggle = new ActionBarDrawerToggle(this, 
				drawerLayout,
				R.drawable.ic_drawer, // nav menu toggle icon
				R.string.app_name, // nav drawer open
				R.string.app_name) { // nav drawer close

			public void onDrawerClosed(View view) {
				getActionBar().setTitle(appTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(navDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}

		};
		
		drawerLayout.setDrawerListener(abDrawerToggle);

		if (savedInstanceState == null) {
			// getFragmentManager().beginTransaction()
			// .add(R.id.container, new PlaceholderFragment()).commit();
			// zavolam custom displayView(0);
		}
		
		Log.d("KONIEC", "koniec oncreate");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
		
		
		if (abDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
        case R.id.action_settings:
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
	}
	
	/***
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerListView);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    
    @Override
    public void setTitle(CharSequence title) {
        appTitle = title;
        getActionBar().setTitle(appTitle);
    }
    
    
    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
 
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        abDrawerToggle.syncState();
    }
 
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        abDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	
	// vrati mi hodnotu z webview
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		  if (requestCode == 1) {

		     if(resultCode == RESULT_OK){      
		         String result=data.getStringExtra("result");   
		         Log.d("som v resulOK", "resultOK: " + result);
		         Toast.makeText(this, result, Toast.LENGTH_LONG).show();
		         
//					GetContacts2 getData = new GetContacts2();
//					AsyncTask<String, String, Void> asyncGetData = getData.execute(dataUrl);
		         AsyncTaskLoginData getLoginData = new AsyncTaskLoginData(getApplicationContext());
		         AsyncTask<String, Void, String[]> asyncGetLoginData = getLoginData.execute(result);
		    
		     }
		     if (resultCode == RESULT_CANCELED) {    
		         //Write your code if there's no result
		    	 Log.d("som v resul cancel", "result CANCEL");
		     }
		  }
		}


}
