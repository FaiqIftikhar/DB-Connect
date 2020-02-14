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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hamza Azam
 */
public class FXML_DBmsExcessController implements Initializable {
 /////////////SAMPLE 
    String week_days[] = 
                   { "Table1", "Table2", "Table3", 
                                   "Table4", "Table5" }; 
    
    ///////////////////
    String ops[] = 
                   { "Edit", "View", "MetaData"}; 
    
    private ArrayList<String> tablesDB=new ArrayList<>();

    
    
    @FXML
    private ComboBox<String> tables; 
    @FXML
    private ComboBox<String> operations;
    @FXML
    private Button submit;
    @FXML
    private TreeView<String> DBTreeView;
  

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> { ///////////////////////////////////////IMPORTANT 
        System.out.println("MS ACCESS HOME PAGE");
        
            try {
//                Statement state = ConnectionManager.getInstance().createStatement();
                  ResultSet rs = ConnectionManager.getInstance().getMetaData().getTables(null, null, null, null);
                  //System.out.println(rs.getString(1));   
                  while(rs.next()){
                      
                    //tablesDB.add(rs.getString(1));
                    String tbname = rs.getString("TABLE_NAME");
                    System.out.println(tbname); 
                     tablesDB.add(tbname);
                  }
                  for(int i=0;i<tablesDB.size();i++){
                                    System.out.print(tablesDB.get(i)+" ---"); 

                  }
                  tables.getItems().setAll(tablesDB);
                  System.out.println("Size: " + tablesDB.size());
                  System.out.println("Size2213: " + tablesDB.size());
                  //create root
                    TreeItem<String> root = new TreeItem<>(ConnectionManager.getDBName());
                   // root.setExpanded(true);
                    //root.setExpanded(true);
                    //create child
                    

                    //tableview.getColumns().addAll(col);
                    ArrayList<TreeItem<String>> itemChild = new ArrayList<>();
                    for(int i=0;i<tablesDB.size();++i) {
                    	System.out.println("Tables: " + i + " -> " + tablesDB.get(i));
                    	
//                    	for (int t = 1; t <= rs2.getMetaData().getColumnCount(); t++) {
//                            //Iterate Column
//                    		System.out.println("CNAME : "+ rs2.getString(t) );
//                    	}
//                        
                    	itemChild.add(new TreeItem<String>(tablesDB.get(i)));
                    }
                   
            		System.out.println("Size of tree : " + itemChild.size());
                   // itemChild.setExpanded(true);
                    //root is the parent of itemChild
                    root.getChildren().addAll(itemChild);
                    DBTreeView.setRoot(root);
            } catch (SQLException ex) {
            System.out.println("connection failed in ms access");
            }
            
                    operations.getItems().setAll("View Table", "View Meta Data", "Edit");

                });
        
    
        // TODO
    }    
    public TreeItem<String> makeBranch(String title, TreeItem<String> parent){
    	TreeItem<String> item = new TreeItem<String>(title);
    	parent.getChildren().add(item);
    	return item;
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
    private void submitOp(ActionEvent event) throws IOException {
        if(operations.getSelectionModel().selectedItemProperty().getValue()=="View Table" && tables.getSelectionModel().selectedItemProperty().getValue()!=null){
                 

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_ViewTable.fxml"));
                    //loader.setController(this);
                    Parent root=(Parent) loader.load();

                    FXML_ViewTableController secondController=loader.getController();

                    secondController.setTableName(tables.getSelectionModel().selectedItemProperty().getValue());
                    secondController.set_dbChoice(2);//2 for ms access
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

                    secondController.setTableName(tables.getSelectionModel().selectedItemProperty().getValue());
                    secondController.set_dbChoice(2);
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

                    secondController.setTableName(tables.getSelectionModel().selectedItemProperty().getValue());
                    secondController.set_dbChoice(2);
                    System.out.println("Connection String send to Second Controller");

                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(new Scene(root));
                    window.show();
                    //////////////////////////////////////////////////
             
                System.out.println("Connectiom Made..Edit DATA PAGE");
        }
    }
    
    
}
