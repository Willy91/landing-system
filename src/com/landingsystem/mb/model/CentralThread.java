package com.landingsystem.mb.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public abstract class CentralThread extends Service<Integer> {

	protected int USUAL_TIME;
	protected int MAX_TIME;
	private int timing;
	private boolean done;
	public volatile boolean flag;

	public CentralThread(Element el) {
		this.timing = 0;
		this.done = false;
		this.flag = true;
	}

	@Override
	protected Task<Integer> createTask() {
		return new Task<Integer>() {
			@Override
			protected Integer call() throws Exception {
				while (timing < MAX_TIME && !done && flag) {
					System.out.println(timing);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						if (isCancelled()) {

							return timing;
						}
						e.printStackTrace();
					}
					
					timing++;

					if (timing == USUAL_TIME) {
						done = true;
					}
					if (isCancelled()) {
						return timing;
					}
				}
				return timing;
			}
		};
	}

	/*
	 * public void run() { while (timing < MAX_TIME && !done && flag) {
	 * System.out.println(true); try { Thread.sleep(10); } catch
	 * (InterruptedException e) { e.printStackTrace(); }
	 * 
	 * timing++; // System.out.println(timing);
	 * 
	 * if (timing == USUAL_TIME) { this.done = true;
	 * this.el.setStatus(!this.el.isStatus()); } }
	 * System.out.println(this.el.isStatus()); }
	 */

}
