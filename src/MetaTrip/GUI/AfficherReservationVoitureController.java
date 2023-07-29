/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetaTrip.GUI;

import MetaTrip.entities.ReservationVoiture;
import MetaTrip.services.ChauffeurServices;
import MetaTrip.services.ReservationVoitureService;
import MetaTrip.services.UserService;
import MetaTrip.services.VoitureService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author salmouch
 */
public class AfficherReservationVoitureController implements Initializable {

    @FXML
    private HBox chosenhotelCard;
    @FXML
    private HBox hosenhotelCard;
    @FXML
    private ImageView hotelimage;
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
    
    ReservationVoitureService rv = new ReservationVoitureService();
    ObservableList<ReservationVoiture> data=FXCollections.observableArrayList();
    @FXML
    private TextField TFSearch;
    @FXML
    private ScrollPane scroll;
    
    ReservationVoitureService rvs = new ReservationVoitureService() ;
    UserService us = new UserService();
    ChauffeurServices chs = new ChauffeurServices();
    VoitureService vs = new VoitureService();
    int idrvoit ; 
    
    ObservableList<ReservationVoiture> d=FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       refreshlist();
       

        recherche_avance();
    }   
    public void recherche_avance() {
          
        try {
            data = FXCollections.observableArrayList(rv.afficher());
            //System.out.println(data);
            FilteredList<ReservationVoiture> filteredData = new FilteredList<>(data, b -> true);
            TFSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(rv -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (rv.getTrajet().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; 
                    } 
                    else if(String.valueOf(rv.getIdch()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else if(String.valueOf(rv.getIdrvoit()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else if(String.valueOf(rv.getIdu()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else if(String.valueOf(rv.getIdvoit()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else if(String.valueOf(rv.getPrix_rent()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    
                    else
                        return false; // Does not match.
                });
                
            });
            System.out.println(filteredData);
            TableReservationVoitureView.setItems(filteredData);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherReservationVoitureController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
        public void refreshlist(){


            data.clear();
        try {
            data = FXCollections.observableArrayList(rv.afficher());
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

      
}
