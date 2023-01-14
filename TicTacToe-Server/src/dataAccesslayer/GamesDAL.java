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
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/playerDatabase", "root", "root");
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
    
     public static  ArrayList<Game> getGameFromDatabase(String username) throws SQLException{
       
                                       //org.apache.derby.jdbc.ClientDriver        
                DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
               Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/playerDatabase", "root", "root");
                PreparedStatement ps = con.prepareStatement("SELECT * FROM Game WHERE player_1 =" +username+ " OR player_2 = "+username);
                ArrayList<Game> games = new ArrayList<Game>();
                ResultSet rs = ps.executeQuery();
                rs.first();
                int i =0;
                while(rs.next())
                {
                    Game game = new Game();
                  game.setPlayer_1(rs.getString(1));
                  game.setPlayer_2(rs.getString(2));
                  game.setDate(rs.getDate(3));
                  game.setStatus(rs.getInt(4));
                  game.setStep_1(rs.getInt(5));
                  game.setStep_2(rs.getInt(6));
                  game.setStep_3(rs.getInt(7));
                  game.setStep_4(rs.getInt(8));
                  game.setStep_5(rs.getInt(9));
                  game.setStep_6(rs.getInt(10));
                  game.setStep_7(rs.getInt(11));
                  game.setStep_8(rs.getInt(12));
                  game.setStep_9(rs.getInt(13));
                  games.add(game);
                      
                }
                ps.close();
                con.close();
                return games;
    }    
}
