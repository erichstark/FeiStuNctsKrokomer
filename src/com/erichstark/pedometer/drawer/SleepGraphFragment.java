package com.erichstark.pedometer.drawer;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.joda.time.DateTime;
import org.joda.time.Period;

import com.erichstark.pedometer.R;
import com.erichstark.pedometer.customListView.sleep.SleepItem;
import com.erichstark.pedometer.sqlite.helper.DatabaseHelper;
import com.erichstark.pedometer.sqlite.model.SleepReport;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SleepGraphFragment extends Fragment {

	private GraphicalView mChart;
	private DatabaseHelper db;
	private ArrayList<SleepItem> sleeps;
	List<SleepReport> sleepReport;

	Period per;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater,
	 * android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_graph_sleep,
				container, false);
		openChart(rootView);
		return rootView;
	}

	private void openChart(View rootView) {

		db = new DatabaseHelper(getActivity());
		//
		sleeps = new ArrayList<SleepItem>();
		sleepReport = db.getAllSleepReports();

		int itemsCount = sleepReport.size();

		for (int i = 0; i < itemsCount; i++) {
			// tmp
			String start_date_unix = sleepReport.get(i).getStartTime();
			String end_date_unix = sleepReport.get(i).getEndTime();
			
			Log.d("long time :::: ", end_date_unix);

			String sleep_eff = Integer.toString(sleepReport.get(i)
					.getSleepEfficiency());
			String hours_slept = "0";
			String fell_sleep = Integer.toString(sleepReport.get(i)
					.getFellSleep());
			String awaken = Integer.toString(sleepReport.get(i).getAwaken());

			Calendar mydate = Calendar.getInstance();
			mydate.setTimeInMillis(Long.parseLong(end_date_unix) * 1000);

			String date = mydate.get(Calendar.DAY_OF_MONTH) + "."
					+ mydate.get(Calendar.MONTH) + "."
					+ mydate.get(Calendar.YEAR);

			// Period p;
			per = diffTime(start_date_unix, end_date_unix);

			String p_s = per.getHours() + ":" + per.getMinutes();

			// * 1000 / 1000 change to 3 decimal places
			sleeps.add(new SleepItem(end_date_unix, sleep_eff, Integer
					.toString(per.getHours()), fell_sleep, awaken));
			// sleeps.add(new StepItem(date, 0, steps_s, 0, Math.ceil(distance *
			// 1000.0) / 1000 , 0, Integer.toString(calories)));
		}

		db.close();
		//
		// //String str_date="11-June-07";
		// SimpleDateFormat formatter ;
		// Date date = null;
		// formatter = new SimpleDateFormat("dd-MMM-yyyy");
		//
		//
		Log.d("datum raw", new Date() + "");
		
		String str_date = "";
		Date[] dt = new Date[sleeps.size()];
		for (int i = 0; i < sleeps.size(); i++) {
//			str_date = Long.parseLong(sleeps.get(i).getDate());
//			Log.d("Datum:   ", "" + str_date);
//			GregorianCalendar gc = new GregorianCalendar();
//			gc.setTimeInMillis(str_date * 1000);
//
//			dt[i] = new Date(str_date);
//				Log.d("datum normal mili 2", new Date(str_date) + " :::::::::: " + dt[i] + "  simple date :");
//
//				
				
				
				str_date = sleeps.get(i).getDate();
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTimeInMillis(Long.parseLong(str_date) * 1000);
				dt[i] = gc.getTime();	
				
				Log.d("gc:  ", "" + gc.getTime());
			// try {
			//dt[i] = new SimpleDateFormat("dd.mm.yyyy").parse(str_date);
			// } catch (ParseException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// gc.getTime();
			str_date = "";
		}

		// int maxStepsNumber = Integer.parseInt(sleeps.get(0).getHoursSlept());

		int maxSleepNumber = 24;
		// int max = Array.get(0);

		// for (int i = 1; i < sleeps.size(); i++) {
		// if (Integer.parseInt(sleeps.get(i).getHoursSlept()) > maxStepsNumber)
		// {
		// maxStepsNumber = Integer.parseInt(sleeps.get(i).getHoursSlept());
		// }
		// }

		// int count = 5;
		// Date[] dt = new Date[5];
		// for(int i=0;i<count;i++){
		// GregorianCalendar gc = new GregorianCalendar();
		// gc.setTimeInMillis(1398114360);
		// dt[i] = gc.getTime();
		// // Toast.makeText(getActivity(), ""+ gc.getTime(),
		// Toast.LENGTH_LONG).show();
		// }

		// Creating TimeSeries for Visits
		TimeSeries visitsSeries = new TimeSeries("Visits");

		// Creating TimeSeries for Views
		TimeSeries viewsSeries = new TimeSeries("Views");

		// Adding data to Visits and Views Series
		for (int i = 0; i < sleeps.size(); i++) {
			//viewsSeries.add(dt[i], Double.parseDouble(sleeps.get(i).getHoursSlept()));
			viewsSeries.add(dt[i], Double.parseDouble(sleeps.get(i).getHoursSlept()));
			
			
			//Log.d("sleep date >", "" +  + " :: " + sleeps.get(i).getDate());

			// Log.d("Sleep int> ","" +
			// Double.parseDouble(sleeps.get(i).getHoursSlept()));
			// Log.d("Sleep date> ","" + dt[i]);
			// viewsSeries.add(dt[i],
			// Double.parseDouble(steps.get(i).getDistance()));
			// Toast.makeText(getActivity(), ""+ steps.get(i).getSteps(),
			// Toast.LENGTH_SHORT).show();
			// viewsSeries.add(dt[i], views[i]);
		}

		// Creating a dataset to hold each series
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

		// Adding Visits Series to the dataset
		dataset.addSeries(visitsSeries);

		// Adding Visits Series to dataset
		dataset.addSeries(viewsSeries);

		// Creating XYSeriesRenderer to customize visitsSeries
		XYSeriesRenderer visitsRenderer = new XYSeriesRenderer();
		visitsRenderer.setColor(Color.RED);
		visitsRenderer.setPointStyle(PointStyle.SQUARE);
		visitsRenderer.setFillPoints(true);
		visitsRenderer.setLineWidth(6);
		visitsRenderer.setDisplayChartValues(true);

		// Creating XYSeriesRenderer to customize viewsSeries
		XYSeriesRenderer viewsRenderer = new XYSeriesRenderer();
		viewsRenderer.setColor(Color.rgb(34, 160, 51));
		viewsRenderer.setPointStyle(PointStyle.SQUARE);
		// viewsRenderer.setChartValuesSpacing(20);
		// viewsRenderer.setDisplayChartValuesDistance(1);
		// viewsRenderer.setFillPoints(true);
		viewsRenderer.setLineWidth(5);
		viewsRenderer.setDisplayChartValues(true);
		viewsRenderer.setChartValuesTextSize(35);

		// Creating a XYMultipleSeriesRenderer to customize the whole chart
		XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();

		multiRenderer.setChartTitle("Počet naspaných hodín za jednotlive dni");
		multiRenderer.setChartTitleTextSize(40);
		multiRenderer.setXTitle("Dni");
		multiRenderer.setYTitle("Pocet hodín");

		multiRenderer.setAxisTitleTextSize(30);

		multiRenderer.setZoomEnabled(false, false);
		multiRenderer.setLabelsTextSize(25);
		multiRenderer.setPanEnabled(true, false);
		multiRenderer.setYAxisMax(maxSleepNumber);
		multiRenderer.setShowGrid(true);
		multiRenderer.setGridColor(Color.BLACK);

		multiRenderer.setXLabelsPadding(10);
		multiRenderer.setYLabelsPadding(10);
		// multiRenderer.setYLabelsVerticalPadding(10);
		// mine

		multiRenderer.setLegendTextSize(40);
		multiRenderer.setShowLegend(false);
		multiRenderer.setMargins(new int[] { 60, 40, 20, 10 });

		// Adding visitsRenderer and viewsRenderer to multipleRenderer
		// Note: The order of adding dataseries to dataset and renderers to
		// multipleRenderer
		// should be same
		multiRenderer.addSeriesRenderer(visitsRenderer);
		multiRenderer.addSeriesRenderer(viewsRenderer);

		// Getting a reference to LinearLayout of the MainActivity Layout
		LinearLayout chartContainer = (LinearLayout) rootView
				.findViewById(R.id.layout_fragment_graph_sleep);

		// Creating a Time Chart
		mChart = (GraphicalView) ChartFactory.getTimeChartView(getActivity(),
				dataset, multiRenderer, "dd-MMM-yyyy");

		multiRenderer.setClickEnabled(true);
		multiRenderer.setSelectableBuffer(100);

		// Setting a click event listener for the graph
		mChart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Format formatter = new SimpleDateFormat("dd-MMM-yyyy");

				SeriesSelection seriesSelection = mChart
						.getCurrentSeriesAndPoint();

				if (seriesSelection != null) {
					int seriesIndex = seriesSelection.getSeriesIndex();
					String selectedSeries = "Visits";
					if (seriesIndex == 0)
						selectedSeries = "Visits";
					else
						selectedSeries = "Views";

					// Getting the clicked Date ( x value )
					long clickedDateSeconds = (long) seriesSelection
							.getXValue();
					Date clickedDate = new Date(clickedDateSeconds);
					String strDate = formatter.format(clickedDate);

					// Getting the y value
					int amount = (int) seriesSelection.getValue();

					// Displaying Toast Message
					Toast.makeText(getActivity(),
							selectedSeries + " on " + strDate + " : " + amount,
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		// Adding the Line Chart to the LinearLayout
		chartContainer.addView(mChart);
	}

	public Period diffTime(String startTime, String endTime) {
		// joda library
		DateTime startTime2, endTime2;
		startTime2 = new DateTime(Long.parseLong(startTime) * 1000L);
		endTime2 = new DateTime(Long.parseLong(endTime) * 1000L);

		Period p = new Period(startTime2, endTime2);
		// long hours = p.getHours();
		// long minutes = p.getMinutes();
		//
		// Log.d("cas", ""+ hours + " " + minutes);

		return p;
	}

}
