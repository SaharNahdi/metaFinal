/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetaTrip.GUI;

import MetaTrip.entities.Voiture;
import MetaTrip.services.VoitureCRUD;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author ASUS I7
 */
public class AjouterunevoitureController implements Initializable {

    @FXML
    private HBox chosenhotelCard;
    @FXML
    private HBox hosenhotelCard;
    @FXML
    private ImageView hotelimage;
    @FXML
    private ScrollPane scroll;
      @FXML
    private TextField matricule;
       @FXML
    private TextField puissance;
      @FXML
    private Label file_path;
  
    @FXML
    private TextField modele;
   
     @FXML
    private ImageView image_view;


    /**
     * Initializes the controller class.
     */
     
    VoitureCRUD vs = new VoitureCRUD();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Enregister();
        // TODO
    }    

    @FXML
    private void Enregister() {
         StringBuilder errors = new StringBuilder();
        if(matricule.getText().trim().isEmpty()){
            errors.append("- le champ matricule est vide.\n");
        }
        if(puissance.getText().trim().isEmpty()){
            errors.append("-le champ puissance fiscale est vide..\n");
        }
        if(file_path.getText().trim().isEmpty()){
            errors.append("- le champ image est vide..\n");
        }
        if(modele.getText().trim().isEmpty()){
            errors.append("- le champ modele est vide..\n");
        }
       
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Required Fields Empty");
            alert.setContentText(errors.toString());

            alert.showAndWait();
        }
        
        else{
            
            VoitureCRUD vs = new VoitureCRUD(
                    matricule.getText(),            
                    puissance.getText(),
                    file_path.getText(),
                    modele.getText()    );
            
          
            
        //Notification
        String tilte;
        String message;
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tilte = "Added Successful";
        message ="Voiture ajoutee avec success";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));
    }

    }
 

//    @FXML
//    private void insert_image(ActionEvent event) {
//             FileChooser open = new FileChooser();
//
//        Stage stage = (Stage) left_main.getScene().getWindow();
//
//        File file = open.showOpenDialog(stage);
//
//        if (file != null) {
//
//            String path = file.getAbsolutePath();
//
//            path = path.replace("\\", "\\\\");
//
//            file_path.setText(path);
//
//            Image image = new Image(file.toURI().toString(), 110, 110, false, true);
//
//            image_view.setImage(image);
//
//        } else {
//
//            System.out.println("NO DATA EXIST!");
//
//        }
//    }
//    
   }
