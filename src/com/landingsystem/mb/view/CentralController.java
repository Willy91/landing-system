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
	
	private boolean upPressed;
	private boolean downPressed;
	
	public CentralController(){
		this.upPressed=false;
		this.downPressed=false;
	}
	
	@FXML
	private void handleUpButton() {
		System.out.println("up");
		//unlock in down position 0,8s
		//1,6s mouvement
		//0,4s de fin
		this.upPressed=true;
		this.downPressed=false;
		int tmp=280;
		while(downPressed || tmp<0){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tmp--;
		}
		if(tmp==0){
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
