/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playersList;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import playersList.playersListCell.*;
import tictactoe.client.Utility;
import welcome.HomeController;

/**
 * FXML Controller class
 *
 * @author 20106
 */
public class PlayerListController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private Button recordsBut;
    @FXML
    private ListView<PlayersData> playerList;
     
     private ObservableList<PlayersData> playersObservableList; 
      
      
       @FXML
    void goToRecords(ActionEvent event) {
        try {
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            Parent  myNewScens = FXMLLoader.load(getClass().getResource("/recordsList/RecordsListFMLX.fxml"));
            Scene scene = new Scene(myNewScens);
            stage.setScene(scene);
            stage.setTitle("My New Scene");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PlayerListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    
    
    @FXML
    void homeButtonAction(ActionEvent event) {
    try {
            Utility.changeTOScene(getClass(), event, "/welcome/home.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        playersObservableList =FXCollections.observableArrayList();
        playersObservableList.addAll(
                new PlayersData("ahmed", "200", "Online"),
                new PlayersData("ali", "50", "inGame"),
                new PlayersData("osama", "100", "Ofline"),
                new PlayersData("ahmed", "200", "Online"),
                new PlayersData("ali", "50", "inGame"),
                new PlayersData("osama", "100", "Ofline")
        );
        playerList.getStylesheets().add(getClass().getResource("listViewcss.css").toString());
        playerList.setCellFactory((param) -> {
            return new PlayerCellController(); //To change body of generated lambdas, choose Tools | Templates.
        });
        playerList.setItems(playersObservableList);
        
    }    
    
}
