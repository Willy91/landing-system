package com.landingsystem.mb.view;

import com.landingsystem.mb.controller.Main;
import com.landingsystem.mb.model.OutgoingThread;
import com.landingsystem.mb.model.RetractingThread;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
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
	private OutgoingThread ot;
	private OutgoingThread otg;
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
		if(!this.mainApp.getGear().isStatus()) {
			ot = new OutgoingThread(this.mainApp.getDoor());
			rt = new RetractingThread(this.mainApp.getGear());
			
			ot.start();
			ot.setOnSucceeded((WorkerStateEvent event)-> {
				System.out.println("porte ouverte");
				rt.start();
				
			});
			
			
			
			//System.out.println("roue fermée");
		}
	}
	
	@FXML
	private void handleDownButton() {
		System.out.println("down");
		ot.flag = false;
		rt.flag = false;
		if(this.mainApp.getGear().isStatus()){
			this.ot=new OutgoingThread(this.mainApp.getDoor());
			this.otg=new OutgoingThread(this.mainApp.getGear());
			this.rt=new RetractingThread(this.mainApp.getDoor());
			ot.setOnSucceeded((WorkerStateEvent event)-> {
				otg.start();
			});
			otg.setOnSucceeded((WorkerStateEvent event)-> {
				rt.start();
			});
			ot.start();
		}
	}
	
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
		
	}
}
