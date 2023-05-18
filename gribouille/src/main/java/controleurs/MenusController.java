package controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

public class MenusController implements Initializable {
	@FXML
	public ToggleGroup epaisseur_group;
	@FXML
	public ToggleGroup outilGroupe;
	@FXML
	public RadioMenuItem crayon;
	@FXML
	public RadioMenuItem etoile;
	
	Controleur controleur;

	public void setControleur(Controleur c) {
		this.controleur = c;
	}
	
	@FXML
	public void onQuitte() {
		controleur.onQuitter();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		crayon.setSelected(true);
		outilGroupe.selectedToggleProperty().addListener(obs -> {
			if (crayon.isSelected()) {
				controleur.onCrayon();
			} else if (etoile.isSelected()) {
				controleur.onEtoile();
			}
		});

		epaisseur_group.selectedToggleProperty().addListener(obs -> {
			RadioMenuItem rmi = (RadioMenuItem) epaisseur_group.getSelectedToggle();
			controleur.setEpaisseur(rmi.getText());
		});
	}
}
