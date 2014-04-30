package com.erichstark.pedometer.oauth2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.http.util.ByteArrayBuffer;

import com.erichstark.pedometer.MainActivity;
import com.erichstark.pedometer.sqlite.helper.DatabaseHelper;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class AsyncTaskDownloadPhoto extends AsyncTask<String, Void, Void> {

	private Context context;
	private DatabaseHelper db;

	public AsyncTaskDownloadPhoto(Context context) {
		this.context = context;
	}

	@Override
	protected Void doInBackground(String... params) {

		db = new DatabaseHelper(context);

		
		
		try {
			String logo = db.getUser(1).getLogo();
					
			String imagePath = context.getFilesDir() + "/"
					+ "iHealthUserLogo.png";
			DownloadFile(logo, imagePath);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		
		db.close();

		return null;
	}

	public void DownloadFile(String imageURL, String fileName) throws IOException, URISyntaxException {
		URL url = new URL(URLDecoder.decode(imageURL));
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
