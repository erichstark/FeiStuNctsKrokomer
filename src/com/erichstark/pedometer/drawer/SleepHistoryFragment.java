package com.erichstark.pedometer.drawer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Period;

import com.erichstark.pedometer.R;
import com.erichstark.pedometer.customListView.sleep.SleepAdapter;
import com.erichstark.pedometer.customListView.sleep.SleepItem;
import com.erichstark.pedometer.sqlite.helper.DatabaseHelper;
import com.erichstark.pedometer.sqlite.model.SleepReport;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class SleepHistoryFragment extends Fragment {
	
	private ListView lv;
	private ArrayList<SleepItem> sleeps;
	private List<SleepReport> sleepReport;
	private SleepAdapter adapter;
	private DatabaseHelper db;
	
	public SleepHistoryFragment() {
		
	}
	

	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_sleep_history,
				container, false);
		
		db = new DatabaseHelper(getActivity());
		
		lv = (ListView) rootView.findViewById(R.id.sleep_listView);
		
		sleeps = new ArrayList<SleepItem>();
		
		sleepReport = db.getAllSleepReports();
		int count = sleepReport.size();
		
		for (int i = 0; i < count; i++) {
			//tmp
			String start_date_unix = sleepReport.get(i).getStartTime();
			String end_date_unix = sleepReport.get(i).getEndTime();
			
			
			String sleep_eff = Integer.toString(sleepReport.get(i).getSleepEfficiency());
			String hours_slept = "0";
			String fell_sleep = Integer.toString(sleepReport.get(i).getFellSleep());
			String awaken = Integer.toString(sleepReport.get(i).getAwaken());
			
			Calendar mydate = Calendar.getInstance();
			mydate.setTimeInMillis(Long.parseLong(end_date_unix) * 1000);

			String date = mydate.get(Calendar.DAY_OF_MONTH) + "."
					+ mydate.get(Calendar.MONTH) + "."
					+ mydate.get(Calendar.YEAR);
			
			Period p;
			p = diffTime(start_date_unix, end_date_unix);
			
			String p_s = p.getHours() + ":" + p.getMinutes();
			
			// * 1000 / 1000 change to 3 decimal places
			sleeps.add(new SleepItem(date, sleep_eff, p_s, fell_sleep, awaken));
			//sleeps.add(new StepItem(date, 0, steps_s, 0, Math.ceil(distance * 1000.0) / 1000 , 0, Integer.toString(calories)));
			
		}
		
		db.close();
		
		adapter = new SleepAdapter(getActivity().getApplicationContext(), sleeps);
		lv.setAdapter(adapter);
		
		return rootView;
	}
	
	public Period diffTime(String startTime, String endTime) {		
		// joda library
		DateTime startTime2, endTime2;
		startTime2 = new DateTime(Long.parseLong(startTime) * 1000L);
		endTime2 = new DateTime(Long.parseLong(endTime) * 1000L);
		
		Period p = new Period(startTime2, endTime2);
//		long hours = p.getHours();
//		long minutes = p.getMinutes();
//		
//		Log.d("cas", ""+ hours + " " + minutes);		
		
		return p;
	}
}
