package com.landingsystem.mb.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public abstract class CentralThread extends Service<Element> {

	protected int USUAL_TIME;
	protected int MAX_TIME;
	private int timing;
	private boolean done;
	private Element el;
	public volatile boolean flag;

	public CentralThread(Element el) {
		this.timing = el.getActualTime();
		this.done = false;
		this.el = el;
		this.flag = true;
	}

	@Override
	protected Task<Element> createTask() {
		return new Task<Element>() {
			@Override
			protected Element call() throws Exception {
				while (timing < MAX_TIME && !done && flag) {
					System.out.println(timing);
					try {
						el.setActualTime(timing);

						updateValue(el);

						Thread.sleep(10);
					} catch (InterruptedException e) {
						if (isCancelled()) {
							el.setActualTime(timing);
							updateValue(el);

							return el;
						}
						e.printStackTrace();
					}
					
					timing++;

					if (timing == USUAL_TIME) {
						done = true;
						el.setStatus(!el.isStatus());
					}
					if (isCancelled()) {
						el.setActualTime(timing);
						return el;
					}
				}
				return el;
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
