/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playersList;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import playersList.playersListCell.*;

/**
 * FXML Controller class
 *
 * @author 20106
 */
public class PlayerListController implements Initializable {

    /**
     * Initializes the controller class.
     */@FXML
    private ListView<PlayersData> playerList;
      private ObservableList<PlayersData> playersObservableList; 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        playersObservableList =FXCollections.observableArrayList();
        playersObservableList.addAll(
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
