package com.erichstark.pedometer.sqlite.model;

public class SleepReport {
	int id;
	int currentRecordCount;
	String nextPageUrl;
	String prevPageUrl;
	int pageLength;
	int pageNumber;
	int recordCount;
	
	// sleep data list
	int awaken;
	String dataid;
	String startTime;
	String endTime;
	int fellSleep;
	int hoursSlept;
	double latitude;
	double longitude;
	String note;
	int sleepEfficiency;
	
	public SleepReport() {
	}
		
	public SleepReport(int currentRecordCount, String nextPageUrl,
			String prevPageUrl, int pageLength, int pageNumber,
			int recordCount, int awaken, String dataid, String startTime,
			String endTime, int fellSleep, int hoursSlept, double latitude,
			double longitude, String note, int sleepEfficiency) {
		super();
		this.currentRecordCount = currentRecordCount;
		this.nextPageUrl = nextPageUrl;
		this.prevPageUrl = prevPageUrl;
		this.pageLength = pageLength;
		this.pageNumber = pageNumber;
		this.recordCount = recordCount;
		this.awaken = awaken;
		this.dataid = dataid;
		this.startTime = startTime;
		this.endTime = endTime;
		this.fellSleep = fellSleep;
		this.hoursSlept = hoursSlept;
		this.latitude = latitude;
		this.longitude = longitude;
		this.note = note;
		this.sleepEfficiency = sleepEfficiency;
	}

	public SleepReport(int id, int currentRecordCount, String nextPageUrl,
			String prevPageUrl, int pageLength, int pageNumber,
			int recordCount, int awaken, String dataid, String startTime,
			String endTime, int fellSleep, int hoursSlept, double latitude,
			double longitude, String note, int sleepEfficiency) {
		super();
		this.id = id;
		this.currentRecordCount = currentRecordCount;
		this.nextPageUrl = nextPageUrl;
		this.prevPageUrl = prevPageUrl;
		this.pageLength = pageLength;
		this.pageNumber = pageNumber;
		this.recordCount = recordCount;
		this.awaken = awaken;
		this.dataid = dataid;
		this.startTime = startTime;
		this.endTime = endTime;
		this.fellSleep = fellSleep;
		this.hoursSlept = hoursSlept;
		this.latitude = latitude;
		this.longitude = longitude;
		this.note = note;
		this.sleepEfficiency = sleepEfficiency;
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
	 * @return the awaken
	 */
	public int getAwaken() {
		return awaken;
	}

	/**
	 * @param awaken the awaken to set
	 */
	public void setAwaken(int awaken) {
		this.awaken = awaken;
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
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the fellSleep
	 */
	public int getFellSleep() {
		return fellSleep;
	}

	/**
	 * @param fellSleep the fellSleep to set
	 */
	public void setFellSleep(int fellSleep) {
		this.fellSleep = fellSleep;
	}

	/**
	 * @return the hoursSlept
	 */
	public int getHoursSlept() {
		return hoursSlept;
	}

	/**
	 * @param hoursSlept the hoursSlept to set
	 */
	public void setHoursSlept(int hoursSlept) {
		this.hoursSlept = hoursSlept;
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
	 * @return the sleepEfficiency
	 */
	public int getSleepEfficiency() {
		return sleepEfficiency;
	}

	/**
	 * @param sleepEfficiency the sleepEfficiency to set
	 */
	public void setSleepEfficiency(int sleepEfficiency) {
		this.sleepEfficiency = sleepEfficiency;
	}
	
}
