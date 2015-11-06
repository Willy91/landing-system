package com.landingsystem.mb.model;

public class Gear extends Element  {

	public Gear(){
		super();
		super.MAX_RETRACTING_TIME = 1000;
		super.USUAL_RETRACTING_TIME = 280;
		super.USUAL_OUTGOING_TIME = 240;
		super.MAX_OUTGOING_TIME = 7000;
		super.setStatus(true);
	}

}
