package com.landingsystem.mb.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public abstract class CentralThread extends Service<Void> {

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
	protected Task<Void> createTask() {
		return new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				while (timing < MAX_TIME && !done && flag) {
					System.out.println(timing);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					timing++;

					if (timing == USUAL_TIME) {
						done = true;
						el.setStatus(!el.isStatus());
					}
				}
				return null;
			}
		};
	}

	/*public void run() {
		while (timing < MAX_TIME && !done && flag) {
			System.out.println(true);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			timing++;
			// System.out.println(timing);

			if (timing == USUAL_TIME) {
				this.done = true;
				this.el.setStatus(!this.el.isStatus());
			}
		}
		System.out.println(this.el.isStatus());
	}*/

}
