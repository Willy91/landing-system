package com.landingsystem.mb.model;

public class Door extends Element {
	public Door(){
		super();
		super.MAX_OUTGOING_TIME = 700;
		super.USUAL_OUTGOING_TIME = 150;
		super.MAX_RETRACTING_TIME = super.MAX_OUTGOING_TIME;
		super.USUAL_RETRACTING_TIME = 160;
		super.setStatus(false);
	}
}
