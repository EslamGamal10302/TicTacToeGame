/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playersList;

import challengeDialog.ChallengeDialogController;
import gameBoard.OnlineGameBoardController;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import playersList.playersListCell.*;
import tictactoe.client.Utility;
import welcome.HomeController;
import login.SocketClient;
import model.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * FXML Controller class
 *
 * @author 20106
 */
public class PlayerListController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private Button recordsBut;
    @FXML
    private ListView<Player> playerList;
     
    private ObservableList<Player> playersObservableList; 
    private boolean gameNotStarted;
    private String josnString;
    private Alert watingAlert;
    
    
    private BufferedReader clientBufferedReader;
      
       @FXML
    void goToRecords(ActionEvent event) {
        try {
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            Parent  myNewScens = FXMLLoader.load(getClass().getResource("/recordsList/RecordsListFMLX.fxml"));
            Scene scene = new Scene(myNewScens);
            stage.setScene(scene);
            stage.setTitle("My New Scene");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PlayerListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    
    
    @FXML
    void homeButtonAction(ActionEvent event) {
    try {
            Utility.changeTOScene(getClass(), event, "/welcome/home.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        watingAlert = new Alert(Alert.AlertType.INFORMATION);
        try {
            clientBufferedReader = new BufferedReader(new InputStreamReader(SocketClient.getInstant().getSocket().getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(PlayerListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        gameNotStarted=true;
        playersObservableList =FXCollections.observableArrayList();
        playerList.getStylesheets().add(getClass().getResource("listViewcss.css").toString());
        playerList.setCellFactory((param) -> {
            return new PlayerCellController(watingAlert); //To change body of generated lambdas, choose Tools | Templates.
        });
        playerList.setItems(playersObservableList);
         new Thread(() -> {
             JSONObject playerJson;
            while(gameNotStarted){
                try {
                playerJson= (JSONObject) new JSONParser().parse(clientBufferedReader.readLine());
                    System.out.println(playerJson.toString());
                    Long temp =(long)playerJson.get("type");
                    int type = temp.intValue();
                    
                  switch(type){
                    case 1:
                        gameNotStarted=false;
                        showDialog(playerJson);                     
                        break;
                        
                    case 2:
                        gameNotStarted =false;
                        watingAlert.close();
                        Platform.runLater(() -> {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader((getClass().getResource("/gameBoard/OnlineGameBoard.fxml")));
                        
                        Parent root = fxmlLoader.load();
                        Scene scene = new Scene(root);
                        Stage stage =(Stage) recordsBut.getScene().getWindow();
                        OnlineGameBoardController gameBoard =fxmlLoader.<OnlineGameBoardController>getController();
                        gameBoard.setTurn(1);
                        
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(PlayerListController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        });
                        break;

                    case 4:
                        JSONArray  playersJSON = (JSONArray) playerJson.get("player");
                        System.out.println(playerJson.toJSONString());
                        playersObservableList.clear();
                        playersObservableList.addAll(decodePlayersJSONArray(playersJSON));
                        break;
                    
                }
                } catch (IOException ex) {
                    Logger.getLogger(PlayerListController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(PlayerListController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }).start();  
    }   

    private void showDialog(JSONObject playerJson) {
        Platform.runLater(() -> {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/challengeDialog/challengeDialogFXML.fxml"));
        Parent parent;
            
                parent = fxmlLoader.load();
           
        ChallengeDialogController dialogController = fxmlLoader.<ChallengeDialogController>getController();
        dialogController.setUserName((String)playerJson.get("userName"));
        Scene scene = new Scene(parent, 300, 200);
        Stage stage = new Stage();
        Stage mainStage =(Stage) recordsBut.getScene().getWindow();
        dialogController.setMainStage(mainStage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
         } catch (IOException ex) {
                Logger.getLogger(PlayerListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private ArrayList<Player> decodePlayersJSONArray(JSONArray playersJSON) {
       ArrayList<Player> Players = new ArrayList<>();
        for (Object playerOpj : playersJSON) {
            
            JSONObject  playerJSON = (JSONObject)playerOpj;   
            Long numOfgames =(Long)  playerJSON.get("no_game");
            if(numOfgames == null){
             numOfgames = new Long(0);
            }
            Long playerScore =(Long)  playerJSON.get("score");
            if(playerScore == null){
             playerScore = new Long(0);
            }
            Player player = new Player((String)playerJSON.get("playerName"),
                                        ((Long) playerJSON.get("status")).intValue(),
                                         numOfgames.intValue(),
                                         playerScore.intValue());
            Players.add(player);
        }
       return Players;
    }
    
    
    
}
