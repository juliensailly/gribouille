package iut.gon.gribouille_tp1;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;
	private double prevX;
	private double prevY;

	@Override
	public void start(Stage stage) throws IOException {
		scene = new Scene(loadFXML("CadreGribouille"), 640, 480);
		stage.setScene(scene);
		stage.setTitle("Gribouille");
		stage.getIcons().add(new Image("file:src\\main\\resources\\iut\\gon\\gribouille_tp1\\icon.jpg"));
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
		
		Pane pane = (Pane) dessin.getParent();
		pane.addEventHandler(MouseEvent.MOUSE_PRESSED, (clickDroit) -> {
			if (clickDroit.getButton() == MouseButton.SECONDARY) {
				Circle c = new Circle(clickDroit.getX(), clickDroit.getY(), 5);
				c.setMouseTransparent(true);
				pane.getChildren().add(c);
				clickDroit.consume();
			}
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