/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assets;

import gameBoard.TwoPlayersGameBoardController;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import tictactoe.client.Utility;
import welcome.HomeController;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class ReplayBoardController implements Initializable {

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
    private Button record;

    private String move;

    private Image image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ImageView[] myButtons = {position_1, position_2, position_3, position_4, position_5, position_6, position_7, position_8, position_9};
            move = "x";
            FileWriter file = null;
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("record.json"));
            System.out.println(jsonObject.keySet().size());
            int size = jsonObject.keySet().size();
            new Thread(() -> {
                for (int i = 1; i <= size; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ReplayBoardController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    int j = i;

                    long position = (long) jsonObject.get(String.valueOf(i));
                    Platform.runLater(() -> {
                        if (j < (size - 1)) {
                            if (move.equalsIgnoreCase("x")) {
                                image = new Image("/assets/xchar (1).png");
                                int x = (int) position;
                                System.out.println(x);
                                myButtons[(int) position - 1].setImage(image);
                                move = "o";
                            } else {

                                image = new Image("/assets/ochar (1).png");
                                int x = (int) position;
                                System.out.println(x);
                                myButtons[(int) position - 1].setImage(image);
                                move = "x";
                            }
                        } else {
                            long firstEnd = (long) jsonObject.get(String.valueOf(size - 1));
                            int first = (int) firstEnd;
                            long secondEnd = (long) jsonObject.get(String.valueOf(size));
                            int last = (int) secondEnd;
                            if(first !=0 &&last !=0 ){
                            drawLine(myButtons[first - 1], myButtons[last - 1]);
                            }
                        }

                    });

                }
            }).start();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReplayBoardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReplayBoardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ReplayBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void backButtonClicked(MouseEvent event) {
        try {
            Utility.changeTOScene(getClass(), event, "/welcome/home.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void positionThreeClicked(MouseEvent event) {
    }

    @FXML
    private void positionSixClicked(MouseEvent event) {
    }

    @FXML
    private void positionOneClicked(MouseEvent event) {
    }

    @FXML
    private void positionFiveClicked(MouseEvent event) {
    }

    @FXML
    private void positionNineClicked(MouseEvent event) {
    }

    @FXML
    private void positionEightClicked(MouseEvent event) {
    }

    @FXML
    private void positionFourClicked(MouseEvent event) {
    }

    @FXML
    private void positionTwoClicked(MouseEvent event) {
    }

    @FXML
    private void positionSevenClicked(MouseEvent event) {
    }

    private void drawLine(ImageView b1, ImageView b2) {
        Bounds bound1 = b1.localToScene(b1.getBoundsInLocal());
        Bounds bound2 = b2.localToScene(b2.getBoundsInLocal());
        double x1, y1, x2, y2;
        x1 = (bound1.getMinX() + bound1.getMaxX()) / 2;
        y1 = (bound1.getMinY() + bound1.getMaxY()) / 2;
        x2 = (bound2.getMinX() + bound2.getMaxX()) / 2;
        y2 = (bound2.getMinY() + bound2.getMaxY()) / 2;
        Line line = new Line(x1, y1, x2, y2);
        line.setStyle("-fx-stroke: red;");
        line.setStrokeWidth(10);
        anchor.getChildren().add(line);
    }

}
