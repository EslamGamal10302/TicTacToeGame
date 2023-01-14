/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameBoard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author Nada
 */
public class ReplayBoardOnlineController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private Button back_button;
    @FXML
    private ImageView position_3;
    @FXML
    private ImageView position_6;
    @FXML
    private ImageView position_1;
    @FXML
    private ImageView position_5;
    @FXML
    private ImageView position_9;
    @FXML
    private ImageView position_8;
    @FXML
    private ImageView position_4;
    @FXML
    private ImageView position_2;
    @FXML
    private ImageView position_7;
    @FXML
    private ImageView player_1;
    @FXML
    private ImageView player_2;
    @FXML
    private MediaView music;
    int moveRec[];
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        moveRec = new int [10];
        moveRec=onlineGame.OnlineGame.getMoveTurn();
        
    }    

    @FXML
    private void backButtonClicked(MouseEvent event) {
    }

    @FXML
    private void positionThreeClicked(MouseEvent event) {
    }

    @FXML
    private void positionSixClicked(MouseEvent event) {
    }

    @FXML
    private void positionOneClicked(MouseEvent event) {
    }

    @FXML
    private void positionFiveClicked(MouseEvent event) {
    }

    @FXML
    private void positionNineClicked(MouseEvent event) {
    }

    @FXML
    private void positionEightClicked(MouseEvent event) {
    }

    @FXML
    private void positionFourClicked(MouseEvent event) {
    }

    @FXML
    private void positionTwoClicked(MouseEvent event) {
    }

    @FXML
    private void positionSevenClicked(MouseEvent event) {
    }
    
}
