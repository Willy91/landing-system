package com.landingsystem.mb.model;

public class Door {
	private boolean open;
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public Door(){
		this.open = true;
	}
}
