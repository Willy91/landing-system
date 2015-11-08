package com.landingsystem.mb.model;
/**
 * 
 * @author grosalex
 * Class to represent a gear of the system
 */
public class Gear extends Element  {
	/**
	 * Default constructor
	 * @param outgoing this params represent the outgoing time of the door
	 * @param retracting this params represent the retracting time of the door
	 */
	public Gear(int outgoing,int retracting){
		super();
		super.MAX_RETRACTING_TIME = 1000;
		super.USUAL_RETRACTING_TIME = retracting;
		super.USUAL_OUTGOING_TIME = outgoing;
		super.MAX_OUTGOING_TIME = 7000;
		super.setStatus(true);
	}

}
