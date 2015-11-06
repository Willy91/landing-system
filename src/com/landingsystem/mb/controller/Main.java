package com.landingsystem.mb.controller;
	
import com.landingsystem.mb.model.Door;
import com.landingsystem.mb.model.Gear;
import com.landingsystem.mb.view.CentralController;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private Gear gear;
	private Door door;
	@Override
	public void start(Stage primaryStage) {
		this.gear=new Gear();
		this.door= new Door();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/Central.fxml"));
			VBox root = (VBox) loader.load();
			Scene scene = new Scene(root,400,400);
			
			primaryStage.setScene(scene);
			CentralController controller = loader.getController();
			controller.setMainApp(this,scene);
	        
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public Gear getGear() {
		return gear;
	}

	public void setGear(Gear gear) {
		this.gear = gear;
	}

	public Door getDoor() {
		return door;
	}

	public void setDoor(Door door) {
		this.door = door;
	}
}
