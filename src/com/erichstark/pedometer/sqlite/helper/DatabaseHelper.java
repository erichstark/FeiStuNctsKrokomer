package com.erichstark.pedometer.sqlite.helper;

import com.erichstark.pedometer.sqlite.model.Login;

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
	private static final int DATABASE_VERSION = 1;
	
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
			+ CURRENT_RECORD_COUNT + " INTEGER,"
			+ DISTANCE_UNIT + " INTEGER,"
			+ NEXT_PAGE_URL + " TEXT,"
			+ PREV_PAGE_URL + " TEXT,"
			+ PAGE_LENGTH + " INTEGER,"
			+ PAGE_NUMBER + " INTEGER,"
			+ RECORD_COUNT + " INTEGER,"
			+ DATA_ID + " TEXT,"
			+ LATITUDE + " DOUBLE,"
			+ LONGITUDE + " DOUBLE,"
			+ USER_ID + " TEXT,"
			+ CALORIES + " INTEGER,"
			+ DISTANCE_TRAVELED + " DOUBLE,"
			+ MDATE + " TEXT,"
			+ NOTE + " TEXT,"
			+ STEPS + " TEXT" + ")";
	
	// create table sleep_report
	private static final String CREATE_TABLE_SLEEP_REPORT = "CREATE TABLE "
			+ TABLE_SLEEP_REPORT + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ CURRENT_RECORD_COUNT + " INTEGER,"
			+ NEXT_PAGE_URL + " TEXT,"
			+ PREV_PAGE_URL + " TEXT,"
			+ PAGE_LENGTH + " INTEGER,"
			+ PAGE_NUMBER + " INTEGER,"
			+ RECORD_COUNT + " INTEGER,"
			+ AWAKEN + " INTEGER,"
			+ DATA_ID + " INTEGER,"
			+ START_TIME + " TEXT,"
			+ END_TIME + " TEXT,"
			+ FELL_SLEEP + " INTEGER,"
			+ HOURS_SLEPT + " INTEGER,"
			+ LATITUDE + " DOUBLE,"
			+ LONGITUDE + " DOUBLE,"
			+ NOTE + " TEXT,"
			+ SLEEP_EFFICIENCY + " INTEGER" + ")";
	
	// create table login
	private static final String CREATE_TABLE_LOGIN = "CREATE TABLE "
			+ TABLE_LOGIN + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ CLIEND_ID + " TEXT,"
			+ CLIENT_SECRET + " TEXT,"
			+ ACCESS_TOKEN + " TEXT,"
			+ REFRESH_TOKEN + " TEXT,"
			+ EXPIRES + " INTEGER,"
			+ USER_ID + " TEXT,"
			+ USER_PARA + " TEXT," 
			+ TIMESTAMP + " TEXT" + ")";
	
	private static final String CREATE_TABLE_USER = "CREATE TABLE "
			+ TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ CURRENT_RECORD_COUNT + " INTEGER,"
			+ HEIGHT_UNIT + " INTEGER,"
			+ WEIGHT_UNIT + " INTEGER,"
			+ NEXT_PAGE_URL + " TEXT,"
			+ PREV_PAGE_URL + " TEXT,"
			+ PAGE_LENGTH + " INTEGER,"
			+ PAGE_NUMBER + " INTEGER," 
			+ RECORD_COUNT + " INTEGER,"
			+ USER_ID + " TEXT,"
			+ NICKNAME + " TEXT,"
			+ GENDER + " TEXT,"
			+ HEIGHT + " DOUBLE,"
			+ WEIGHT + " DOUBLE,"
			+ DATE_OF_BIRTH + " TEXT,"
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
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_LOGIN);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_USER);
        // create new tables
        onCreate(db);

	}
	
//	/*
//	 * Creating a todo
//	 */
//	public long createToDo(Todo todo, long[] tag_ids) {
//	    SQLiteDatabase db = this.getWritableDatabase();
//	 
//	    ContentValues values = new ContentValues();
//	    values.put(KEY_TODO, todo.getNote());
//	    values.put(KEY_STATUS, todo.getStatus());
//	    values.put(KEY_CREATED_AT, getDateTime());
//	 
//	    // insert row
//	    long todo_id = db.insert(TABLE_TODO, null, values);
//	 
//	    // assigning tags to todo
//	    for (long tag_id : tag_ids) {
//	        createTodoTag(todo_id, tag_id);
//	    }
//	 
//	    return todo_id;
//	}
	
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
		
		// insert row
		long login_id = db.insert(TABLE_LOGIN, null, values);
		
		return login_id;
	}
	
	public Login getLogin(long login_id) {
		SQLiteDatabase db = this.getReadableDatabase();
		
		String query = "SELECT * FROM " + TABLE_LOGIN + " WHERE "
	            + KEY_ID + " = " + login_id;
		
		Log.e("DatabaseHelper: ", query);
		
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
	
	
//	public Todo getTodo(long todo_id) {
//	    SQLiteDatabase db = this.getReadableDatabase();
//	 
//	    String selectQuery = "SELECT  * FROM " + TABLE_TODO + " WHERE "
//	            + KEY_ID + " = " + todo_id;
//	 
//	    Log.e(LOG, selectQuery);
//	 
//	    Cursor c = db.rawQuery(selectQuery, null);
//	 
//	    if (c != null)
//	        c.moveToFirst();
//	 
//	    Todo td = new Todo();
//	    td.setId(c.getInt(c.getColumnIndex(KEY_ID)));
//	    td.setNote((c.getString(c.getColumnIndex(KEY_TODO))));
//	    td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
//	 
//	    return td;
//	}
	
//	int id;
//	
//	// app data
//	String client_id;
//	String client_secret;
//	
//	// user data
//	String access_token;
//	String refresh_token;
//	int expires;
//	String user_id;
//	String user_para;
//	
//	// date
//	String timestamp;
	

}
