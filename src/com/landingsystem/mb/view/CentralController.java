package com.landingsystem.mb.view;

import com.landingsystem.mb.controller.Main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CentralController {
	public static int RETRACTING_TIME = 280;
	
	@FXML
	private Button upButton;
	
	@FXML
	private Button downButton;
	
	private Main mainApp;

	private boolean upPressed;
	private boolean downPressed;
	private int timing;
	public CentralController(){
		this.upPressed=false;
		this.downPressed=false;
		this.timing=280; // temps de retractation des roues 
	}
	
	@FXML
	private void handleUpButton() {
		System.out.println("up");
		//unlock in down position 0,8s
		//1,6s mouvement
		//0,4s de fin
		this.upPressed=true;
		this.downPressed=false;
		while(!downPressed || timing<RETRACTING_TIME){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			timing--;
		}
		if(timing==0){
			this.mainApp.getGear().setOut(false);
		}
		this.upPressed=false;
	}
	
	@FXML
	private void handleDownButton() {
		System.out.println("down");
	}
	
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
		
	}
}
