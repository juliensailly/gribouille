package iut.gon.gribouille;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;
	private double prevX;
	private double prevY;

	@Override
	public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
		scene = new Scene(loadFXML("CadreGribouille"), 640, 480);
		stage.setScene(scene);
		stage.setTitle("Gribouille");
		stage.getIcons().add(new Image("file:src\\main\\resources\\iut\\gon\\gribouille\\icon.jpg"));
		stage.show();
		Dialogues.prepareFermeture(stage);

		Canvas dessin = (Canvas) scene.lookup("Canvas");
		dessin.addEventHandler(MouseEvent.MOUSE_PRESSED, (click) -> {
			this.prevX = click.getX();
			this.prevY = click.getY();
		});
		dessin.addEventHandler(MouseEvent.MOUSE_DRAGGED, (appui) -> {
			dessin.getGraphicsContext2D().strokeLine(prevX, prevY, appui.getX(), appui.getY());
			this.prevX = appui.getX();
			this.prevY = appui.getY();
		});
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