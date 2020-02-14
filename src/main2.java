//package main2;
package a03_04_ap;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;

/**
 *
 * @author Hamza Azam
 */
public class main2 {
    public static void main(String[] args){
		// variables
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		// Step 2: Opening database connection
		try {

			
			// Step 2.A: Create and 
			// get connection using DriverManager class
			

			// Step 2.B: Creating JDBC Statement 
			statement = ConnectionManager.getInstance().createStatement();

			// Step 2.C: Executing SQL and 
			// retrieve data into ResultSet
			resultSet = statement.executeQuery("SELECT * FROM apmarks");

			System.out.println("ID\tName\t\t\tAge\tMatches");
			System.out.println("==\t================\t===\t=======");

			// processing returned data and printing into console
			while(resultSet.next()) {
				System.out.println(resultSet.getInt(1) + "\t" + 
						resultSet.getString(2) + "\t" + 
						resultSet.getString(3) + "\t" +
						resultSet.getString(4));
			}
		}
		catch(SQLException sqlex){
			sqlex.printStackTrace();
		}
		finally {
			// Step 3: Closing database connection
			try {
				if(null != connection) {
					// cleanup resources, once after processing
					resultSet.close();
					statement.close();

					// and then finally close connection
					connection.close();
				}
			}
			catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
		}
	}
}
