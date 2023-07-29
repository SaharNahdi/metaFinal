/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetaTrip;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author salmouch
 */
public class MainFx extends Application {
    
    @Override
    public void start(Stage stage) {

        
        try{
                                  //  Parent root = FXMLLoader.load(getClass().getResource("/MetaTrip/GUI/Gestionvoiture.fxml"));

           Parent root = FXMLLoader.load(getClass().getResource("/MetaTrip/GUI/GestionVoiture.fxml"));
                     //  Parent root = FXMLLoader.load(getClass().getResource("/MetaTrip/GUI/AfficherReservationVoiture.fxml"));
                       //  Parent root = FXMLLoader.load(getClass().getResource("/MetaTrip/GUI/GestionReservationVoiture.fxml"));
                        //Parent root = FXMLLoader.load(getClass().getResource("MetaTrip/GUI/Ajouterunevoiture.fxml"));
                      //  Parent root = FXMLLoader.load(getClass().getResource("/MetaTrip/GUI/ReserverVoiture.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            }catch(Exception e){
                System.out.println(e.getMessage());
            }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
