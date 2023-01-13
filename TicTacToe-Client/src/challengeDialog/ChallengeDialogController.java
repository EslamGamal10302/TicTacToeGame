/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challengeDialog;

import gameBoard.OnlineGameBoardController;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import login.SocketClient;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author 20106
 */
public class ChallengeDialogController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button acceptButton;

    @FXML
    private Button refuseButton;

    @FXML
    private Label playerName;
    private PrintStream serverDataOutput;
    private String userName;
    private Stage mainStage;
    

    @FXML
    void acceptPlaying(ActionEvent event) throws IOException {
        JSONObject positionJson= new JSONObject();
                        positionJson.put("userName", userName);
                        positionJson.put("type", 2);
                        serverDataOutput = new PrintStream(SocketClient.getInstant().getSocket().getOutputStream());
                        
                        serverDataOutput.println(positionJson.toString());
        FXMLLoader fxmlLoader = new FXMLLoader((getClass().getResource("/gameBoard/OnlineGameBoard.fxml")));
       
                        
                        Stage stage =(Stage)((Node) event.getSource()).getScene().getWindow();
                        Parent root = fxmlLoader.load();
                        OnlineGameBoardController gameBoard =fxmlLoader.getController();
                        Scene scene = new Scene(root);
                        gameBoard.setTurn(2);
                        mainStage.setScene(scene);
   
                        mainStage.show();
                        stage.close();
                        
                        
                        
    }

    @FXML
    void refusePlaying(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setUserName(String userName) {
        this.userName = userName;
        playerName.setText(userName);
    }

    public void setMainStage(Stage mainStage) {
       this.mainStage = mainStage; 
    }

}
