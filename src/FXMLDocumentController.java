/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a03_04_ap;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import javafx.scene.Parent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



/**
 *
 * @author Hamza Azam
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button btnMySql;
    @FXML
    private Button buttonMsExcess;
    @FXML
    private Label password;
    @FXML
    private TextField databaseName;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField pass;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("HOME PAGE");
        
//    try{
//        Class.forName("com.mysql.jdbc.Driver");  
////here sonoo is database name, root is username and password
//    try (Connection con = DriverManager.getConnection(  
//            "jdbc:mysql://127.0.0.1:3306/apassignmentschema","root","12345678Hamza")) {
//        //here sonoo is database name, root is username and password
//        System.out.println("Connectiom MAde to MYSQL");
//        Statement stmt=con.createStatement();
//        int opt=0;
//        while(opt!=-1){
//        Scanner input;
//        input = new Scanner(System.in);
//        System.out.println("Enter 1 to create table");
//        System.out.println("Enter 2 to view database tables");
//        System.out.println("Enter 3 to describe table");
//        System.out.println("Enter 4 to view table data");
//        System.out.println("Enter 5 to insert record into table");
//        System.out.println("Enter 6 to delete record from table");
//        System.out.println("Enter -1 to exit");
//        opt=input.nextInt();
//        if(opt==1){
//         System.out.println("Enter Name");
//         String name="";
//         name=input.next();
//         input.nextLine();
//         String cols="";
//         System.out.println("Enter coulmns ");
//         cols=input.nextLine();
//         String createtable = "Create table "+name+" ("+cols+")";
//         System.out.println(createtable);
//         stmt.executeUpdate(createtable); //What does this statement do?
//        //String insertLehigh = "Insert into Lehigh values"+"(123456789,'Haider',100)";
//       // stmt.executeUpdate(insertLehigh);
//        }
//        ResultSet rs;//stmt.executeQuery("select * from Lehigh");
//      /*  while(rs.next()){
//           int ssn = rs.getInt("SSN"); 
//           String name = rs.getString("NAME"); 
//           int marks = rs.getInt("MARKS");
//           System.out.println(ssn+" "+name+" "+marks);
//    }*/
//       if(opt==2){
//        // ArrayList<String> tables=new ArrayList();
//         rs=stmt.executeQuery("show tables");
//         while(rs.next())
//         {
//          //   tables.add(rs.getString(1));
//            System.out.println(rs.getString(1));
//         }
//       }
//         ArrayList<String> tables=new ArrayList();
//         rs=stmt.executeQuery("show tables");
//         while(rs.next())
//         {
//            tables.add(rs.getString(1));
//         }
//        if(opt==3)
//        {
//            // ArrayList<String> tables=new ArrayList();
//            // for(int i=0;i<tables.size();i++){
//             //String table=tables.get(i);
//             //System.out.println(table);
//           //}
//                System.out.println("Enter table Name");
//                String tname=input.next();
//                 System.out.println("// "+tname+"\\\\");
//                 rs=stmt.executeQuery("describe "+tname);
//                 while(rs.next())
//                {
//                    for(int j=1;rs.getString(j)!=null;j++){
//                    System.out.print(rs.getString(j)+" ");}
//                    System.out.println();
//                }
//        }
//        
//        if(opt==4)
//        {
//            System.out.println("Enter table Name");
//            String tname=input.next();
//            rs=stmt.executeQuery("select * from "+tname);
//            int colCount=rs.getMetaData().getColumnCount();
//            while(rs.next())
//            {
//                String row = "";
//                for (int k = 1; k <= colCount;k++) {
//                row += rs.getString(k) + ", ";    
//                }
//                System.out.println(row);
//            }
//        }
//        if(opt==5)
//        {
////            System.out.println("Enter table Name");
////            String tname=input.next();
////            rs=stmt.executeQuery("select * from "+tname);
////            int colCount=rs.getMetaData().getColumnCount();
////            while(rs.next())
////            {
////                String row = "";
////                for (int k = 1; k <= colCount;k++) {
////                row += rs.getString(k) + ", ";    
////                }
////                System.out.println(row);
////            }
//            String tableN="";
//            System.out.println("Insert table Name");
//            tableN=input.next();
//            input.nextLine();
//            String values="";
//            System.out.println("Insert table Name");
//            values=input.nextLine();
//            String insertTable = "Insert into "+tableN+" values("+values+")";
//            stmt.executeUpdate(insertTable);
//
//        }
//        if(opt==6)
//        {
//            System.out.println("Enter table Name");
//            String tname=input.next();
//            rs=stmt.executeQuery("select * from "+tname);
//            int colCount=rs.getMetaData().getColumnCount();
//            while(rs.next())
//            {
//                String row = "";
//                for (int k = 1; k <= colCount;k++) {
//                row += rs.getString(k) + ", ";    
//                }
//                System.out.println(row);
//            }
//        }
//      }
//    }catch(SQLException e){ System.out.println(e);}  
//     } catch(ClassNotFoundException e)
//     {
//         System.out.println("hsajsahs");
//     }
        // TODO
    }    

    
    @FXML
    private void changeViewToMySqlDB(ActionEvent event) throws IOException {
        
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } 
        catch (ClassNotFoundException ex) {
            System.out.println("Connectiom NOT MADE, Driver check");
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con;
        
        String u=userName.getText();
        String p=pass.getText();
        String dn=databaseName.getText();
        try {
            String tempS="jdbc:mysql://127.0.0.1:3306/";
            String s_con=tempS.concat(dn);
                        // System.out.println("Connection Str :"+s_con);
            
            con= DriverManager.getConnection(s_con,u,p);
             ///////////////////////////////////////////
             System.out.println("Connectiom successful");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_DBmySQL.fxml"));
            //loader.setController(this);
            Parent root=(Parent) loader.load();

            FXML_DBmySQLController secondController=loader.getController();
            //secondController.setTextc(databaseName.getText());
            secondController.set_scon(s_con);//send connection string to second controller
            secondController.setUserName(u);//send username to second controller
            secondController.setPass(p);//send password to second controller
            System.out.println("Connection String send to Second Controller");
            
            System.out.println(databaseName.getText());

            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.show();
            //////////////////////////////////////////////////
        } 
        catch (SQLException ex) {
            System.out.println("Connectiom NOT MADE wrong userName or pass  :"+userName.getText()+pass.getText());
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        //here sonoo is database name, root is username and password
        System.out.println("Connectiom MAde to MYSQL");
        
        
        
       
        
        //////////////////////////////////
        //Parent MySqlParent=FXMLLoader.load(getClass().getResource("FXML_DBmySQL.fxml"));
        //Scene MySqlViewScene= new Scene(MySqlParent);
        
        //This Line gets the stage info
        //Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        //window.setScene(MySqlViewScene);
        //window.show();
        ////////////////////////////////////
    }

    @FXML
    private void changeViewToMsExcessDB(ActionEvent event) throws IOException {
        try {
            Statement state = ConnectionManager.getInstance().createStatement();
            
            Parent MsExcessParent=FXMLLoader.load(getClass().getResource("FXML_DBmsExcess.fxml"));
            Scene MsExcessViewScene= new Scene(MsExcessParent);

            //This Line gets the stage info
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(MsExcessViewScene);
            window.show();
        } catch (SQLException ex) {
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("connection failed in ms access");
        }
        
    }

    @FXML
    private void goToAboutView(ActionEvent event) throws IOException {
        Parent AboutParent=FXMLLoader.load(getClass().getResource("FXML_About.fxml"));
        Scene AboutViewScene= new Scene(AboutParent);
        
        //This Line gets the stage info
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(AboutViewScene);
        window.show();
    }
    
}
