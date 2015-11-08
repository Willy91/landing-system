package com.landingsystem.mb.model;

/**
 * 
 * @author grosalex
 * Class to represent a door of the system
 */
public class Door extends Element {
	/**
	 * Default constructor
	 * @param outgoing this params represent the outgoing time of the door
	 * @param retracting this params represent the retracting time of the door
	 */
	public Door(int outgoing,int retracting){
		super();
		super.MAX_OUTGOING_TIME = 700;
		super.USUAL_OUTGOING_TIME = outgoing;
		super.MAX_RETRACTING_TIME = super.MAX_OUTGOING_TIME;
		super.USUAL_RETRACTING_TIME = retracting;
		super.setStatus(false);
	}

}
