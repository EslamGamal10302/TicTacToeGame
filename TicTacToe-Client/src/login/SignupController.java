/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.simple.JSONObject;
import tictactoe.client.Utility;
import welcome.HomeController;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class SignupController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button signUp;

    @FXML
    private TextField signUpUserName;

    @FXML
    private TextField email;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private PasswordField signUpConfirmPassword;
    @FXML
    private Text signUp_repeat;
    Socket mySocket;
    DataInputStream dis;
    PrintStream ps;
    private boolean sign = false;

    @FXML
    private Button exit;

    @FXML
    void exitAction(ActionEvent event) {

        try {

            Utility.changeTOScene(getClass(), event, "/welcome/home.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML

    void signUpButtonAction(ActionEvent event) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email.getText());
        String name = signUpUserName.getText().trim();
        String Email = email.getText().trim();
        String pass = signUpPassword.getText().trim();
        String passConfirm = signUpConfirmPassword.getText().trim();
        if (name.isEmpty() || Email.isEmpty()
                || pass.isEmpty() || passConfirm.isEmpty()) {

            signUp_repeat.setText("Empty Fields is Required");

        } else if (!matcher.matches()) {

            signUp_repeat.setText("Please enter a valid mail");

        }else if (signUpPassword.getText().length() < 5) {

            signUp_repeat.setText("Your password less than 5 character");

        } else if (!signUpPassword.getText().equals(signUpConfirmPassword.getText())) {

            signUp_repeat.setText("Please check your password");

        } //signUpPassword.textProperty().bind(Bindings.length(signUpPassword.textProperty())
        //                        .asString("String length: %d"));
        else {
            /*try {
                // TODO
                mySocket = new Socket("127.0.0.1", 5005);
                dis = new DataInputStream(mySocket.getInputStream());
                ps = new PrintStream(mySocket.getOutputStream());
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            try {
                //SocketClient.getInstant().getSocket();
                dis = new DataInputStream(SocketClient.getInstant().getSocket().getInputStream());
                ps = new PrintStream(SocketClient.getInstant().getSocket().getOutputStream());

            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

            long type = 1;
            JSONObject obj = new JSONObject();
            obj.put("userName", name);
            obj.put("email", Email);
            obj.put("password", pass);
            obj.put("type", type);
            // String json=("{\"userName\":"+name+"},{\"password\":"+pass+"}");
            //System.out.println(json);
            ps.println(obj);
            //System.out.println("9");
            /*try {
                       ps.close();
                       dis.close();
                       // mySocket.close();
                      SocketClient.getInstant().CloseSocket();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                   }*/
            new Thread(() -> {
                try {
                    String replyMsg = dis.readLine();
                    System.out.println(replyMsg);
                    if (replyMsg.equals("username_notAvailable")) {
                        try {
                            signUp_repeat.setText("Username_notAvailable");
                            ps.close();
                            dis.close();
                            // mySocket.close();
                            SocketClient.getInstant().CloseSocket();
                            sign = false;

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else if (replyMsg.equals("success_signup")) {
                        try {
                            //  sign = true;
                            ps.close();
                            dis.close();
                            // mySocket.close();
                            SocketClient.getInstant().CloseSocket();
                            sign = true;

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                    if (sign == true) {
                        try {
                            Utility.changeTOScene(getClass(), event, "/login/login.fxml");
                        } catch (Exception ex) {
                            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
                }


                /*try {
                    ps.close();
                    dis.close();
                    // mySocket.close();
                    SocketClient.getInstant().CloseSocket();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }*/
            }).start();

        }

        /*new Thread(() -> {  
        try {
        String replyMsg = dis.readLine();
        System.out.println(replyMsg);
       
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         finally
    {
     try
    {
    ps.close();
    dis.close();
    mySocket.close();
    }
    catch(Exception ex)
    {
    ex.printStackTrace();
    }
    } }).start();*/
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //SocketClient.getInstance();
        // TODO
        /*try {
            // TODO
            mySocket = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(mySocket.getInputStream ());
            ps = new PrintStream(mySocket.getOutputStream ());
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

}
