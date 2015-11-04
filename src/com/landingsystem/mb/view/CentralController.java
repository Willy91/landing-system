package com.landingsystem.mb.view;

import com.landingsystem.mb.controller.Main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CentralController {
	
	@FXML
	private Button upButton;
	
	@FXML
	private Button downButton;
	
	private Main mainApp;
	
	
	@FXML
	private void handleUpButton() {
		System.out.println("up");
	}
	
	@FXML
	private void handleDownButton() {
		System.out.println("down");
	}
	
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}
}
