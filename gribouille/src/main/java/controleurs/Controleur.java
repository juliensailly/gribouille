package controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import iut.gon.modele.Dessin;
import iut.gon.modele.Figure;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.util.converter.NumberStringConverter;

public class Controleur {
	public final Dessin dessin = new Dessin();
	
	public Figure trace;
	
	public SimpleDoubleProperty prevX;
	public SimpleDoubleProperty prevY;
	
	public SimpleObjectProperty<Color> couleur;
	public SimpleIntegerProperty epaisseur;

	public @FXML CouleursController couleursController;
	public @FXML DessinController dessinController;
	public @FXML MenusController menusController;
	public @FXML StatutController statutController;
	
	public void initialize(URL url, ResourceBundle ressourceBundle) {
		prevX = new SimpleDoubleProperty();
		prevY = new SimpleDoubleProperty();
		couleur = new SimpleObjectProperty<Color>(Color.BLACK);
		epaisseur = new SimpleIntegerProperty(1);
		
		couleursController.setControleur(this);
		dessinController.setControleur(this);
		menusController.setControleur(this);
		statutController.setControleur(this);
		
		dessinController.centralCanva.widthProperty().bind(dessinController.central_pane.widthProperty());
		dessinController.centralCanva.heightProperty().bind(dessinController.central_pane.heightProperty());
		dessinController.centralCanva.widthProperty().addListener(obs -> dessinController.reDraw());
		dessinController.centralCanva.heightProperty().addListener(obs -> dessinController.reDraw());
		Bindings.bindBidirectional(statutController.XlabelValue.textProperty(), prevX, new NumberStringConverter());
		Bindings.bindBidirectional(statutController.YlabelValue.textProperty(), prevY, new NumberStringConverter());
		
		dessinController.index = -1;
		prevX.set(0);
		prevY.set(0);
	}
}
