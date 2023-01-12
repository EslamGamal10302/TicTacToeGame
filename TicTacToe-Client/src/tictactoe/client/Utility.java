/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Dell
 */
public class Utility {

    //Utility.changeTOScene(getClass(), event, "tryscenetwo.fxml");
    public static void changeTOScene(Class aClass, Event aEvent, String sceneFile)  {
        Platform.runLater(() -> {
            try {
                Parent root = FXMLLoader.load(aClass.getResource(sceneFile));
                
                Scene scene = new Scene(root);
                
                Stage stage = (Stage) ((Node) aEvent.getSource()).getScene().getWindow();
                
                stage.setScene(scene);
                
                stage.show();
            } catch (Exception ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

}
