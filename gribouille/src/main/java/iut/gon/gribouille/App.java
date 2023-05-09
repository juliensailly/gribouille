package iut.gon.gribouille;


import java.io.IOException;

import controleurs.Controleur;
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
	
	@Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        //scene = new Scene(loadFXML("CadreGribouille"), 800, 600);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CadreGribouille.fxml"));

        Dessin dessin = new Dessin();
        dessin.setNomDuFichier("nomFichier.txt");

        Controleur controlleur = new Controleur(stage, dessin);

        fxmlLoader.setController(controlleur);

        scene = new Scene(fxmlLoader.load());

        stage.titleProperty().bind(dessin.nomDuFichierProperty());

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src\\main\\resources\\iut\\gon\\gribouille\\icon.png"));
        stage.show();
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

/*
 * To-Do List :
 * 
 * Sélectionner le bon outil dès le départ
 * Régler problème de sélection d'outil
 * Faire l'outil étoile
 *
*/



