/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetaTrip.services;

import MetaTrip.entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import MetaTrip.Config.DataSource;

/**
 *
 * @author dali
 */
public class LoginAndSignupService {
      
    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    public LoginAndSignupService() {
        conn = DataSource.getInstance().getCnx();
    }

      private String Email;
      private String Password;
public String login (String email,String password) throws Exception {
    String test = null;
        List<User> users = new ArrayList<>();
        String req = "SELECT *  FROM `user` where Email='"+email+"' and Password ='"+password+"'";
        System.out.println(email);
         System.out.println(password);
        try {

       ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
            User u = new User();
                u.setIdu(rs.getInt(1));
                  u.setCin( rs.getDouble(2));
                u.setNom(rs.getString(3));
                u.setPrenom(rs.getString(4));      
                    u.setTel(rs.getDouble(5));
            
              u.setEmail( rs.getString(6));
               u.setPassword(rs.getString(7));
                u.setImage(rs.getString(8));
                 u.setRole(rs.getInt(9));
                u.setDateNaissance(rs.getDate(10));
                 users.add(u) ;   
                        
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
  
           try{
               
         if(users.size()==0){
                             test="login failed";
                         
                         }
                         else{
                             test="login successful";
                           
                         }
           }catch(IndexOutOfBoundsException e){
         
        }
       return test;
    }
  public void Signup(User u) {
              List<User> users = new ArrayList<>();
      String Email1=u.getEmail();
      String pass1=u.getPassword();
    //  System.out.println(Email1);
          // System.out.println(pass1);
      String password=u.getPassword();
      String req = "SELECT *  FROM `user` where Email='"+Email1+"'";
   
        try {

       ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
              User u1 = new User();
                u1.setIdu(rs.getInt(1));
                  u1.setCin( rs.getDouble(2));
                u1.setNom(rs.getString(3));
                u1.setPrenom(rs.getString(4));      
                    u1.setTel(rs.getDouble(5));
            
              u1.setEmail( rs.getString(6));
               u1.setPassword(rs.getString(7));
                u1.setImage(rs.getString(8));
                u1.setRole(rs.getInt(9));
                u1.setDateNaissance(rs.getDate(10));
                 users.add(u) ; 
                     
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
                    System.out.println("+++++++size++++++++"+users.size());
                                    if(users.size()==0){
                                        
                                        

                                                         String req10 = "INSERT INTO `user` (`Cin`,`Nom`,`Prenom`,`Tel`,`Email`,`Password`,`Image`,`dateNaissance`) VALUES (?,?,?,?,?,?,?,?) ;";
                                                       try {
                                                           pste = conn.prepareStatement(req10);


                                                           pste.setDouble(1,u.getCin());
                                                           pste.setString(2, u.getNom());
                                                           pste.setString(3, u.getPrenom());
                                                            pste.setDouble(4,u.getTel());
                                                             pste.setString(5, u.getEmail());
                                                              pste.setString(6, u.getPassword());
                                                               pste.setString(7, u.getImage());
                                                                 pste.setDate(8, u.getDateNaissance());
                                                           pste.executeUpdate();
                                                           System.out.println("user créée");
                                                       } catch (SQLException ex) {
                                                           Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                                                       }
                                                       
                                    }
                                                       else{
                                             System.out.print("signup failed ");

                                    }
}

}