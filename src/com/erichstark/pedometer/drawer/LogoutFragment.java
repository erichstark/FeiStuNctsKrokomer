package com.erichstark.pedometer.drawer;

import com.erichstark.pedometer.R;
import com.erichstark.pedometer.sqlite.helper.DatabaseHelper;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class LogoutFragment extends Fragment {
	
	private DatabaseHelper db;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = (View) inflater.inflate(R.layout.fragment_logout, container, false);
		
		db = new DatabaseHelper(getActivity());
		
		
		db.onLogoutDropDatabase(db.getReadableDatabase());
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				android.os.SystemClock.sleep(3000);
				getActivity().finish();
				System.exit(0);
			}
		}).start();
		

		
		Toast.makeText(getActivity(), "Prebieha odhlasovanie", Toast.LENGTH_LONG).show();
		return rootView;
	}
	
}
