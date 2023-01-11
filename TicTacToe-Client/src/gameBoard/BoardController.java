/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameBoard;

import java.io.IOException;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import tictactoe.client.Utility;
import welcome.HomeController;


/**
 * FXML Controller class
 *
 * @author menna
 */
public class BoardController implements Initializable {
    
    @FXML
    private AnchorPane anchor_online;

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
    private String move ;
    private String storedMove;
    private String id ;
    private Character c;
    private Image image ;  
    private String [] played ;
    private int position;
    private int flag = 0 ;
    private int flag2=0;
    private int i=0;
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
    private ActionEvent event;
    boolean[] Clicked = {pos1_IsClicked,pos2_IsClicked,pos3_IsClicked,pos4_IsClicked,pos5_IsClicked,pos6_IsClicked,pos7_IsClicked,pos8_IsClicked,pos9_IsClicked};
     public void choosePlayer() {
     if (move.equalsIgnoreCase("x")) {  
         storedMove=move;
        image = new Image("/assets/xchar.png");
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
        anchor_online.getChildren().add(line);
    }
     
     private void computerTurn(MouseEvent event)  {
        
          new Thread(() -> {
            try {
                  Thread.sleep(1000);
              } catch (InterruptedException ex) {
                  Logger.getLogger(TwoPlayersGameBoardController.class.getName()).log(Level.SEVERE, null, ex);
              }


            Platform.runLater(() ->{

             ImageView[] btns = {position_1,position_2,position_3,position_4,position_5,position_6,position_7,position_8,position_9};
             ImageView myBtn;
             boolean flag_pos = true;
                while(flag_pos&&i<9)
                {
                  if(Clicked[i])
                   { 
                    myBtn = btns[i];
                    myBtn.setImage(image);   
                    flag_pos = false;
                    Clicked[i]=false;
                    flag2=0;
                    played[i+1]="o";
                       
                    checkWinner();
                      if(flag == 1){
                     goToWinScreen(event);    
                    }
                   }
                  
                 i++;
                  
               }

             });
             

        }).start();
     
    }
    
    public int  checkWinner(){
     if (played[1]=="x" && played[2]=="x" && played[3]=="x"){
         System.out.println("YOU WIN");
         drawLine(position_1,position_3);
          flag = 1 ; 
          BoardController.setWinner(1);
     }
     else if (played[4]=="x" && played[5]=="x" && played[6]=="x"){
         drawLine(position_4,position_6);
         flag = 1 ; 
         BoardController.setWinner(1);
     }
     else if (played[7]=="x" && played[8]=="x" && played[9]=="x"){
         drawLine(position_7,position_9);
         flag = 1 ; 
         BoardController.setWinner(1);
     }
     else if (played[1]=="x" && played[4]=="x" && played[7]=="x"){
         drawLine(position_1,position_7);
         flag = 1 ; 
         BoardController.setWinner(1);
     }
     else if (played[2]=="x" && played[5]=="x" && played[8]=="x"){
         drawLine(position_2,position_8);
         flag = 1 ; 
         BoardController.setWinner(1);
     }
     else if (played[3]=="x" && played[6]=="x" && played[9]=="x"){
         drawLine(position_3,position_9);
         flag = 1 ; 
         BoardController.setWinner(1);
     }
     else if (played[1]=="x" && played[5]=="x" && played[9]=="x"){
         drawLine(position_1,position_9);
         flag = 1 ; 
         BoardController.setWinner(1);
     }
     else if (played[3]=="x" && played[5]=="x" && played[7]=="x"){
         drawLine(position_3,position_7);
         flag = 1 ; 
         BoardController.setWinner(1);
     } 
     else if (played[1]=="o" && played[2]=="o" && played[3]=="o"){
         drawLine(position_1,position_3);
         flag = 1 ; 
         BoardController.setWinner(2);
     }
     else if (played[4]=="o" && played[5]=="o" && played[6]=="o"){
         drawLine(position_4,position_6);
         flag = 1 ; 
         BoardController.setWinner(2);
     }
     else if (played[7]=="o" && played[8]=="o" && played[9]=="o"){
         drawLine(position_7,position_9);
         flag = 1 ; 
         BoardController.setWinner(2);
     }
     else if (played[1]=="o" && played[4]=="o" && played[7]=="o"){
         drawLine(position_1,position_7);
         flag = 1 ; 
         BoardController.setWinner(2);
     }
     else if (played[2]=="o" && played[5]=="o" && played[8]=="o"){
         drawLine(position_2,position_8);
         flag = 1 ; 
         BoardController.setWinner(2);
     }
     else if (played[3]=="o" && played[6]=="o" && played[9]=="o"){
         drawLine(position_3,position_9);
         flag = 1 ; 
         BoardController.setWinner(2);
     }
     else if (played[1]=="o" && played[5]=="o" && played[9]=="o"){
         drawLine(position_1,position_9);
         flag = 1 ; 
         BoardController.setWinner(2);
     }
     else if (played[3]=="o" && played[5]=="o" && played[7]=="o"){
         drawLine(position_3,position_7);
         flag = 1 ; 
         BoardController.setWinner(2);
     } else if (Clicked[0]==false&&Clicked[1]==false&&Clicked[2]==false&&Clicked[3]==false&&Clicked[4]==false&&Clicked[5]==false&&Clicked[6]==false&&Clicked[7]==false&&Clicked[8]==false) {
            

         try {
             BoardController.setWinner(3);
             
             Parent root = FXMLLoader.load(getClass().getResource("/welcome/resultVsComputer.fxml"));
             
             Scene scene = new Scene(root);
             
             Stage stage =(Stage) position_9.getScene().getWindow();
             
             stage.setScene(scene);
             
             stage.show();
         } catch (IOException ex) {
             Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
                 
                 
     }
       return flag;  
        
    }

      public static void setWinner(int theWinner){
          System.out.println(theWinner);
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
   
      public void goToWinScreen(MouseEvent event){
        new Thread(() -> {
            try {
                  Thread.sleep(1000);
              } catch (InterruptedException ex) {
                  Logger.getLogger(TwoPlayersGameBoardController.class.getName()).log(Level.SEVERE, null, ex);
              }


            Platform.runLater(() ->{

             try {
                   Utility.changeTOScene(getClass(), event, "/welcome/resultVsComputer.fxml");
               } catch (Exception ex) {
                   Logger.getLogger(TwoPlayersGameBoardController.class.getName()).log(Level.SEVERE, null, ex);
               }

             });
             

        }).start();
    }
    @FXML
    void positionEightClicked(MouseEvent event) {
     //check if the square is empty to fill it
     if(pos8_IsClicked && Clicked[7]&&flag2==0){
      flag2=1;   
      pos8_IsClicked = false;
      Clicked[7]=false;
      choosePlayer();
      position_8.setImage(image);
      played[8]=storedMove;
      System.out.println("8");
      System.out.println(played[8]);
      checkWinner();
      if(flag == 1){
         goToWinScreen(event); 
       }
      else{
       choosePlayer();
       computerTurn(event);
      // played[i]=storedMove;
       System.out.println(i);
       System.out.println(played[i]);
       //checkWinner();
       //if(flag == 1){
        // goToWinScreen(event);    
      // }
      }
     }
    }

    @FXML
    void positionFiveClicked(MouseEvent event) {
       //check if the square is empty to fill it
     if(pos5_IsClicked && Clicked[4]&&flag2==0){
      flag2=1;      
      pos5_IsClicked = false;
      Clicked[4]=false;
      choosePlayer();
      position_5.setImage(image);
      played[5]=storedMove;
      System.out.println("5");
      System.out.println(played[5]);
      checkWinner();
      if(flag == 1){
         goToWinScreen(event);    
       }
      else{
       choosePlayer();
       computerTurn(event);
      // played[i]=storedMove;
       System.out.println(i);
       System.out.println(played[i]);
      // checkWinner();
      // if(flag == 1){
       //  goToWinScreen(event);    
     //  }
      }
     }
    }

    @FXML
    void positionFourClicked(MouseEvent event) {
      //check if the square is empty to fill it
     if(pos4_IsClicked && Clicked[3]&&flag2==0){
      flag2=1;      
      pos4_IsClicked = false;
      Clicked[3]=false;
      choosePlayer();
      position_4.setImage(image);
     played[4]=storedMove;
      System.out.println("4");
      System.out.println(played[4]);
      checkWinner();
      if(flag == 1){
         goToWinScreen(event);    
       }
      else{
       choosePlayer();
       computerTurn(event);
      // played[i]=storedMove;
       System.out.println(i);
       System.out.println(played[i]);
      // checkWinner();
       //if(flag == 1){
        // goToWinScreen(event);    
      // }
      }
     }
    }

    @FXML
    void positionNineClicked(MouseEvent event) {
     //check if the square is empty to fill it
     if(pos9_IsClicked && Clicked[8]&&flag2==0){
      flag2=1;      
      pos9_IsClicked = false;
      Clicked[8]=false;
      choosePlayer();
      position_9.setImage(image);
      played[9]=storedMove;
      System.out.println("9");
      System.out.println(played[9]);
      checkWinner();
      if(flag == 1){
         goToWinScreen(event);    
       }
      else{
       choosePlayer();
       computerTurn(event);
       //played[i]=storedMove;
       System.out.println(i);
       System.out.println(played[i]);
       //checkWinner();
       //if(flag == 1){
         //goToWinScreen(event);    
       //}
      }
     }
    }

    @FXML
    void positionOneClicked(MouseEvent event) {
     //check if the square is empty to fill it
     if(pos1_IsClicked && Clicked[0]&&flag2==0 ){
      flag2=1;      
      pos1_IsClicked = false;
      Clicked[0]=false;
      choosePlayer();
      position_1.setImage(image);
      played[1]=storedMove;
      System.out.println("1");
      System.out.println(played[1]);
      checkWinner();
      if(flag == 1){
         goToWinScreen(event);    
       }
      else{ 
       choosePlayer();
       computerTurn(event);
       //played[i]=storedMove;
       System.out.println(i);
       System.out.println(played[i]);
      // checkWinner();
       //if(flag == 1){
        // goToWinScreen(event);    
      // }
         
      }
     }
    }

    @FXML
    void positionSevenClicked(MouseEvent event) {
     //check if the square is empty to fill it
     if(pos7_IsClicked && Clicked[6]&&flag2==0){
      flag2=1;      
      pos7_IsClicked = false;
      Clicked[6]=false;
      choosePlayer();
      position_7.setImage(image);
      played[7]=storedMove;
      System.out.println("7");
      System.out.println(played[7]);
      checkWinner();
      if(flag == 1){
         goToWinScreen(event);    
       }
      else{
       choosePlayer();
       computerTurn(event);
      // played[i]=storedMove;
       System.out.println(i);
       System.out.println(played[i]);
       //checkWinner();
       //if(flag == 1){
        // goToWinScreen(event);    
       //}
      }
     }
    }

    @FXML
    void positionSixClicked(MouseEvent event) {
      //check if the square is empty to fill it
     if(pos6_IsClicked && Clicked[5]&&flag2==0){
       flag2=1;     
      pos6_IsClicked = false;
      Clicked[5]=false;
      choosePlayer();
      position_6.setImage(image);
      played[6]=storedMove;
      System.out.println("6");
      System.out.println(played[6]);
      checkWinner();
      if(flag == 1){
         goToWinScreen(event);    
       }
      else{
       choosePlayer();
       computerTurn(event);
       //played[i]=storedMove;
       System.out.println(i);
       System.out.println(played[i]);
       //checkWinner();
       //if(flag == 1){
        // goToWinScreen(event);    
       //}
      }
     }
    }

    @FXML
    void positionThreeClicked(MouseEvent event) {
      //check if the square is empty to fill it
     if(pos3_IsClicked && Clicked[2]&&flag2==0){
      flag2=1;      
      pos3_IsClicked = false;
      Clicked[2]=false;
      choosePlayer();
      position_3.setImage(image);
      played[3]=storedMove;
      System.out.println("3");
      System.out.println(played[3]);
      checkWinner();
      if(flag == 1){
         goToWinScreen(event);    
       }
      else{
       choosePlayer();
       computerTurn(event);
       //played[i]=storedMove;
       System.out.println(i);
       System.out.println(played[i]);
       //checkWinner();
      // if(flag == 1){
        // goToWinScreen(event);    
       //}
      }
     }
    }

    @FXML
    void positionTwoClicked(MouseEvent event) {
      //check if the square is empty to fill it
     if(pos2_IsClicked && Clicked[1]&&flag2==0){
      flag2=1;      
      pos2_IsClicked = false;
      Clicked[1]=false;
      choosePlayer();
      position_2.setImage(image);
      played[2]=storedMove;
      System.out.println("2");
      System.out.println(played[2]);
      checkWinner();
      if(flag == 1){
         goToWinScreen(event);    
       }
      else{
       choosePlayer();
       computerTurn(event);
      // played[i]=storedMove;
       System.out.println(i);
       System.out.println(played[i]);
      // checkWinner();
       //if(flag == 1){
        // goToWinScreen(event);    
      // }
      }
     }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         move="x";
        played = new String[10];
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

    
  
