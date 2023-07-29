/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetaTrip.GUI;

import MetaTrip.Config.DataSource;
import MetaTrip.entities.ReservationVoiture;
import MetaTrip.entities.Voiture;
import MetaTrip.services.VoitureService;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class GestionVoitureController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private TableView<Voiture> tab_voiture;
    @FXML
    private TableColumn<Voiture, Integer> id_tab;
    @FXML
    private TableColumn<Voiture, String> matricule_tab;
    @FXML
    private TableColumn<Voiture, Integer> puissance_tab;
    @FXML
    private TableColumn<Voiture, String> image_tab;
    @FXML
    private TableColumn<Voiture, String> modele_tab;
    @FXML
    private TextField matricule;
    @FXML
    private TextField puissance;
    @FXML
    private TextField modele;
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

        int Idvoit;
    VoitureService vs = new VoitureService();
    ObservableList<Voiture> data = FXCollections.observableArrayList();
    Connection con = null;
    ResultSet rs = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id_tab.setVisible(false);
        refreshlist();
    }    
        public void refreshlist(){
            data.clear();
            data = FXCollections.observableArrayList(vs.afficherVoitures());
            tab_voiture.setEditable(true);
            tab_voiture.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            
        id_tab.setCellValueFactory(new PropertyValueFactory<>("Idvoit"));
        matricule_tab.setCellValueFactory(new PropertyValueFactory<>("Matricule"));
        puissance_tab.setCellValueFactory(new PropertyValueFactory<>("Puissance_fiscalle"));
        image_tab.setCellValueFactory(new PropertyValueFactory<>("Image_v"));
        modele_tab.setCellValueFactory(new PropertyValueFactory<>("Modele"));
        tab_voiture.setItems(data);
    }
        
    @FXML
    private void tablehandleButtonAction(MouseEvent event) {
        
        if (tab_voiture.getSelectionModel().getSelectedItem() != null) {
            Voiture v = tab_voiture.getSelectionModel().getSelectedItem();
            Idvoit=v.getIdvoit();
            matricule.setText(v.getMatricule());
            puissance.setText(Integer.toString(v.getPuissance_fiscalle()));
            modele.setText(v.getModele());
            String picture = "file:" + v.getImage_v();
            Image image = new Image(picture, 110, 110, false, true);
            image_view.setImage(image);
            String path = v.getImage_v();
            file_path.setText(path);
            file_path.setOpacity(0);
        }

       refreshlist();
    }

    @FXML
    private void Delete(ActionEvent event) {
                con = DataSource.getInstance().getCnx();
        String delete = "DELETE  FROM voiture  where Idvoit = ?";
        try {
            PreparedStatement st = con.prepareStatement(delete);
            st.setInt(1,tab_voiture.getSelectionModel().getSelectedItem().getIdvoit());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deleting voiture");

            alert.showAndWait();
            st.executeUpdate();
            //AffichageHotel();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        refreshlist();
    }

    @FXML
    private void Edit(ActionEvent event) {
        updateVoiture();
        refreshlist();
    }
     public void updateVoiture(){
        
        Voiture v = new Voiture(matricule.getText(),
                                Integer.parseInt(puissance.getText()),
                                file_path.getText(),
                                modele.getText());                   
        vs.modifierVoiture(Idvoit, v);
         
    }
    @FXML
    private void Save(ActionEvent event) {
                Voiture v = new Voiture(matricule.getText(),
                                Integer.parseInt(puissance.getText()),
                                file_path.getText(),
                                modele.getText());                   
        vs.ajouterVoiture(v);
         refreshlist();
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
    
}
