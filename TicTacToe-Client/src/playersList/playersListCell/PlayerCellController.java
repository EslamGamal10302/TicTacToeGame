/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playersList.playersListCell;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import login.SocketClient;
import model.Player;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author 20106
 */
public class PlayerCellController extends ListCell<Player> {

    @FXML
    private Label playerName;

    @FXML
    private Label playerScore;

    @FXML
    private Label playerStat;
    @FXML
    private Button challengeButton;

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
    protected void updateItem(Player player, boolean empty) {
        super.updateItem(player, empty);

        if(empty || player == null) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
        else {
            playerName.setText(player.getUsername());
            playerScore.setText(Integer.toString(player.getScore()));
            switch (player.getStates()) {
                case Player.AVAILBLE:
                    challengeButton.setStyle("-fx-background-color: (255,255,102); ");
                    challengeButton.setDisable(false);
                    playerStat.setText("Online");
                    break;
                case Player.OFFLINE:
                    challengeButton.setStyle("-fx-background-color: #ff0000; ");
                    challengeButton.setDisable(true);
                    playerStat.setText("Offlin");
                    break;
                case Player.INGAME:
                    challengeButton.setStyle("-fx-background-color: #808080; ");
                    challengeButton.setDisable(true);
                    playerStat.setText("In game");
                    break;
            }
            challengeButton.setOnAction((event) -> {
                PrintStream serverDataOutput;
                try {
                        serverDataOutput = new PrintStream(SocketClient.getInstant().getSocket().getOutputStream());
                        JSONObject challengeJson= new JSONObject();
                        challengeJson.put("type", 1);
                        challengeJson.put("userName", player.getUsername());
                        serverDataOutput.println(challengeJson.toString());
                } catch (IOException ex) {
                    Logger.getLogger(PlayerCellController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
      
    
}
