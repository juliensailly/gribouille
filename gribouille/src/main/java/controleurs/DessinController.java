package controleurs;

import iut.gon.modele.Figure;
import iut.gon.modele.Trace;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class DessinController {
	@FXML
	public Canvas centralCanva;
	@FXML
	public Pane central_pane;
	@FXML
	Controleur controleur;
	
	public int index;

	public void setControleur(Controleur c) {
		this.controleur = c;
	}
	
	/**
	 * Fonction permettant de redessinner le canvas lors du redimensionnement de la fenêtre et donc du canvas.
	 */
	public void reDraw() {
		// Efface le canva afin d'éviter de superposer les traits.
		centralCanva.getGraphicsContext2D().clearRect(0, 0, centralCanva.getWidth(), centralCanva.getHeight()); 

		for (Figure trace : controleur.dessin.getFigures()) {
			controleur.prevX.set(trace.getPoints().get(0).getX());
			controleur.prevY.set(trace.getPoints().get(0).getY());

			for (int i = 1; i < trace.getPoints().size(); i++) {
				centralCanva.getGraphicsContext2D().strokeLine(controleur.prevX.get(), controleur.prevY.get(),
						trace.getPoints().get(i).getX(), trace.getPoints().get(i).getY());
				controleur.prevX.set(trace.getPoints().get(i).getX());
				controleur.prevY.set(trace.getPoints().get(i).getY());
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
		controleur.prevX.set(evt.getX());
		controleur.prevY.set(evt.getY());
		index++;
		controleur.dessin.getFigures().add(new Trace(1, "noir", evt.getX(), evt.getY()));
	}
	
	/**
	 * Fonction appelée lors d'un click-déplacement de la souris sur le canvas.
	 * Elle permet de dessiner des liaisons entre les points cliqués.
	 * 
	 * @param evt Utilisé pour récupérer les coordonnées de la souris.
	 */
	public void onMouseDragged(MouseEvent evt) {
		centralCanva.getGraphicsContext2D().strokeLine(controleur.prevX.get(), controleur.prevY.get(), evt.getX(), evt.getY());
		controleur.prevX.set(evt.getX());
		controleur.prevY.set(evt.getY());
		controleur.dessin.getFigures().get(index).addPoint(evt.getX(), evt.getY());
	}

	/**
	 * Fonction permettant d'afficher les coordonnées de la souris dans les Labels situés sous le canvas.
	 * 
	 * @param evt Utilisé pour récupérer les coordonnées de la souris.
	 */
	public void onMouseMoved(MouseEvent evt) {
		// On teste la longueur des coordonnées pour éviter les problèmes d'affichage
		controleur.statutController.XlabelValue.textProperty()
				.set(Double.toString(evt.getX()).length() > 5 ? 
						Double.toString(evt.getX()).substring(0, 5)
						: Double.toString(evt.getX()));
		controleur.statutController.YlabelValue.textProperty()
		.set(Double.toString(evt.getY()).length() > 5 ? 
				Double.toString(evt.getY()).substring(0, 5)
				: Double.toString(evt.getY()));
	}
}
