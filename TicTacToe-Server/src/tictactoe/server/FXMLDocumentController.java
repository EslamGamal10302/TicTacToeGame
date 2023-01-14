/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.server;

import com.sun.jndi.dns.DnsContextFactory;
import gameHandler.PlayerHandler;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Dell
 */
public class FXMLDocumentController implements Initializable {

       ServerSocket serverSocket;
    DataInputStream serverDataInput;
   PrintStream serverDataOutput;
    Thread mainThread;
    @FXML
    private Label label;
    
    
     @FXML
    private AnchorPane anchor;
    private boolean online;

     


    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
<<<<<<< HEAD
    
    public void changeStatistics (){
        
           ArrayList<Integer> numberOfPlayers = new ArrayList<>();
           
        try {
            numberOfPlayers = DataAccessmethods.getCountOfPlayers();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Defining the x axis               
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
                "players")));
        xAxis.setLabel("kind of players");

        //Defining the y axis 
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("count");
      
        BarChart barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("show statistics of players");
        
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
       //XYChart.Series series1 = new XYChart.Series();
        series1.setName("online");
       
        //numberOfPlayers.get(1)
        
        series1.getData().add(new XYChart.Data<>("players", numberOfPlayers.get(0)));

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("in game");
        //series2.getData().add(new XYChart.Data<>("players", numberOfPlayers.get(1)));

        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
       series3.setName("offline");
       
      // series3.getData().add(new XYChart.Data<>("players", numberOfPlayers.get(2)));

        barChart.getData().addAll(series1, series2,series3);
        
          xAxis.setTickLabelFill(Color.YELLOW);
          yAxis.setTickLabelFill(Color.YELLOW);
          barChart.setMaxWidth(300);
          barChart.setMaxHeight(300);
          barChart.setLayoutX(140);
          barChart.setLayoutY(0);
          anchor.getChildren().add(barChart);
        
    }
=======
    @FXML void startButtenIsPresed(ActionEvent event){

        if(!online){
            startThread();
            online=true;
             ((Button)event.getSource()).setText("Stop");
        }else{
            online=false;
            ((Button)event.getSource()).setText("start");
              try {
               serverSocket.close();
               mainThread.stop();
           } catch (IOException ex) {
               Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
           }

        }
   
    }

    public void startThread() {
        mainThread= new Thread(() -> {
            try {
                
                serverSocket = new ServerSocket(5005);
                while (true){
                    Socket clientSocket = serverSocket.accept();
                    serverDataInput = new DataInputStream(clientSocket.getInputStream());
                    serverDataOutput = new PrintStream(clientSocket.getOutputStream());
                    BufferedReader clientBufferedReader = new BufferedReader(new InputStreamReader(serverDataInput));
                    String client = clientBufferedReader.readLine();
                    System.out.println(client);
                    JSONObject positionJson= (JSONObject) new JSONParser().parse(client);
                    
                    long type =(long) positionJson.get("type");
                    if (type ==1){
                        /*String name_singUp =(String) positionJson.get("userName");
                        String email_signUp =(String) positionJson.get("email");
                        String password_signUp =(String) positionJson.get("password");
                        
                        System.out.println(name_singUp);
                        System.out.println(email_signUp);
                        System.out.println(password_signUp);*/
                        int status=2;
                        int checkUnique=DataAccessmethods.checkUnique(positionJson);
                        if (checkUnique==0){
                            int x =DataAccessmethods.signUp(positionJson,status);
                            System.out.println(x);
                            serverDataOutput.println("success_signup");
                            System.out.println("success_signup");
                        }
                        else{
                            serverDataOutput.println("username_notAvailable");
                            System.out.println("username_notAvailable");
                        }
                    }
                    else if (type==2){
                        int status=1;
                        String name_login =(String) positionJson.get("userName");
                        String password_login =(String) positionJson.get("password");
                        System.out.println(name_login);
                        System.out.println(password_login);
                        int x =DataAccessmethods.login(positionJson,status);
                        System.out.println(x);
                        if (x==1){
                            System.out.println("success");
                            serverDataOutput.println("success_login");
                            new PlayerHandler(clientSocket, name_login);
                        }
                        else{
                            System.out.println("fail login");
                            serverDataOutput.println("fail_login");
                        }
                    }
                    
                    //  int x=DataAccessmethods.login(positionJson);
                    // to send the data to DB for login
                    //  DataAccessmethods.signUp(positionJson);
                    //to send the data to DB to sign up
                    //serverDataOutput.println("recevied");
                }
                //new RequestHandler(clientSocket);
            }   catch (IOException | ParseException ex) {
                Logger.getLogger(TicTacToeServer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(TicTacToeServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            
              
            
        });
        mainThread.start();
    }
   
    
>>>>>>> 52676c483d5cc50d2c2f5db784f0e0b31ad5dd09

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
           ArrayList<Integer> numberOfPlayers = new ArrayList<>();
           
        try {
            numberOfPlayers = DataAccessmethods.getCountOfPlayers();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Defining the x axis               
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
                "players")));
        xAxis.setLabel("kind of players");

        //Defining the y axis 
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("count");
      
        BarChart barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("show statistics of players");
        
      

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
       //XYChart.Series series1 = new XYChart.Series();
        series1.setName("online");
       
        //numberOfPlayers.get(1)
        
        series1.getData().add(new XYChart.Data<>("players", numberOfPlayers.get(0)));

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("in game");
        series2.getData().add(new XYChart.Data<>("players", numberOfPlayers.get(1)));

        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
       series3.setName("offline");
       
       series3.getData().add(new XYChart.Data<>("players", numberOfPlayers.get(2)));

        barChart.getData().addAll(series1, series2,series3);
        
          xAxis.setTickLabelFill(Color.YELLOW);
      yAxis.setTickLabelFill(Color.YELLOW);
          barChart.setMaxWidth(300);
          barChart.setMaxHeight(300);
          barChart.setLayoutX(140);
          barChart.setLayoutY(0);
        anchor.getChildren().add(barChart);
    
    }

}
