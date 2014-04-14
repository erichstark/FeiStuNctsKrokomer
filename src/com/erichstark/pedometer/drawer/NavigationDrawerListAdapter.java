package com.erichstark.pedometer.drawer;

import java.util.ArrayList;

import com.erichstark.pedometer.R;







import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NavigationDrawerListAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<NavigationDrawerItem> navItems;

	public NavigationDrawerListAdapter(Context context,
			ArrayList<NavigationDrawerItem> navItems) {
		super();
		this.context = context;
		this.navItems = navItems;
	}

	// return count of items
	@Override
	public int getCount() {
		return navItems.size();
	}

	// return object with that position
	@Override
	public Object getItem(int position) {
		return navItems.get(position);
	}

	// return position of item in list
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.drawer_list_item, null);
		}
		
		ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
		TextView tvTitle = (TextView) convertView.findViewById(R.id.title);
		TextView tvCount = (TextView) convertView.findViewById(R.id.counter);
		
		imgIcon.setImageResource(navItems.get(position).getIcon());
		tvTitle.setText(navItems.get(position).getTitle());
		
		// check if count must be visible or not and set number
		if (navItems.get(position).isCounterVisible()) {
			tvCount.setText(navItems.get(position).getCount());
		} else {
			tvCount.setVisibility(View.GONE);
		}
		
		return convertView;
	}

}
