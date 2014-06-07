package com.erichstark.pedometer.drawer;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.erichstark.pedometer.R;
import com.erichstark.pedometer.customListView.steps.StepItem;
import com.erichstark.pedometer.sqlite.helper.DatabaseHelper;
import com.erichstark.pedometer.sqlite.model.ActivityReport;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class StepGraphFragment extends Fragment {

	private GraphicalView mChart;
	private DatabaseHelper db;
	private ArrayList<StepItem> steps;
	List<ActivityReport> activityReport;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_graph_steps,
				container, false);
		openChart(rootView);
		return rootView;
	}

	private void openChart(View rootView) {

		db = new DatabaseHelper(getActivity());
		//
		steps = new ArrayList<StepItem>();
		activityReport = db.getAllActivityReports();

		int itemsCount = activityReport.size();

		for (int i = 0; i < itemsCount; i++) {
			String date_unix = activityReport.get(i).getMdate();
			String steps_s = activityReport.get(i).getSteps();
			float distance = activityReport.get(i).getDistanceTraveled();
			int calories = activityReport.get(i).getCalories();

			// Calendar mydate = Calendar.getInstance();
			// mydate.setTimeInMillis(Long.parseLong(date_unix) * 1000);
			//
			// String date = mydate.get(Calendar.DAY_OF_MONTH) + "."
			// + mydate.get(Calendar.MONTH) + "."
			// + mydate.get(Calendar.YEAR);

			steps.add(new StepItem(date_unix, 0, steps_s, 0, distance, 0, Integer.toString(calories)));
		}

		db.close();
		//
		// //String str_date="11-June-07";
		// SimpleDateFormat formatter ;
		// Date date = null;
		// formatter = new SimpleDateFormat("dd-MMM-yyyy");
		//
		//
		String str_date = "";
		Date[] dt = new Date[steps.size()];
		for (int i = 0; i < steps.size(); i++) {
			str_date = steps.get(i).getDate();
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTimeInMillis(Long.parseLong(str_date) * 1000);
			dt[i] = gc.getTime();
			// Toast.makeText(getActivity(), ""+ gc.getTime(),
			// Toast.LENGTH_SHORT).show();
		}

		double maxStepsNumber = Double.parseDouble(steps.get(0).getSteps());

		// int max = Array.get(0);

		for (int i = 1; i < steps.size(); i++) {
			if (Double.parseDouble(steps.get(i).getSteps()) > maxStepsNumber) {
				maxStepsNumber = Double.parseDouble(steps.get(i).getSteps());
			}
		}

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
		for (int i = 0; i < steps.size(); i++) {
			viewsSeries.add(dt[i], Double.parseDouble(steps.get(i).getSteps()));
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
		//viewsRenderer.setChartValuesSpacing(20);
		//viewsRenderer.setDisplayChartValuesDistance(1);
		//viewsRenderer.setFillPoints(true);
		viewsRenderer.setLineWidth(5);
		viewsRenderer.setDisplayChartValues(true);
		viewsRenderer.setChartValuesTextSize(35);

		// Creating a XYMultipleSeriesRenderer to customize the whole chart
		XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();

		multiRenderer.setChartTitle("Graf krokov za jednotlive dni");
		multiRenderer.setChartTitleTextSize(40);
		multiRenderer.setXTitle("Dni");
		multiRenderer.setYTitle("Pocet krokov");

		multiRenderer.setAxisTitleTextSize(30);

		multiRenderer.setZoomEnabled(false, false);
		multiRenderer.setLabelsTextSize(25);
		multiRenderer.setPanEnabled(true, false);
		multiRenderer.setYAxisMax(maxStepsNumber + 1000);
		multiRenderer.setShowGrid(true);
		multiRenderer.setGridColor(Color.BLACK);
		
		multiRenderer.setXLabelsPadding(10);
		multiRenderer.setYLabelsPadding(10);
		//multiRenderer.setYLabelsVerticalPadding(10);
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
				.findViewById(R.id.layout_fragment_graph_steps);

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

}
