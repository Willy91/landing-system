package com.landingsystem.mb.view;

import com.landingsystem.mb.controller.Main;
import com.landingsystem.mb.model.OutgoingThread;
import com.landingsystem.mb.model.RetractingThread;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class CentralController {

	@FXML
	private Button upButton;
	
	@FXML
	private Button downButton;
	
	private Main mainApp;
	private Scene scene; 
	private boolean done;
	private RetractingThread rt;
	private OutgoingThread ot;
	private OutgoingThread otg;
	private int timing;
	
	public CentralController(){
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
		}
	}
	
	@FXML
	private void handleDownButton() {
		System.out.println("down");
		//ot.flag = false;
		//rt.flag = false;
		if(this.mainApp.getGear().isStatus()){
			this.ot=new OutgoingThread(this.mainApp.getDoor());
			this.otg=new OutgoingThread(this.mainApp.getGear());
			this.rt=new RetractingThread(this.mainApp.getDoor());
			ot.setOnSucceeded((WorkerStateEvent event)-> {
				System.out.println("porte ouverte");
				otg.start();
			});
			otg.setOnSucceeded((WorkerStateEvent event)-> {
				System.out.println("roue sortie");

				rt.start();
			});
			rt.setOnSucceeded((WorkerStateEvent event)-> {
				System.out.println("end door : "+this.mainApp.getDoor().isStatus()+this.mainApp.getGear().isStatus());
				Text t_gear = (Text)this.scene.lookup("#front_gear_status");
				Text t_door =(Text)this.scene.lookup("#front_gear_status");
				
				t_gear.setText(Boolean.toString(this.mainApp.getGear().isStatus()));
				t_door.setText(Boolean.toString(this.mainApp.getDoor().isStatus()));
				
			});
			ot.start();
		}
	}
	
	public void setMainApp(Main mainApp,Scene scene) {
		this.mainApp = mainApp;
		this.scene = scene;
		
	}
}
