/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameBoard;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

import login.SocketClient;
import onlineGame.OnlineGame;
import org.json.simple.JSONObject;

import javafx.stage.Stage;
import onlineGame.OnlineGame;
import tictactoe.client.Utility;

/**
 * FXML Controller class
 *
 * @author menna
 */
public class OnlineGameBoardController implements Initializable {
    private OnlineGame onlineGameHandler;
    private int playerTurn; 
    private int currentTurn;
    private boolean playerMoved;
    private String currentImageUrl;

    private PrintStream clientOutputStream;
 
    private static int win;
    
    
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
    private Button back_button1;

    @FXML
    void backButtonClicked(MouseEvent event) {
        try {
            JSONObject positionJson= new JSONObject();
            positionJson.put("type", 6);
            
            clientOutputStream = new PrintStream(SocketClient.getInstant().getSocket().getOutputStream ());
            clientOutputStream.println(positionJson.toString());
            Utility.changeTOScene(getClass(), event, "PlayerListFXML.fxml");
        } catch (IOException ex) {
            Logger.getLogger(OnlineGameBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        System.out.println(playerTurn+" , "+currentTurn);
        if(!playerMoved && playerTurn==currentTurn && ((ImageView)event.getSource()).getImage()==null ){
           onlineGameHandler.sendMoveToServer(position);
            System.out.println(playerTurn+" , "+currentTurn+" , "+position);
           playerMoved=true;
        }
        
    
    }
    public void setImage(Long position){
        switch(position.intValue()){
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
         System.out.println("currentTurn ="+currentTurn);
        if(currentTurn==1){
           
            currentImageUrl= "/assets/xchar (1).png"; 
        }else{currentImageUrl= "/assets/ochar (1).png";  }
        
    }
    public void winner(long position1,long position2){
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Random random = new Random(); 
        random.nextInt();
        try {
            onlineGameHandler = new OnlineGame(this);
        } catch (IOException ex) {
            Logger.getLogger(OnlineGameBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        playerMoved = false;
       
        currentTurn=1;
        currentImageUrl="/assets/xchar (1).png"; 
        
    }   
    
    

    public void changeTurn(Long currentTurn) {
       
        this.currentTurn = currentTurn.intValue();
       playerMoved = false;
    }

    public void drawLine(long pos1,long pos2) {
        playerTurn=0;
        if (pos1==1&&pos2==3){
            drawLine(position_1,position_3);
        }else if(pos1==4&&pos2==6){
            drawLine(position_4,position_6);
        }else if(pos1==7&&pos2==9){
            drawLine(position_7,position_9);
        }else if(pos1==1&&pos2==7){
            drawLine(position_1,position_7);
        }else if(pos1==2&&pos2==8){
            drawLine(position_2,position_8);
        }else if(pos1==3&&pos2==9){
            drawLine(position_3,position_9);
        }else if(pos1==1&&pos2==9){
            drawLine(position_1,position_9);
        }else if(pos1==3&&pos2==7){
            drawLine(position_3,position_7);
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
    public void setTurn(int playerTurn){
        this.playerTurn=playerTurn;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }
    public void didWin(){
        try {
            win = 1;
            Parent root = FXMLLoader.load(getClass().getResource("/assets/OnlineResult.fxml"));
            
            Scene scene = new Scene(root);
            
            Stage stage =(Stage) position_1.getScene().getWindow();
            
            stage.setScene(scene);
            
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(OnlineGameBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void opponentSurrender() {
        Alert SurrenderAlert= new Alert(AlertType.INFORMATION);
        SurrenderAlert.setContentText("Opponent Surrender");
        SurrenderAlert.show();
        
    }
    public void didLose(){
      try {
            win = 2;
            Parent root = FXMLLoader.load(getClass().getResource("/assets/OnlineResult.fxml"));
            
            Scene scene = new Scene(root);
            
            Stage stage =(Stage) position_1.getScene().getWindow();
            
            stage.setScene(scene);
            
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(OnlineGameBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public void didTie(){
       try {
            win = 3;
            Parent root = FXMLLoader.load(getClass().getResource("/assets/OnlineResult.fxml"));
            
            Scene scene = new Scene(root);
            
            Stage stage =(Stage) position_1.getScene().getWindow();
            
            stage.setScene(scene);
            
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(OnlineGameBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   public static int getResult(){
       return win;
   } 
    
}
