/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetaTrip.entities;


import MetaTrip.entities.User;
import java.util.Date;

/**
 *
 * @author medal
 */
public class ReservationVoiture {
    private int Idrvoit;
    private float prix_rent;
    private String Trajet;
    private User user;
    private Voiture Voiture;
    private Chauffeur chauffeur ;
    private int Idu;
    private int idch;
    private int Idvoit;
    
        public ReservationVoiture(int Idrvoit, float prix_rent, String Trajet, User user, Voiture Voiture, Chauffeur chauffeur) {
        this.Idrvoit = Idrvoit;
        this.prix_rent = prix_rent;
        this.Trajet = Trajet;
        this.user = user;
        this.Voiture = Voiture;
        this.chauffeur = chauffeur;
    }

    public ReservationVoiture(float prix_rent, String Trajet, int Idu, int idch, int Idvoit) {
        this.prix_rent = prix_rent;
        this.Trajet = Trajet;
        this.Idu = Idu;
        this.idch = idch;
        this.Idvoit = Idvoit;
    }
        

    public ReservationVoiture(float prix_rent, String Trajet, User user, Voiture Voiture, Chauffeur chauffeur) {
        this.prix_rent = prix_rent;
        this.Trajet = Trajet;
        this.user = user;
        this.Voiture = Voiture;
        this.chauffeur = chauffeur;
    }
    
    public ReservationVoiture(int Idrvoit, float prix_rent, String Trajet,int Idu,int idch) {
        this.Idrvoit = Idrvoit;
        this.prix_rent = prix_rent;
        this.Trajet = Trajet;
        this.Idu = Idu;
        this.idch = idch;
    }

    public ReservationVoiture() {
    }

    public ReservationVoiture(float prix_rent, String Trajet, int Idu, int Idvoit) {
        this.prix_rent = prix_rent;
        this.Trajet = Trajet;
        this.Idu = Idu;
        this.Idvoit = Idvoit;
    }

    public ReservationVoiture(float prix_rent, String Trajet, User user, Voiture Voiture) {
        this.prix_rent = prix_rent;
        this.Trajet = Trajet;
        this.user = user;
        this.Voiture = Voiture;
    }

    public ReservationVoiture(int Idrvoit, float prix_rent, int idch, String Trajet) {
        this.Idrvoit = Idrvoit;
        this.prix_rent = prix_rent;
        this.idch = idch;
        this.Trajet = Trajet;
    }

    public ReservationVoiture(float prix_rent, int idch, String Trajet, int Idu, int Idvoit) {
        this.prix_rent = prix_rent;
        this.idch = idch;
        this.Trajet = Trajet;
        this.Idu = Idu;
        this.Idvoit = Idvoit;
    }
    
    

    public ReservationVoiture(int Idrvoit, float prix_rent, int idch, String Trajet, int Idu, int Idvoit) {
        this.Idrvoit = Idrvoit;
        this.prix_rent = prix_rent;
        this.idch = idch;
        this.Trajet = Trajet;
        this.Idu = Idu;
        this.Idvoit = Idvoit;
    }


    public int getIdrvoit() {
        return Idrvoit;
    }

    public void setIdrvoit(int Idrvoit) {
        this.Idrvoit = Idrvoit;
    }

    public float getPrix_rent() {
        return prix_rent;
    }

    public void setPrix_rent(float prix_rent) {
        this.prix_rent = prix_rent;
    }

    public int getIdch() {
        return idch;
    }

    public void setIdch(int idch) {
        this.idch = idch;
    }

   
    public String getTrajet() {
        return Trajet;
    }

    public void setTrajet(String Trajet) {
        this.Trajet = Trajet;
    }

    public int getIdu() {
        return Idu;
    }

    public void setIdu(int Idu) {
        this.Idu = Idu;
    }

    public int getIdvoit() {
        return Idvoit;
    }

    public void setIdvoit(int Idvoit) {
        this.Idvoit = Idvoit;
    }

    @Override
    public String toString() {
        return "reservation_voiture{" + "Idrvoit=" + Idrvoit + ", prix_rent=" + prix_rent + ", idch=" + idch + ", Trajet=" + Trajet + ", Idu=" + Idu + ", Idvoit=" + Idvoit + '\n' +'}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setChauffeur(Chauffeur chauffeur) {
        this.chauffeur = chauffeur;
    }
    
    public Chauffeur getChauffeur() {
        return chauffeur;
    }
    
      public Voiture getVoiture() {
        return Voiture;
    }

    public void setVoiture(Voiture Voiture) {
        this.Voiture = Voiture;
    }

}
