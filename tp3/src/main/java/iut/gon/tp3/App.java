package iut.gon.tp3;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;

	@Override
	public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
		GrilleModel gm = new GrilleModel();
		GrilleController gc = new GrilleController(gm);
		stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			String textLabel = "Touche";
			switch (event.getText()) {
			case "1":
				gm.setCase(textLabel, 2, 0);
				break;
			case "2":
				gm.setCase(textLabel, 2, 1);
				break;
			case "3":
				gm.setCase(textLabel, 2, 2);
				break;
			case "4":
				gm.setCase(textLabel, 1, 0);
				break;
			case "5":
				gm.setCase(textLabel, 1, 1);
				break;
			case "6":
				gm.setCase(textLabel, 1, 2);
				break;
			case "7":
				gm.setCase(textLabel, 0, 0);
				break;
			case "8":
				gm.setCase(textLabel, 0, 1);
				break;
			case "9":
				gm.setCase(textLabel, 0, 2);
				break;
			}
		});
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