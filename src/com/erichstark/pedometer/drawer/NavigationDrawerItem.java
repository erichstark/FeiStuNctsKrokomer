package com.erichstark.pedometer.drawer;

public class NavigationDrawerItem {

	private int icon;
	private String title;
	private String count;
	private boolean isCounterVisible = false;
	
	public NavigationDrawerItem() {
	}
	
	public NavigationDrawerItem(int icon, String title){
        this.icon = icon;
        this.title = title;
    }
	
	public NavigationDrawerItem(int icon, String title, String count,
			boolean isCounterVisible) {
		this.icon = icon;
		this.title = title;
		this.count = count;
		this.isCounterVisible = isCounterVisible;
	}
	
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public boolean isCounterVisible() {
		return isCounterVisible;
	}
	public void setCounterVisible(boolean isCounterVisible) {
		this.isCounterVisible = isCounterVisible;
	}
	
}
