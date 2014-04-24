package com.erichstark.pedometer.drawer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.erichstark.pedometer.R;
import com.erichstark.pedometer.customListView.steps.StepItem;
import com.erichstark.pedometer.customListView.steps.StepsAdapter;
import com.erichstark.pedometer.sqlite.helper.DatabaseHelper;
import com.erichstark.pedometer.sqlite.model.ActivityReport;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class StepsHistoryFragment extends Fragment {

	private ListView lv;
	private ArrayList<StepItem> steps;
	private List<ActivityReport> activityReport;
	private StepsAdapter adapter;
	private DatabaseHelper db;

	public StepsHistoryFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_steps_history,
				container, false);

				
		db = new DatabaseHelper(getActivity());

		lv = (ListView) rootView.findViewById(R.id.step_listView);

		steps = new ArrayList<StepItem>();

		activityReport = db.getAllActivityReports();
		int count = activityReport.size();

		for (int i = 0; i < count; i++) {
			String date_unix = activityReport.get(i).getMdate();
			String steps_s = activityReport.get(i).getSteps();
			double distance = activityReport.get(i).getDistanceTraveled();
			int calories = activityReport.get(i).getCalories();
			
			Calendar mydate = Calendar.getInstance();
			mydate.setTimeInMillis(Long.parseLong(date_unix) * 1000);

			String date = mydate.get(Calendar.DAY_OF_MONTH) + "."
					+ mydate.get(Calendar.MONTH) + "."
					+ mydate.get(Calendar.YEAR);
			// * 1000 / 1000 change to 3 decimal places
			steps.add(new StepItem(date, 0, steps_s, 0, Math.ceil(distance * 1000.0) / 1000 , 0, Integer.toString(calories)));
			
		}

		db.close();
		
		adapter = new StepsAdapter(getActivity().getApplicationContext(), steps);
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				adapter = new StepsAdapter(getActivity().getApplicationContext(), steps);
				lv.setAdapter(adapter);
				Toast.makeText(getActivity(), R.string.list_of_items_updated, Toast.LENGTH_LONG).show();				
			}
		});
		
		
		return rootView;
	}

}
