package outils;

import controleurs.Controleur;
import iut.gon.modele.Trace;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.MouseEvent;

public class OutilCrayon extends Outils{
	public SimpleDoubleProperty prevX;
	public SimpleDoubleProperty prevY;
	public int index;
	
	public OutilCrayon(Controleur controleur) {
		super(controleur);
		index = -1;
		prevX = new SimpleDoubleProperty();
		prevY = new SimpleDoubleProperty();
		prevX.set(0);
		prevY.set(0);
	}
	
	/**
	 * Fonction appelée lors du click du canvas. Elle permet de commencer un tracé.
	 * 
	 * @param evt Utilisé pour récupérer les coordonnées de la souris.
	 */
	public void onMousePress(MouseEvent evt) {
		prevX.set(evt.getX());
		prevY.set(evt.getY());
		index++;
		super.controleur.dessin.getFigures().add(new Trace(1, "noir", evt.getX(), evt.getY()));
	}

	/**
	 * Fonction appelée lors d'un click-déplacement de la souris sur le canvas. Elle
	 * permet de dessiner des liaisons entre les points cliqués.
	 * 
	 * @param evt Utilisé pour récupérer les coordonnées de la souris.
	 */
	public void onMouseDrag(MouseEvent evt) {
		super.controleur.dessinController.centralCanva.getGraphicsContext2D().strokeLine(prevX.get(), prevY.get(), evt.getX(),
				evt.getY());
		prevX.set(evt.getX());
		prevY.set(evt.getY());
		super.controleur.dessin.getFigures().get(index).addPoint(evt.getX(), evt.getY());
	}
}
