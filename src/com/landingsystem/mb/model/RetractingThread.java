package com.landingsystem.mb.model;

public class RetractingThread extends CentralThread {
	
	public boolean done;
	public Element el;
	
	public RetractingThread(Element in){
		super(in);
		super.MAX_TIME = in.MAX_RETRACTING_TIME;
		super.USUAL_TIME = in.USUAL_RETRACTING_TIME;
	}
	
}
