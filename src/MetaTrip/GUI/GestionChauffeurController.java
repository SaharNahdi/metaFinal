/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetaTrip.GUI;

import MetaTrip.Config.DataSource;
import MetaTrip.Config.MailerApi;
import MetaTrip.Config.SMSApi;
import MetaTrip.entities.Chauffeur;
import MetaTrip.entities.ReservationVoiture;
import MetaTrip.services.ChauffeurServices;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
public class GestionChauffeurController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private TableView<Chauffeur> tab_chauffeur;
    @FXML
    private TableColumn<Chauffeur, Integer> id_tab;
    @FXML
    private TableColumn<Chauffeur, String> nom_tab;
    @FXML
    private TableColumn<Chauffeur, String> prenom_tab;
    @FXML
    private TableColumn<Chauffeur, String> tel_tab;
    @FXML
    private TableColumn<Chauffeur, String> desc_tab;
    @FXML
    private TableColumn<Chauffeur, String> etat_tab;
    @FXML
    private TableColumn<Chauffeur, String> image_tab;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField tel;
    @FXML
    private Button delete;
    @FXML
    private Button bsave1;
    @FXML
    private Button bsave11;
    @FXML
    private Button insert_image;
    @FXML
    private ImageView image_view;
    @FXML
    private Label file_path;
    @FXML
    private TextField tfrecherche;
    @FXML
    private TextField desc2;
    @FXML
    private ComboBox<String> etat;
    ObservableList<String> etatt = FXCollections.observableArrayList("Disponible", "Non Disponible");
    int idch;
    ChauffeurServices chs = new ChauffeurServices();
    ObservableList<Chauffeur> data = FXCollections.observableArrayList();
    

    
     Connection con = null;
    ResultSet rs = null;
    @FXML
    private TextField Emailtf;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       id_tab.setVisible(false);
        etat.setPromptText("Selectioner l'etat du chauffeur");
        etat.setItems(etatt);
        
        id_tab.setVisible(false);
        refreshlist();
        // TODO
    }
             public void refreshlist(){
            data.clear();
            data = FXCollections.observableArrayList(chs.afficher());
            tab_chauffeur.setEditable(true);
            tab_chauffeur.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            
        id_tab.setCellValueFactory(new PropertyValueFactory<>("idch"));
        nom_tab.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_tab.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tel_tab.setCellValueFactory(new PropertyValueFactory<>("tel"));
        desc_tab.setCellValueFactory(new PropertyValueFactory<>("description"));
        etat_tab.setCellValueFactory(new PropertyValueFactory<>("etatDispo"));
        image_tab.setCellValueFactory(new PropertyValueFactory<>("photo"));
        tab_chauffeur.setItems(data);
        }
    @FXML
    private void tablehandleButtonAction(MouseEvent event) {
        if (tab_chauffeur.getSelectionModel().getSelectedItem() != null) {
            Chauffeur h = tab_chauffeur.getSelectionModel().getSelectedItem();
            idch=h.getIdch();
            nom.setText(h.getnom());
            prenom.setText(h.getprenom());
            tel.setText(h.gettel());
            desc2.setText(h.getdescription());
            tel.setText(h.gettel());
            etat.getSelectionModel().select(h.getetatDispo());

            String picture = "file:" + h.getphoto();
            Image image = new Image(picture, 110, 110, false, true);

            image_view.setImage(image);

            String path = h.getphoto();

            file_path.setText(path);
            file_path.setOpacity(0);
        }

    }

    @FXML
    private void insert_image(ActionEvent event) {
        FileChooser open = new FileChooser();
        Stage stage = (Stage) left_main.getScene().getWindow();
        File file = open.showOpenDialog(stage);
        if (file != null) {
            String path = file.getAbsolutePath();
            path = path.replace("\\", "\\\\");
            file_path.setText(path);
            Image image = new Image(file.toURI().toString(), 110, 110, false, true);
            image_view.setImage(image);
        } else {
            System.out.println("NO DATA EXIST!");
        }
    }

    @FXML
    private void Retour(ActionEvent event) {
    }
//
//    @FXML
//    private void EditHotel(ActionEvent event) {
//    }

    private void Afficher() {
        data.clear();
        data = getChauffeur();
        id_tab.setCellValueFactory(new PropertyValueFactory<>("idch"));
        nom_tab.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_tab.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        image_tab.setCellValueFactory(new PropertyValueFactory<>("photo"));
        tel_tab.setCellValueFactory(new PropertyValueFactory<>("tel"));
        desc_tab.setCellValueFactory(new PropertyValueFactory<>("description"));
        etat_tab.setCellValueFactory(new PropertyValueFactory<>("etatDispo"));
        tab_chauffeur.setItems(data);
    }

    private ObservableList<Chauffeur> getChauffeur() {
        ObservableList<Chauffeur> list = FXCollections.observableArrayList();

        Connection con = DataSource.getInstance().getCnx();
        String select = "SELECT * FROM chauffeur";

        try {
            PreparedStatement st = con.prepareStatement(select);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Chauffeur ch = new Chauffeur();
                ch.setidch(rs.getInt("idch"));
                ch.setnom(rs.getString("nom"));
                ch.setprenom(rs.getString("prenom"));
                 ch.setphoto(rs.getString("photo"));
                ch.settel(rs.getString("tel"));
                ch.setdescription(rs.getString("description"));
                ch.setetatDispo(rs.getString("etatDispo"));
              
                list.add(ch);
            }
        } catch (SQLException ex) {
            System.err.println("ereeur offfff" + ex);

        }
        return list;
    }
 private void delete() {
        con = DataSource.getInstance().getCnx();
        String delete = "DELETE  FROM chauffeur  where idch = ?";
        try {
            PreparedStatement st = con.prepareStatement(delete);
            st.setInt(1,tab_chauffeur.getSelectionModel().getSelectedItem().getidch());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deleting chauffeur");

            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText(" chauffeur " + nom.getText() + " avec ID" + tab_chauffeur.getSelectionModel().getSelectedItem().getidch() + " est supprimé avec succés");

            alert.showAndWait();

            st.executeUpdate();
            //AffichageHotel();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

   
    @FXML
    private void DeleteChauffeur(ActionEvent event) {
            System.out.println(event.toString());
        delete();
      //  clear();
       refreshlist();
        
    }

    @FXML
    private void EditChauffeur(ActionEvent event) {
        updateChauffeur();
        refreshlist();
        
    }
     public void updateChauffeur(){
        
        Chauffeur ch = new Chauffeur(nom.getText(),
                                prenom.getText(),
                                file_path.getText(),
                                tel.getText(),
                                desc2.getText(),
                                etat.getValue());
        chs.modifier(idch, ch);
        refreshlist(); 
    }
    @FXML
    private void SaveChauffeur(ActionEvent event) {
        Chauffeur ch = new Chauffeur(nom.getText(),
                                prenom.getText(),
                                file_path.getText(),
                                tel.getText(),
                                desc2.getText(),
                                etat.getValue()
                                );
                
                        //Notification
        String tilte;
        String message;
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tilte = "Added Successful";
        message ="Magasin créer avec success";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));
        
        
        // SEND MAIL
        MailerApi mailer = new MailerApi();
        mailer.SendMail(Emailtf.getText(), "Admin.");
        
                //SEND SMS
        SMSApi sms = new SMSApi();
        //sms.sendSMS("+216"+numero.getText(), "Admin.");
       // sms.sendSMS("+21620071775", "Admin.");
        chs.ajouter(ch);
        refreshlist();
    }
    
}


