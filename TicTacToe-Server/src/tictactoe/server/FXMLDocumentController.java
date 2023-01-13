/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.server;

import com.sun.jndi.dns.DnsContextFactory;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 *
 * @author Dell
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    
    
     @FXML
    private AnchorPane anchor;



    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

 
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

}
