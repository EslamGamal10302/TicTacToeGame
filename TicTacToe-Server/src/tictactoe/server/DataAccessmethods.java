/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.server;

import dataAccesslayer.Player;
import gameHandler.PlayerHandler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import org.json.simple.JSONObject;

/**
 *
 * @author Dell
 */
public class DataAccessmethods {

    public static int signUp(JSONObject positionJson, int status) throws SQLException {
        int result = 0;
        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());

        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/xo_game", "root", "root");
        PreparedStatement pst = con.prepareStatement("INSERT INTO PLAYER (USERNAME,PASSWORD,EMAIL,STATUS,NO_GAMES,SCORE) VALUES (? , ? , ? ,?,0 ,0 )");

        pst.setString(1, (String) positionJson.get("userName"));
        pst.setString(2, (String) positionJson.get("password"));
        pst.setString(3, (String) positionJson.get("email"));
        pst.setInt(4, status);

        result = pst.executeUpdate();

        con.commit();
        pst.close();
        con.close();
        return result;

    }

    public static int login(JSONObject positionJson, int status) throws SQLException {
        int result = 0;
        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/xo_game", "root", "root");
        PreparedStatement pst = con.prepareStatement("SELECT * FROM PLAYER " + "WHERE USERNAME LIKE ? AND PASSWORD LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        pst.setString(1, (String) positionJson.get("userName"));
        pst.setString(2, (String) positionJson.get("password"));
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            PreparedStatement pst_2 = con.prepareStatement("UPDATE  PLAYER  SET STATUS= ?  WHERE USERNAME =?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst_2.setInt(1, status);
            pst_2.setString(2, (String) positionJson.get("userName"));
            int rs2 = pst_2.executeUpdate();
            pst_2.close();
            result = 1;
        }

        con.commit();
        pst.close();
        con.close();

        return result;

    }
    
     public static int inGame(int status,String player1 ,String player2) throws SQLException {
        int result = 0;
        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/xo_game", "root", "root");
            PreparedStatement pst_2 = con.prepareStatement("UPDATE  PLAYER  SET STATUS= ?  WHERE USERNAME =? OR  USERNAME =? ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst_2.setInt(1, status);
            pst_2.setString(2, player1);
            pst_2.setString(3, player2);
            int rs2 = pst_2.executeUpdate();
           // FXMLDocumentController fxml = new FXMLDocumentController();
            //fxml.changeStatistics();
            result = 1;
        

        con.commit();
        pst_2.close();
        con.close();

        return result;

    }
      public static int online(int status,String player1 ,String player2) throws SQLException {
        int result = 0;
        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/xo_game", "root", "root");
            PreparedStatement pst_2 = con.prepareStatement("UPDATE  PLAYER  SET STATUS= ?  WHERE USERNAME =? OR  USERNAME =? ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst_2.setInt(1, status);
            pst_2.setString(2, player1);
            pst_2.setString(3, player2);
            int rs2 = pst_2.executeUpdate();
            result = 1;
        

        con.commit();
        pst_2.close();
        con.close();

        return result;

    }
       public static int noGame(String player1 ,String player2) throws SQLException {
        int result = 0;
        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/xo_game", "root", "root");
            PreparedStatement pst_2 = con.prepareStatement("UPDATE  PLAYER  SET NO_GAMES=NO_GAMES+1  WHERE USERNAME =? OR  USERNAME =? ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
    
            pst_2.setString(2, player1);
            pst_2.setString(3, player2);
            int rs2 = pst_2.executeUpdate();
            result = 1;
        

        con.commit();
        pst_2.close();
        con.close();

        return result;

    }

     public static int score(String player2) throws SQLException {
        int result = 0;
        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/xo_game", "root", "root");
            PreparedStatement pst_2 = con.prepareStatement("UPDATE  PLAYER  SET SCORE=SCORE+10  WHERE USERNAME =? ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            pst_2.setString(3, player2);
            int rs2 = pst_2.executeUpdate();
            result = 1;
        

        con.commit();
        pst_2.close();
        con.close();

        return result;

    }
    public static int checkUnique(JSONObject positionJson) throws SQLException {
        int check = 0;
        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/xo_game", "root", "root");
     
        PreparedStatement pst = con.prepareStatement("SELECT * FROM PLAYER " + "WHERE USERNAME LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        pst.setString(1, (String) positionJson.get("userName"));

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            check = 1;
        }

        con.commit();
        pst.close();
        con.close();

        return check;

    }

    public static ArrayList<Player> getPlayersFromDatabase() throws SQLException {

        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/xo_game", "root", "root");
        PreparedStatement ps = con.prepareStatement("SELECT * FROM PLAYER ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        ArrayList<Player> players = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        int i = 0;
        while (rs.next()) {
            Player myPlayer = new Player();
            myPlayer.setPlayerName(rs.getString(1));
            myPlayer.setNo_games(rs.getInt(4));
            myPlayer.setPlayerScore(rs.getInt(5));
            myPlayer.setPlayerStat(rs.getInt(6));
            players.add(myPlayer);

            i++;
        }
        ps.close();
        rs.close();
        con.close();
        return players;

    }

    public static ArrayList<Integer> getCountOfPlayers() throws SQLException {
        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/xo_game", "root", "root");
        ArrayList<Integer> numberOfPlayers = new ArrayList<Integer>(Collections.nCopies(3, 0));
        PreparedStatement pst = con.prepareStatement("SELECT COUNT(USERNAME) FROM PLAYER GROUP BY STATUS ORDER BY STATUS ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = pst.executeQuery();
        int i = 0;

        while (rs.next()) {
            numberOfPlayers.set(i, rs.getInt(1));
            System.out.println("number " + rs.getInt(1));
            i++;
        }

        con.commit();
        pst.close();
        con.close();

        return numberOfPlayers;

    }

}