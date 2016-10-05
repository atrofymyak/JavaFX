package com.tas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class FXMLExampleController {
    @FXML private Text actiontarget;
    
    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
    	String text = ((javafx.scene.control.TextField)((javafx.scene.layout.GridPane)((javafx.scene.control.Button)event.getSource()).getParent().getParent()).getChildren().get(2)).getText();
        actiontarget.setText(text);
    }

    
    @FXML protected void newWindow(ActionEvent event) {
    	StackPane root = new StackPane();
    	Stage stage = new Stage();
        stage.setTitle("My New Stage Title");
        stage.setScene(new Scene(root, 450, 450));
        stage.show();
    }
}
