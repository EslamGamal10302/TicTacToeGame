/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameBoard;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;
import javafx.stage.Stage;
import tictactoe.client.Utility;
import welcome.HomeController;

/**
 * FXML Controller class
 *
 * @author menna
 */
public class TwoPlayersGameBoardController implements Initializable {
   @FXML
    private Button back_button;
    
   @FXML
    private AnchorPane anchor;

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
    
   // @FXML
    //private MediaView video;
    //@FXML
   // private MediaPlayer  mediaPlayer;
    private String move ;
    private String storedMove;
    private String id ;
    private Character c;
    private Image image ; 
    private String [] played ;
    private int position;
    private int flag = 0 ;
    boolean pos1_IsClicked = true;
    boolean pos2_IsClicked = true;
    boolean pos3_IsClicked = true;
    boolean pos4_IsClicked = true;
    boolean pos5_IsClicked = true;
    boolean pos6_IsClicked = true;
    boolean pos7_IsClicked = true;
    boolean pos8_IsClicked = true;
    boolean pos9_IsClicked = true;
    private static int winner;
     
       
    
    
      public void choosePlayer(){
     if (move.equalsIgnoreCase("x")) {  
         storedMove=move;
        image = new Image("/assets/xchar (1).png"); 
        move = "o";
     }     else {
           storedMove=move;
           image = new Image("/assets/ochar (1).png"); 
           move = "x";        
           }
    }
      
        private void drawLine(ImageView b1, ImageView b2){
        Bounds bound1 = b1.localToScene(b1.getBoundsInLocal());
        Bounds bound2 = b2.localToScene(b2.getBoundsInLocal());
        double x1, y1, x2, y2;
        x1 = (bound1.getMinX() + bound1.getMaxX())/2 ;
        y1 = (bound1.getMinY() + bound1.getMaxY())/2;
        x2 = (bound2.getMinX() + bound2.getMaxX())/2 ;
        y2 = (bound2.getMinY() + bound2.getMaxY())/2;
        Line line = new Line(x1,y1,x2,y2);
        line.setStyle("-fx-stroke: red;");
        line.setStrokeWidth(10);
        anchor.getChildren().add(line);
    }
    
    public void  checkWinner(){
     if (played[1]=="x" && played[2]=="x" && played[3]=="x"){
         drawLine(position_1,position_3);
          flag = 1 ; 
          TwoPlayersGameBoardController.setWinner(1);
          
     }
     else if (played[4]=="x" && played[5]=="x" && played[6]=="x"){
          drawLine(position_4,position_6);
         flag = 1 ; 
         TwoPlayersGameBoardController.setWinner(1);
     }
     else if (played[7]=="x" && played[8]=="x" && played[9]=="x"){
          drawLine(position_7,position_9);
         flag = 1 ; 
         TwoPlayersGameBoardController.setWinner(1);
     }
     else if (played[1]=="x" && played[4]=="x" && played[7]=="x"){
          drawLine(position_1,position_7);
         flag = 1 ; 
         TwoPlayersGameBoardController.setWinner(1);
     }
     else if (played[2]=="x" && played[5]=="x" && played[8]=="x"){
          drawLine(position_2,position_8);
         flag = 1 ; 
         TwoPlayersGameBoardController.setWinner(1);
     }
     else if (played[3]=="x" && played[6]=="x" && played[9]=="x"){
          drawLine(position_3,position_9);
         flag = 1 ; 
         TwoPlayersGameBoardController.setWinner(1);
     }
     else if (played[1]=="x" && played[5]=="x" && played[9]=="x"){
          drawLine(position_1,position_9);
         flag = 1 ; 
         TwoPlayersGameBoardController.setWinner(1);
     }
     else if (played[3]=="x" && played[5]=="x" && played[7]=="x"){
          drawLine(position_3,position_7);
         flag = 1 ; 
         TwoPlayersGameBoardController.setWinner(1);
     } 
     else if (played[1]=="o" && played[2]=="o" && played[3]=="o"){
          drawLine(position_1,position_3);
         flag = 1 ; 
         TwoPlayersGameBoardController.setWinner(2);
     }
     else if (played[4]=="o" && played[5]=="o" && played[6]=="o"){
          drawLine(position_4,position_6);
         flag = 1 ;
         TwoPlayersGameBoardController.setWinner(2);
     }
     else if (played[7]=="o" && played[8]=="o" && played[9]=="o"){
          drawLine(position_7,position_9);
         flag = 1 ; 
         TwoPlayersGameBoardController.setWinner(2);
     }
     else if (played[1]=="o" && played[4]=="o" && played[7]=="o"){
          drawLine(position_1,position_7);
         flag = 1 ; 
         TwoPlayersGameBoardController.setWinner(2);
     }
     else if (played[2]=="o" && played[5]=="o" && played[8]=="o"){
          drawLine(position_2,position_8);
           
         flag = 1 ; 
         TwoPlayersGameBoardController.setWinner(2);
     }
     else if (played[3]=="o" && played[6]=="o" && played[9]=="o"){
          drawLine(position_3,position_9);
         flag = 1 ; 
         TwoPlayersGameBoardController.setWinner(2);
     }
     else if (played[1]=="o" && played[5]=="o" && played[9]=="o"){
          drawLine(position_1,position_9);
         flag = 1 ; 
         TwoPlayersGameBoardController.setWinner(2);
     }
     else if (played[3]=="o" && played[5]=="o" && played[7]=="o"){
          drawLine(position_3,position_7);
         flag = 1 ; 
         TwoPlayersGameBoardController.setWinner(2);
     } 
        
    }
    
    public static void setWinner(int theWinner){
         winner = theWinner ; 
    }
     public static int getWinner(){
         return winner; 
    }
    
    
    @FXML
    void backButtonClicked(MouseEvent event) {
       try {
            Utility.changeTOScene(getClass(), event, "/welcome/home.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    void positionEightClicked(MouseEvent event) throws InterruptedException{ 
       
        //check if the square is empty to fill it
        if(pos8_IsClicked){
        pos8_IsClicked = false;
        choosePlayer();
        position_8.setImage(image);
        played[8]=storedMove;
        checkWinner(); 
        }
        //check if he win
        if (flag ==1){
           goToWinScreen(event); 
        }

    }
    @FXML
    void positionSevenClicked(MouseEvent event) throws InterruptedException{ 
       
        //check if the square is empty to fill it
        if(pos7_IsClicked){
        pos7_IsClicked = false;
        choosePlayer();
        position_7.setImage(image);
        played[7]=storedMove;
        checkWinner(); 
        }
        //check if he win
        if (flag ==1){
           goToWinScreen(event); 
        }

    }
    
    public void goToWinScreen(MouseEvent event){
        new Thread(() -> {
            try {
                  Thread.sleep(1000);
              } catch (InterruptedException ex) {
                  Logger.getLogger(TwoPlayersGameBoardController.class.getName()).log(Level.SEVERE, null, ex);
              }


            Platform.runLater(() ->{

             try {             //"/welcome/winner.fxml"
                   Utility.changeTOScene(getClass(), event, "/welcome/win.fxml");
               } catch (Exception ex) {
                   Logger.getLogger(TwoPlayersGameBoardController.class.getName()).log(Level.SEVERE, null, ex);
               }
               //  mediaPlayer.stop();

             });
             

        }).start();
    }

       @FXML
       void positionFiveClicked(MouseEvent event) {
                //check if the square is empty to fill it
             if(pos5_IsClicked){
             pos5_IsClicked = false;
             choosePlayer();
             position_5.setImage(image);
             played[5]=storedMove;
             checkWinner(); 
             }
             //check if he win
             if (flag ==1){
                goToWinScreen(event); 
             }

    }

    @FXML
    void positionFourClicked(MouseEvent event) {
        //check if the square is empty to fill it
        if(pos4_IsClicked){
        pos4_IsClicked = false;
        choosePlayer();
        position_4.setImage(image);
        played[4]=storedMove;
        checkWinner(); 
        }
        //check if he win
        if (flag ==1){
           goToWinScreen(event); 
        }

    }

    @FXML
    void positionNineClicked(MouseEvent event) throws InterruptedException {
        //check if the square is empty to fill it
        if(pos9_IsClicked){
        pos9_IsClicked = false;
        choosePlayer();
        position_9.setImage(image);
        played[9]=storedMove;
        checkWinner(); 
        }
        //check if he win
        if (flag ==1){
           goToWinScreen(event); 
        }
    }

    @FXML
    void positionOneClicked(MouseEvent event) {
             //check if the square is empty to fill it
        if(pos1_IsClicked){
        pos1_IsClicked = false;
        choosePlayer();
        position_1.setImage(image);
        played[1]=storedMove;
        checkWinner(); 
        }
        //check if he win
        if (flag ==1){
           goToWinScreen(event); 
        }

    }

    @FXML
    void positionThreeClicked(MouseEvent event) {
        //check if the square is empty to fill it
        if(pos3_IsClicked){
        pos3_IsClicked = false;
        choosePlayer();
        position_3.setImage(image);
        played[3]=storedMove;
        checkWinner(); 
        }
        //check if he win
        if (flag ==1){
           goToWinScreen(event); 
        }
    }

    @FXML
    void positionTwoClicked(MouseEvent event) {
         //check if the square is empty to fill it
        if(pos2_IsClicked){
        pos2_IsClicked = false;
        choosePlayer();
        position_2.setImage(image);
        played[2]=storedMove;
        checkWinner(); 
        }
        //check if he win
        if (flag ==1){
           goToWinScreen(event); 
        }
   }
    
     @FXML
    void positionSixClicked(MouseEvent event) {
                 //check if the square is empty to fill it
        if(pos6_IsClicked){
        pos6_IsClicked = false;
        choosePlayer();
        position_6.setImage(image);
        played[6]=storedMove;
        checkWinner(); 
        }
        //check if he win
        if (flag ==1){
           goToWinScreen(event); 
        }
   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        move="x";
        played = new String[10];
         
      // Media media = new Media(getClass().getResource("/assets/music.mp3").toExternalForm());
       //mediaPlayer= new MediaPlayer(media);
        //video.setMediaPlayer(mediaPlayer);
        //video.setFitHeight(400);
        //video.setFitWidth(350);
        //mediaPlayer.play();
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
}