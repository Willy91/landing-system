package com.landingsystem.mb.model;

public class RetractingThread extends Thread {
	public static int RETRACTING_TIME = 280;
	public static int MAX_RETRACTING_TIME = 10000;
	
	public int timing;
	public boolean done;
	public Gear gear;
	
	public RetractingThread(Gear inGear){
		this.timing=0;
		this.done = false;
		this.gear=inGear;
		
	}
	
	@Override
	public void run(){
		while(timing<MAX_RETRACTING_TIME && !done){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			timing++;
			System.out.println(timing);
			
			if(timing==280){
				this.done=true;
				this.gear.setOut(false);
			}
		}
		System.out.println(this.gear.isOut());
	}
	
	
}
