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
	Label labels[][] = new Label[3][3];
	GrilleModel gm;

	public void initialize(URL url, ResourceBundle ressourceBundle) {
		grille.setStyle("-fx-background-color: seashell");
		int count = 0;
		
		for (int i = 0; i < grille.getRowCount(); i++) {
			for (int j = 0; j < grille.getColumnCount(); j++) {
				Label l = new Label();
				
				l.setAlignment(Pos.CENTER);
				l.setTextAlignment(TextAlignment.CENTER);
				l.setMaxSize(1000, 1000);
				GridPane.setHalignment(l, HPos.CENTER);
				
				gm.getCase(i, j).bindBidirectional(l.textProperty());
				gm.getCase(i, j).set("Case n°" + count + "\nL" + i + "C" + j);
				
				l.addEventHandler(MouseEvent.MOUSE_CLICKED, (click) -> setLabelBonjour(l));
				
				grille.add(l, j, i);
				labels[j][i] = l;
				count++;
			}
		}
	}

	public void setLabelBonjour(@SuppressWarnings("exports") Label l) {
		int row = GridPane.getRowIndex(l), col = GridPane.getColumnIndex(l);
		
		if (l.getText() == "Bonjour") {
			gm.setCase("Case n°" + (row * 3 + col) + "\nL" + row + "C" + col, row, col); // Example : Case n°1\nL0C1

			l.setText("Case n°" + (row * 3 + col) + "\nL" + row + "C" + col);
		} else {
			gm.setCase("Bonjour", row, col);

			l.setText("Bonjour");
		}
	}

	public GrilleController(GrilleModel gm) {
		this.gm = gm;
	}
}
