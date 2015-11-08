package com.landingsystem.mb.model;

/**
 * 
 * @author grosalex
 *Element class that can represent an element of the system
 */
public abstract class Element {
	private boolean status;
	private boolean moving;
	//true if door is open and gear is out
	//false if door is closed and gear is in

	protected int USUAL_RETRACTING_TIME;
	protected int MAX_RETRACTING_TIME;
	protected int USUAL_OUTGOING_TIME;
	protected int MAX_OUTGOING_TIME;
	
	/**
	 * Default Constructor of Element Object
	 */
	public Element () {
		this.moving = false;
	}

	/**
	 * get current status of the element
	 * @return true if the element is out and false if it's in
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * set current status of the element
	 * @param status set to true when out and false when in
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	/**
	 * is the element moving
	 * @return true if the element is moving
	 */
	public boolean isMoving() {
		return moving;
	}
	/**
	 * Set if the element is moving
	 * @param moving true if is moving and false if not
	 */
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
}
