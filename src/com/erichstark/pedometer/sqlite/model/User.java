package com.erichstark.pedometer.sqlite.model;

// user info from iHealth
public class User {
	int id;
	int currentRecordCount;
	int heightUnit; //ok
	int weightUnit; //ok
	String nextPageUrl;
	String prevPageUrl;
	int pageLength;
	int pageNumber;
	int recordCount;

	// UserInfoList - inner Object
	String userid;
	String nickname;
	String gender;
	float height;
	float weight;
	String dateOfBirth;

	// should be URL with .png
	String logo;

	public User() {
	}
	
	public User(int currentRecordCount, int heightUnit, int weightUnit,
			String nextPageUrl, String prevPageUrl, int pageLength,
			int pageNumber, int recordCount, String userid, String nickname,
			String gender, float height, float weight, String dateOfBirth,
			String logo) {
		super();
		this.currentRecordCount = currentRecordCount;
		this.heightUnit = heightUnit;
		this.weightUnit = weightUnit;
		this.nextPageUrl = nextPageUrl;
		this.prevPageUrl = prevPageUrl;
		this.pageLength = pageLength;
		this.pageNumber = pageNumber;
		this.recordCount = recordCount;
		this.userid = userid;
		this.nickname = nickname;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.dateOfBirth = dateOfBirth;
		this.logo = logo;
	}

	public User(int id, int currentRecordCount, int heightUnit, int weightUnit,
			String nextPageUrl, String prevPageUrl, int pageLength,
			int pageNumber, int recordCount, String userid, String nickname,
			String gender, float height, float weight, String dateOfBirth,
			String logo) {
		super();
		this.id = id;
		this.currentRecordCount = currentRecordCount;
		this.heightUnit = heightUnit;
		this.weightUnit = weightUnit;
		this.nextPageUrl = nextPageUrl;
		this.prevPageUrl = prevPageUrl;
		this.pageLength = pageLength;
		this.pageNumber = pageNumber;
		this.recordCount = recordCount;
		this.userid = userid;
		this.nickname = nickname;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.dateOfBirth = dateOfBirth;
		this.logo = logo;
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
	 * @return the heightUnit
	 */
	public int getHeightUnit() {
		return heightUnit;
	}

	/**
	 * @param heightUnit the heightUnit to set
	 */
	public void setHeightUnit(int heightUnit) {
		this.heightUnit = heightUnit;
	}

	/**
	 * @return the weightUnit
	 */
	public int getWeightUnit() {
		return weightUnit;
	}

	/**
	 * @param weightUnit the weightUnit to set
	 */
	public void setWeightUnit(int weightUnit) {
		this.weightUnit = weightUnit;
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
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(float height) {
		this.height = height;
	}

	/**
	 * @return the weight
	 */
	public float getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(float weight) {
		this.weight = weight;
	}

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the logo
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * @param logo the logo to set
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

}
