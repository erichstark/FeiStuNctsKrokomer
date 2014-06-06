package com.erichstark.pedometer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class InfoActivity extends Activity {
	private String text;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_app);
		
		text = "Autorom tejto aplikácie je Erich Stark a bola vytvorená ako súčasť bakalárskej práce"
				+ "na škole Fakulty elektrotechniky a informatiky STU.\n"
				+ "Vedúci tejto práce bol Ing. Fedor Lehocki, PhD.\n\n"
				+ "Aplikácia slúží na získanie dát z krokomera od firmy iHealth Labs Inc. resp. "
				+ "dáta sa nezískavaju priamo, ale cez ich cloud. Do neho sú odosielané pomocou ich oficiálnej aplikácie.";
		
		
		TextView tvInfo = (TextView) findViewById(R.id.textViewInfoApp);
		
		tvInfo.setText(text);
		
	}

	

}
