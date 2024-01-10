/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartappclient;

import bean.CartBeanRemote;
import javax.ejb.EJB;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import java.util.List;
import javafx.collections.FXCollections;

/**
 *
 * @author nasif
 */
public class Main extends Application {

    @EJB
    private static CartBeanRemote cartBean;

    public void start(Stage stage) {
        //Setting the label for combobox
        Label lbl1 = new Label("Select An Item:");
        
        //Setting the label for Order Summary
        Label lbl2 = new Label();
        
        //Setting the label for list of items
        Label lbl3 = new Label();
        
        ObservableList<String> itemList = FXCollections.observableArrayList(
                "Apple", "Banana", "Orange", "Peach", "Pear", "Plum", "Strawberry", "Broccoli", "Beans", "Carrot", "Corn", "Lettuce", "Okra", "Peas");
        ComboBox inventory = new ComboBox(itemList);

        //Creating Buttons
        Button button1 = new Button("Add to Cart");
        Button button2 = new Button("Place Order");
        Button button3 = new Button("Exit");

        button1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Object currentItem = inventory.getValue();
                if (!(currentItem == null)) {
                    cartBean.addItem("" + inventory.getValue());
                }
            }
        });

        button2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                List<String> cart = cartBean.placeOrder();
                if (!cart.isEmpty()) {
                    lbl2.setText("Your Order Summary:");
                    String items = "";
                    for (String item : cart) {
                        items += ("" + item + "\n");
                    }
                    lbl3.setText(items);
                    cartBean.clearCart();
                } else {
                    lbl2.setText("Your cart is empty!");
                    lbl3.setText("");
                }
            }
        });

        button3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                cartBean.remove();
                Platform.exit();
            }
        });

        GridPane gridPane = new GridPane();      //Setting size for the pane  
        gridPane.setMinSize(400, 200);

        //Setting the padding  
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        //Setting the Grid alignment 
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(lbl1, 0, 0);
        gridPane.add(lbl2, 0, 1);
        gridPane.add(lbl3, 0, 2);
        gridPane.add(inventory, 1, 0);
        gridPane.add(button1, 2, 0);
        gridPane.add(button2, 2, 1);
        gridPane.add(button3, 2, 2);
        //Creating a scene object
        //Scene scene = new Scene(new Group(hbox), 595, 280, Color.BEIGE);
        Scene scene = new Scene(gridPane);
        stage.setTitle("Shopping Cart");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }
}
