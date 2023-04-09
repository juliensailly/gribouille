package iut.gon.tp3;

public class GrilleModel {
	String[][] tableau = new String[3][3];
	
	public GrilleModel() {
		int i, j, count = 0;
		for(i = 0; i < tableau.length; i++) {
			for(j = 0; j < tableau.length; j++) {
				tableau[i][j] = "Case n°"+count+"\nL"+i+"C"+j;
				count++;
			}
		}
	}
	
	public String getCase(int lg, int col) {
		return tableau[lg][col];
	}
	
	public void setCase(String text, int lg, int col) {
		tableau[lg][col] = text;
	}
}
