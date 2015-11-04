package fr.sigl.imo.tp3.bean;

import fr.sigl.imoe.tp3.dto.Contact;
import fr.sigl.imoe.tp3.dto.Telephone;
import fr.sigl.imoe.tp3.dto.TypeTelephone;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by flo on 02/11/15.
 */
@Remote
public interface ContactManagerRemote {

    Contact creerContact(String lastname, String firstname, List<String> telephone);
    List<Contact> listerTousContacts();
    void supprimerContact(Integer id);
    List<String> listerTousTypesTelephone();

}
