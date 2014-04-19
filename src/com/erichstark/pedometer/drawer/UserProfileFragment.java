package com.erichstark.pedometer.drawer;


import com.erichstark.pedometer.R;
import com.erichstark.pedometer.sqlite.helper.DatabaseHelper;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfileFragment extends Fragment {
	
	private TextView nickname;
	private TextView gender;
	private TextView height;
	private TextView weight;
	
	private Button btn;
	
	private DatabaseHelper db;
	
	public UserProfileFragment() {
	}
	
//	public UserProfileFragment(Context context) {
//		this.context = context;
//	}
	
	
	
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		
//		
//		
//		super.onCreate(savedInstanceState);
//	}



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_user, container, false);
		
		btn = (Button) rootView.findViewById(R.id.btn_fill_user);
		nickname = (TextView) rootView.findViewById(R.id.tv_nickname);
		gender = (TextView) rootView.findViewById(R.id.tv_gender);
		height = (TextView) rootView.findViewById(R.id.tv_height);
		weight = (TextView) rootView.findViewById(R.id.tv_weight);
		
		db = new DatabaseHelper(getActivity());
		
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String s_nickname = db.getUser(1).getNickname(); 
				String s_gender = db.getUser(1).getGender();
				float s_height = db.getUser(1).getHeight();
				float s_weight = db.getUser(1).getWeight();
				
				//Toast.makeText(getActivity(), "" + s_gender, Toast.LENGTH_LONG).show();
				if (s_gender.toLowerCase().compareTo("male") == 0) {
					gender.setText("Muž");
				} else {
					gender.setText("Žena");
				}
				
				nickname.setText(s_nickname);
				
				height.setText(Float.toString(s_height));
				weight.setText(Float.toString(s_weight));				
			}
		});
		db.close();
		
		return rootView;
	}

	
	
}
