package iut.gon.tp3;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class GrilleController implements Initializable {
	@FXML
	GridPane grille;
	Label labels [][] = new Label[3][3];
	
	public void initialize(URL url, ResourceBundle ressourceBundle) {
		grille.setStyle("-fx-background-color: seashell");
		for (int i = 0; i < grille.getRowCount(); i++) {
			for (int j = 0; j < grille.getColumnCount(); j++) {
				Label l = new Label();
				l.setAlignment(Pos.CENTER);
				l.setText(String.format("L%dC%d", i, j));
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
			l.setText(String.format("L%dC%d", GridPane.getRowIndex(l), GridPane.getColumnIndex(l)));
		} else {
			l.setText("Bonjour");
		}
	}
}
