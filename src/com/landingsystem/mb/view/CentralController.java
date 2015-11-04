package com.landingsystem.mb.view;

import com.landingsystem.mb.controller.Main;
import com.landingsystem.mb.model.RetractingThread;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CentralController {

	@FXML
	private Button upButton;
	
	@FXML
	private Button downButton;
	
	private Main mainApp;

	private boolean upPressed;
	private boolean downPressed;
	private boolean done;
	private RetractingThread rt;
	private int timing;
	public CentralController(){
		this.upPressed=false;
		this.downPressed=false;
		this.timing=0; // temps de retractation des roues 
		this.done=false;
	}
	
	@FXML
	private void handleUpButton() {
		System.out.println("up");
		//unlock in down position 0,8s
		//1,6s mouvement
		//0,4s de fin
		rt = new RetractingThread(this.mainApp.getGear());
		rt.start();
		
	}
	
	@FXML
	private void handleDownButton() {
		System.out.println("down");
		rt.stop();
		this.downPressed=true;
	}
	
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
		
	}
}
