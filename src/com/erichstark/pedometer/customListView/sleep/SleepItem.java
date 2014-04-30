package com.erichstark.pedometer.customListView.sleep;

public class SleepItem {
	
	private String date;
	private String sleepEfficiency;
	
	// end time - start time
	private String hoursSlept;
	private String fellSleep;
	private String awaken;
	
	public SleepItem() {
		
	}
	
	public SleepItem(String date, String sleepEfficiency, String hoursSlept,
			String fellSleep, String awaken) {
		super();
		this.date = date;
		this.sleepEfficiency = sleepEfficiency;
		this.hoursSlept = hoursSlept;
		this.fellSleep = fellSleep;
		this.awaken = awaken;
	}

	/**
	 * @return the sleepEfficiency
	 */
	public String getSleepEfficiency() {
		return sleepEfficiency;
	}

	/**
	 * @param sleepEfficiency the sleepEfficiency to set
	 */
	public void setSleepEfficiency(String sleepEfficiency) {
		this.sleepEfficiency = sleepEfficiency;
	}

	/**
	 * @return the hoursSlept
	 */
	public String getHoursSlept() {
		return hoursSlept;
	}

	/**
	 * @param hoursSlept the hoursSlept to set
	 */
	public void setHoursSlept(String hoursSlept) {
		this.hoursSlept = hoursSlept;
	}

	/**
	 * @return the fellSleep
	 */
	public String getFellSleep() {
		return fellSleep;
	}

	/**
	 * @param fellSleep the fellSleep to set
	 */
	public void setFellSleep(String fellSleep) {
		this.fellSleep = fellSleep;
	}

	/**
	 * @return the awaken
	 */
	public String getAwaken() {
		return awaken;
	}

	/**
	 * @param awaken the awaken to set
	 */
	public void setAwaken(String awaken) {
		this.awaken = awaken;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	

}
