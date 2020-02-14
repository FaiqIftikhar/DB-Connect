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
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.lang.Thread;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Hamza Azam
 */
public class FXML_DBmySQLController implements Initializable {

    /////////////SAMPLE CHECK
    String week_days[] = 
                   { "Table1", "Table2", "Table3", 
                                   "Table4", "Table5" }; 
   ////////////////////////////
    String ops[] = 
                   { "Edit", "View", "MetaData"}; 
    
   
    
    @FXML
    private ComboBox<String> tables; 
    @FXML
    private ComboBox<String> operations;
    @FXML
    private Button submitOp;

    @FXML
    private Label labCheck;

    private String s_con;
    private String userName;
    private String pass;
    private ArrayList<String> tablesDB;
    @FXML
    private TableView genTable;
    @FXML
    private TableColumn col1Name;
    @FXML
    private TableColumn col2NoOfCol;
    @FXML
    private TableColumn col3TotalTuples;
    @FXML
    private TreeView<String> DBsqlTreeView;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Platform.runLater(() -> { ///////////////////////////////////////IMPORTANT 
        System.out.println("MY SQL HOME PAGE");
        System.out.println("1)"+s_con+"2)"+userName+"3)"+pass);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Wrong Driver");
            Logger.getLogger(FXML_DBmySQLController.class.getName()).log(Level.SEVERE, null, ex);
        }
       Connection con;
        try {
            con=DriverManager.getConnection(s_con,userName,pass);
            System.out.println("Connection To MySQL Successfull");
            
            
            Statement stmt=con.createStatement();
            
           
            ResultSet rs;
        
            tablesDB=new ArrayList();
            rs=stmt.executeQuery("show tables");
             while(rs.next())
            {
                //col1Name.setCellValueFactory(new PropertyValueFactory(rs.getString(1)));
                tablesDB.add(rs.getString(1));
                System.out.println(rs.getString(1)); 
            }
            tables.getItems().setAll(tablesDB);
            System.out.println("Size: " + tablesDB.size());
            System.out.println("Size2213: " + tablesDB.size());
            //create root
            String DBName = s_con.substring(28);
              TreeItem<String> root = new TreeItem<>(DBName);
            //  root.setExpanded(true);
              //root.setExpanded(true);
              //create child
              

              //tableview.getColumns().addAll(col);
              ArrayList<TreeItem<String>> itemChild = new ArrayList<>();
              for(int i=0;i<tablesDB.size();++i) {
              	System.out.println("Tables: " + i + " -> " + tablesDB.get(i));
              	
//              	for (int t = 1; t <= rs2.getMetaData().getColumnCount(); t++) {
//                      //Iterate Column
//              		System.out.println("CNAME : "+ rs2.getString(t) );
//              	}
//                  
              	itemChild.add(new TreeItem<String>(tablesDB.get(i)));
              }
             
      		System.out.println("Size of tree : " + itemChild.size());
             // itemChild.setExpanded(true);
              //root is the parent of itemChild
              root.getChildren().addAll(itemChild);
              DBsqlTreeView.setRoot(root);
            
            
            
        } catch (SQLException ex) {
            System.out.println("Wrong USER NAME OR PASS");
            Logger.getLogger(FXML_DBmySQLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        //tables.getItems().setAll("Table1", "Table2", "table3");
        operations.getItems().setAll("View Table", "View Meta Data", "Edit");
    
        // TODO
                });
    }    

    @FXML
    private void goToHomeView(ActionEvent event) throws IOException {
        Parent HomeParent=FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene HomeViewScene= new Scene(HomeParent);
        
        //This Line gets the stage info
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(HomeViewScene);
        window.show();
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

    @FXML
    private void choose_Table(ActionEvent event) {
        
        System.out.println("Chosen Table is :"+tables.getSelectionModel().selectedItemProperty().getValue());
        
    }

    @FXML
    private void chooseOperation(ActionEvent event) {
    
        
        System.out.println("Chosen Operation is :"+operations.getSelectionModel().selectedItemProperty().getValue());
        
    }

    @FXML
    private void submitOperation(ActionEvent event) throws IOException {
        if(operations.getSelectionModel().selectedItemProperty().getValue()=="View Table" && tables.getSelectionModel().selectedItemProperty().getValue()!=null){
                 

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_ViewTable.fxml"));
                    //loader.setController(this);
                    Parent root=(Parent) loader.load();

                    FXML_ViewTableController secondController=loader.getController();
                    //secondController.setTextc(databaseName.getText());
                    secondController.set_scon(s_con);//send connection string to second controller
                    secondController.setUserName(userName);//send username to second controller
                    secondController.setPass(pass);//send password to second controller
                    secondController.setTableName(tables.getSelectionModel().selectedItemProperty().getValue());
                    secondController.set_dbChoice(1);
                    System.out.println("Connection String send to Second Controller");

                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(new Scene(root));
                    window.show();
                    //////////////////////////////////////////////////
               
            

                //here sonoo is database name, root is username and password
                System.out.println("Connectiom Made..VIEW TABLE PAGE");
        }
        else if(operations.getSelectionModel().selectedItemProperty().getValue()=="View Meta Data" && tables.getSelectionModel().selectedItemProperty().getValue()!=null){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_ViewMetaData.fxml"));
                    //loader.setController(this);
                    Parent root=(Parent) loader.load();

                    FXML_ViewMetaDataController secondController=loader.getController();
                    //secondController.setTextc(databaseName.getText());
                    secondController.set_scon(s_con);//send connection string to second controller
                    secondController.setUserName(userName);//send username to second controller
                    secondController.setPass(pass);//send password to second controller
                    secondController.setTableName(tables.getSelectionModel().selectedItemProperty().getValue());
                    secondController.set_dbChoice(1);
                    System.out.println("Connection String send to Second Controller");

                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(new Scene(root));
                    window.show();
                    //////////////////////////////////////////////////
             
                System.out.println("Connectiom Made..VIEW META DATA PAGE");
        }
        else if(operations.getSelectionModel().selectedItemProperty().getValue()=="Edit" && tables.getSelectionModel().selectedItemProperty().getValue()!=null){
                           FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEditData.fxml"));
                    //loader.setController(this);
                    Parent root=(Parent) loader.load();

                    FXMLEditDataController secondController=loader.getController();
                    //secondController.setTextc(databaseName.getText());
                    secondController.set_scon(s_con);//send connection string to second controller
                    secondController.setUserName(userName);//send username to second controller
                    secondController.setPass(pass);//send password to second controller
                    secondController.setTableName(tables.getSelectionModel().selectedItemProperty().getValue());
                    secondController.set_dbChoice(1);
                    System.out.println("Connection String send to Second Controller");

                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(new Scene(root));
                    window.show();
                    //////////////////////////////////////////////////
             
                System.out.println("Connectiom Made..Edit DATA PAGE");
        }
        
    }
    public void setTextc(String textA){
        labCheck.setText(textA);
    }
    public void set_scon(String textA){
        s_con=textA;
    }
    public void setUserName(String textA){
        userName=textA;
    }
    public void setPass(String textA){
        pass=textA;
    }

    @FXML
    private void goToAbout(ActionEvent event) throws IOException {
        Parent AboutParent=FXMLLoader.load(getClass().getResource("FXML_About.fxml"));
        Scene AboutViewScene= new Scene(AboutParent);
        
        //This Line gets the stage info
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(AboutViewScene);
        window.show();
    }
}
