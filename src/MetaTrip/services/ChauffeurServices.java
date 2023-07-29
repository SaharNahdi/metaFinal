/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetaTrip.services;
import MetaTrip.entities.Chauffeur;
import MetaTrip.Config.DataSource;
import MetaTrip.entities.ReservationVoiture;
import MetaTrip.entities.User;
import MetaTrip.entities.Voiture;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS I7
 */
public class ChauffeurServices implements Ichaufffeur {
     private final Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    public  ChauffeurServices() {
           conn = DataSource.getInstance().getCnx();

    }


    @Override
    public void ajouter(Chauffeur ch) {
//              System.out.println("595xssssssssssss"+ch); 
//              System.out.println("sssssssssssssssssssssssssssssssssssss"+ch.getetatDispo());
  String req = "INSERT INTO `chauffeur` (`nom`,`prenom`,`photo`,`tel`,`description`,`etatDispo`) VALUES (?,?,?,?,?,?)";
     
  try {	       
            pste = conn.prepareStatement(req);
           // pste.setInt(1,ch.getidch());
            pste.setString(1,ch.getnom());
            pste.setString(2,ch.getprenom());
            pste.setString(3,ch.getphoto());
            pste.setString(4,ch.gettel());
            pste.setString(5,ch.getdescription());
            pste.setString(6,ch.getetatDispo());
            pste.executeUpdate();
            System.out.println("Ajouter ajoutée avesc succès");
                
        } catch (SQLException ex) {
            Logger.getLogger(ChauffeurServices.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public void modifier(int idch, Chauffeur ch) {
            String req = "UPDATE  `chauffeur` SET "
                + "`nom`=?,`prenom`=?,`photo`=?,`tel`=?,`description`=?,`etatDispo`=? "
                + "WHERE idch = '" + idch + "'";
        System.out.println(ch);
        try {
            pste = conn.prepareStatement(req);
       
            pste.setString(1,ch.getnom());
            pste.setString(2,ch.getprenom());
            pste.setString(3,ch.getphoto());
            pste.setString(4,ch.gettel());
            pste.setString(5,ch.getdescription());
            pste.setString(6,ch.getetatDispo());
            pste.executeUpdate();
            System.out.println("Chauffeur modifiée");
           
            }
        catch (SQLException ex){
            Logger.getLogger(Chauffeur.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
       

    }

    @Override
    public void supprimer(int idch) {
              String delete = "DELETE FROM chauffeur  WHERE idch = ?";
        try {
            pste = conn.prepareStatement(delete);
            pste.setInt(1, idch);
            pste.executeUpdate();

        } catch (SQLException ex) {

            Logger.getLogger(ChauffeurServices.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
  public int nbChauffeurDispo() throws SQLException {
   int nb=0;
   
          String req = "SELECT count(*) from `chauffeur` where etatDispo='DISPO';";
            try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
            nb=rs.getInt(1);            
            }}
            
            catch (SQLException ex) {
            Logger.getLogger(ChauffeurServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nb;   
  }

    @Override
    public List<Chauffeur> afficher() {
List<Chauffeur> maList=new ArrayList();
        try {
          
            String requete ="SELECT * FROM chauffeur";
                      ste = conn.createStatement();
            ResultSet res =ste.executeQuery(requete);
            
            while (res.next()){
                Chauffeur cha =new Chauffeur();
                  cha.setidch(res.getInt(1));
                  cha.setnom(res.getString(2));
                  cha.setprenom(res.getString(3));
                  cha.setphoto(res.getString(4));
                  cha.settel(res.getString(5));
                  cha.setdescription(res.getString(6));
                  cha.setetatDispo(res.getString(7));
                  
               
               maList.add(cha);
            }
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
        return maList ;      }
    
    public Chauffeur SearchById(int idch) {
        Chauffeur chauffeur =new Chauffeur();
         try {
             Statement stm = conn.createStatement();
             
             String query = "select * from chauffeur where idch="+idch;
             ResultSet rs= stm.executeQuery(query);
             while (rs.next()){
                 chauffeur.setidch(rs.getInt("idch"));
                 chauffeur.setnom(rs.getString("nom"));
                 chauffeur.setprenom(rs.getString("prenom"));
                 chauffeur.setphoto(rs.getString("photo"));
                 chauffeur.settel(rs.getString("tel"));
                 chauffeur.setdescription(rs.getString("description"));
                 chauffeur.setetatDispo(rs.getString("etatDispo"));
                 
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(ChauffeurServices.class.getName()).log(Level.SEVERE, null, ex);
         }
         return chauffeur;
    }


    
}
