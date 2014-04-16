package com.erichstark.pedometer.sqlite.model;

public class Login {
	int id;
	
	// app data
	String client_id;
	String client_secret;
	
	// user data
	String access_token;
	String refresh_token;
	int expires;
	String user_id;
	String user_para;
	
	// date
	String timestamp;
	
	public Login() {
	}
	
	public Login(String client_id, String client_secret, String access_token,
			String refresh_token, int expires, String user_id, String user_para, String timestamp) {
		super();
		this.client_id = client_id;
		this.client_secret = client_secret;
		this.access_token = access_token;
		this.refresh_token = refresh_token;
		this.expires = expires;
		this.user_id = user_id;
		this.user_para = user_para;
		this.timestamp = timestamp;
	}

	public Login(int id, String client_id, String client_secret,
			String access_token, String refresh_token, int expires,
			String user_id, String user_para, String timestamp) {
		super();
		this.id = id;
		this.client_id = client_id;
		this.client_secret = client_secret;
		this.access_token = access_token;
		this.refresh_token = refresh_token;
		this.expires = expires;
		this.user_id = user_id;
		this.user_para = user_para;
		this.timestamp = timestamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public int getExpires() {
		return expires;
	}

	public void setExpires(int expires) {
		this.expires = expires;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_para() {
		return user_para;
	}

	public void setUser_para(String user_para) {
		this.user_para = user_para;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
}
