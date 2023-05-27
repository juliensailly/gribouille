package fr.iutgon.tp6.modele;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.paint.Color;

public class MonetaryType extends TableCell<MonetaryType, Number> {

    public MonetaryType() {
        super();
    }
    @Override
    public void updateItem(Number item, boolean empty) {
        super.updateItem(item, empty);
        this.setAlignment(Pos.CENTER_RIGHT);
        if (empty || item == null) {
            setGraphic(null);
            setText(null);
        } else {
            setText(String.format("%.2f €", item.doubleValue()));
            if (item.doubleValue() < 0) {
                setTextFill(Color.RED);
            }
        }
    }
}
