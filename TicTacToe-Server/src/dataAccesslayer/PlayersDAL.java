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

/**
 *
 * @author 20106
 */
public class PlayersDAL {
   /* public int addContact() throws SQLException{
        int result = 0;

        DriverManager.registerDriver(new );
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DBTest", "root", "root");
        PreparedStatement pst= con.prepareStatement( " INSERT INTO Player VALUES ( ?,?,?,?,?,? )" );
        
        result= pst.executeUpdate(); 
        pst.close();
        con.close();
        return result;
    }*/
    /* public ResultSet getContact() throws SQLException{ 
        DriverManager.registerDriver(new );
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DBTest", "root", "root");
        PreparedStatement pst= con.prepareStatement( " select * from CONTACT",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY );
        ResultSet rs= pst.executeQuery() ;
        
        
        return rs;
    }*/
}
