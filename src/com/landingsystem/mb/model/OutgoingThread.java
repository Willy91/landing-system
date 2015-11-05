package com.landingsystem.mb.model;

public class OutgoingThread extends CentralThread {
	public boolean done;
	public Element el;
	
	public OutgoingThread(Element in){
		super(in);
		super.MAX_TIME = in.MAX_OUTGOING_TIME;
		super.USUAL_TIME = in.USUAL_OUTGOING_TIME;
	}
}
