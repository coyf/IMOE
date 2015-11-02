package fr.sigl.imo.tp3.bean;

import fr.sigl.imoe.tp3.dto.Telephone;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by flo on 02/11/15.
 */
@Remote
public interface ContactManagerRemote {

    void creerContact(String lastname, String firstname, List<String> telephone);

}
