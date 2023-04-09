package iut.gon.tp3;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

public class GrilleController implements Initializable {
	@FXML
	GridPane grille;
	Label labels [][] = new Label[3][3];
	GrilleModel gm;
	
	public void initialize(URL url, ResourceBundle ressourceBundle) {
		grille.setStyle("-fx-background-color: seashell");
		for (int i = 0; i < grille.getRowCount(); i++) {
			for (int j = 0; j < grille.getColumnCount(); j++) {
				Label l = new Label();
				l.setAlignment(Pos.CENTER);
				l.setTextAlignment(TextAlignment.CENTER);
				l.setText(gm.getCase(i, j));
				l.setMaxSize(1000, 1000);
				GridPane.setHalignment(l, HPos.CENTER);
				l.addEventHandler(MouseEvent.MOUSE_CLICKED, (click) -> setLabelBonjour(l));
				grille.add(l, j, i);
				labels[j][i] = l;
			}
		}
	}

	public void setLabelBonjour(Label l) {
		if (l.getText() == "Bonjour") {
			gm.setCase("Case n°"+(GridPane.getRowIndex(l)*3+GridPane.getColumnIndex(l))
					+"\nL"+GridPane.getRowIndex(l)+"C"+GridPane.getColumnIndex(l), 
					GridPane.getRowIndex(l), GridPane.getColumnIndex(l));
			l.setText("Case n°"+(GridPane.getRowIndex(l)*3+GridPane.getColumnIndex(l))
					+"\nL"+GridPane.getRowIndex(l)+"C"+GridPane.getColumnIndex(l));
		} else {
			gm.setCase("Bonjour", GridPane.getRowIndex(l), GridPane.getColumnIndex(l));
			l.setText("Bonjour");
		}
	}
	
	public GrilleController(GrilleModel gm) {
		this.gm = gm;
	}
}
