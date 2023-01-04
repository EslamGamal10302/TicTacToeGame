/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameBoard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import onlineGame.OnlineGame;

/**
 * FXML Controller class
 *
 * @author menna
 */
public class OnlineGameBoardController implements Initializable {
    private OnlineGame onlineGameHandler;
    private boolean playerTurn; 
    private boolean playerMoved;
    
    private String currentImageUrl;
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
    private Button back_button1;

    @FXML
    void backButtonClicked(MouseEvent event) {

    }

    @FXML
    void positionEightClicked(MouseEvent event) {
        sendMove(8, event);

    }

    @FXML
    void positionFiveClicked(MouseEvent event) {
         sendMove(5, event);
    }

    @FXML
    void positionFourClicked(MouseEvent event) {
         sendMove(4, event);
    }

    @FXML
    void positionNineClicked(MouseEvent event) {
         sendMove(9, event);
    }

    @FXML
    void positionOneClicked(MouseEvent event) {
        
         sendMove(1, event);
    }

    @FXML
    void positionSevenClicked(MouseEvent event) {
        sendMove(7, event);
    }

    @FXML
    void positionSixClicked(MouseEvent event) {
        sendMove(6, event);
    }

    @FXML
    void positionThreeClicked(MouseEvent event) {
        sendMove(3, event);
    }

    @FXML
    void positionTwoClicked(MouseEvent event) {
        sendMove(2, event);
    }
    private void sendMove(int position,MouseEvent event){
        if(!playerMoved && playerTurn && ((ImageView)event.getSource()).getImage()==null ){
           onlineGameHandler.sendMoveToServer(position);
           playerMoved=false;
        }
        
    
    }
    public void setImage(int position){
        switch(position){
            case 1:
                position_1.setImage(new Image(currentImageUrl));
                break;
            case 2:
                position_2.setImage(new Image(currentImageUrl));
                break;
            case 3:
                position_3.setImage(new Image(currentImageUrl));
                break;
            case 4:
                position_4.setImage(new Image(currentImageUrl));
                break;
            case 5:
                position_5.setImage(new Image(currentImageUrl));
                break;
            case 6:
                position_6.setImage(new Image(currentImageUrl));
                break;
            case 7:
                position_7.setImage(new Image(currentImageUrl));
                break;
            case 8:
                position_8.setImage(new Image(currentImageUrl));
                break;
            case 9:
                position_9.setImage(new Image(currentImageUrl)); 
                break;
        }
        currentImageUrl= "";
        changeTurn();
    }
    public void winner(int position1,int position2){
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            onlineGameHandler = new OnlineGame(this);
        } catch (IOException ex) {
            Logger.getLogger(OnlineGameBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        playerMoved = false;
        playerTurn = true;
        currentImageUrl= "";
        
    }    

    private void changeTurn() {
       playerTurn = !playerTurn;
       playerMoved = false;
    }
    
}
