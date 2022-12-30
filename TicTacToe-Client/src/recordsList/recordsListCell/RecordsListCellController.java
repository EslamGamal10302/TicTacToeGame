/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recordsList.recordsListCell;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import playersList.playersListCell.PlayersData;

/**
 * FXML Controller class
 *
 * @author 20106
 */
public class RecordsListCellController  extends ListCell<OldGames> {

    @FXML
    private Label player1;

    @FXML
    private Label player2;

    @FXML
    private Label winner;

    public RecordsListCellController() {
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RecordsListCellFMLX.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void updateItem(OldGames game, boolean empty) {
        super.updateItem(game, empty);

        if(empty || game == null) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
        else {
            player1.setText(game.getPlayer1());
            player2.setText(game.getPlayer2());
            winner.setText(game.getWinner());

            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
      
    
}
