package com.erichstark.pedometer.customListView.steps;

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

public class StepsAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<StepItem> items;
	
	// metrics
	private static String KM = " km";
	private static String KCAL = " kcal";
	
	public StepsAdapter(Context context, ArrayList<StepItem> items) {
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
            convertView = mInflater.inflate(R.layout.steps_history_item, null);
        }

		
		TextView date = (TextView) convertView.findViewById(R.id.tv_steps_date);
		
		ImageView stepsIcon = (ImageView) convertView.findViewById(R.id.img_steps_steps);
        TextView stepValue = (TextView) convertView.findViewById(R.id.tv_steps_value);
		
		ImageView distanceIcon = (ImageView) convertView.findViewById(R.id.img_steps_distance);
		TextView distanceValue = (TextView) convertView.findViewById(R.id.tv_distance_value);
		
		ImageView caloriesIcon = (ImageView) convertView.findViewById(R.id.img_step_calories);
		TextView caloriesValue = (TextView) convertView.findViewById(R.id.tv_calories_value);

		date.setText(items.get(position).getDate());
		
//		stepsIcon.setImageResource(R.drawable.step_feet);
//		distanceIcon.setImageResource(R.drawable.step_distance);
//		caloriesIcon.setImageResource(R.drawable.step_burn);
		
		stepValue.setText(items.get(position).getSteps());
		distanceValue.setText(items.get(position).getDistance() + KM);
		caloriesValue.setText(items.get(position).getCalories() + KCAL);
		
		
        
        return convertView;
	
	}

}
