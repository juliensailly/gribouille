package iut.gon.gribouille;

import java.net.URL;
import java.util.ResourceBundle;

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

	public void initialize(URL url, ResourceBundle ressourceBundle) {
		centralCanva.widthProperty().bind(central_pane.widthProperty());
		centralCanva.heightProperty().bind(central_pane.heightProperty());
	}
	
	public void onMousePressed(MouseEvent evt) {
		this.prevX = evt.getX();
		this.prevY = evt.getY();
	}
	
	public void onMouseDragged(MouseEvent evt) {
		centralCanva.getGraphicsContext2D().strokeLine(prevX, prevY, evt.getX(), evt.getY());
		this.prevX = evt.getX();
		this.prevY = evt.getY();
	}
}
