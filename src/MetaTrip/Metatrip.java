package MetaTrip;

import javafx.application.Application;
import javafx.stage.Stage;
import MetaTrip.services.ReservationVoitureService;




public class Metatrip extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   public static void main(String[] args) {
        
    launch(args);        
   } 
//    
//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        
//        Parent root = FXMLLoader.load(getClass().getResource("View/chauffeurdesign.fxml"));
//        
//        Scene scene = new Scene(root);
//        
//        Stage stage = new Stage();
//        
//        stage.setScene(scene);
//        stage.show();
//    }
    


       
//Voiture vr =new Voiture(5,"220TU120",96,"image","Mercedes");
        
       

//     VoitureCRUD VC=new VoitureCRUD();
//  //  VC.ajouterVoiture(vr);
//  //    VC.afficherVoitures();
////Chauffeur chauff= new Chauffeur("salah","chakhari","picture",55555,"hayaemchiaman","sjjjjjjjjal");
//chauffeur_services ch= new chauffeur_services();
//ch.afficher();
//    
////             Voiture v98 =new Voiture(3,"220TU120",12,"image","bmw");
////        //VC.modifierVoiture(v98);
//               reservation_voiture rhv=new reservation_voiture();
   
   
                 ReservationVoitureService rs = new ReservationVoitureService();
                 
//                  
//             
    }
    
    
    
  


