package controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import iut.gon.modele.Dessin;
import iut.gon.modele.Figure;
import iut.gon.modele.Trace;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.converter.NumberStringConverter;

public class Controleur implements Initializable{
	
	public Figure trace;
	
	public SimpleDoubleProperty prevX;
	public SimpleDoubleProperty prevY;
	
	public SimpleObjectProperty<Color> couleur;
	public SimpleIntegerProperty epaisseur;

	private @FXML CouleursController couleursController;
	private @FXML DessinController dessinController;
	private @FXML MenusController menusController;
	private @FXML StatutController statutController;

	public int index;
	
	
	private Dessin dessin;
	
	public Controleur(Dessin dessin) {
		this.dessin = dessin;
		
		prevX = new SimpleDoubleProperty();
		prevY = new SimpleDoubleProperty();
		couleur = new SimpleObjectProperty<Color>(Color.BLACK);
		epaisseur = new SimpleIntegerProperty(1);
	}
	
	
	public void initialize(URL url, ResourceBundle ressourceBundle) {
		
		couleursController.setControleur(this);
		dessinController.setControleur(this);
		menusController.setControleur(this);
		statutController.setControleur(this);
		
		dessinController.centralCanva.widthProperty().bind(dessinController.central_pane.widthProperty());
		dessinController.centralCanva.heightProperty().bind(dessinController.central_pane.heightProperty());
		dessinController.centralCanva.widthProperty().addListener(obs -> reDraw());
		dessinController.centralCanva.heightProperty().addListener(obs -> reDraw());
		Bindings.bindBidirectional(statutController.XlabelValue.textProperty(), prevX, new NumberStringConverter());
		Bindings.bindBidirectional(statutController.YlabelValue.textProperty(), prevY, new NumberStringConverter());
		
		index = -1;
		prevX.set(0);
		prevY.set(0);
	}
	
	/**
	 * Fonction permettant de redessinner le canvas lors du redimensionnement de la fenêtre et donc du canvas.
	 */
	public void reDraw() {
		// Efface le canva afin d'éviter de superposer les traits.
		dessinController.centralCanva.getGraphicsContext2D().clearRect(0, 0, dessinController.centralCanva.getWidth(), dessinController.centralCanva.getHeight()); 

		for (Figure trace : dessin.getFigures()) {
			prevX.set(trace.getPoints().get(0).getX());
			prevY.set(trace.getPoints().get(0).getY());

			for (int i = 1; i < trace.getPoints().size(); i++) {
				dessinController.centralCanva.getGraphicsContext2D().strokeLine(prevX.get(), prevY.get(),
						trace.getPoints().get(i).getX(), trace.getPoints().get(i).getY());
				prevX.set(trace.getPoints().get(i).getX());
				prevY.set(trace.getPoints().get(i).getY());
			}
		}
	}
	
	/**
	 * Fonction appelée lors du click du canvas.
	 * Elle permet de commencer un tracé.
	 * 
	 * @param evt Utilisé pour récupérer les coordonnées de la souris.
	 */
	public void onMousePressed(MouseEvent evt) {
		prevX.set(evt.getX());
		prevY.set(evt.getY());
		index++;
		dessin.getFigures().add(new Trace(1, "noir", evt.getX(), evt.getY()));
	}
	

	
	/**
	 * Fonction appelée lors d'un click-déplacement de la souris sur le canvas.
	 * Elle permet de dessiner des liaisons entre les points cliqués.
	 * 
	 * @param evt Utilisé pour récupérer les coordonnées de la souris.
	 */
	public void onMouseDragged(MouseEvent evt) {
		dessinController.centralCanva.getGraphicsContext2D().strokeLine(prevX.get(), prevY.get(), evt.getX(), evt.getY());
		prevX.set(evt.getX());
		prevY.set(evt.getY());
		dessin.getFigures().get(index).addPoint(evt.getX(), evt.getY());
	}

	/**
	 * Fonction permettant d'afficher les coordonnées de la souris dans les Labels situés sous le canvas.
	 * 
	 * @param evt Utilisé pour récupérer les coordonnées de la souris.
	 */
	public void onMouseMoved(MouseEvent evt) {
		// On teste la longueur des coordonnées pour éviter les problèmes d'affichage
		statutController.XlabelValue.textProperty()
				.set(Double.toString(evt.getX()).length() > 5 ? 
						Double.toString(evt.getX()).substring(0, 5)
						: Double.toString(evt.getX()));
		statutController.YlabelValue.textProperty()
		.set(Double.toString(evt.getY()).length() > 5 ? 
				Double.toString(evt.getY()).substring(0, 5)
				: Double.toString(evt.getY()));
	}
}
