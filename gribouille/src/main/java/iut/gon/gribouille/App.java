package iut.gon.gribouille;


import java.io.IOException;

import iut.gon.modele.Dessin;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;

	@Override
	public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
		scene = new Scene(loadFXML("CadreGribouille"), 640, 480);
		stage.setScene(scene);
		stage.getIcons().add(new Image("file:src\\main\\resources\\iut\\gon\\gribouille\\icon.png"));
		stage.show();
		Dialogues.prepareFermeture(stage);

		Dessin dessin = new Dessin();
		GribouilleController gc = new GribouilleController(dessin);
		stage.titleProperty().bind(dessin.nomDuFichierProperty());
		
		
	}

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}

}