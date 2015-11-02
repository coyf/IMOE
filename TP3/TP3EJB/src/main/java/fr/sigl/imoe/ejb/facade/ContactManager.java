package fr.sigl.imoe.ejb.facade;

import fr.sigl.imo.tp3.bean.ContactManagerRemote;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by flo on 02/11/15.
 */
@Stateless
public class ContactManager implements ContactManagerRemote{
    public ContactManager() {

    }

    @Override
    public void creerContact(String lastname, String firstname, List<String> telephone) {

    }
}
