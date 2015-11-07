package com.landingsystem.mb.model;

public class Gear extends Element  {

	public Gear(int outgoing,int retracting){
		super();
		super.MAX_RETRACTING_TIME = 1000;
		super.USUAL_RETRACTING_TIME = retracting;
		super.USUAL_OUTGOING_TIME = outgoing;
		super.MAX_OUTGOING_TIME = 7000;
		super.setStatus(true);
	}

}
