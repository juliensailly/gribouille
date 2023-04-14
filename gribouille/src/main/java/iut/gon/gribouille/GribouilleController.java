package iut.gon.gribouille;

import java.net.URL;
import java.util.ResourceBundle;

import iut.gon.modele.Dessin;
import iut.gon.modele.Figure;
import iut.gon.modele.Trace;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.converter.NumberStringConverter;

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

	private SimpleDoubleProperty prevX = new SimpleDoubleProperty();
	private SimpleDoubleProperty prevY = new SimpleDoubleProperty();

	private Dessin dessin;
	private static int index;

	public void initialize(URL url, ResourceBundle ressourceBundle) {
		centralCanva.widthProperty().bind(central_pane.widthProperty());
		centralCanva.heightProperty().bind(central_pane.heightProperty());
		centralCanva.widthProperty().addListener(obs -> reDraw());
		centralCanva.heightProperty().addListener(obs -> reDraw());
		Bindings.bindBidirectional(XlabelValue.textProperty(), prevX, new NumberStringConverter());
		Bindings.bindBidirectional(YlabelValue.textProperty(), prevY, new NumberStringConverter());
	}

	private void reDraw() {
		// Permet de conserver des traits fins
		centralCanva.getGraphicsContext2D().clearRect(0, 0, centralCanva.getWidth(), centralCanva.getHeight()); 

		for (Figure trace : dessin.getFigures()) {
			prevX.set(trace.getPoints().get(0).getX());
			prevY.set(trace.getPoints().get(0).getY());

			for (int i = 1; i < trace.getPoints().size(); i++) {
				centralCanva.getGraphicsContext2D().strokeLine(prevX.get(), prevY.get(),
						trace.getPoints().get(i).getX(), trace.getPoints().get(i).getY());
				prevX.set(trace.getPoints().get(i).getX());
				prevY.set(trace.getPoints().get(i).getY());
			}
		}
	}

	public void onMousePressed(MouseEvent evt) {
		this.prevX.set(evt.getX());
		this.prevY.set(evt.getY());
		index++;
		dessin.getFigures().add(new Trace(1, "noir", evt.getX(), evt.getY()));
	}

	public void onMouseDragged(MouseEvent evt) {
		centralCanva.getGraphicsContext2D().strokeLine(prevX.get(), prevY.get(), evt.getX(), evt.getY());
		this.prevX.set(evt.getX());
		this.prevY.set(evt.getY());
		dessin.getFigures().get(index).addPoint(evt.getX(), evt.getY());
	}

	public void onMouseMoved(MouseEvent evt) {
		// On teste la longueur des coordonnées pour éviter les problèmes d'affichage
		this.XlabelValue.textProperty()
				.set(Double.toString(evt.getX()).length() > 5 ? 
						Double.toString(evt.getX()).substring(0, 5)
						: Double.toString(evt.getX()));
		this.YlabelValue.textProperty()
		.set(Double.toString(evt.getY()).length() > 5 ? 
				Double.toString(evt.getY()).substring(0, 5)
				: Double.toString(evt.getY()));
	}

	public GribouilleController() {
		this.dessin = new Dessin();
		GribouilleController.index = -1;
		prevX.set(0);
		prevY.set(0);
	}

	public GribouilleController(Dessin dessin) {
		this.dessin = dessin;
		GribouilleController.index = -1;
		prevX.set(0);
		prevY.set(0);
	}
}
