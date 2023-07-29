/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetaTrip;


import MetaTrip.entities.Chauffeur;
import MetaTrip.entities.ReservationVoiture;
import MetaTrip.entities.User;
import MetaTrip.entities.Voiture;
import MetaTrip.services.ChauffeurServices;
import java.awt.BorderLayout;
import java.sql.SQLException;
import MetaTrip.services.ReservationVoitureService;
import MetaTrip.services.VoitureService;

/**
 *
 * @author Wala
 */
public class Main {

    public static void main(String[] args) throws SQLException {

        ReservationVoitureService reservationVoitureService = new ReservationVoitureService();
        ChauffeurServices chauffeurServices = new ChauffeurServices();
                VoitureService VoitureCRUD = new VoitureService();


        User u = new User(41);
        Voiture v = new Voiture(1);
        Chauffeur ch = new Chauffeur(666);
        
//      ReservationVoiture rv = new ReservationVoiture(500, "100km",u,v,ch);
//      reservationVoitureService.ajouter(rv);
//        Voiture v99 =new Voiture(99,"220TU120",8888888,"image","Mercedes");
//        VoitureService VC=new VoitureService();
//            VC.ajouterVoiture(v99);
            chauffeurServices.supprimer(668);
            
          

         
        // System.out.println(reservationVoitureService.afficher());
        
        //System.out.println(reservationVoitureService.getById(36));
        //System.out.println(reservationVoitureService.nbReservationVoiture());
        //reservationVoitureService.supprimer(36);
        
        //Chauffeur ch = new  Chauffeur("bb","aa","aa","aa","aa","aa");
        //chauffeurServices.ajouter(ch);
        //chauffeurServices.modifier(668, ch);
        //System.out.println(chauffeurServices.afficher()); 
        //chauffeurServices.supprimer(668);
    }

}
