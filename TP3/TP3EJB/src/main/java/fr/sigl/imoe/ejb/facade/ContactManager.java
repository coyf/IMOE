package fr.sigl.imoe.ejb.facade;

import fr.sigl.imo.tp3.bean.ContactManagerRemote;
import fr.sigl.imoe.ejb.entity.tp.bean.ContactEntity;
import fr.sigl.imoe.tp3.dto.Contact;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by flo on 02/11/15.
 */
@Stateless
public class ContactManager implements ContactManagerRemote{
    public ContactManager() {

    }

    /**
     * Contexte de persistance.
     */

    /*
        Équivalent du SessionManager dans hibernate (pour les beginTransaction() et commit()-> ici implicite dans
        chacune des méthodes de cette classe Stateless)
    */
    @PersistenceContext(unitName = "ContactManager")
    protected EntityManager em;

    @Override
    public Contact creerContact(String lastname, String firstname, List<String> telephone) {
        Contact new_contact = new Contact();
        new_contact.setNom(lastname);
        new_contact.setPrenom(firstname);
        // TODO : liste des téléphones
        // ...
        ContactEntity ce = convert(new_contact);
        em.persist(ce);
        System.out.println("ContactManager: Contact <" + lastname + ", " + firstname + "> persisted");
        return convert(ce);
    }

    @Override
    public List<Contact> listerTousContacts() {
        List<ContactEntity> contact_entities = em.createQuery("FROM ContactEntity ce").getResultList();
        List<Contact> contacts = new ArrayList<Contact>();
        for (ContactEntity ce : contact_entities)
            contacts.add(convert(ce));

        return contacts;
    }

    @Override
    public void supprimerContact(Integer id) {
        em.remove(em.find(ContactEntity.class, id));
    }



    private ContactEntity convert(Contact contact) {
        ContactEntity entity = null;
        if (contact.getId() != null) {
            entity = em.find(ContactEntity.class, contact.getId());
        } else {
            entity = new ContactEntity();
        }
        entity.setNom(contact.getNom());
        entity.setPrenom(contact.getPrenom());

        return entity;
    }

    private Contact convert(ContactEntity entity) {
        Contact contact = new Contact();
        contact.setId(entity.getId());
        contact.setNom(entity.getNom());
        contact.setPrenom(entity.getPrenom());

        return contact;
    }
}
