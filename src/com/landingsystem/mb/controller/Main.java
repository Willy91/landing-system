package com.landingsystem.mb.controller;

import com.landingsystem.mb.model.Door;
import com.landingsystem.mb.model.Gear;
import com.landingsystem.mb.view.CentralController;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	private Gear[] gears;
	private Door[] doors;
	private static int FRONT_GEAR_RETRACTING = 280;
	private static int FRONT_GEAR_OUTGOING = 240;
	private static int SIDE_GEAR_RETRACTING = 320;
	private static int SIDE_GEAR_OUTGOING = 280;
	private static int FRONT_DOOR_RETRACTING = 150;
	private static int FRONT_DOOR_OUTGOING = 160;
	private static int SIDE_DOOR_RETRACTING = 190;
	private static int SIDE_DOOR_OUTGOING = 190;

	@Override
	public void start(Stage primaryStage) {
		this.gears = new Gear[] {
				new Gear(FRONT_GEAR_OUTGOING, FRONT_GEAR_RETRACTING),
				new Gear(SIDE_GEAR_OUTGOING, SIDE_GEAR_RETRACTING),
				new Gear(SIDE_GEAR_OUTGOING, SIDE_GEAR_RETRACTING) };
		this.doors = new Door[] { 
				new Door(FRONT_DOOR_OUTGOING,FRONT_DOOR_RETRACTING), 
				new Door(SIDE_DOOR_OUTGOING,SIDE_DOOR_RETRACTING), 
				new Door(SIDE_DOOR_OUTGOING,SIDE_DOOR_RETRACTING) };
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/Central.fxml"));
			VBox root = (VBox) loader.load();
			Scene scene = new Scene(root, 400, 400);

			primaryStage.setScene(scene);
			CentralController controller = loader.getController();
			controller.setMainApp(this, scene);

			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Gear[] getGears() {
		return gears;
	}

	public void setGear(Gear gear, int index) {
		this.gears[index] = gear;
	}

	public Door[] getDoors() {
		return doors;
	}

	public void setDoor(Door door, int index) {
		this.doors[index] = door;
	}
}
