package com.landingsystem.mb.model;

public abstract class Element {
	private boolean status;
	//true if door is open and gear is out
	//false if door is closed and gear is in
	
	protected int USUAL_RETRACTING_TIME;
	protected int MAX_RETRACTING_TIME;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
