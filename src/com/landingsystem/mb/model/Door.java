package com.landingsystem.mb.model;

public class Door extends Element {
	public Door(int outgoing,int retracting){
		super();
		super.MAX_OUTGOING_TIME = 700;
		super.USUAL_OUTGOING_TIME = outgoing;
		super.MAX_RETRACTING_TIME = super.MAX_OUTGOING_TIME;
		super.USUAL_RETRACTING_TIME = retracting;
		super.setStatus(false);
	}

}
