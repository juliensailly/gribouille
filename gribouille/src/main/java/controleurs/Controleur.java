package controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import iut.gon.modele.Dessin;
import iut.gon.modele.Figure;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;

public class Controleur {
	public final Dessin dessin = new Dessin();
	
	public Figure trace;
	
	public SimpleDoubleProperty prevX;
	public SimpleDoubleProperty prevY;
	
	public SimpleObjectProperty<Color> couleur;
	public SimpleIntegerProperty epaisseur;

	private @FXML CouleursController couleursController;
	private @FXML DessinController dessinController;
	private @FXML MenusController menusController;
	private @FXML StatutController statutController;
	
	public void initialize(URL url, ResourceBundle ressourceBundle) {
		prevX = new SimpleDoubleProperty();
		prevY = new SimpleDoubleProperty();
		couleur = new SimpleObjectProperty<Color>(Color.BLACK);
		epaisseur = new SimpleIntegerProperty(1);
		
		couleursController.setControleur(this);
		dessinController.setControleur(this);
		menusController.setControleur(this);
		statutController.setControleur(this);
	}
}
