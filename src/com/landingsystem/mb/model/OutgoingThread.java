package com.landingsystem.mb.model;
/**
 * This thread represent the action of putting something out
 * @author grosalex
 *
 */
public class OutgoingThread extends CentralThread {
	public boolean done;
	public Element el;
	/**
	 * Default Constructor
	 * @param in is the moving element
	 */
	public OutgoingThread(Element in){
		super(in);
		super.MAX_TIME = in.MAX_OUTGOING_TIME;
		super.USUAL_TIME = in.USUAL_OUTGOING_TIME;
	}
}
