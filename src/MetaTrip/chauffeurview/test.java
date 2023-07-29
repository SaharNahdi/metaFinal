/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetaTrip.chauffeurview;

//import Viewchauffeur.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ASUS I7
 */
public class test extends Application{  
          @Override
    public void start(Stage stage) throws Exception {  Parent root = FXMLLoader.load(getClass().getResource("chauffeur.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Chauffeur");
        stage.setScene(scene);
        stage.show();
    }
        public static void main(String[] args) throws Exception {
            launch(args);
        }

}
