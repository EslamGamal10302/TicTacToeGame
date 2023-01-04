/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameBoard;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class TwoPlayersGameBoardController implements Initializable {
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
    
    
      public void choosePlayer(){
     if (move.equalsIgnoreCase("x")) {  
         storedMove=move;
        image = new Image("/assets/xchar.png"); 
        move = "o";
     }     else {
           storedMove=move;
           image = new Image("/assets/ochar.png"); 
           move = "x";        
           }
    }
    
    public int  checkWinner(){
     if (played[1]=="x" && played[2]=="x" && played[3]=="x"){
         System.out.println("YOU WIN");
          flag = 1 ; 
          
     }
     else if (played[4]=="x" && played[5]=="x" && played[6]=="x"){
         flag = 1 ; 
     }
     else if (played[7]=="x" && played[8]=="x" && played[9]=="x"){
         flag = 1 ; 
     }
     else if (played[1]=="x" && played[4]=="x" && played[7]=="x"){
         flag = 1 ; 
     }
     else if (played[2]=="x" && played[5]=="x" && played[8]=="x"){
         flag = 1 ; 
     }
     else if (played[3]=="x" && played[6]=="x" && played[9]=="x"){
         flag = 1 ; 
     }
     else if (played[1]=="x" && played[5]=="x" && played[9]=="x"){
         flag = 1 ; 
     }
     else if (played[3]=="x" && played[5]=="x" && played[7]=="x"){
         flag = 1 ; 
     }  
     else if (played[1]=="o" && played[2]=="o" && played[3]=="o"){
         flag = 1 ; 
     }
     else if (played[4]=="o" && played[5]=="o" && played[6]=="o"){
         flag = 1 ; 
     }
     else if (played[7]=="o" && played[8]=="o" && played[9]=="o"){
         flag = 1 ; 
     }
     else if (played[1]=="o" && played[4]=="o" && played[7]=="o"){
         flag = 1 ; 
     }
     else if (played[2]=="o" && played[5]=="o" && played[8]=="o"){
         flag = 1 ; 
     }
     else if (played[3]=="o" && played[6]=="o" && played[9]=="o"){
         flag = 1 ; 
     }
     else if (played[1]=="o" && played[5]=="o" && played[9]=="o"){
         flag = 1 ; 
     }
     else if (played[3]=="o" && played[5]=="o" && played[7]=="o"){
         flag = 1 ; 
     } 
       
       return flag;  
        
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
    void positionEightClicked(MouseEvent event) {
      choosePlayer();
      position_8.setImage(image);
      id=position_8.getId();
      c = id.charAt(9);
      position=Integer.parseInt(String.valueOf(c));
      played[position]=storedMove;
      System.out.println(position);
      System.out.println(played[position]);
      checkWinner();
       if(flag == 1){
       try {
            Utility.changeTOScene(getClass(), event, "/welcome/winner.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }    
       }
    }

    @FXML
    void positionFiveClicked(MouseEvent event) {
      choosePlayer();
      position_5.setImage(image);
      id=position_5.getId();
      c = id.charAt(9);
      position=Integer.parseInt(String.valueOf(c));
      played[position]=storedMove;
      System.out.println(position);
      System.out.println(played[position]);
      checkWinner();
       if(flag == 1){
       try {
            Utility.changeTOScene(getClass(), event, "/welcome/winner.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }    
       }
    }

    @FXML
    void positionFourClicked(MouseEvent event) {
      choosePlayer();
      position_4.setImage(image);
      id=position_4.getId();
      c = id.charAt(9);
      position=Integer.parseInt(String.valueOf(c));
      played[position]=storedMove;
      System.out.println(position);
      System.out.println(played[position]);
      checkWinner();
       if(flag == 1){
       try {
            Utility.changeTOScene(getClass(), event, "/welcome/winner.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }    
       }
    }

    @FXML
    void positionNineClicked(MouseEvent event) throws InterruptedException {
      choosePlayer();
      position_9.setImage(image);
      id=position_9.getId();
      c = id.charAt(9);
      position=Integer.parseInt(String.valueOf(c));
      played[position]=storedMove;
      System.out.println(position);
      System.out.println(played[position]);
      checkWinner();
       if(flag == 1){
           Thread.sleep(2000);
       try {
            Utility.changeTOScene(getClass(), event, "/welcome/winner.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }    
       }
    }

    @FXML
    void positionOneClicked(MouseEvent event) {
      choosePlayer();
      position_1.setImage(image);
      position_1.setDisable(false);
      id=position_1.getId();
      c = id.charAt(9);
      position=Integer.parseInt(String.valueOf(c));
      played[position]=storedMove;
      System.out.println(position);
      System.out.println(played[position]);
      checkWinner();
       if(flag == 1){
       try {
            Utility.changeTOScene(getClass(), event, "/welcome/winner.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }    
       }
    }

    @FXML
    void positionSevenClicked(MouseEvent event) {
      choosePlayer();
      position_7.setImage(image);
      id=position_7.getId();
      c = id.charAt(9);
      position=Integer.parseInt(String.valueOf(c));
      played[position]=storedMove;
      System.out.println(position);
      System.out.println(played[position]);
      checkWinner();
       if(flag == 1){
       try {
            Utility.changeTOScene(getClass(), event, "/welcome/winner.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }    
       }
    }

    @FXML
    void positionSixClicked(MouseEvent event) {
      choosePlayer();
      position_6.setImage(image);
      id=position_6.getId();
      c = id.charAt(9);
      position=Integer.parseInt(String.valueOf(c));
      played[position]=storedMove;
      System.out.println(position);
      System.out.println(played[position]);
      checkWinner();
       if(flag == 1){
       try {
            Utility.changeTOScene(getClass(), event, "/welcome/winner.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }    
       }
    }

    @FXML
    void positionThreeClicked(MouseEvent event) {
      choosePlayer();
      position_3.setImage(image);
      id=position_3.getId();
      c = id.charAt(9);
      position=Integer.parseInt(String.valueOf(c));
      played[position]=storedMove;
      System.out.println(position);
      System.out.println(played[position]);
      checkWinner();
      if(flag == 1){
       try {
            Utility.changeTOScene(getClass(), event, "/welcome/winner.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }    
      }
    }

    @FXML
    void positionTwoClicked(MouseEvent event) {
      choosePlayer();
      position_2.setImage(image);
      id=position_2.getId();
      c = id.charAt(9);
      position=Integer.parseInt(String.valueOf(c));
      played[position]=storedMove;
      System.out.println(position);
      System.out.println(played[position]);
      checkWinner();
       if(flag == 1){
       try {
            Utility.changeTOScene(getClass(), event, "/welcome/winner.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }    
       }
   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        move="x";
        played = new String[10];
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
}
