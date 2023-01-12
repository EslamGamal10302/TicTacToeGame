/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challengeDialog;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author 20106
 */
public class ChallengeDialogController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button acceptButton;

    @FXML
    private Button refuseButton;

    @FXML
    private Label playerName;

    @FXML
    void acceptPlaying(ActionEvent event) {

    }

    @FXML
    void refusePlaying(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setUserName(String userName) {
        playerName.setText(userName);
    }

}
