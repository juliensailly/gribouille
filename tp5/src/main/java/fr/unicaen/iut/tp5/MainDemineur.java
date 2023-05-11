package fr.unicaen.iut.tp5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainDemineur extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(MainDemineur.class.getResource("cadreDemineur.fxml"));
		ControleurDemineur controleurDemineur = new ControleurDemineur();
		loader.setController(controleurDemineur);
		Scene scene = new Scene(loader.load());
		stage.setScene(scene);
		stage.show();
	}
}
