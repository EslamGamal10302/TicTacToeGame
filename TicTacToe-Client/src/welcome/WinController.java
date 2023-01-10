/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package welcome;

import gameBoard.TwoPlayersGameBoardController;
import java.net.URL;
import java.nio.file.Paths;
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
public class WinController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private MediaView video;
    
    @FXML
    private Text winText;    
        
    @FXML
    private Button playAgain;

    @FXML
    private Button exit;
    
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
            Utility.changeTOScene(getClass(), event, "/gameBoard/TwoPlayersGameBoard.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }    

    }
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO      "/assets/loser.mp4"       "file:/G:/ITI/loser.mp4"   new Media(Paths.get("04.mp3").toUri().toString());
      //  String myVideo = ("/assets/loser.mp4");
        Media media = new Media(getClass().getResource("/assets/winner.mp4").toExternalForm());
        mediaPlayer= new MediaPlayer(media);
        video.setMediaPlayer(mediaPlayer);
        video.setFitHeight(450);
        video.setFitWidth(350);
        mediaPlayer.play();
        //winText.setText(value);
        int x= TwoPlayersGameBoardController.getWinner();
        if (x == 1){
         winText.setText("player 1 wins");
        }
        else {
          winText.setText("player 2 wins");  
        }
       
        
    }    
    
}
