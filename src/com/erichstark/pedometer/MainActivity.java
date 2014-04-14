package com.erichstark.pedometer;

import java.util.ArrayList;

import com.erichstark.pedometer.drawer.NavigationDrawerItem;
import com.erichstark.pedometer.drawer.NavigationDrawerListAdapter;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.os.Build;

public class MainActivity extends Activity {

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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

}
