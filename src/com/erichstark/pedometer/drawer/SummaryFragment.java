package com.erichstark.pedometer.drawer;

import java.util.List;

import com.erichstark.pedometer.R;
import com.erichstark.pedometer.sqlite.helper.DatabaseHelper;
import com.erichstark.pedometer.sqlite.model.ActivityReport;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SummaryFragment extends Fragment {

	private DatabaseHelper db;
	TextView tvStep;
	TextView tvDistance;
	TextView tvCalories;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_summary, container,
				false);

		db = new DatabaseHelper(getActivity());


		
		LinearLayout linear = (LinearLayout) rootView
				.findViewById(R.id.summary_linear);

		tvStep = (TextView) rootView.findViewById(R.id.tv_summary_steps);
		tvDistance = (TextView) rootView.findViewById(R.id.tv_summary_distance);
		tvCalories = (TextView) rootView.findViewById(R.id.tv_summary_calories);
		
		String access = "";
		try {
			access = db.getLogin(1).getAccess_token();
		} catch (Exception e) {
		}
		
		if (!access.isEmpty()) {
			float tmpArrayResult[] = getSumSteps();
			tvStep.setText(Integer.toString((int) tmpArrayResult[0]));
			tvDistance.setText(Float.toString(tmpArrayResult[1]));
			tvCalories.setText(Integer.toString((int) tmpArrayResult[2]));
		} else {
			tvStep.setText("-");
			tvDistance.setText("-");
			tvCalories.setText("-");
		}
		
		db.close();

		linear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				float tmpArrayResult[] = getSumSteps();

				tvStep.setText(Integer.toString((int) tmpArrayResult[0]));
				tvDistance.setText(Float.toString(tmpArrayResult[1]));
				tvCalories.setText(Integer.toString((int) tmpArrayResult[2]));

				Toast.makeText(getActivity(), R.string.summary_text_changed,
						Toast.LENGTH_LONG).show();
			}
		});

		return rootView;
	}

	public float[] getSumSteps() {
		float tmpSteps = 0;
		float tmpDistance = 0;
		float tmpCalories = 0;

		float[] tmpArray = { 0, 0, 0 };

		db = new DatabaseHelper(getActivity());

		List<ActivityReport> activityReports = db.getAllActivityReports();

		for (int i = 0; i < activityReports.size(); i++) {
			tmpCalories += activityReports.get(i).getCalories();
			tmpDistance += activityReports.get(i).getDistanceTraveled();
			tmpSteps += Integer.parseInt(activityReports.get(i).getSteps());
		}
		db.close();

		tmpArray[0] = tmpSteps;
		tmpArray[1] = tmpDistance;
		tmpArray[2] = tmpCalories;

		return tmpArray;
	}

}
