package com.landingsystem.mb.model;
/**
 * This thread represent the action of putting something in
 * @author grosalex
 *
 */
public class RetractingThread extends CentralThread {
	
	public boolean done;
	public Element el;
	/**
	 * Default Constructor
	 * @param in is the moving element
	 */
	public RetractingThread(Element in){
		super(in);
		super.MAX_TIME = in.MAX_RETRACTING_TIME;
		super.USUAL_TIME = in.USUAL_RETRACTING_TIME;
	}
	
}
