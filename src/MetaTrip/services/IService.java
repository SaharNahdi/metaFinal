
package MetaTrip.services;

import MetaTrip.entities.Chauffeur;
import MetaTrip.entities.ReservationVoiture;
import MetaTrip.entities.User;
import MetaTrip.entities.Voiture;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author FLAM
 */
public interface IService<T> {
    void ajouter(T entity);
    void modifier(int id,T entity);
    void supprimer(int id);
    List<T> afficher()throws SQLException;
   
}
