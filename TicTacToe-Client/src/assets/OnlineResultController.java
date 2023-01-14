/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assets;

import gameBoard.BoardController;
import gameBoard.OnlineGameBoardController;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import login.SocketClient;
import org.json.simple.JSONObject;
import tictactoe.client.Utility;
import welcome.HomeController;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class OnlineResultController implements Initializable {

    @FXML
    private MediaView video;
    @FXML
    private Button playAgain;
    @FXML
    private Button exit;
    @FXML
    private Text winText;
    @FXML
    private Button replay;
     private MediaPlayer  mediaPlayer ;
     private PrintStream clientOutputStream;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      int x= OnlineGameBoardController.getResult();
      Media media ;
       if (x == 1){
         winText.setText("you wins");
          media = new Media(getClass().getResource("/assets/keber.mp4").toExternalForm());
        }
        else if(x == 2){
          winText.setText("you lose");
          media = new Media(getClass().getResource("/assets/fashel.mp4").toExternalForm());
        }  else {
            winText.setText("drawn");
             media = new Media(getClass().getResource("/assets/drwan.mp4").toExternalForm());
        } 
        mediaPlayer= new MediaPlayer(media);
        video.setMediaPlayer(mediaPlayer);
        video.setFitHeight(400);
        video.setFitWidth(700);
        mediaPlayer.play();   
        
        
        
    }    

    @FXML
    private void playAgainAction(MouseEvent event) {
        mediaPlayer.pause();
        Utility.changeTOScene(getClass(), event, "/playersList/PlayerListFXML.fxml");
    }

    @FXML
    private void exitAction(MouseEvent event) {
        mediaPlayer.pause();
     try {
          JSONObject positionJson= new JSONObject();
            positionJson.put("type", 8);
            
            clientOutputStream = new PrintStream(SocketClient.getInstant().getSocket().getOutputStream ());
            clientOutputStream.println(positionJson.toString());
                            Utility.changeTOScene(getClass(), event, "/playersList/PlayerListFXML.fxml");
                        } catch (Exception ex) {
                            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                        }   
        
    }

    @FXML
    private void replayAction(MouseEvent event) {
        mediaPlayer.pause();
    }
    
}
