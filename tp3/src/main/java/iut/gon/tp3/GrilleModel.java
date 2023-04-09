package iut.gon.tp3;

import javafx.beans.property.SimpleStringProperty;

public class GrilleModel {
	SimpleStringProperty[][] tableau = new SimpleStringProperty[3][3];
	
	public GrilleModel() {
		int i, j, count = 0;
		for(i = 0; i < tableau.length; i++) {
			for(j = 0; j < tableau.length; j++) {
				tableau[i][j] = new SimpleStringProperty();
				tableau[i][j].set("Case n°"+count+"\nL"+i+"C"+j); 
				count++;
			}
		}
	}
	
	@SuppressWarnings("exports")
	public SimpleStringProperty getCase(int lg, int col) {
		return tableau[lg][col];
	}
	
	public void setCase(String text, int lg, int col) {
		tableau[lg][col].set(text);
	}
}
