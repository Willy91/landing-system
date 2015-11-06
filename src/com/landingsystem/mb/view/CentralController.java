package com.landingsystem.mb.view;

import com.landingsystem.mb.controller.Main;
import com.landingsystem.mb.model.Door;
import com.landingsystem.mb.model.Gear;
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
	private RetractingThread rt_g;
	private OutgoingThread ot_d;
	private OutgoingThread ot_g;
	private RetractingThread rt_d;
	private int timing;
	
	private Text t_door;
	private Text t_gear;
	
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
			ot_d = new OutgoingThread(this.mainApp.getDoor());
			rt_g = new RetractingThread(this.mainApp.getGear());
			rt_d = new RetractingThread(this.mainApp.getDoor());
			ot_d.setOnSucceeded((WorkerStateEvent event)-> {
				System.out.println("porte ouverte");
				this.mainApp.setDoor((Door)ot_d.getValue());
				rt_g.start();
			});	
			rt_g.setOnSucceeded((WorkerStateEvent event)-> {
				System.out.println("porte ouverte");
				this.mainApp.setGear((Gear)rt_g.getValue());
				rt_d.start();
			});	
			
			ot_d.start();


		}
	}
	
	@FXML
	private void handleDownButton() {
		System.out.println("down");
		//ot.flag = false;
		//rt.flag = false;
		if(this.mainApp.getGear().isStatus()){
			this.ot_d=new OutgoingThread(this.mainApp.getDoor());
			this.ot_g=new OutgoingThread(this.mainApp.getGear());
			this.rt_d=new RetractingThread(this.mainApp.getDoor());
			ot_d.setOnSucceeded((WorkerStateEvent event)-> {
				System.out.println("porte ouverte");
				ot_g.start();
			});
			ot_g.setOnSucceeded((WorkerStateEvent event)-> {
				System.out.println("roue sortie");
				rt_d.start();
			});
			rt_d.setOnSucceeded((WorkerStateEvent event)-> {
				System.out.println("end door : "+this.mainApp.getDoor().isStatus()+this.mainApp.getGear().isStatus());
		
				t_gear.setText(Boolean.toString(this.mainApp.getGear().isStatus()));
				t_door.setText(Boolean.toString(this.mainApp.getDoor().isStatus()));
				
			});
			ot_d.start();
		}
	}
	
	public void setMainApp(Main mainApp,Scene scene) {
		this.mainApp = mainApp;
		this.scene = scene;
		this.t_gear = (Text)this.scene.lookup("#front_gear_status");
		this.t_door =(Text)this.scene.lookup("#front_gear_status");

	}
}
