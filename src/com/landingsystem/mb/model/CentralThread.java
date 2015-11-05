package com.landingsystem.mb.model;

public abstract class CentralThread extends Thread{
	
	protected int USUAL_TIME;
	protected int MAX_TIME;
	private int timing;
	private boolean done;
	private Element el;
	public volatile boolean flag;
	
	public CentralThread (Element el) {
		this.timing = 0;
		this.done = false;
		this.el = el;
		this.flag = true;
	}
	
	@Override
	public void run() {
		while(timing<MAX_TIME && !done && flag){
			System.out.println(true);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			timing++;
			//System.out.println(timing);
			
			if(timing==USUAL_TIME){
				this.done=true;
				this.el.setStatus(!this.el.isStatus());
			}
		}
		System.out.println(this.el.isStatus());
	}

}
