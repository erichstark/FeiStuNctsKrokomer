package com.erichstark.pedometer.oauth2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.erichstark.pedometer.sqlite.helper.DatabaseHelper;
import com.erichstark.pedometer.sqlite.model.ActivityReport;
import com.erichstark.pedometer.sqlite.model.SleepReport;


public class AsyncTaskSleepData extends AsyncTask<String, Void, Void> {

	private Context context;
	private DatabaseHelper db;
	private ProgressDialog pd;

	private int prevSize;
	private JSONArray jData;

	public AsyncTaskSleepData(Context context) {
		this.context = context;
	}

	@Override
	protected Void doInBackground(String... arg0) {
		// Creating service handler class instance
		ServiceHandler sh = new ServiceHandler();

		// Making a request to url and getting response
		String jsonStr = sh.makeServiceCall(arg0[0], ServiceHandler.GET);

		if (jsonStr != null) {
			try {
				JSONObject jObjLogin = new JSONObject(jsonStr);

				jData = jObjLogin.getJSONArray("SRDataList");

				db = new DatabaseHelper(context);

				Log.d("ASyncTaskSleep: ", "" + jData);

				prevSize = 0;
				try {
					prevSize = db.getAllSleepReports().size();
				} catch (Exception e) {
				}

				if (prevSize == jData.length()) {
					Log.d("SleepAsyncTask", "Ziadne nove itemy v cloude");
				} else if (prevSize > 0) {
					// int startSize = jData.length() - prevSize;
					for (int i = prevSize; i < jData.length(); i++) {
						JSONObject c = jData.getJSONObject(i);

						
						SleepReport report = new SleepReport();
						report.setAwaken(c.getInt("Awaken"));
						report.setEndTime(c.getString("EndTime"));
						report.setFellSleep(c.getInt("FellSleep"));
						report.setHoursSlept(c.getInt("HoursSlept"));
						report.setSleepEfficiency(c.getInt("SleepEfficiency"));
						report.setStartTime(c.getString("StartTime"));
												
						db.createSleepReport(report);

					}
				} else if (prevSize == 0) {

					// looping through All Contacts
					for (int i = 0; i < jData.length(); i++) {
						JSONObject c = jData.getJSONObject(i);

						SleepReport report = new SleepReport();
						report.setAwaken(c.getInt("Awaken"));
						report.setEndTime(c.getString("EndTime"));
						report.setFellSleep(c.getInt("FellSleep"));
						report.setHoursSlept(c.getInt("HoursSlept"));
						report.setSleepEfficiency(c.getInt("SleepEfficiency"));
						report.setStartTime(c.getString("StartTime"));
												
						db.createSleepReport(report);

					}
				}
				db.close();

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();

		pd = new ProgressDialog(context);
		pd.setMessage("Sťahujú sa najnovšie dáta...");
		pd.show();
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);

		if (prevSize == 0) {
		} else if (prevSize == jData.length()) {
			Toast.makeText(context, "Žiadne nové dáta.", Toast.LENGTH_SHORT)
					.show();
		} else if ((prevSize < 0) && (prevSize < jData.length())) {
			Toast.makeText(context, "Sťahujú sa novšie dáta.",
					Toast.LENGTH_SHORT).show();
		}

		pd.dismiss();
	}

}