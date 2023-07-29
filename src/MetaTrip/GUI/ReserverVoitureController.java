/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetaTrip.GUI;

import MetaTrip.Config.MailerApi;
import MetaTrip.Config.SMSApi;
import MetaTrip.entities.Chauffeur;
import MetaTrip.entities.ReservationVoiture;
import MetaTrip.entities.User;
import MetaTrip.entities.Voiture;
import MetaTrip.services.ChauffeurServices;
import MetaTrip.services.ReservationVoitureService;
import MetaTrip.services.UserService;
import MetaTrip.services.VoitureService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author salmouch
 */
public class ReserverVoitureController implements Initializable {

    @FXML
    private HBox chosenhotelCard;
    @FXML
    private HBox hosenhotelCard;
    @FXML
    private ImageView hotelimage;
    @FXML
    private ScrollPane scroll;
    @FXML
    private TextField UtilisateurTx;
    @FXML
    private TextField VoitureTx;
    @FXML
    private TextField chauffeurTx;
    @FXML
    private TextField PrixRentTx;
    @FXML
    private TextField TrajetTx;

    /**
     * Initializes the controller class.
     */
    
    ReservationVoitureService rvs = new ReservationVoitureService() ;
    UserService us = new UserService();
    ChauffeurServices chs = new ChauffeurServices();
    VoitureService vs = new VoitureService();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Enregister(ActionEvent event)  {
        
               StringBuilder errors = new StringBuilder();
        if(UtilisateurTx.getText().trim().isEmpty()){
            errors.append("- Please enter a First Name.\n");
        }
        if(VoitureTx.getText().trim().isEmpty()){
            errors.append("- Please enter a Last Name.\n");
        }
        if(chauffeurTx.getText().trim().isEmpty()){
            errors.append("- Please enter a Username.\n");
        }
        if(PrixRentTx.getText().trim().isEmpty()){
            errors.append("- Please enter a Password.\n");
        }
        if(TrajetTx.getText().trim().isEmpty()){
            errors.append("- Please enter a Email.\n");
        }
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Required Fields Empty");
            alert.setContentText(errors.toString());

            alert.showAndWait();
        }
        
        else{
            
            User u = us.FindById(Integer.parseInt(UtilisateurTx.getText()));
            Voiture v = vs.TrouveById(Integer.parseInt(VoitureTx.getText()));
            Chauffeur ch = chs.SearchById(Integer.parseInt(chauffeurTx.getText()));
            ReservationVoiture rv = new ReservationVoiture(
                    Float.parseFloat(PrixRentTx.getText()),
                    TrajetTx.getText(),
                u,v,ch                 
            );
            
            
            
        //Notification
        String tilte;
        String message;
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tilte = "Added Successful";
        message ="Réservation créer avec success";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));
        
        // SEND MAIL
        MailerApi mailer = new MailerApi();
        mailer.SendMail("mahdi.homrani@esprit.tn", "Admin.");

        //SEND SMS
        SMSApi sms = new SMSApi();
        sms.sendSMS("+21658932889", "Admin.");
        
        rvs.ajouter(rv);
        }
        
    }
    
}
