/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playersList.playersListCell;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

/**
 * FXML Controller class
 *
 * @author 20106
 */
public class PlayerCellController extends ListCell<PlayersData> {

    @FXML
    private Label playerName;

    @FXML
    private Label playerScore;

    @FXML
    private Label playerStat;

    public PlayerCellController() {
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerCellFXML.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void updateItem(PlayersData Player, boolean empty) {
        super.updateItem(Player, empty);

        if(empty || Player == null) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
        else {
            playerName.setText(Player.getPlayerName());
            playerScore.setText(Player.getPlayerScore());
            playerStat.setText(Player.getPlayerStat());

            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
      
    
}
