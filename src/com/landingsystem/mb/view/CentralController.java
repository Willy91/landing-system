package com.landingsystem.mb.view;

import com.landingsystem.mb.controller.Main;
import com.landingsystem.mb.model.OutgoingThread;
import com.landingsystem.mb.model.RetractingThread;

import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
	@FXML
	private ImageView light;

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

	
	private Image door_closed;
	private Image door_opened;
	private Image door_moving;
	private Image gear_close;
	private Image gear_opened;
	private Image gear_moving;
	private Image light_empty;
	private Image light_red;
	private Image light_green;
	private Image light_orange;

	int rt_d_count = 0;
	int ot_d_count=0;
	int rt_g_count=0;
	int ot_g_count=0;
	
	public CentralController() {
		this.timing = 0; // temps de retractation des roues
		this.done = false;
		door_closed = new Image("file:res/door2_closed.jpg");
		door_opened = new Image("file:res/door2_opened.jpg");
		door_moving = new Image("file:res/door2_moving.jpg");

		gear_close = new Image("file:res/gear2_retracted.jpg");
		gear_moving = new Image("file:res/gear2_moving.jpg");
		gear_opened = new Image("file:res/gear2_extracted.jpg");
		
		light_empty = new Image("file:res/feu_vide.jpg");
		light_red = new Image("file:res/feu_rouge.jpg");
		light_orange = new Image("file:res/feu_orange.jpg");
		light_green = new Image("file:res/feu_vert.jpg");
	}

	@FXML
	private void handleUpButton() {
		System.out.println("up");
		boolean door_opening_gear_inside = false; // ETAPE UNE DE DOWN
		boolean door_opened_gear_moving = false; // ETAPE 2 DE DOWN
		boolean door_closing_gear_outside = false; // ETAPE 3 DE DOWN
		boolean door_closed_gear_outside = false; // ETAPE 4 DE DOWN, ETAPE INITIALE
												// DE UP
		
		for (int i = 0; i < 3; i++) {
			// SI PORTE EN COURS D OUVERTURE ET ROUE ENCORE INTERIEUR
			if (this.mainApp.getDoors()[i].isMoving() && !this.mainApp.getGears()[i].isStatus()) {
				door_opening_gear_inside = true;
			}
			
			// SI PORTE OUVERTE ET ROUE ENTRAIN DE SORTIR
			if (this.mainApp.getDoors()[i].isStatus() && this.mainApp.getGears()[i].isMoving()) {
				door_opened_gear_moving = true;
			}
			
			// SI PORTE ENTRAIN DE SE FERMER ET LA ROUE A L'EXTERIEUR
			if (this.mainApp.getDoors()[i].isMoving() && this.mainApp.getGears()[i].isStatus()) {
				door_closing_gear_outside = true;
			}
			
			// SI PORTE FERMEE ET LA ROUE EST TRKL DEDANS
			if (!this.mainApp.getDoors()[i].isStatus() && this.mainApp.getGears()[i].isStatus()) {
				door_closed_gear_outside = true;
			}
		}
		
		if (door_opening_gear_inside) {
			System.out.println("dans 1");
			for (int i = 0; i < 3; i++) {
				final int tmp = i;
				ot_d[i].setOnCancelled((WorkerStateEvent event) -> {
					rt_d[tmp].restart();
				});
				ot_d[i].cancel();
			}
		}

		else if (door_opened_gear_moving) {
			System.out.println("dans 2");
			for (int i = 0; i < 3; i++) {
				final int tmp = i;
				ot_g[i].setOnCancelled((WorkerStateEvent event) -> {
					rt_g[tmp].restart();
				});
				ot_g[i].cancel();
			}
		}

		else if (door_closing_gear_outside) {
			System.out.println("dans 3");

			for (int i = 0; i < 3; i++) {
				final int tmp = i;
				rt_d[i].setOnCancelled((WorkerStateEvent event) -> {
					ot_d[tmp].restart();
				});
				rt_d[i].cancel();
			}
		}

		else if (door_closed_gear_outside) {
			System.out.println("dans 4");
			

			for (int i = 0; i < 3; i++) {
				imageViewDoors[i].setImage(door_moving);
				light.setImage(light_orange);
				ot_d[i].restart();
				mainApp.getDoors()[i].setMoving(true);
			}
		}
	}

	@FXML
	private void handleDownButton() {
		System.out.println("down");
		boolean door_opening_gear_extracted = false; // ETAPE UNE DE UP
		boolean door_opened_gear_moving = false; // ETAPE 2 DE UP
		boolean door_closing_gear_inside = false; // ETAPE 3 DE UP
		boolean door_closed_gear_inside = false; // ETAPE 4 DE UP, ETAPE INITIALE
												// DE DOWN

		for (int i = 0; i < 3; i++) {
			// SI PORTE EN COURS D OUVERTURE ET ROUE ENCORE EXTERIEUR
			if (this.mainApp.getDoors()[i].isMoving() && this.mainApp.getGears()[i].isStatus()) {
				door_opening_gear_extracted = true;
			}
			
			// SI PORTE OUVERTE ET ROUE ENTRAIN DE RENTRER
			if (this.mainApp.getDoors()[i].isStatus() && this.mainApp.getGears()[i].isMoving()) {
				door_opened_gear_moving = true;
			}
			
			// SI PORTE ENTRAIN DE SE FERMER ET LA ROUE A L'INTERIEUR
			if (this.mainApp.getDoors()[i].isMoving() && !this.mainApp.getGears()[i].isStatus()) {
				door_closing_gear_inside = true;
			}
			
			// SI PORTE FERMEE ET LA ROUE EST TRKL DEDANS
			if (!this.mainApp.getDoors()[i].isStatus() && !this.mainApp.getGears()[i].isStatus()) {
				door_closed_gear_inside = true;
			}
		}
		
		if (door_opening_gear_extracted) {
			System.out.println("dans 1");
			for (int i = 0; i < 3; i++) {
				final int tmp = i;
				ot_d[i].setOnCancelled((WorkerStateEvent event) -> {
					rt_d[tmp].restart();
				});
				ot_d[i].cancel();
			}
		}

		else if (door_opened_gear_moving) {
			System.out.println("dans 2");
			for (int i = 0; i < 3; i++) {
				final int tmp = i;
				rt_g[i].setOnCancelled((WorkerStateEvent event) -> {
					ot_g[tmp].restart();
				});
				rt_g[i].cancel();
			}
		}

		else if (door_closing_gear_inside) {
			System.out.println("dans 3");

			for (int i = 0; i < 3; i++) {
				final int tmp = i;
				rt_d[i].setOnCancelled((WorkerStateEvent event) -> {
					ot_d[tmp].restart();
				});
				rt_d[i].cancel();
			}
		}

		else if (door_closed_gear_inside) {
			System.out.println("dans 4");

			for (int i = 0; i < 3; i++) {
				imageViewDoors[i].setImage(door_moving);
				light.setImage(light_orange);
				ot_d[i].restart();
				mainApp.getDoors()[i].setMoving(true);
			}
		}
	}

	public void setMainApp(Main mainApp, Scene scene) {
		this.mainApp = mainApp;
		this.scene = scene;
		
		imageViewDoors = new ImageView[] {
				(ImageView) scene.lookup("#frontDoor"),
				(ImageView) scene.lookup("#leftDoor"),
				(ImageView) scene.lookup("#rightDoor") };
		imageViewGears = new ImageView[] { frontGear, leftGear, rightGear };
		ot_d = new OutgoingThread[] {
				new OutgoingThread(this.mainApp.getDoors()[0]),
				new OutgoingThread(this.mainApp.getDoors()[1]),
				new OutgoingThread(this.mainApp.getDoors()[2]) };

		ot_g = new OutgoingThread[] {
				new OutgoingThread(this.mainApp.getGears()[0]),
				new OutgoingThread(this.mainApp.getGears()[1]),
				new OutgoingThread(this.mainApp.getGears()[2]) };

		rt_g = new RetractingThread[] {
				new RetractingThread(this.mainApp.getGears()[0]),
				new RetractingThread(this.mainApp.getGears()[1]),
				new RetractingThread(this.mainApp.getGears()[2]) };

		rt_d = new RetractingThread[] {
				new RetractingThread(this.mainApp.getDoors()[0]),
				new RetractingThread(this.mainApp.getDoors()[1]),
				new RetractingThread(this.mainApp.getDoors()[2]) };
		

		for (int i = 0; i < 3; i++) {
			final int tmp = i;
			rt_d[i].setOnSucceeded((WorkerStateEvent event) -> {
				rt_d_count++;
				if(rt_d_count==3){
					for(int j=0;j<3;j++){
						rt_d[j].reset();
					}
					rt_d_count=0;
					light.setImage(light_green);
				}
				this.mainApp.getDoors()[tmp].setStatus(false);
				this.mainApp.getDoors()[tmp].setMoving(false);
				imageViewDoors[tmp].setImage(door_closed);
				
			});

			ot_d[i].setOnSucceeded((WorkerStateEvent event) -> {
				System.out.println("porte ouverte");
				ot_d_count++;
				if(ot_d_count==3){
					for(int j=0;j<3;j++){
						ot_d[j].reset();
						
					}
					ot_d_count=0;
				}
				this.mainApp.getDoors()[tmp].setMoving(false);
				this.mainApp.getDoors()[tmp].setStatus(true);
				imageViewDoors[tmp].setImage(door_opened);
				imageViewGears[tmp].setImage(gear_moving);

				if (!this.mainApp.getGears()[tmp].isStatus()) {
					ot_g[tmp].restart();
				} else {
					rt_g[tmp].restart();
				}
				this.mainApp.getGears()[tmp].setMoving(true);
				
			});

			rt_g[i].setOnSucceeded((WorkerStateEvent event) -> {
				rt_g_count++;
				if(rt_g_count==3){
					for(int j=0;j<3;j++){

						rt_g[j].reset();


						rt_d[j].restart();
						
					}
					rt_g_count=0;
				}
				this.mainApp.getGears()[tmp].setStatus(false);
				this.mainApp.getGears()[tmp].setMoving(false);

				imageViewGears[tmp].setImage(gear_close);
				imageViewDoors[tmp].setImage(door_moving);
				mainApp.getDoors()[tmp].setMoving(true);
				
			});

			ot_g[i].setOnSucceeded((WorkerStateEvent event) -> {
				ot_g_count++;
				if(ot_g_count==3){
					for(int j=0;j<3;j++){
						ot_g[j].reset();
						

						rt_d[j].restart();
						
					}
					ot_g_count=0;
				}
				this.mainApp.getGears()[tmp].setStatus(true);
				this.mainApp.getGears()[tmp].setMoving(false);

				imageViewGears[tmp].setImage(gear_opened);
				imageViewDoors[tmp].setImage(door_moving);
				mainApp.getDoors()[tmp].setMoving(true);
			});
		}
	}
}
