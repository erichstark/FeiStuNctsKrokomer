package com.erichstark.pedometer;

import java.util.ArrayList;
import com.erichstark.pedometer.drawer.NavigationDrawerItem;
import com.erichstark.pedometer.drawer.NavigationDrawerListAdapter;
import com.erichstark.pedometer.drawer.UserProfileFragment;
import com.erichstark.pedometer.oauth2.AsyncTaskLoginData;
import com.erichstark.pedometer.oauth2.WebViewActivity;
import com.erichstark.pedometer.sqlite.helper.DatabaseHelper;
import com.erichstark.pedometer.sqlite.model.Login;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static final String CLIENT_ID = "417483e552844d0a8bd37fb7166401a0";
	public static final String CLIENT_SECRET = "4f6afba3cf624833807e9f64ca2638d6";
	public static final String API_NAME = "&APIName=OpenApiActivity+OpenApiBG+OpenApiSleep+OpenApiUserInfo+OpenApiWeight&RequiredAPIName=OpenApiActivity+OpenApiBG+OpenApiSleep+OpenApiUserInfo+OpenApiWeight";

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

		// SimpleDateFormat dateFormat = new SimpleDateFormat(
		// "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		// Date date = new Date();
		// dateFormat.format(date);

		// Log.d("show login1 before: ", db.getLogin(1).getAccess_token() + "c "
		// + db.getLogin(1).getId());
		//
		// Login logg = new
		// Login(1,"clID","clSecret","accessTok","refresToke",7501,"usrID",
		// "usrPara","cas");
		// db.updateLogin(logg);
		// Log.d("show login1 after:", db.getLogin(1).getAccess_token() + "c " +
		// db.getLogin(1).getId());

		String access = "";
		try {
			access = db.getLogin(1).getAccess_token();
		} catch (Exception e) {
		}
		db.close();

		if (access.isEmpty()) {
			Intent intent = new Intent(this, WebViewActivity.class);
			intent.putExtra(
					"Key",
					"https://api.ihealthlabs.com:8443/OpenApiV2/OAuthv2/userauthorization/?"
							+ "client_id="
							+ CLIENT_ID
							+ "&response_type=code&redirect_uri=http://erichstark.com/"
							+ API_NAME);

			startActivityForResult(intent, 1);
			Log.d("MainActivity", "Starting WebView");
		} else {
			Log.d("MainActivity", "WebView is not starting");
		}

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
		
		drawerListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				displayView(position);
			}
		});

		// set navigation drawer list adapter
		navDrawerAdapter = new NavigationDrawerListAdapter(
				getApplicationContext(), navDrawerItems);
		drawerListView.setAdapter(navDrawerAdapter);
		


		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		abDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
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
			displayView(0);
		}
		


		Log.d("KONIEC", "koniec oncreate");

	}

//	private class SlideMenuClickListener implements
//			ListView.OnItemClickListener {
//		@Override
//		public void onItemClick(AdapterView<?> parent, View view, int position,
//				long id) {
//			// display view for selected nav drawer item
//			displayView(position);
//		}
//	}
	
	/**
     * Diplaying fragment view for selected nav drawer list item
     * */
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
        case 0:
            fragment = new UserProfileFragment();
            break;
//        case 1:
//            fragment = new FindPeopleFragment();
//            break;
//        case 2:
//            fragment = new PhotosFragment();
//            break;
//        case 3:
//            fragment = new CommunityFragment();
//            break;
//        case 4:
//            fragment = new PagesFragment();
//            break;
//        case 5:
//            fragment = new WhatsHotFragment();
//            break;
 
        default:
            break;
        }
 
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();
 
            // update selected item and title, then close the drawer
            drawerListView.setItemChecked(position, true);
            drawerListView.setSelection(position);
            setTitle(navMenuTitles[position]);
            drawerLayout.closeDrawer(drawerListView);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
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
		// int id = item.getItemId();
		// if (id == R.id.action_settings) {
		// return true;
		// }
		// return super.onOptionsItemSelected(item);

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

			if (resultCode == RESULT_OK) {
				String result = data.getStringExtra("result");
				Log.d("som v resulOK", "resultOK: " + result);
				Toast.makeText(this, result, Toast.LENGTH_LONG).show();

				//
				AsyncTaskLoginData getLoginData = new AsyncTaskLoginData(
						getApplicationContext());
				AsyncTask<String, Void, Login> asyncGetLoginData = getLoginData
						.execute(result);

			}
			if (resultCode == RESULT_CANCELED) {
				// Write your code if there's no result
				Log.d("som v resul cancel", "result CANCEL");
			}
		}
	}

}
