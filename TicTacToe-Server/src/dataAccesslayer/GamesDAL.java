/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccesslayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author menna
 */
public class GamesDAL {

    public static int insertGame(Game game) throws SQLException{
        
                int rs;
                DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/xo_game", "root", "root");
                PreparedStatement ps = con.prepareStatement("insert into GAME VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)" );
                ps.setString(1,game.getPlayer_1());
                ps.setString(2,game.getPlayer_2());
                ps.setDate(3,game.getDate());
                ps.setInt(4,game.getStatus());
                ps.setInt(5,game.getStep_1());
                ps.setInt(6,game.getStep_2());
                ps.setInt(7,game.getStep_3());
                ps.setInt(8,game.getStep_4());
                ps.setInt(9,game.getStep_5());
                ps.setInt(10,game.getStep_6());
                ps.setInt(11,game.getStep_7());
                ps.setInt(12,game.getStep_8());
                ps.setInt(13,game.getStep_9());               
                rs = ps.executeUpdate();
                ps.close();
                con.close();
                return rs;
    }    
    
     public static Game[] getGameFromDatabase(String username) throws SQLException{
       
               
                DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
               Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/playerDatabase", "root", "root");
                PreparedStatement ps = con.prepareStatement("SELECT * FROM Game WHERE player_1 =" +username+ " OR player_2 = "+username);
                Game [] games = null ;
                ResultSet rs = ps.executeQuery();
                rs.first();
                int i =0;
                while(rs.next())
                {
                  games[i].setPlayer_1(rs.getString(1));
                  games[i].setPlayer_2(rs.getString(2));
                  games[i].setDate(rs.getDate(3));
                  games[i].setStatus(rs.getInt(4));
                  games[i].setStep_1(rs.getInt(5));
                  games[i].setStep_2(rs.getInt(6));
                  games[i].setStep_3(rs.getInt(7));
                  games[i].setStep_4(rs.getInt(8));
                  games[i].setStep_5(rs.getInt(9));
                  games[i].setStep_6(rs.getInt(10));
                  games[i].setStep_7(rs.getInt(11));
                  games[i].setStep_8(rs.getInt(12));
                  games[i].setStep_9(rs.getInt(13));
                       
                  i++;    
                }
                ps.close();
                con.close();
                return games;
    }    
}
