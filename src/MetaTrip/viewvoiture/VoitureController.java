/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetaTrip.viewvoiture;

import MetaTrip.Config.DataSource;
import MetaTrip.entities.Voiture;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import MetaTrip.services.VoitureService;

/**
 * FXML Controller class
 *
 * @author ASUS I7
 */
public class VoitureController implements Initializable {

    @FXML
    private TextField idvoiture;
    @FXML
    private TextField mat;
    @FXML
    private TextField puiss;
    @FXML
    private TextField model;
    @FXML
    private Button insert;
    @FXML
    private Button supprimervoiture;
    @FXML
    private TableColumn<Voiture, Integer> idvoit2;
    @FXML
    private TableColumn<Voiture, String> mat2;
    @FXML
    private TableColumn<Voiture, Integer> puiss2;
    @FXML
    private TableColumn<Voiture, String> model2;
    @FXML
    private TableColumn<Voiture, String> image2;
    @FXML
    private TableView<Voiture> tabvoit;
    @FXML
    private AnchorPane left_main;
    @FXML
    private Label filepath;
    @FXML
    private ImageView imageview;
    @FXML
    private Button insertimage;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //insert();
      showData();
        // TODO
    }  
       @FXML
     public void insertImage(){
        System.out.println("testttttttttttttt");
        FileChooser open = new FileChooser();
        
        Stage stage = (Stage)left_main.getScene().getWindow();
        
        File file = open.showOpenDialog(stage);
        
        if(file != null){
            
            String path = file.getAbsolutePath();
            
            path = path.replace("\\", "\\\\");
            
            filepath.setText(path);

            Image image = new Image(file.toURI().toString(), 110, 110, false, true);
            
            imageview.setImage(image);
            
        }else{
            
            System.out.println("NO DATA EXIST!");
            
        }}
     
         public ObservableList<Voiture> dataList(){
        
        Connection con = DataSource.getInstance().getCnx();
        
        ObservableList<Voiture> dataList = FXCollections.observableArrayList();
        
        String sql = "SELECT  *  FROM `voiture`";
        
        try{
          
            PreparedStatement prepare = con.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
     
            
            while(rs.next()){
             Voiture v = new Voiture();                
              v.setIdvoit(rs.getInt(1));
                 v.setMatricule(rs.getString(2));
                     v.setPuissance_fiscalle(rs.getInt(3));
                     v.setImage_v(rs.getString(4));
                     v.setModele(rs.getString(5));
                     dataList.add(v);
                
            }
            
        }catch(Exception e){
        System.out.println(e.getMessage());
        }
        
        return dataList;
        
    }
            @FXML
    public void showData(){
       
        ObservableList<Voiture> showList = dataList();

        idvoit2.setCellValueFactory(new PropertyValueFactory<Voiture, Integer>("Idvoit"));
        mat2.setCellValueFactory(new PropertyValueFactory<Voiture, String>("Matricule"));
        puiss2.setCellValueFactory(new PropertyValueFactory<Voiture, Integer>("Puissance_fiscalle"));
        model2.setCellValueFactory(new PropertyValueFactory<Voiture, String>("Image_v"));
        image2.setCellValueFactory(new PropertyValueFactory<Voiture, String>("Modele"));
                
        tabvoit.setItems(showList);
        
    }

      private void insert() {
        Connection con = DataSource.getInstance().getCnx();
       String insert = "INSERT INTO voiture (Idvoit,`Matricule`,`Puissance_fiscalle`,`Image_v`,`Modele`) VALUES (?,?,?,?,?,) ;";
        try {
            
            PreparedStatement st = con.prepareStatement(insert);
            st.setString(1, idvoiture.getText());
            st.setString(2, mat.getText());
            st.setString(3, puiss.getText());
            st.setString(4, model.getText());
            st.setString(7, filepath.getText());
            filepath.setOpacity(0);
            
   
            Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Supprimer voiture");

		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText("'Voiture  est ajouté avec succés");

		alert.showAndWait();
            st.executeUpdate();
            showData();
        } catch (SQLException ex) {
            Logger.getLogger(VoitureService.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//   public void insertImage(){
//        
//        FileChooser open = new FileChooser();
//        
//        Stage stage = (Stage)left_main.getScene().getWindow();
//        
//        File file = open.showOpenDialog(stage);
//        
//        if(file != null){
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
//        }else{
//            
//            System.out.println("NO DATA EXIST!");
//            
//        }
//    } 

   
