/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aracbakim;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author alfa
 */
public class AracBakim extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        try {
            AnchorPane belirsiz = (AnchorPane)FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene scene = new Scene(belirsiz,1200,800);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        	
    }

 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        launch(args);
        
        
        
        
        
        
            File file=new File("VeriTabaniTemp.txt");
                if (!file.exists()) {
                        file.createNewFile();
                }
        
        
    }
    
}
