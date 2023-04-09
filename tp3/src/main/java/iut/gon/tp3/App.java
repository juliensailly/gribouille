package iut.gon.tp3;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;

	@Override
	public void start(Stage stage) throws IOException {
		GrilleModel gm = new GrilleModel();
		GrilleController gc = new GrilleController(gm);
		scene = new Scene(loadFXML("scene", gc), 640, 480);
		stage.setScene(scene);
		stage.show();
	}

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml, new GrilleController(new GrilleModel())));
	}

	private static Parent loadFXML(String fxml, GrilleController c) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		fxmlLoader.setController(c);
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}

}