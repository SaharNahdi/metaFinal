/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetaTrip.GUI;

import MetaTrip.Config.JfreeChartApi;
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
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author salmouch
 */
public class GestionReservationVoitureController implements Initializable {

    @FXML
    private HBox chosenhotelCard;
    @FXML
    private HBox hosenhotelCard;
    @FXML
    private ImageView hotelimage;
    @FXML
    private TextField UtlisateurFX;
    @FXML
    private TextField VoitureFx;
    @FXML
    private TextField chaffeurFx;
    @FXML
    private TextField RentFx;
    @FXML
    private TextField TrajetFx;
    @FXML
    private TableView<ReservationVoiture> TableReservationVoitureView;
    @FXML
    private TableColumn<ReservationVoiture, Long> Idrvoit;
    @FXML
    private TableColumn<ReservationVoiture, Long> prix_rent;
    @FXML
    private TableColumn<ReservationVoiture, String> Trajet;
    @FXML
    private TableColumn<ReservationVoiture, Long> Idu;
    @FXML
    private TableColumn<ReservationVoiture, Long> idch;
    @FXML
    private TableColumn<ReservationVoiture, Long> Idvoit;

    /**
     * Initializes the controller class.
     */
    
    
    ReservationVoitureService rvs = new ReservationVoitureService() ;
    UserService us = new UserService();
    ChauffeurServices chs = new ChauffeurServices();
    VoitureService vs = new VoitureService();
    int idrvoit ; 
    
    ObservableList<ReservationVoiture> data=FXCollections.observableArrayList();
    @FXML
    private Label label;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         refreshlist();
         
         label.setText("Nombre  :"+rvs.nbReservationVoiture());
    }    
            public void refreshlist(){
            data.clear();
        try {
            data = FXCollections.observableArrayList(rvs.afficher());
            TableReservationVoitureView.setEditable(true);
            TableReservationVoitureView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherReservationVoitureController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        Idrvoit.setCellValueFactory(new PropertyValueFactory<>("Idrvoit"));
        prix_rent.setCellValueFactory(new PropertyValueFactory<>("prix_rent"));
        Trajet.setCellValueFactory(new PropertyValueFactory<>("Trajet"));
        Idu.setCellValueFactory(new PropertyValueFactory<>("Idu"));
        idch.setCellValueFactory(new PropertyValueFactory<>("idch"));
        Idvoit.setCellValueFactory(new PropertyValueFactory<>("Idvoit"));
        TableReservationVoitureView.setItems(data);
        }

    @FXML
    private void ReserverVoiture(ActionEvent event) {
        
    }

    @FXML
    private void GererReservation(ActionEvent event) {
    }

    @FXML
    private void ModifierReservation(ActionEvent event) {
            
            User u = us.FindById(Integer.parseInt(UtlisateurFX.getText()));
            Voiture v = vs.TrouveById(Integer.parseInt(VoitureFx.getText()));
            Chauffeur ch = chs.SearchById(Integer.parseInt(chaffeurFx.getText()));
            ReservationVoiture rv = new ReservationVoiture(
                    Float.parseFloat(RentFx.getText()),
                    TrajetFx.getText(),
                u,v,ch                 
            );
            rvs.modifier(idrvoit, rv);


        refreshlist();
        
    }


    @FXML
    private void Supprimer_reservation(ActionEvent event) {
        int Idrvoit;
        Idrvoit=TableReservationVoitureView.getSelectionModel().getSelectedItem().getIdrvoit();
        rvs.supprimer(Idrvoit);
        refreshlist();
    }

    @FXML
    private void fillforum(MouseEvent event) {
        ReservationVoiture rv=TableReservationVoitureView.getSelectionModel().getSelectedItem();
        idrvoit=rv.getIdrvoit();
        UtlisateurFX.setText(Integer.toString(rv.getIdu()));
        VoitureFx.setText(Integer.toString(rv.getIdvoit()));
        chaffeurFx.setText(Integer.toString(rv.getIdch()));
        RentFx.setText(Float.toString(rv.getPrix_rent()));
        TrajetFx.setText(rv.getTrajet());
    }

    @FXML
    private void Statistique(ActionEvent event) {
        ReservationVoitureService rvs = new ReservationVoitureService();
        HashMap<String, Double> data = rvs.StatistiqueParPrix();
        JfreeChartApi chart = new JfreeChartApi(data);
        chart.afficherStatistique();
    }
    
    
}
