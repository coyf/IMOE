package fr.sigl.imoe.ejb.facade;

import fr.sigl.imo.tp3.bean.ContactManagerRemote;
import fr.sigl.imoe.ejb.entity.tp.bean.ContactEntity;
import fr.sigl.imoe.ejb.entity.tp.bean.TelephoneEntity;
import fr.sigl.imoe.tp3.dto.Contact;
import fr.sigl.imoe.tp3.dto.Telephone;
import fr.sigl.imoe.tp3.dto.TypeTelephone;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

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
    public Contact creerContact(String lastname, String firstname, List<Telephone> telephone) {
        Contact new_contact = new Contact();
        new_contact.setNom(lastname);
        new_contact.setPrenom(firstname);
        new_contact.setTelephones(telephone);
        ContactEntity ce = convert(new_contact);
        em.persist(ce);
        System.out.println("ContactManager: Contact <" + lastname + ", " + firstname + ", contact id = "+ new_contact.getId() +
                ", contact entity id = " + ce.getId() +  "> persisted");
        return convert(ce);
    }

    @Override
    public Telephone creerTelephone(String valeur, String type) {
        TypeTelephone typeTelephone = TypeTelephone.valueOf(type);
        Telephone new_phone = new Telephone();
        new_phone.setValeur(valeur);
        new_phone.setType(typeTelephone);
        TelephoneEntity te = convert(new_phone);
        em.persist(te);
        System.out.println("ContactManager: Telephone <" + typeTelephone.name() + ", tel entity id = " + te.getId() + ", " + valeur + "> persisted");

        return convert(te);
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

    @Override
    public List<String> listerTousTypesTelephone() {
        // Récupération des valeurs de l'énumération TypeTelephone
        TypeTelephone[] types = TypeTelephone.values();
        ArrayList<String> values = new ArrayList<>();

        // Création de la liste
        for (TypeTelephone t : types)
            values.add(t.name());

        return values;
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

        List<Integer> telIds = new ArrayList<>();
        for (Telephone tel : contact.getTelephones()) {
            TelephoneEntity te = convert(tel);
            telIds.add(te.getId());
        }

        entity.setTelephoneIds(telIds);

        return entity;
    }

    private Contact convert(ContactEntity entity) {
        Contact contact = new Contact();
        contact.setId(entity.getId());
        contact.setNom(entity.getNom());
        contact.setPrenom(entity.getPrenom());
        List<Telephone> telephones = new ArrayList<>();
        for (Integer telId : entity.getTelephoneIds()){
            // recuperer le tel
            TelephoneEntity te = em.find(TelephoneEntity.class, telId);

            Telephone tel = convert(te);
            // ajouter a la liste des tel
            telephones.add(tel);
        }
        contact.setTelephones(telephones);

        return contact;
    }

    private TelephoneEntity convert(Telephone telephone){
        TelephoneEntity te = null;
        if (telephone.getId() != null)
            te = em.find(TelephoneEntity.class, telephone.getId());
        else {
            te = new TelephoneEntity();
        }
        te.setValeur(telephone.getValeur());
        te.setType(telephone.getType());

        return te;
    }

    private Telephone convert(TelephoneEntity telephoneEntity){
        Telephone telephone = new Telephone();
        telephone.setId(telephoneEntity.getId());
        telephone.setValeur(telephoneEntity.getValeur());
        telephone.setType(telephoneEntity.getType());

        return telephone;
    }

}
