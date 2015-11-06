package com.landingsystem.mb.model;

public abstract class Element {
	private boolean status;
	private int actualTime;
	//true if door is open and gear is out
	//false if door is closed and gear is in

	protected int USUAL_RETRACTING_TIME;
	protected int MAX_RETRACTING_TIME;
	protected int USUAL_OUTGOING_TIME;
	protected int MAX_OUTGOING_TIME;

	public Element(){
		this.actualTime=0;
	}
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getActualTime() {
		return actualTime;
	}
	public void setActualTime(int actualTime) {
		this.actualTime = actualTime;
	}
	
}
