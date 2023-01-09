/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.simple.JSONObject;

/**
 *
 * @author Dell
 */
public class DataAccessmethods {
    
    
       public static int signUp(JSONObject positionJson ,int status) throws SQLException {
        int result = 0;                
        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/xo_game", "root", "root");
        //PreparedStatement pst = con.prepareStatement("INSERT INTO PLAYER VALUES (? , ?,? )");
        PreparedStatement pst = con.prepareStatement("INSERT INTO PLAYER (USERNAME,PASSWORD,EMAIL,STATUS) VALUES (? , ? , ? ,? )");
        
        /*pst.setString(1,name);
        pst.setString(2,email);
        pst.setString(3,password);
        pst.setInt(4,status);*/
        pst.setString(1, (String) positionJson.get("userName"));
        pst.setString(2, (String) positionJson.get("email"));
        pst.setString(3, (String) positionJson.get("password"));
        pst.setInt(4, status);
  
        result = pst.executeUpdate();

        con.commit();
        pst.close();
        con.close();
        return result;

    }
       
      
       
       
       
}
