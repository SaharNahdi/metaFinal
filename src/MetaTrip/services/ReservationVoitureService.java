/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetaTrip.services;

import MetaTrip.Config.DataSource;

import MetaTrip.entities.Chauffeur;
import MetaTrip.entities.ReservationVoiture;
import MetaTrip.entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 *
 * @author medal
 */
public class ReservationVoitureService implements IReservation_voiture {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    public ReservationVoitureService() {
        conn = DataSource.getInstance().getCnx();
    }


    @Override
    public void ajouter(ReservationVoiture rv) {

        String req2 = "INSERT INTO `reservation_voiture` (`prix_rent`,`Trajet`,`Idu`,`Idvoit`,`idch`) VALUES (?,?,?,?,?) ;";

        try {
            //System.out.println(rv.getVoiture().getIdvoit()+"  " +rv.getUser().getIdu());
            pste = conn.prepareStatement(req2);
            pste.setFloat(1, rv.getPrix_rent());
                         pste.setString(2, rv.getTrajet());
                           pste.setInt(3, rv.getUser().getIdu());
                           pste.setInt(4, rv.getVoiture().getIdvoit());
           pste.setInt(5, rv.getChauffeur().getidch());
            
            
   
        ;
            pste.executeUpdate();
            System.out.println("reservation de voiture  crée avec succes");

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public int sommePrixlocationvoiture() throws SQLException {
        int sum =0;

        String req = "SELECT SUM(reservation_voiture.Prix_rent) FROM reservation_Voiture;";
        try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);

            while (rs.next()) {
                sum = rs.getInt(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationVoitureService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sum;
    }
 


    @Override
    public void modifier(int idrvoit, ReservationVoiture rv) {
        String req = "UPDATE `reservation_voiture` SET "
                + "`prix_rent`=?,`Trajet`=?,`Idu`=?,`Idvoit`=?,`idch`=? WHERE `Idrvoit` = " + idrvoit;

        try {
            pste = conn.prepareStatement(req);
             pste.setFloat(1, rv.getPrix_rent());
                         pste.setString(2, rv.getTrajet());
                           pste.setInt(3, rv.getUser().getIdu());
                           pste.setInt(4, rv.getVoiture().getIdvoit());
           pste.setInt(5, rv.getChauffeur().getidch());

            pste.executeUpdate();
            System.out.println("reservation voiture   modifier");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int Idrvoit) {
        String delete = "DELETE FROM reservation_voiture  where Idrvoit  = ?";
        try {
            pste = conn.prepareStatement(delete);
            pste.setInt(1, Idrvoit);
            pste.executeUpdate();

        } catch (SQLException ex) {

            Logger.getLogger(ReservationVoitureService.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    }


    
    @Override
    public List<ReservationVoiture> afficher() throws SQLException {
        List<ReservationVoiture> lp = new ArrayList<>();



        Statement stm = conn.createStatement();


        String query = "SELECT * FROM reservation_voiture";
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
            ReservationVoiture rv = new ReservationVoiture();
            rv.setIdrvoit(rs.getInt("Idrvoit"));
            rv.setPrix_rent(rs.getFloat("prix_rent"));
            rv.setTrajet(rs.getString("Trajet"));
            rv.setIdu(rs.getInt("Idu"));
            rv.setIdch(rs.getInt("idch"));
            rv.setIdvoit(rs.getInt("Idvoit"));
            
            lp.add(rv);
        }
        return lp;
}
    
        
    public ReservationVoiture getById(int Idrvoit) throws SQLException{
        Statement stm = conn.createStatement();
        ReservationVoiture rv =new ReservationVoiture();
        String query = "select * from reservation_voiture where Idrvoit="+Idrvoit;
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
            rv.setIdrvoit(rs.getInt("Idrvoit"));
            rv.setPrix_rent(rs.getFloat("prix_rent"));
            rv.setTrajet(rs.getString("Trajet"));
            rv.setIdu(rs.getInt("Idu"));
            rv.setIdch(rs.getInt("idch"));
            rv.setIdvoit(rs.getInt("Idvoit"));
        }
        return rv;
    }
    
    
    public int nbReservationVoiture(){
        int nbr = 0;
        try {
            ResultSet set = DataSource.getInstance().
                    getCnx().prepareStatement("SELECT COUNT(Idrvoit) FROM reservation_voiture")
                    .executeQuery();
            if (set.next()) {
                nbr = set.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationVoitureService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nbr;
    }

    
    public HashMap<String, Double> StatistiqueParPrix() {
        HashMap<String, Double> data = new HashMap<>();
        try {
            Statement stm = conn.createStatement();
            String query = "SELECT prix_rent, COUNT(*) as nb FROM reservation_voiture GROUP BY prix_rent;";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int nb = rs.getInt("nb");
                String key = nb + " " + rs.getString("prix_rent");
                data.put(key, new Double(nb));
            }
            System.out.println(data.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
}
    
}
