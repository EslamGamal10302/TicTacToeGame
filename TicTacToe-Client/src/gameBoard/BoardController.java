/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameBoard;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import tictactoe.client.Utility;
import welcome.HomeController;


/**
 * FXML Controller class
 *
 * @author menna
 */
public class BoardController implements Initializable {

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
    void backButtonClicked(MouseEvent event) {
       try {
            Utility.changeTOScene(getClass(), event, "/welcome/home.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    void positionEightClicked(MouseEvent event) {
    }

    @FXML
    void positionFiveClicked(MouseEvent event) {
    }

    @FXML
    void positionFourClicked(MouseEvent event) {
    }

    @FXML
    void positionNineClicked(MouseEvent event) {
    }

    @FXML
    void positionOneClicked(MouseEvent event) throws Exception {
    }

    @FXML
    void positionSevenClicked(MouseEvent event) {
    }

    @FXML
    void positionSixClicked(MouseEvent event) {
    }

    @FXML
    void positionThreeClicked(MouseEvent event) {
    }

    @FXML
    void positionTwoClicked(MouseEvent event) {
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

}

    
  
