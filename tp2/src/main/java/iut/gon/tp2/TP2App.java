package iut.gon.tp2;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TP2App extends Application {

	private BorderPane contenu;
	private ListView<String> gauche;
	private ListView<String> droite;
	private Button versGauche;
	private Button versDroite;
	private Button retireTout;
	private Button ajouteTout;

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(TP2App.class.getResource("Tp2.fxml"));
		contenu = fxmlLoader.load();
		Scene scene = new Scene(contenu);
		extraitIds(scene);

		prepareMenus((MenuBar) scene.lookup("#menus"));
		prepareListe();
		prepareBoutons();
		prepareFermeture(stage);

		stage.setTitle("Gestion de groupe");
		stage.setScene(scene);
		stage.setWidth(800);
		stage.setHeight(400);
		stage.show();
	}

	/** Prépare la fenêtre pour demander confirmation avant fermeture */
	private void prepareFermeture(Stage stage) {
		stage.setOnCloseRequest(event -> {
			// TODO confirmer ou consommer l'événement
		});
	}

	/** Prépare les actions des boutons */
	private void prepareBoutons() {
		ajouteTout.setOnAction(this::onAjouteTout);
		retireTout.setOnAction(this::onRetireTout);
		// TODO actions des deux boutons centraux
	}

	/**
	 * Ajoute tous les éléments de gauche dans la liste de droite Active le bouton
	 * "Retirer tout" et désactive le bouton "Ajouter tout"
	 */
	private void onAjouteTout(ActionEvent actionEvent) {
		droite.getItems().addAll(gauche.getItems());
		gauche.getItems().clear();
		// TODO active/désactive les boutons
		ajouteTout.setDisable(true);
		retireTout.setDisable(false);
	}

	/**
	 * Ajoute tous les éléments de droite dans la liste de gauche Active le bouton
	 * "Ajouter tout" et désactive le bouton "Retirer tout"
	 */
	private void onRetireTout(ActionEvent actionEvent) {
		// TODO
		gauche.getItems().addAll(droite.getItems());
		droite.getItems().clear();
		// TODO active/désactive les boutons
		retireTout.setDisable(true);
		ajouteTout.setDisable(false);
	}

	/** Prépare les menus et leurs événements */
	private void prepareMenus(MenuBar menus) {
		// TODO Remplir la barre de menus
		ObservableList<Menu> menu = menus.getMenus();
		menu.addAll(new Menu("_Fichiers"), new Menu("_Aide"));
		
		menu.get(0).getItems().add(new MenuItem("Exit"));
		menu.get(1).getItems().add(new MenuItem("About"));
		// Ajout des Events Listener
		menu.get(0).getItems().get(0).addEventHandler(ActionEvent.ACTION, (gestion) -> {
			Platform.exit();
		});
		menu.get(1).getItems().get(0).addEventHandler(ActionEvent.ACTION, (gestion) -> {
			Alert about = new Alert(AlertType.NONE);
			about.getButtonTypes().add(new ButtonType("Done"));
			about.setContentText("Application developped by Julien Sailly");
			about.setTitle("About");
			about.show();
		});
	}
	
	

	/**
	 * Remplit la liste de gauche avec des valeurs Active le bouton "Ajouter tout"
	 */
	private void prepareListe() {
		gauche.getItems().addAll("Alenso Lopes", 
				"Guillaume Bergerot", 
				"Victor Friboulet",
				"Julien Ait Azzouzene",
				"Julien Sailly",
				"Clément Baratin",
				"Cyprien de la Poëze d'Harambure",
				"Gabrielle Harang",
				"Guilhem Saint-Gaudin",
				"Andgel Brissaud");
		// TODO active le bouton "Ajouter tout"
		ajouteTout.setDisable(false);
	}

	private void extraitIds(Scene scene) {
		gauche = (ListView<String>) scene.lookup("#gauche");
		droite = (ListView<String>) scene.lookup("#droite");
		versGauche = (Button) scene.lookup("#versGauche");
		versDroite = (Button) scene.lookup("#versDroite");
		retireTout = (Button) scene.lookup("#retireTout");
		ajouteTout = (Button) scene.lookup("#ajouteTout");
	}

	public static void main(String[] args) {
		launch();
	}
}
