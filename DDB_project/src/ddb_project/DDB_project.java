/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddb_project;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jawad
 */
public class DDB_project {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName ("com.mysql.jdbc.Driver");

        Connection conn;
        try {
            conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/kairos", "root", "");
            System.out.println("Connection Established!");
        } catch (SQLException ex) {
            Logger.getLogger(DDB_project.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
