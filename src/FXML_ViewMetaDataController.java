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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hamza Azam
 */
public class FXML_ViewMetaDataController implements Initializable {

    @FXML
    private Button backButton;
    
    
     private String s_con;
    private String userName;
    private String pass;
    private ArrayList<String> tablesDB;
    @FXML
    private Label tableName;
    @FXML
    private TableView tableview;
        private int dbChoice;//1 for my sql,2 for ms access

    private ObservableList<ObservableList>  data = FXCollections.observableArrayList(); 

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              Platform.runLater(() -> { ///////////////////////////////////////IMPORTANT 
                if(dbChoice==1){  
                    System.out.println("VIEW TABLE PAGE");
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
                     String tt=tableName.getText();
                     rs=stmt.executeQuery("describe "+tt);

                     for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                            //We are using non property style for making dynamic table
                            final int j = i;
                            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                                    return new SimpleStringProperty(param.getValue().get(j).toString());
                                }
                            });

                            tableview.getColumns().addAll(col);
                            System.out.println("Column [" + i + "] ");
                        }

                        /**
                         * ******************************
                         * Data added to ObservableList *
                         *******************************
                         */
                        while (rs.next()) {
                            //Iterate Row
                            ObservableList<String> row = FXCollections.observableArrayList();
                            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                                //Iterate Column
                                if(rs.getString(i)==null){
                                                        row.add("-");
                                }
                                  else{
                                row.add(rs.getString(i));
                                }
                            }
                            System.out.println("Row [1] added " + row);
                            data.add(row);
                        }
                         //FINALLY ADDED TO TableView
                        tableview.setItems(data);


                     ////////////////////////////////////////////////////////////////
                    } catch (SQLException ex) {
                        System.out.println("Wrong USER NAME OR PASS");
                        Logger.getLogger(FXML_DBmySQLController.class.getName()).log(Level.SEVERE, null, ex);
                    }           
                        
                }
                else if(dbChoice==2){
                    String tt = tableName.getText();
                    try {
                        ResultSet rs = ConnectionManager.getInstance().getMetaData().getColumns(null, null, tt, null);
                       
                            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(3));
                            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                                    return new SimpleStringProperty(param.getValue().get(3).toString());
                                }
                            });

                            tableview.getColumns().addAll(col);
                            //System.out.println("Column [" + i + "] ");
                        
                            col = new TableColumn(rs.getMetaData().getColumnName(5));
                            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                                    return new SimpleStringProperty(param.getValue().get(5).toString());
                                }
                            });

                            tableview.getColumns().addAll(col);
                            col = new TableColumn(rs.getMetaData().getColumnName(17));
                            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                                    return new SimpleStringProperty(param.getValue().get(17).toString());
                                }
                            });

                            tableview.getColumns().addAll(col);
                            col = new TableColumn(rs.getMetaData().getColumnName(22));
                            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                                    return new SimpleStringProperty(param.getValue().get(22).toString());
                                }
                            });

                            tableview.getColumns().addAll(col);
                            
                            while (rs.next()) {
                            //Iterate Row
                            ObservableList<String> row = FXCollections.observableArrayList();
                            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                                //Iterate Column
                                if(rs.getString(i)==null){
                                                        row.add("-");
                                }
                                  else{
                                row.add(rs.getString(i));
                                }
                            }
                            System.out.println("Row [1] added " + row);
                            data.add(row);
                        }
                         //FINALLY ADDED TO TableView
                        tableview.setItems(data);

                        
                    } catch (SQLException ex) {
                        Logger.getLogger(FXML_ViewMetaDataController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                
                
                }
        });
        // TODO
    }    

    @FXML
    private void submitOp(ActionEvent event) throws IOException {
        if (dbChoice==1){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_DBmySQL.fxml"));
                    //loader.setController(this);
                    Parent root=(Parent) loader.load();

                    FXML_DBmySQLController secondController=loader.getController();
                    //secondController.setTextc(databaseName.getText());
                    secondController.set_scon(s_con);//send connection string to second controller
                    secondController.setUserName(userName);//send username to second controller
                    secondController.setPass(pass);//send password to second controller
                    System.out.println("Connection String send to Second Controller");

                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(new Scene(root));
                    window.show();
        }
        else if(dbChoice==2){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_DBmsExcess.fxml"));
                    //loader.setController(this);
                    Parent root=(Parent) loader.load();

                    System.out.println("Connection String send to Second Controller");

                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(new Scene(root));
                    window.show();
        }
                    
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
    public void setTableName(String textA){
        tableName.setText(textA);
    }
    public void set_dbChoice(int textA){
        dbChoice=textA;
        
    }
}
