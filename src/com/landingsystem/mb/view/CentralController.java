package com.landingsystem.mb.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CentralController {
	
	@FXML
	private Button upButton;
	
	@FXML
	private Button downButton;
	
	@FXML
	private void handleUpButton() {
		System.out.println("up");
	}
	
	@FXML
	private void handleDownButton() {
		System.out.println("down");
	}
}
