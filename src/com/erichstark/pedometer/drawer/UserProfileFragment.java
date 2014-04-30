package com.erichstark.pedometer.drawer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;

import com.erichstark.pedometer.R;
import com.erichstark.pedometer.sqlite.helper.DatabaseHelper;

import android.app.Fragment;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfileFragment extends Fragment {

	private TextView nickname;
	private TextView gender;
	private TextView height;
	private TextView weight;
	
	private ImageView photo;

	private RelativeLayout userLayout;

	private DatabaseHelper db;

	public UserProfileFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_user, container,
				false);

		userLayout = (RelativeLayout) rootView
				.findViewById(R.id.layout_fragment_user);

		nickname = (TextView) rootView.findViewById(R.id.tv_nickname);
		gender = (TextView) rootView.findViewById(R.id.tv_gender);
		height = (TextView) rootView.findViewById(R.id.tv_height);
		weight = (TextView) rootView.findViewById(R.id.tv_weight);
		
		photo = (ImageView) rootView.findViewById(R.id.userImage);

		db = new DatabaseHelper(getActivity());

		String access = "";
		try {
			access = db.getLogin(1).getAccess_token();
		} catch (Exception e) {
		}
//		String imagePath = "";
//		try {
//			imagePath = getActivity().getFilesDir() + "/" + "logo1w.png";
//			DownloadFile("http://www.google.de/intl/en_com/images/srpr/logo1w.png", imagePath);
//		} catch (IOException e) {
//			// Something went wrong here
//		}
		
		String imagePath = getActivity().getFilesDir() + "/" + "iHealthUserLogo.png";
		photo.setImageBitmap(BitmapFactory.decodeFile(imagePath));

		if (!access.isEmpty()) {
			String s_nickname = db.getUser(1).getNickname();
			String s_gender = db.getUser(1).getGender();
			float s_height = db.getUser(1).getHeight();
			float s_weight = db.getUser(1).getWeight();
			


			if (s_gender.toLowerCase().compareTo("male") == 0) {
				gender.setText("Muž");
			} else {
				gender.setText("Žena");
			}

			nickname.setText(s_nickname);

			height.setText(Float.toString(s_height));
			weight.setText(Float.toString(s_weight));
			
		} else {
			gender.setText("-");
			nickname.setText("-");
			height.setText("-");
			weight.setText("-");
		}

		userLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String s_nickname = db.getUser(1).getNickname();
				String s_gender = db.getUser(1).getGender();
				float s_height = db.getUser(1).getHeight();
				float s_weight = db.getUser(1).getWeight();

				if (s_gender.toLowerCase().compareTo("male") == 0) {
					gender.setText("Muž");
				} else {
					gender.setText("Žena");
				}

				nickname.setText(s_nickname);

				height.setText(Float.toString(s_height));
				weight.setText(Float.toString(s_weight));

				Toast.makeText(getActivity(), R.string.toast_profil_updated,
						Toast.LENGTH_LONG).show();
			}
		});
		db.close();

		return rootView;
	}
	
	public static void DownloadFile(String imageURL, String fileName) throws IOException {
		URL url = new URL(imageURL);
		File file = new File(fileName);
	 
		long startTime = System.currentTimeMillis();
		Log.d("DownloadFile", "Begin Download URL: " + url + " Filename: " + fileName);
		URLConnection ucon = url.openConnection();
		InputStream is = ucon.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(is);
		ByteArrayBuffer baf = new ByteArrayBuffer(50);
		int current = 0;
		while ((current = bis.read()) != -1)
			baf.append((byte) current);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(baf.toByteArray());
		fos.close();
		Log.d("DownloadFile", "File was downloaded in: " + ((System.currentTimeMillis() - startTime) / 1000) + "s");
	}

}
