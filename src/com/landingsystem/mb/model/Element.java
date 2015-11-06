package com.landingsystem.mb.model;

public abstract class Element {
	private boolean status;
	private boolean moving;
	//true if door is open and gear is out
	//false if door is closed and gear is in

	protected int USUAL_RETRACTING_TIME;
	protected int MAX_RETRACTING_TIME;
	protected int USUAL_OUTGOING_TIME;
	protected int MAX_OUTGOING_TIME;
	
	public Element () {
		this.moving = false;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	

	public boolean isMoving() {
		return moving;
	}
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
}
