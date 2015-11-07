package fr.sigl.imo.tp3.bean;

import fr.sigl.imoe.tp3.dto.Contact;
import fr.sigl.imoe.tp3.dto.Telephone;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by flo on 02/11/15.
 */
@Remote
public interface ContactManagerRemote {

    void actualiserContact(Integer id, String lastname, String firstname, List<Telephone> telephones);
    Contact creerContact(String lastname, String firstname, List<Telephone> telephone);
    Telephone creerTelephone(String valeur, String type);
    List<Contact> listerTousContacts();
    void supprimerContact(Integer id);
    void supprimerTelephone(Integer id);
    List<String> listerTousTypesTelephone();
    Contact recupererContact(Integer id);

}
