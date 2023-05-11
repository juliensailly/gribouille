package outils;

import controleurs.Controleur;
import javafx.scene.input.MouseEvent;

public abstract class Outils {
	protected Controleur controleur;
	
	public Outils(Controleur controleur) {
		this.controleur = controleur;
	}
	
	public abstract void onMousePress(MouseEvent e);
	
	public abstract void onMouseDrag(MouseEvent e);
}
