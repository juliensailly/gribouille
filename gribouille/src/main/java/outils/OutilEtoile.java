package outils;

import controleurs.Controleur;
import iut.gon.modele.Etoile;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.MouseEvent;

public class OutilEtoile extends Outils {
	private SimpleDoubleProperty centerX;
	private SimpleDoubleProperty centerY;

	public OutilEtoile(Controleur controleur) {
		super(controleur);
		centerX = new SimpleDoubleProperty();
		centerY = new SimpleDoubleProperty();
	}

	@Override
	public void onMousePress(MouseEvent e) {
		centerX.set(e.getX());
		centerY.set(e.getY());
		controleur.etoile = new Etoile(controleur.epaisseur.get(), controleur.couleur.get().toString(), centerX.get(), centerY.get());
		controleur.dessin.addFigure(controleur.etoile);
	}

	@Override
	public void onMouseDrag(MouseEvent e) {
		controleur.dessinController.centralCanva.getGraphicsContext2D().strokeLine(controleur.etoile.getCentre().getX(), controleur.etoile.getCentre().getY(), e.getX(), e.getY());
		controleur.etoile.addPoint(e.getX(), e.getY());

	}

}
