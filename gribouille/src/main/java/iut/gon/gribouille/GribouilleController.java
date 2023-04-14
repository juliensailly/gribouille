package iut.gon.gribouille;

import java.net.URL;
import java.util.ResourceBundle;

import iut.gon.modele.Dessin;
import iut.gon.modele.Figure;
import iut.gon.modele.Point;
import iut.gon.modele.Trace;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class GribouilleController implements Initializable {
	@FXML
	private Label XlabelValue;
	@FXML
	private Label YlabelValue;
	@FXML
	private Rectangle black;
	@FXML
	private Rectangle blue;
	@FXML
	private Canvas centralCanva;
	@FXML
	private Pane central_pane;
	@FXML
	private Label colorLabel;
	@FXML
	private Rectangle cyan;
	@FXML
	private ToggleGroup epaisseur_group;
	@FXML
	private Rectangle green;
	@FXML
	private Rectangle magenta;
	@FXML
	private ToggleGroup outil_group;
	@FXML
	private Rectangle red;
	@FXML
	private ColorPicker sideColorPicker;
	@FXML
	private Label thicknessLabelValue;
	@FXML
	private Label toolLabel;
	@FXML
	private Rectangle white;
	@FXML
	private Rectangle yellow;

	private double prevX;
	private double prevY;

	private Dessin dessin;
	private static int index;

	public void initialize(URL url, ResourceBundle ressourceBundle) {
		centralCanva.widthProperty().bind(central_pane.widthProperty());
		centralCanva.heightProperty().bind(central_pane.heightProperty());
		centralCanva.widthProperty().addListener(obs -> reDraw());
		centralCanva.heightProperty().addListener(obs -> reDraw());
	}

	private void reDraw() {
		centralCanva.getGraphicsContext2D().clearRect(0, 0, centralCanva.getWidth(), centralCanva.getHeight()); // Pour conserver des traits fins
		for (Figure trace : dessin.getFigures()) {
			prevX = trace.getPoints().get(0).getX();
			prevY = trace.getPoints().get(0).getY();
			for (int i = 1; i < trace.getPoints().size(); i++) {
				centralCanva.getGraphicsContext2D().strokeLine(prevX, prevY, trace.getPoints().get(i).getX(),
						trace.getPoints().get(i).getY());
				prevX = trace.getPoints().get(i).getX();
				prevY = trace.getPoints().get(i).getY();
			}
		}
	}

	public void onMousePressed(MouseEvent evt) {
		this.prevX = evt.getX();
		this.prevY = evt.getY();
		index++;
		dessin.getFigures().add(new Trace(1, "noir", evt.getX(), evt.getY()));
	}

	public void onMouseDragged(MouseEvent evt) {
		centralCanva.getGraphicsContext2D().strokeLine(prevX, prevY, evt.getX(), evt.getY());
		this.prevX = evt.getX();
		this.prevY = evt.getY();
		dessin.getFigures().get(index).addPoint(evt.getX(), evt.getY());
	}

	public GribouilleController() {
		this.dessin = new Dessin();
		this.index = -1;
	}

	public GribouilleController(Dessin dessin) {
		this.dessin = dessin;
		this.index = -1;
	}
}
