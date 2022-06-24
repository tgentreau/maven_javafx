package org.article;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.article.bll.BLLExeption;
import org.article.bll.ProduitManager;
import org.article.bo.Glace;
import org.article.bo.Pain;
import org.article.bo.Produit;
import org.article.bo.Stylo;
import org.article.dal.DALException;
import org.w3c.dom.Text;

import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;


/**
 * JavaFX App
 */
public class App extends Application {
    private List<Produit> produits = new ArrayList<>();
    private Scene scene = null;
    private VBox vBox = null;
    private Pain pain = null;
    private Glace glace = null;
    private Stylo stylo = null;
    private ProduitManager instance = null;
    @Override
    public void start(Stage stage) throws BLLExeption {
        //Defaut
        stage.setTitle("Produits");

        var labelReference = new Label("Reférence:");
        instance = ProduitManager.getInstance();
        Long refProd = null;
        for (Produit produit : instance.getProduits()) {
            refProd = produit.getRefProd();
        }
        TextField textReference = new TextField(String.valueOf(refProd + 1));
        textReference.setDisable(true);
        Label labelLibelle = new Label("Libellé:");
        TextField textLibelle = new TextField();
        Label labelMarque = new Label("Marque:");
        TextField textMarque = new TextField();
        Label labelPrix = new Label("Prix:");
        TextField textPrix = new TextField();
        Label labelQuantite = new Label("Quantité:");
        TextField textQuantite = new TextField();
        Label labelType = new Label("Type de produit:");
        System.out.println();
        ChoiceBox choiceBox= new ChoiceBox(FXCollections.observableArrayList("Pain", "Glace", "Stylo", "Carte Postale"));
        //Pain
        Label labelPoids = new Label("Poids:");
        TextField textPoids = new TextField();
        Label labelDate = new Label("Date de péremption:");
        DatePicker textDate = new DatePicker();
        textDate.setValue(LocalDate.now().plusDays(2));
        textDate.setDisable(true);
        //Glace
        Label labelGlace = new Label("Parfum:");
        TextField textGlace = new TextField();
        Label labelDateGlace = new Label("Date de péremption:");
        DatePicker textDateGlace = new DatePicker();
        Label labelTemperature = new Label("Température de conservation:");
        TextField textTemperature = new TextField();
        //Stylo
        Label labelColor = new Label("Couleur:");
        ColorPicker colorPicker = new ColorPicker();
        final ToggleGroup group = new ToggleGroup();
        Label labelMine = new Label("Type de mine:");
        RadioButton radioButton1 = new RadioButton("crayon à papier");
        RadioButton radioButton2 = new RadioButton("crayon à bille");
        RadioButton radioButton3 = new RadioButton("feutre");
        radioButton1.setToggleGroup(group);
        radioButton2.setToggleGroup(group);
        radioButton3.setToggleGroup(group);
        radioButton1.setSelected(true);
        //Carte Postale
        final ToggleGroup group2 = new ToggleGroup();
        Label labelCarte = new Label("Type de carte postale:");
        RadioButton radioButton4 = new RadioButton("Portrait");
        RadioButton radioButton5 = new RadioButton("Paysage");
        RadioButton radioButton6 = new RadioButton("Monuments");
        radioButton4.setToggleGroup(group);
        radioButton5.setToggleGroup(group);
        radioButton6.setToggleGroup(group);
        radioButton4.setSelected(true);
        Button buttonPrec = new Button("Précédent");
        Button buttonNouveau = new Button("Nouveau");
        Button buttonRec = new Button("Enregistrer");
        buttonRec.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(choiceBox.getValue().equals("Pain")) {
                    try {
                        pain = new Pain(textLibelle.getText(), textMarque.getText(), Integer.parseInt(textPoids.getText()) , Long.parseLong(textQuantite.getText()) , Float.parseFloat(textPrix.getText()));
                        instance.addProduit(pain);
                    } catch (BLLExeption e) {
                        throw new RuntimeException(e);
                    }
                } else if(choiceBox.getValue().equals("Glace")) {
                    try {
                        glace = new Glace(textDateGlace.getValue(), textMarque.getText(), textLibelle.getText(), Integer.parseInt(textTemperature.getText()), textGlace.getText() , Long.parseLong(textQuantite.getText()) , Float.parseFloat(textPrix.getText()));
                        instance.addProduit(glace);
                    } catch (BLLExeption e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        Button buttonSupprimer = new Button("Supprimer");
        Button buttonSuivant = new Button("Suivant");
        HBox buttons = new HBox(buttonPrec, buttonNouveau, buttonRec, buttonSupprimer, buttonSuivant);
        buttons.setSpacing(10);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(choiceBox.getValue().equals("Pain")) {
                    vBox = new VBox(labelReference, textReference, labelLibelle, textLibelle, labelMarque, textMarque, labelPrix, textPrix, labelQuantite, textQuantite,labelType, choiceBox,labelPoids, textPoids, labelDate, textDate, buttons);
                    IHM(vBox, scene, stage);
                } else if(choiceBox.getValue().equals("Glace")) {
                    vBox = new VBox(labelReference, textReference, labelLibelle, textLibelle, labelMarque, textMarque, labelPrix, textPrix, labelQuantite, textQuantite,labelType, choiceBox,labelGlace, textGlace, labelDateGlace, textDateGlace, labelTemperature, textTemperature, buttons);
                    IHM(vBox, scene, stage);
                } else if(choiceBox.getValue().equals("Stylo")) {
                    vBox = new VBox(labelReference, textReference, labelLibelle, textLibelle, labelMarque, textMarque, labelPrix, textPrix, labelQuantite, textQuantite,labelType, choiceBox, labelColor, colorPicker, labelMine, radioButton1, radioButton2, radioButton3, buttons);
                    IHM(vBox, scene, stage);
                } else if(choiceBox.getValue().equals("Carte Postale")) {
                    vBox = new VBox(labelReference, textReference, labelLibelle, textLibelle, labelMarque, textMarque, labelPrix, textPrix, labelQuantite, textQuantite,labelType, choiceBox, labelCarte, radioButton4, radioButton5, radioButton6, buttons);
                    IHM(vBox, scene, stage);
                }
            }
        };

        choiceBox.setOnAction(event);
        choiceBox.setValue("Pain");

    }

    public void IHM(VBox vbox, Scene scene, Stage stage) {
        scene = new Scene(vBox, 640, 480);
        vBox.setSpacing(4);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}