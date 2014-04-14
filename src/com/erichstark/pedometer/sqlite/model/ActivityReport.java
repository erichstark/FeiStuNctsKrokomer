package com.erichstark.pedometer.sqlite.model;

public class ActivityReport {
	int id;
	int currentRecordCount;
	int distanceUnit;
	String nextPageUrl;
	String prevPageUrl;
	int pageLength;
	int pageNumber;
	int recordCount;
	
	// data list activity report
	int calories;
	String dataid;
	float distanceTraveled;
	double latitude;
	double longitude;
	String mdate; // measurement datetime -- should be datetime?
	String userid;
	String note;
	String steps;
	
	/*
	 * long time = 1367832568 * (long) 1000;
    Date date = new Date(time);
    SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy a");
    format.setTimeZone(TimeZone.getTimeZone("GMT"));
    Log.d("date", format.format(date));
	 * 
	 * */
	
	public ActivityReport() {
	}
		
	public ActivityReport(int currentRecordCount, int distanceUnit,
			String nextPageUrl, String prevPageUrl, int pageLength,
			int pageNumber, int recordCount, int calories, String dataid,
			float distanceTraveled, double latitude, double longitude,
			String mdate, String userid, String note, String steps) {
		super();
		this.currentRecordCount = currentRecordCount;
		this.distanceUnit = distanceUnit;
		this.nextPageUrl = nextPageUrl;
		this.prevPageUrl = prevPageUrl;
		this.pageLength = pageLength;
		this.pageNumber = pageNumber;
		this.recordCount = recordCount;
		this.calories = calories;
		this.dataid = dataid;
		this.distanceTraveled = distanceTraveled;
		this.latitude = latitude;
		this.longitude = longitude;
		this.mdate = mdate;
		this.userid = userid;
		this.note = note;
		this.steps = steps;
	}

	public ActivityReport(int id, int currentRecordCount, int distanceUnit,
			String nextPageUrl, String prevPageUrl, int pageLength,
			int pageNumber, int recordCount, int calories, String dataid,
			float distanceTraveled, double latitude, double longitude,
			String mdate, String userid, String note, String steps) {
		super();
		this.id = id;
		this.currentRecordCount = currentRecordCount;
		this.distanceUnit = distanceUnit;
		this.nextPageUrl = nextPageUrl;
		this.prevPageUrl = prevPageUrl;
		this.pageLength = pageLength;
		this.pageNumber = pageNumber;
		this.recordCount = recordCount;
		this.calories = calories;
		this.dataid = dataid;
		this.distanceTraveled = distanceTraveled;
		this.latitude = latitude;
		this.longitude = longitude;
		this.mdate = mdate;
		this.userid = userid;
		this.note = note;
		this.steps = steps;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the currentRecordCount
	 */
	public int getCurrentRecordCount() {
		return currentRecordCount;
	}

	/**
	 * @param currentRecordCount the currentRecordCount to set
	 */
	public void setCurrentRecordCount(int currentRecordCount) {
		this.currentRecordCount = currentRecordCount;
	}

	/**
	 * @return the distanceUnit
	 */
	public int getDistanceUnit() {
		return distanceUnit;
	}

	/**
	 * @param distanceUnit the distanceUnit to set
	 */
	public void setDistanceUnit(int distanceUnit) {
		this.distanceUnit = distanceUnit;
	}

	/**
	 * @return the nextPageUrl
	 */
	public String getNextPageUrl() {
		return nextPageUrl;
	}

	/**
	 * @param nextPageUrl the nextPageUrl to set
	 */
	public void setNextPageUrl(String nextPageUrl) {
		this.nextPageUrl = nextPageUrl;
	}

	/**
	 * @return the prevPageUrl
	 */
	public String getPrevPageUrl() {
		return prevPageUrl;
	}

	/**
	 * @param prevPageUrl the prevPageUrl to set
	 */
	public void setPrevPageUrl(String prevPageUrl) {
		this.prevPageUrl = prevPageUrl;
	}

	/**
	 * @return the pageLength
	 */
	public int getPageLength() {
		return pageLength;
	}

	/**
	 * @param pageLength the pageLength to set
	 */
	public void setPageLength(int pageLength) {
		this.pageLength = pageLength;
	}

	/**
	 * @return the pageNumber
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @return the recordCount
	 */
	public int getRecordCount() {
		return recordCount;
	}

	/**
	 * @param recordCount the recordCount to set
	 */
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	/**
	 * @return the calories
	 */
	public int getCalories() {
		return calories;
	}

	/**
	 * @param calories the calories to set
	 */
	public void setCalories(int calories) {
		this.calories = calories;
	}

	/**
	 * @return the dataid
	 */
	public String getDataid() {
		return dataid;
	}

	/**
	 * @param dataid the dataid to set
	 */
	public void setDataid(String dataid) {
		this.dataid = dataid;
	}

	/**
	 * @return the distanceTraveled
	 */
	public float getDistanceTraveled() {
		return distanceTraveled;
	}

	/**
	 * @param distanceTraveled the distanceTraveled to set
	 */
	public void setDistanceTraveled(float distanceTraveled) {
		this.distanceTraveled = distanceTraveled;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the mdate
	 */
	public String getMdate() {
		return mdate;
	}

	/**
	 * @param mdate the mdate to set
	 */
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the steps
	 */
	public String getSteps() {
		return steps;
	}

	/**
	 * @param steps the steps to set
	 */
	public void setSteps(String steps) {
		this.steps = steps;
	}

}
