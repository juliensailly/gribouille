package fr.iutgon.tp6.modele;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.text.NumberFormat;
import java.util.Locale;

public class TypeMonetaire<T> extends TableCell<T, Number> {

    public TypeMonetaire() {
        super();
    }
    @Override
    public void updateItem(Number item, boolean empty) {
        super.updateItem(item, empty);
        this.setTextAlignment(TextAlignment.RIGHT);
        if (empty || item == null) {
            setGraphic(null);
            setText(null);
        } else {
            setText(String.format(NumberFormat.getCurrencyInstance(Locale.FRANCE).format(item)));
            if(item.floatValue() < 0) {
                setTextFill(Color.RED);
            }
        }
    }
}
