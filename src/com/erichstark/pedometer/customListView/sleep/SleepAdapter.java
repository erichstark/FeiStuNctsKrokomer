package com.erichstark.pedometer.customListView.sleep;

import java.util.ArrayList;

import com.erichstark.pedometer.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class SleepAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<SleepItem> items;
	
	// metrics
	private static String PERCENT = " %";
	private static String HOURS = " hodín";
	private static String FELL_SLEEP = " minút";
	private static String AWAKEN = " krát";
	
	public SleepAdapter(Context context, ArrayList<SleepItem> items) {
		super();
		this.context = context;
		this.items = items;
	}
	
	@Override
	public int getCount() {
		return items.size();
	}
	@Override
	public Object getItem(int position) {
		return items.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.sleep_history_item, null);
        }

	
		TextView date = (TextView) convertView.findViewById(R.id.tv_sleep_date);
        TextView sleepEff = (TextView) convertView.findViewById(R.id.tv_sleep_efficiency);
		TextView hoursSlept = (TextView) convertView.findViewById(R.id.tv_sleep_hours_slept);
		TextView fellSleep = (TextView) convertView.findViewById(R.id.tv_sleep_fell_sleep);
		TextView awaken = (TextView) convertView.findViewById(R.id.tv_sleep_awaken);
		
	
		date.setText(items.get(position).getDate());
		sleepEff.setText(items.get(position).getSleepEfficiency() + PERCENT);
		hoursSlept.setText(items.get(position).getHoursSlept() + HOURS);
		fellSleep.setText(items.get(position).getFellSleep() + FELL_SLEEP);
		awaken.setText(items.get(position).getAwaken() + AWAKEN);
		       
        return convertView;
	
	}
	
	public void updateView() {
		notifyDataSetChanged();
	}
}
