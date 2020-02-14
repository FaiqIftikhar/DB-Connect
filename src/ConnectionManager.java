/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a03_04_ap;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Hamza Azam
 */
public class ConnectionManager {
    private static Connection conn =null;
    private static String DBName;
    private ConnectionManager(){
        
    }
    public static Connection getInstance(){
        if(conn == null){
            try{
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                DBName = "Students";
                String msAccDB = "D:\\" + DBName+ ".accdb";
			String dbURL = "jdbc:ucanaccess://" 
					+ msAccDB; 

			// Step 2.A: Create and 
			// get connection using DriverManager class
			conn = DriverManager.getConnection(dbURL); 
                        System.out.println("Connection get succes.");
            }catch(Exception e){
                System.out.println("Exception in MSACCESS Connection.");
            }
        }
        return conn;
    }
    public static String getDBName() {
    	return DBName;
    }
}
