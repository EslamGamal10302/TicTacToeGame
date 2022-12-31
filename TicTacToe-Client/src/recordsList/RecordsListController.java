/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recordsList;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import playersList.PlayerListController;
import playersList.playersListCell.PlayerCellController;
import playersList.playersListCell.PlayersData;
import recordsList.recordsListCell.OldGames;
import recordsList.recordsListCell.RecordsListCellController;
import tictactoe.client.Utility;
import welcome.HomeController;

/**
 * FXML Controller class
 *
 * @author 20106
 */
public class RecordsListController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button playerBut;
    @FXML
    private ListView<OldGames> recordsList;
    private ObservableList<OldGames> recordsObservableList; 
    
    
    
    
     
    
    @FXML
    void homeButtonAction(ActionEvent event) {
      try {
            Utility.changeTOScene(getClass(), event, "/welcome/home.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    

    @FXML
    void logoutButtonAction(ActionEvent event) {
    
        
    }
    
    
    
    
    
    
    @FXML
    void goToPlayers(ActionEvent event) {
      try {
            Utility.changeTOScene(getClass(), event, "/playersList/PlayerListFXML.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
        
        
        
        
        /*  try {
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            Parent  myNewScens = FXMLLoader.load(getClass().getResource("/playersList/PlayerListFXML.fxml"));
            Scene scene = new Scene(myNewScens);
            stage.setScene(scene);
            stage.setTitle("My New Scene");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(RecordsListController.class.getName()).log(Level.SEVERE, null, ex);
        }  */
      

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recordsObservableList =FXCollections.observableArrayList();
        recordsObservableList.addAll(
                new OldGames("ahmed", "ali", "player1"),
                new OldGames("ahmed", "ali", "player1"), 
                new OldGames("ahmed", "ali", "player1"),
                new OldGames("ahmed", "ali", "player1")
                 
                 
        );
        recordsList.getStylesheets().add(getClass().getResource("listViewcss.css").toString());
        recordsList.setCellFactory((param) -> {
            return new RecordsListCellController(); //To change body of generated lambdas, choose Tools | Templates.
        });
        recordsList.setItems(recordsObservableList);
        
    }    
    
}