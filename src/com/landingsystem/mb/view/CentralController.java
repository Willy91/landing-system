package com.landingsystem.mb.view;

import java.io.File;

import com.landingsystem.mb.controller.Main;
import com.landingsystem.mb.model.Door;
import com.landingsystem.mb.model.Gear;
import com.landingsystem.mb.model.OutgoingThread;
import com.landingsystem.mb.model.RetractingThread;

import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class CentralController {

	@FXML
	private Button upButton;

	@FXML
	private Button downButton;

	@FXML
	private ImageView frontDoor;
	@FXML
	private ImageView leftDoor;
	@FXML
	private ImageView rightDoor;
	@FXML
	private ImageView leftGear;
	@FXML
	private ImageView rightGear;
	@FXML
	private ImageView frontGear;

	private ImageView[] imageViewDoors;
	private ImageView[] imageViewGears;
	private Main mainApp;
	private Scene scene;
	private boolean done;
	private RetractingThread[] rt_g;
	private OutgoingThread[] ot_d;
	private OutgoingThread[] ot_g;
	private RetractingThread[] rt_d;
	private int timing;

	private Text t_door;
	private Text t_gear;
	private Image door_closed;
	private Image door_opened;
	private Image door_moving;
	private Image gear_close;
	private Image gear_opened;
	private Image gear_moving;

	public CentralController() {
		this.timing = 0; // temps de retractation des roues
		this.done = false;
		door_closed = new Image("file:res/door2_closed.jpg");
		door_opened = new Image("file:res/door2_opened.jpg");
		door_moving = new Image("file:res/door2_moving.jpg");

		gear_close = new Image("file:res/gear2_retracted.jpg");
		gear_moving = new Image("file:res/gear2_moving.jpg");
		gear_opened = new Image("file:res/gear2_extracted.jpg");
		
		imageViewDoors = new ImageView[]{frontDoor,leftDoor,rightDoor};
		imageViewGears = new ImageView[]{frontGear,leftGear,rightGear};
	}

	@FXML
	private void handleUpButton() {
		System.out.println("up");
		frontDoor.setImage(door_moving);
		boolean ok = true;
		// unlock in down position 0,8s
		// 1,6s mouvement
		// 0,4s de fin
		for(int i=0;i<3;i++) {
			if(!this.mainApp.getGears()[i].isStatus()) {
				ok = false;
			}
		}
		if(ok) {
			for(int i=0;i<3;i++) {
				ot_d[i].restart();
				mainApp.getDoors()[i].setMoving(true);
			}
		}
	}

	@FXML
	private void handleDownButton() {
		System.out.println("down");
		boolean ok = true;
		
		// unlock in down position 0,8s
		// 1,6s mouvement
		// 0,4s de fin
		
		//cas porte moving && false
				// cas porte true et gear moving et true
				
				// cas porte moving et gear false
				//cas
		
		//SI PORTE EN COURS D OUVERTURE ET ROUE ENCORE EXTERIEUR
		for(int i=0;i<3;i++) {
			if(!this.mainApp.getDoors()[i].isMoving() && this.mainApp.getGears()[i].isStatus()){
				ok = false;
			}	
		}
		if(ok) {
			for(int i=0;i<3;i++) {
				rt_d[i].setOnCancelled((WorkerStateEvent event) -> {
					ot_d[i].restart();
				});
				rt_d[i].cancel();
			}
		}
		
		else if(this.mainApp.getDoor().isStatus() && this.mainApp.getGear().isMoving()){
			//cas porte sortie et gear sortante
			rt_g.setOnCancelled((WorkerStateEvent event) -> {
				ot_g.restart();
			});
			rt_g.cancel();
		}
		else if(this.mainApp.getDoor().isMoving() && this.mainApp.getGear().isStatus()){
			//cas porte se rentrant et gear sortie
			System.out.println("dedans");
			ot_d.setOnCancelled((WorkerStateEvent event) -> {
				rt_d.restart();
			});
			ot_d.cancel();

		}
		else if (!this.mainApp.getDoor().isMoving() && !this.mainApp.getGear().isStatus()) {
			//cas initial
			frontDoor.setImage(door_moving);
			ot_d.restart();
			mainApp.getDoor().setMoving(true);
		}

	}

	public void setMainApp(Main mainApp, Scene scene) {
		this.mainApp = mainApp;
		this.scene = scene;
		this.t_gear = (Text) this.scene.lookup("#front_gear_status");
		this.t_door = (Text) this.scene.lookup("#front_gear_status");
		
		ot_d = new OutgoingThread[]{
				new OutgoingThread(this.mainApp.getDoors()[0]),
				new OutgoingThread(this.mainApp.getDoors()[1]),
				new OutgoingThread(this.mainApp.getDoors()[2])
				};
		
		ot_g = new OutgoingThread[]{
				new OutgoingThread(this.mainApp.getGears()[0]),
				new OutgoingThread(this.mainApp.getGears()[1]),
				new OutgoingThread(this.mainApp.getGears()[2])
				};
		
		rt_g = new RetractingThread[]{
				new RetractingThread(this.mainApp.getGears()[0]),
				new RetractingThread(this.mainApp.getGears()[1]),
				new RetractingThread(this.mainApp.getGears()[2])
				};
		
		rt_d = new RetractingThread[]{
				new RetractingThread(this.mainApp.getDoors()[0]),
				new RetractingThread(this.mainApp.getDoors()[1]),
				new RetractingThread(this.mainApp.getDoors()[2])
				};
		for(int i=0;i<3;i++){
			rt_d[i].setOnSucceeded((WorkerStateEvent event) -> {
				rt_d[i].reset();
				this.mainApp.getDoors()[i].setStatus(false);
				imageViewDoors[i].setImage(door_closed);
			});
			
			ot_d.setOnSucceeded((WorkerStateEvent event) -> {
				System.out.println("porte ouverte");
				this.mainApp.getDoor().setMoving(false);
				this.mainApp.getDoor().setStatus(true);
				ot_d.reset();
				
				frontDoor.setImage(door_opened);
				frontGear.setImage(gear_moving);
				
				if(!this.mainApp.getGear().isStatus()) {
					ot_g.restart();
				}
				else {
					rt_g.restart();
				}
				this.mainApp.getGear().setMoving(true);
			});
			
			rt_g.setOnSucceeded((WorkerStateEvent event) -> {
				this.mainApp.getGear().setStatus(false);
				this.mainApp.getGear().setMoving(false);
				rt_g.reset();
	
				frontGear.setImage(gear_close);
				frontDoor.setImage(door_moving);
				
				rt_d.restart();
				mainApp.getDoor().setMoving(true);
			});
			
			ot_g.setOnSucceeded((WorkerStateEvent event) -> {
				ot_g.reset();
				this.mainApp.getGear().setStatus(true);
				this.mainApp.getGear().setMoving(false);
				System.out.println("down on succed");
	
				frontGear.setImage(gear_opened);
				frontDoor.setImage(door_moving);
				
				rt_d.restart();
				mainApp.getDoor().setMoving(true);
			});
		}
	}
}
