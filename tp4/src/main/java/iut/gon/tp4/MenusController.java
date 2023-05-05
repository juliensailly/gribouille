package iut.gon.tp4;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.GridPane;

public class MenusController {
	private GrilleModel grilleModel;
	private Scores scores;
	@FXML MenuBar menu;
	
	public void setParams(GrilleModel gm, Scores s) {
		this.grilleModel = gm;
		this.scores = s;
	}
	
	@FXML
	public void onMenuNouvelle(ActionEvent evt) {
		grilleModel.nouvellePartie();
	}

	@FXML
	public void onMenuTable(ActionEvent evt) throws IOException {
		// TODO appeler la table des scores
		FXMLLoader fxmlLoader = new FXMLLoader(Morpion.class.getResource("table.fxml"));
		menu.getScene().setRoot(fxmlLoader.load());
		TableController tctrl = fxmlLoader.getController();
		tctrl.setScores(scores);
	}

	@FXML
	public void onMenuQuitter(ActionEvent evt) {
		Platform.exit();
	}
}
