package com.erichstark.pedometer.customListView.steps;

public class StepItem {
	
	private String date;
	private int stepsIcon;
	private String steps;
	
	private int distanceIcon;
	private double distance;
	
	private int caloriesIcon;
	private String calories;
	
	
	public StepItem() {
		
	}
	
	public StepItem(String date, int stepsIcon, String steps, int distanceIcon,
			double distance, int caloriesIcon, String calories) {
		super();
		this.date = date;
		this.stepsIcon = stepsIcon;
		this.steps = steps;
		this.distanceIcon = distanceIcon;
		this.distance = distance;
		this.caloriesIcon = caloriesIcon;
		this.calories = calories;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getStepsIcon() {
		return stepsIcon;
	}

	public void setStepsIcon(int stepsIcon) {
		this.stepsIcon = stepsIcon;
	}

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public int getDistanceIcon() {
		return distanceIcon;
	}

	public void setDistanceIcon(int distanceIcon) {
		this.distanceIcon = distanceIcon;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public int getCaloriesIcon() {
		return caloriesIcon;
	}

	public void setCaloriesIcon(int caloriesIcon) {
		this.caloriesIcon = caloriesIcon;
	}

	public String getCalories() {
		return calories;
	}

	public void setCalories(String calories) {
		this.calories = calories;
	}

}
