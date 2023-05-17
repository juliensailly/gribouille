package fr.unicaen.iut.tp5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
		Scene scene = new Scene(loader.load(), 650, 700);
		stage.getIcons().add(new Image("file:src/main/resources/fr/unicaen/iut/tp5/icon.png"));
		stage.setTitle("Démineur");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();

		// Pour redimensionner la fenêtre en fonction de la difficulté
		controleurDemineur.setStage(stage);
	}


}
