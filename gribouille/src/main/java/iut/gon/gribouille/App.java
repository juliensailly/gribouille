package iut.gon.gribouille;


import java.io.IOException;

import iut.gon.modele.Dessin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App
 * 
 * @author Julien Sailly
 */
public class App extends Application {

	private static Scene scene;

	/**
	 * Coordonne le démarrage de l'application JavaFX.
	 * 
	 */
	@Override
	public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
		scene = new Scene(loadFXML("CadreGribouille"), 640, 480);
		stage.setScene(scene);
		stage.getIcons().add(new Image("file:src\\main\\resources\\iut\\gon\\gribouille\\icon.png"));
		stage.show();
		Dialogues.prepareFermeture(stage);

		Dessin dessin = new Dessin();
		@SuppressWarnings("unused")
		GribouilleController gc = new GribouilleController(dessin);
		stage.titleProperty().bind(dessin.nomDuFichierProperty());
	}

	/**
	 * Définit la racine du graph de la scène.
	 * 
	 * @param fxml Le chemin du fichier .fxml concerncé.
	 * @throws IOException
	 */
	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	/**
	 * Permet de charger le contenu d'un fichier .fxml
	 * 
	 * @param fxml Le chemin du fichier sans l'extension .fxml.
	 * @return Une hiérarchie de scène FXML correspondant au fichier.
	 * @throws IOException
	 */
	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	/**
	 * La fonction main.
	 * 
	 * @param args Pas utilisé ici.
	 */
	public static void main(String[] args) {
		launch();
	}

}