/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.client;

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
    public static void changeTOScene(Class aClass,Event aEvent , String sceneFile ) throws Exception{
        Parent root = FXMLLoader.load(aClass.getResource(sceneFile));
        System.out.println("1");
        Scene scene = new Scene(root);
        System.out.println("2");
        Stage stage =(Stage) ((Node) aEvent.getSource()).getScene().getWindow();
        System.out.println("3");
        stage.setScene(scene);
        System.out.println("4");
        stage.show();
        System.out.println("5");
   }
    
    
    
    
    
    
    
}
