package fr.iutgon.tp6;

import fr.iutgon.tp6.modele.FabriqueProduits;
import fr.iutgon.tp6.modele.Ligne;
import fr.iutgon.tp6.modele.MonetaryType;
import fr.iutgon.tp6.modele.Produit;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberExpression;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.EventListener;
import java.util.Random;
import java.util.ResourceBundle;

public class FactureController implements Initializable {
    public TableView<Ligne> table;
    public TableColumn<Ligne, Integer> qte;
    public TableColumn<Ligne, Produit> produit;
    public TableColumn<Ligne, Number> prixUnitaire;
    public TableColumn<Ligne, Number> totalHT;
    public TableColumn<Ligne, Number> totalTTC;
    public TextField sommeFacture;

    /**
     * Called to initialize a controller after its root element has been completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO préparer la table
        sommeFacture.setText("0.0 €");
    }

    public void onAjouter(ActionEvent actionEvent) {
        //TODO ajouter un produit aléatoire à la table
        Random rd = new Random();
        Ligne ligne = new Ligne(rd.nextInt(500), FabriqueProduits.getProduits().get(rd.nextInt(FabriqueProduits.getProduits().size() - 1)));
        table.getItems().add(ligne);

        qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
        Callback<TableColumn.CellDataFeatures<Ligne, Produit>, ObservableValue<Produit>> callback =
                ligneProduitCellDataFeatures -> {
                    return ligneProduitCellDataFeatures.getValue().produitProperty();
                };
        produit.setCellValueFactory(callback);

        prixUnitaire.setCellValueFactory(ligneProduitCellDataFeatures ->
                ligneProduitCellDataFeatures.getValue().getProduit().prixProperty());
        totalHT.setCellValueFactory(ligneProduitCellDataFeatures ->
                ligneProduitCellDataFeatures.getValue().totalHTProperty());
        totalTTC.setCellValueFactory(ligneProduitCellDataFeatures ->
                ligneProduitCellDataFeatures.getValue().totalTTCProperty());

        qte.setCellFactory(cell -> new TextFieldTableCell<>(new IntegerStringConverter()));
        produit.setCellFactory(cell -> new ChoiceBoxTableCell<>(new StringConverter<Produit>() {
            @Override
            public String toString(Produit object) {
                return object.getNom();
            }

            @Override
            public Produit fromString(String string) {
                for (Ligne ligne : table.getItems()) {
                    if (ligne.getProduit().getNom().equals(string)) {
                        return ligne.getProduit();
                    }
                }
                return null;
            }
        }, FXCollections.observableList(FabriqueProduits.getProduits())));

        // Je n'ai pas réussi à utiliser le Bindings.add()
        ligne.totalTTCProperty().addListener(evt -> updateSum());
        updateSum();
    }

    public void updateSum() {
        double sum = 0;
        for (Ligne line : table.getItems()) {
            sum += line.getTotalTTC().doubleValue();
        }
        sommeFacture.setText(String.format("%.2f", sum) + " €");
    }
}
