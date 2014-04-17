package com.erichstark.pedometer.sqlite.helper;

import java.util.ArrayList;
import java.util.List;

import com.erichstark.pedometer.sqlite.model.ActivityReport;
import com.erichstark.pedometer.sqlite.model.Login;
import com.erichstark.pedometer.sqlite.model.SleepReport;
import com.erichstark.pedometer.sqlite.model.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	// database version - minimum 1
	private static final int DATABASE_VERSION = 4;

	// database name
	private static final String DATABASE_NAME = "ncts_pedometer.db";

	// table names
	private static final String TABLE_ACTIVITY_REPORT = "activity_report";
	private static final String TABLE_SLEEP_REPORT = "sleep_report";
	private static final String TABLE_LOGIN = "login";
	private static final String TABLE_USER = "user_t";

	// common column names
	private static final String KEY_ID = "id";
	// report // login - user_id
	private static final String CURRENT_RECORD_COUNT = "current_record_count";
	private static final String DISTANCE_UNIT = "distance_unit";
	private static final String NEXT_PAGE_URL = "next_page_url";
	private static final String PREV_PAGE_URL = "prev_page_url";
	private static final String PAGE_LENGTH = "page_length";
	private static final String PAGE_NUMBER = "page_number";
	private static final String RECORD_COUNT = "record_count";
	private static final String DATA_ID = "dataid";
	private static final String LATITUDE = "latitude";
	private static final String LONGITUDE = "longitude";
	private static final String NOTE = "note";
	private static final String USER_ID = "userid";

	// activity_report columns
	private static final String CALORIES = "calories";
	private static final String DISTANCE_TRAVELED = "distance_traveled";
	private static final String MDATE = "mdate";
	private static final String STEPS = "steps";

	// sleep_report columns
	private static final String AWAKEN = "awaken";
	private static final String START_TIME = "start_time";
	private static final String END_TIME = "end_time";
	private static final String FELL_SLEEP = "fell_sleep";
	private static final String HOURS_SLEPT = "hours_slept";
	private static final String SLEEP_EFFICIENCY = "sleep_efficiency";

	// login columns
	private static final String CLIEND_ID = "client_id";
	private static final String CLIENT_SECRET = "client_secret";
	private static final String ACCESS_TOKEN = "access_token";
	private static final String REFRESH_TOKEN = "refresh_token";
	private static final String EXPIRES = "expires";
	private static final String USER_PARA = "user_para";
	private static final String TIMESTAMP = "timestamp";

	// user columns
	private static final String HEIGHT_UNIT = "height_unit";
	private static final String WEIGHT_UNIT = "weight_unit";
	private static final String NICKNAME = "nickname";
	private static final String GENDER = "gender";
	private static final String HEIGHT = "height";
	private static final String WEIGHT = "weight";
	private static final String DATE_OF_BIRTH = "date_of_birth";
	private static final String LOGO = "logo";

	// create table statements
	// create table activity_report
	private static final String CREATE_TABLE_ACTIVITY_REPORT = "CREATE TABLE "
			+ TABLE_ACTIVITY_REPORT + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ CURRENT_RECORD_COUNT + " INTEGER," + DISTANCE_UNIT + " INTEGER,"
			+ NEXT_PAGE_URL + " TEXT," + PREV_PAGE_URL + " TEXT," + PAGE_LENGTH
			+ " INTEGER," + PAGE_NUMBER + " INTEGER," + RECORD_COUNT
			+ " INTEGER," + DATA_ID + " TEXT," + LATITUDE + " DOUBLE,"
			+ LONGITUDE + " DOUBLE," + USER_ID + " TEXT," + CALORIES
			+ " INTEGER," + DISTANCE_TRAVELED + " DOUBLE," + MDATE + " TEXT,"
			+ NOTE + " TEXT," + STEPS + " TEXT" + ")";

	// create table sleep_report
	private static final String CREATE_TABLE_SLEEP_REPORT = "CREATE TABLE "
			+ TABLE_SLEEP_REPORT + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ CURRENT_RECORD_COUNT + " INTEGER," + NEXT_PAGE_URL + " TEXT,"
			+ PREV_PAGE_URL + " TEXT," + PAGE_LENGTH + " INTEGER,"
			+ PAGE_NUMBER + " INTEGER," + RECORD_COUNT + " INTEGER," + AWAKEN
			+ " INTEGER," + DATA_ID + " INTEGER," + START_TIME + " TEXT,"
			+ END_TIME + " TEXT," + FELL_SLEEP + " INTEGER," + HOURS_SLEPT
			+ " INTEGER," + LATITUDE + " DOUBLE," + LONGITUDE + " DOUBLE,"
			+ NOTE + " TEXT," + SLEEP_EFFICIENCY + " INTEGER" + ")";

	// create table login
	private static final String CREATE_TABLE_LOGIN = "CREATE TABLE "
			+ TABLE_LOGIN + "(" + KEY_ID + " INTEGER PRIMARY KEY," + CLIEND_ID
			+ " TEXT," + CLIENT_SECRET + " TEXT," + ACCESS_TOKEN + " TEXT,"
			+ REFRESH_TOKEN + " TEXT," + EXPIRES + " INTEGER," + USER_ID
			+ " TEXT," + USER_PARA + " TEXT," + TIMESTAMP + " TEXT" + ")";

	private static final String CREATE_TABLE_USER = "CREATE TABLE "
			+ TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ CURRENT_RECORD_COUNT + " INTEGER," + HEIGHT_UNIT + " INTEGER,"
			+ WEIGHT_UNIT + " INTEGER," + NEXT_PAGE_URL + " TEXT,"
			+ PREV_PAGE_URL + " TEXT," + PAGE_LENGTH + " INTEGER,"
			+ PAGE_NUMBER + " INTEGER," + RECORD_COUNT + " INTEGER," + USER_ID
			+ " TEXT," + NICKNAME + " TEXT," + GENDER + " TEXT," + HEIGHT
			+ " DOUBLE," + WEIGHT + " DOUBLE," + DATE_OF_BIRTH + " TEXT,"
			+ LOGO + " TEXT" + ")";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// CREATE TABLES
		db.execSQL(CREATE_TABLE_ACTIVITY_REPORT);
		db.execSQL(CREATE_TABLE_SLEEP_REPORT);
		db.execSQL(CREATE_TABLE_LOGIN);
		db.execSQL(CREATE_TABLE_USER);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITY_REPORT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SLEEP_REPORT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
		// create new tables
		onCreate(db);

	}

	// /*
	// * Creating a todo
	// */
	// public long createToDo(Todo todo, long[] tag_ids) {
	// SQLiteDatabase db = this.getWritableDatabase();
	//
	// ContentValues values = new ContentValues();
	// values.put(KEY_TODO, todo.getNote());
	// values.put(KEY_STATUS, todo.getStatus());
	// values.put(KEY_CREATED_AT, getDateTime());
	//
	// // insert row
	// long todo_id = db.insert(TABLE_TODO, null, values);
	//
	// // assigning tags to todo
	// for (long tag_id : tag_ids) {
	// createTodoTag(todo_id, tag_id);
	// }
	//
	// return todo_id;
	// }

	public long createLogin(Login login) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(CLIEND_ID, login.getClient_id());
		values.put(CLIENT_SECRET, login.getClient_secret());
		values.put(ACCESS_TOKEN, login.getAccess_token());
		values.put(REFRESH_TOKEN, login.getRefresh_token());
		values.put(EXPIRES, login.getExpires());
		values.put(USER_ID, login.getUser_id());
		values.put(USER_PARA, login.getUser_para());
		values.put(TIMESTAMP, login.getTimestamp());

		// insert row
		long login_id = db.insert(TABLE_LOGIN, null, values);

		return login_id;
	}

	public Login getLogin(long login_id) {
		SQLiteDatabase db = this.getReadableDatabase();

		String query = "SELECT * FROM " + TABLE_LOGIN + " WHERE " + KEY_ID
				+ " = " + login_id;

		Cursor c = db.rawQuery(query, null);

		if (c != null)
			c.moveToFirst();

		Login login = new Login();
		login.setId(c.getInt(c.getColumnIndex(KEY_ID)));
		login.setAccess_token(c.getString(c.getColumnIndex(ACCESS_TOKEN)));
		login.setRefresh_token(c.getString(c.getColumnIndex(REFRESH_TOKEN)));
		login.setClient_id(c.getString(c.getColumnIndex(CLIEND_ID)));
		login.setClient_secret(c.getString(c.getColumnIndex(CLIENT_SECRET)));
		login.setExpires(c.getInt(c.getColumnIndex(EXPIRES)));
		login.setTimestamp(c.getString(c.getColumnIndex(TIMESTAMP)));
		login.setUser_id(c.getString(c.getColumnIndex(USER_ID)));
		login.setUser_para(c.getString(c.getColumnIndex(USER_PARA)));

		return login;
	}

	public long updateLogin(Login login) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(CLIEND_ID, login.getClient_id());
		values.put(CLIENT_SECRET, login.getClient_secret());
		values.put(ACCESS_TOKEN, login.getAccess_token());
		values.put(REFRESH_TOKEN, login.getRefresh_token());
		values.put(EXPIRES, login.getExpires());
		values.put(USER_ID, login.getUser_id());
		values.put(USER_PARA, login.getUser_para());
		values.put(TIMESTAMP, login.getTimestamp());

		// update row
		return db.update(TABLE_LOGIN, values, KEY_ID + " = ?",
				new String[] { String.valueOf(login.getId()) });
	}

	public void deleteLogin(long login_id) {
		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_LOGIN, KEY_ID + " = ?",
				new String[] { String.valueOf(login_id) });
	}

	public long createUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(DATE_OF_BIRTH, user.getDateOfBirth());
		values.put(GENDER, user.getGender());
		values.put(LOGO, user.getLogo());
		values.put(NEXT_PAGE_URL, user.getNextPageUrl());
		values.put(NICKNAME, user.getNickname());
		values.put(PREV_PAGE_URL, user.getPrevPageUrl());
		values.put(USER_ID, user.getUserid());
		values.put(CURRENT_RECORD_COUNT, user.getCurrentRecordCount());
		values.put(HEIGHT, user.getHeight());
		values.put(HEIGHT_UNIT, user.getHeightUnit());
		values.put(PAGE_LENGTH, user.getPageLength());
		values.put(PAGE_NUMBER, user.getPageNumber());
		values.put(RECORD_COUNT, user.getRecordCount());
		values.put(WEIGHT, user.getWeight());
		values.put(WEIGHT_UNIT, user.getWeightUnit());

		// insert row
		long user_id = db.insert(TABLE_USER, null, values);

		return user_id;
	}
	
	public User getUser(long user_id) {
		SQLiteDatabase db = this.getReadableDatabase();

		String query = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_ID
				+ " = " + user_id;

		Cursor c = db.rawQuery(query, null);

		if (c != null)
			c.moveToFirst();

		User user = new User();
		
		user.setId(c.getInt(c.getColumnIndex(KEY_ID)));
		user.setDateOfBirth(c.getString(c.getColumnIndex(DATE_OF_BIRTH)));
		user.setGender(c.getString(c.getColumnIndex(GENDER)));
		user.setLogo(c.getString(c.getColumnIndex(LOGO)));
		user.setNextPageUrl(c.getString(c.getColumnIndex(NEXT_PAGE_URL)));
		user.setNickname(c.getString(c.getColumnIndex(NICKNAME)));
		user.setPrevPageUrl(c.getString(c.getColumnIndex(PREV_PAGE_URL)));
		user.setUserid(c.getString(c.getColumnIndex(USER_ID)));
		user.setCurrentRecordCount(c.getInt(c.getColumnIndex(CURRENT_RECORD_COUNT)));
		user.setHeight(c.getInt(c.getColumnIndex(HEIGHT)));
		user.setHeightUnit(c.getInt(c.getColumnIndex(HEIGHT_UNIT)));
		user.setPageLength(c.getInt(c.getColumnIndex(PAGE_LENGTH)));
		user.setPageNumber(c.getInt(c.getColumnIndex(PAGE_NUMBER)));
		user.setRecordCount(c.getInt(c.getColumnIndex(RECORD_COUNT)));
		user.setWeight(c.getInt(c.getColumnIndex(WEIGHT)));
		user.setWeightUnit(c.getInt(c.getColumnIndex(WEIGHT_UNIT)));
		

		return user;
	}
	
	public long updateUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(DATE_OF_BIRTH, user.getDateOfBirth());
		values.put(GENDER, user.getGender());
		values.put(LOGO, user.getLogo());
		values.put(NEXT_PAGE_URL, user.getNextPageUrl());
		values.put(NICKNAME, user.getNickname());
		values.put(PREV_PAGE_URL, user.getPrevPageUrl());
		values.put(USER_ID, user.getUserid());
		values.put(CURRENT_RECORD_COUNT, user.getCurrentRecordCount());
		values.put(HEIGHT, user.getHeight());
		values.put(HEIGHT_UNIT, user.getHeightUnit());
		values.put(PAGE_LENGTH, user.getPageLength());
		values.put(PAGE_NUMBER, user.getPageNumber());
		values.put(RECORD_COUNT, user.getRecordCount());
		values.put(WEIGHT, user.getWeight());
		values.put(WEIGHT_UNIT, user.getWeightUnit());

		// update row
		return db.update(TABLE_USER, values, KEY_ID + " = ?",
						new String[] { String.valueOf(user.getId()) });
	}
	
	public void deleteUser(long user_id) {
		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_USER, KEY_ID + " = ?",
				new String[] { String.valueOf(user_id) });
	}

	// sleep report
	public long createSleepReport(SleepReport sleep) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(DATA_ID, sleep.getDataid());
		values.put(END_TIME, sleep.getEndTime());
		values.put(NEXT_PAGE_URL, sleep.getNextPageUrl());
		values.put(NOTE, sleep.getNote());
		values.put(PREV_PAGE_URL, sleep.getPrevPageUrl());
		values.put(START_TIME, sleep.getStartTime());
		values.put(AWAKEN, sleep.getAwaken());
		values.put(CURRENT_RECORD_COUNT, sleep.getCurrentRecordCount());
		values.put(FELL_SLEEP, sleep.getFellSleep());
		values.put(HOURS_SLEPT, sleep.getHoursSlept());
		values.put(LATITUDE, sleep.getLatitude());
		values.put(LONGITUDE, sleep.getLongitude());
		values.put(PAGE_LENGTH, sleep.getPageLength());
		values.put(PAGE_NUMBER, sleep.getPageNumber());
		values.put(RECORD_COUNT, sleep.getRecordCount());
		values.put(SLEEP_EFFICIENCY, sleep.getSleepEfficiency());

		// insert row
		long sleep_id = db.insert(TABLE_SLEEP_REPORT, null, values);

		return sleep_id;
	}
	
	public SleepReport getSleepReport(long sleep_id) {
		SQLiteDatabase db = this.getReadableDatabase();

		String query = "SELECT * FROM " + TABLE_SLEEP_REPORT + " WHERE " + KEY_ID
				+ " = " + sleep_id;

		Cursor c = db.rawQuery(query, null);

		if (c != null)
			c.moveToFirst();

		SleepReport sleep = new SleepReport();
		
		sleep.setAwaken(c.getInt(c.getColumnIndex(AWAKEN)));
		sleep.setCurrentRecordCount(c.getInt(c.getColumnIndex(CURRENT_RECORD_COUNT)));
		sleep.setDataid(c.getString(c.getColumnIndex(DATA_ID)));
		sleep.setEndTime(c.getString(c.getColumnIndex(END_TIME)));
		sleep.setFellSleep(c.getInt(c.getColumnIndex(FELL_SLEEP)));
		sleep.setHoursSlept(c.getInt(c.getColumnIndex(HOURS_SLEPT)));
		sleep.setId(c.getInt(c.getColumnIndex(KEY_ID)));
		sleep.setLatitude(c.getDouble(c.getColumnIndex(LATITUDE)));
		sleep.setLongitude(c.getDouble(c.getColumnIndex(LONGITUDE)));
		sleep.setNextPageUrl(c.getString(c.getColumnIndex(NEXT_PAGE_URL)));
		sleep.setNote(c.getString(c.getColumnIndex(NOTE)));
		sleep.setPageLength(c.getInt(c.getColumnIndex(PAGE_LENGTH)));
		sleep.setPageNumber(c.getInt(c.getColumnIndex(PAGE_NUMBER)));
		sleep.setPrevPageUrl(c.getString(c.getColumnIndex(PREV_PAGE_URL)));
		sleep.setRecordCount(c.getInt(c.getColumnIndex(RECORD_COUNT)));
		sleep.setSleepEfficiency(c.getInt(c.getColumnIndex(SLEEP_EFFICIENCY)));
		sleep.setStartTime(c.getString(c.getColumnIndex(START_TIME)));
		
		return sleep;
	}
	
	public List<SleepReport> getAllSleepReports() {
		List<SleepReport> sleepReports = new ArrayList<SleepReport>();
		String query = "SELECT * FROM " + TABLE_SLEEP_REPORT;
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		
	    // looping through all rows and adding to list
	    if (c.moveToFirst()) {
	        do {
	    		SleepReport sleep = new SleepReport();
	    		
	    		sleep.setAwaken(c.getInt(c.getColumnIndex(AWAKEN)));
	    		sleep.setCurrentRecordCount(c.getInt(c.getColumnIndex(CURRENT_RECORD_COUNT)));
	    		sleep.setDataid(c.getString(c.getColumnIndex(DATA_ID)));
	    		sleep.setEndTime(c.getString(c.getColumnIndex(END_TIME)));
	    		sleep.setFellSleep(c.getInt(c.getColumnIndex(FELL_SLEEP)));
	    		sleep.setHoursSlept(c.getInt(c.getColumnIndex(HOURS_SLEPT)));
	    		sleep.setId(c.getInt(c.getColumnIndex(KEY_ID)));
	    		sleep.setLatitude(c.getDouble(c.getColumnIndex(LATITUDE)));
	    		sleep.setLongitude(c.getDouble(c.getColumnIndex(LONGITUDE)));
	    		sleep.setNextPageUrl(c.getString(c.getColumnIndex(NEXT_PAGE_URL)));
	    		sleep.setNote(c.getString(c.getColumnIndex(NOTE)));
	    		sleep.setPageLength(c.getInt(c.getColumnIndex(PAGE_LENGTH)));
	    		sleep.setPageNumber(c.getInt(c.getColumnIndex(PAGE_NUMBER)));
	    		sleep.setPrevPageUrl(c.getString(c.getColumnIndex(PREV_PAGE_URL)));
	    		sleep.setRecordCount(c.getInt(c.getColumnIndex(RECORD_COUNT)));
	    		sleep.setSleepEfficiency(c.getInt(c.getColumnIndex(SLEEP_EFFICIENCY)));
	    		sleep.setStartTime(c.getString(c.getColumnIndex(START_TIME)));
	 
	            // adding to tags list
	            sleepReports.add(sleep);
	        } while (c.moveToNext());
	    }
	    return sleepReports;
	}
	
	// TODO
	// sleep delete one/all, sleep update
	
	
	// activity report
	public long createActivityReport(ActivityReport activity) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(DATA_ID, activity.getDataid());
		values.put(MDATE, activity.getMdate());
		values.put(NEXT_PAGE_URL, activity.getNextPageUrl());
		values.put(NOTE, activity.getNote());
		values.put(PREV_PAGE_URL, activity.getPrevPageUrl());
		values.put(STEPS, activity.getSteps());
		values.put(USER_ID, activity.getUserid());
		values.put(CALORIES, activity.getCalories());
		values.put(CURRENT_RECORD_COUNT, activity.getCurrentRecordCount());
		values.put(DISTANCE_TRAVELED, activity.getDistanceTraveled());
		values.put(DISTANCE_UNIT, activity.getDistanceUnit());
		values.put(LATITUDE, activity.getLatitude());
		values.put(LONGITUDE, activity.getLongitude());
		values.put(PAGE_LENGTH, activity.getPageLength());
		values.put(PAGE_NUMBER, activity.getPageNumber());
		values.put(RECORD_COUNT, activity.getRecordCount());

		// insert row
		long activity_id = db.insert(TABLE_ACTIVITY_REPORT, null, values);

		return activity_id;
	}
	
	public ActivityReport getActivityReport(long activity_id) {
		SQLiteDatabase db = this.getReadableDatabase();

		String query = "SELECT * FROM " + TABLE_ACTIVITY_REPORT + " WHERE " + KEY_ID
				+ " = " + activity_id;

		Cursor c = db.rawQuery(query, null);

		if (c != null)
			c.moveToFirst();

		ActivityReport activity = new ActivityReport();
		
		activity.setCalories(c.getInt(c.getColumnIndex(CALORIES)));
		activity.setCurrentRecordCount(c.getInt(c.getColumnIndex(CURRENT_RECORD_COUNT)));
		activity.setDataid(c.getString(c.getColumnIndex(DATA_ID)));
		activity.setDistanceTraveled(c.getInt(c.getColumnIndex(DISTANCE_TRAVELED)));
		activity.setDistanceUnit(c.getInt(c.getColumnIndex(DISTANCE_UNIT)));
		activity.setLatitude(c.getDouble(c.getColumnIndex(LATITUDE)));
		activity.setLongitude(c.getDouble(c.getColumnIndex(LONGITUDE)));
		activity.setMdate(c.getString(c.getColumnIndex(MDATE)));
		activity.setNextPageUrl(c.getString(c.getColumnIndex(NEXT_PAGE_URL)));
		activity.setNote(c.getString(c.getColumnIndex(NOTE)));
		activity.setPageLength(c.getInt(c.getColumnIndex(PAGE_LENGTH)));
		activity.setPageNumber(c.getInt(c.getColumnIndex(PAGE_NUMBER)));
		activity.setPrevPageUrl(c.getString(c.getColumnIndex(PREV_PAGE_URL)));
		activity.setRecordCount(c.getInt(c.getColumnIndex(RECORD_COUNT)));
		activity.setSteps(c.getString(c.getColumnIndex(STEPS)));
		activity.setUserid(c.getString(c.getColumnIndex(USER_ID)));
			
		
		return activity;
	}
	
	public List<ActivityReport> getAllActivityReports() {
		List<ActivityReport> sleepReports = new ArrayList<ActivityReport>();
		String query = "SELECT * FROM " + TABLE_ACTIVITY_REPORT;
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		
	    // looping through all rows and adding to list
	    if (c.moveToFirst()) {
	        do {
	        	ActivityReport activity = new ActivityReport();
	    		
	    		activity.setCalories(c.getInt(c.getColumnIndex(CALORIES)));
	    		activity.setCurrentRecordCount(c.getInt(c.getColumnIndex(CURRENT_RECORD_COUNT)));
	    		activity.setDataid(c.getString(c.getColumnIndex(DATA_ID)));
	    		activity.setDistanceTraveled(c.getInt(c.getColumnIndex(DISTANCE_TRAVELED)));
	    		activity.setDistanceUnit(c.getInt(c.getColumnIndex(DISTANCE_UNIT)));
	    		activity.setLatitude(c.getDouble(c.getColumnIndex(LATITUDE)));
	    		activity.setLongitude(c.getDouble(c.getColumnIndex(LONGITUDE)));
	    		activity.setMdate(c.getString(c.getColumnIndex(MDATE)));
	    		activity.setNextPageUrl(c.getString(c.getColumnIndex(NEXT_PAGE_URL)));
	    		activity.setNote(c.getString(c.getColumnIndex(NOTE)));
	    		activity.setPageLength(c.getInt(c.getColumnIndex(PAGE_LENGTH)));
	    		activity.setPageNumber(c.getInt(c.getColumnIndex(PAGE_NUMBER)));
	    		activity.setPrevPageUrl(c.getString(c.getColumnIndex(PREV_PAGE_URL)));
	    		activity.setRecordCount(c.getInt(c.getColumnIndex(RECORD_COUNT)));
	    		activity.setSteps(c.getString(c.getColumnIndex(STEPS)));
	    		activity.setUserid(c.getString(c.getColumnIndex(USER_ID)));
	 
	            // adding to tags list
	            sleepReports.add(activity);
	        } while (c.moveToNext());
	    }
	    return sleepReports;
	}
	
	// TODO
	// activity delete one/all, activity update
}
