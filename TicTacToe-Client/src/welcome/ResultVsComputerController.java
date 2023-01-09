/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package welcome;

import gameBoard.BoardController;
import gameBoard.TwoPlayersGameBoardController;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tictactoe.client.Utility;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class ResultVsComputerController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private MediaView video;

    @FXML
    private Button playAgain;

    @FXML
    private Button exit;

    @FXML
    private Text winText;

    private MediaPlayer  mediaPlayer ;
    
    @FXML
    void exitAction(MouseEvent event) {
      Stage stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void playAgainAction(MouseEvent event) {
        mediaPlayer.stop();
          try {
            Utility.changeTOScene(getClass(), event, "/gameBoard/board.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      int x= BoardController.getWinner(); 
      Media media ;
       if (x == 1){
         winText.setText("you wins");
          media = new Media(getClass().getResource("/assets/keber.mp4").toExternalForm());
        }
        else if(x == 2){
          winText.setText("computer wins");
          media = new Media(getClass().getResource("/assets/fashel.mp4").toExternalForm());
        }  else {
            winText.setText("drawn");
             media = new Media(getClass().getResource("/assets/drwan.mp4").toExternalForm());
        } 
        mediaPlayer= new MediaPlayer(media);
        video.setMediaPlayer(mediaPlayer);
        video.setFitHeight(400);
        video.setFitWidth(350);
        mediaPlayer.play();
        //winText.setText(value);
        
       
        
    }    
    
}
