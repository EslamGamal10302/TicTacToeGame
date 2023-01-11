/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challengeDialog;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author 20106
 */
public class ChallengeDialogController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private String userName;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setUserName(String userName) {
       this.userName = userName;
    }
    
}
