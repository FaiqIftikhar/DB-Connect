/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a03_04_ap;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hamza Azam
 */
public class FXML_AboutController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
}
