package fr.sigl.imoe.tp3.delegate.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import fr.sigl.imoe.tp3.delegate.ContactsServiceDelegate;
import fr.sigl.imoe.tp3.dto.Contact;
import fr.sigl.imoe.tp3.dto.Telephone;
import fr.sigl.imoe.tp3.dto.TypeTelephone;

/**
 * Implémentation mock de service de gestion des contacts permettant
 * le développement de la couche Web sans la partie EJB.
 */
@ApplicationScoped
public class ContactsServiceDelegateMock implements ContactsServiceDelegate {
    /**
     * Generated Serial Version UID.
     */
    private static final long serialVersionUID = 1016413833538189155L;

    /**
     * Logger JUL.
     */
    private static final Logger LOGGER = Logger.getLogger(ContactsServiceDelegateMock.class.getName());

    /**
     * Liste des contacts sous forme de mock.
     */
    private ArrayList<Contact> contacts;

    /**
     * Prochain identifiant à distribuer.
     */
    private static int generateurIdentifiantTechnique = 1;

    /**
     * Méthode permettant d'initialiser les données de contacts.
     */
    @PostConstruct
    public void init() {
        contacts = new ArrayList<Contact>();
        Contact c = new Contact();
        c.setId(generateurIdentifiantTechnique++);
        c.setNom("Dupond");
        c.setPrenom("Marc");
        c.getTelephones().add(new Telephone(TypeTelephone.MOBILE, "06 85 48 75 98"));
        c.getTelephones().add(new Telephone(TypeTelephone.DOMICILE, "01 58 24 67 11"));
        contacts.add(c);
        c = new Contact();
        c.setId(generateurIdentifiantTechnique++);
        c.setNom("Giraud");
        c.setPrenom("Irène");
        c.getTelephones().add(new Telephone(TypeTelephone.MOBILE, "06 27 84 10 36"));
        contacts.add(c);
    }

    /**
     * Service permettant de lister l'ensemble des contacts
     * disponibles dans l'application.
     *
     * @return La liste des contacts.
     * @see fr.sigl.imoe.tp3.delegate.ContactsServiceDelegate#listerTousContacts()
     */
    @Override
    public List<Contact> listerTousContacts() {
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info("Listing de toutes les contacts via le mock");
        }
        return contacts;
    }

    /**
     * Service de récupération d'un contact à partir de son
     * identifiant technique.
     *
     * @param id    Identifiant technique du contact à rechercher.
     * @return Le contact correspondant.
     * @see fr.sigl.imoe.tp3.delegate.ContactsServiceDelegate#obtenirContactParId(java.lang.Integer)
     */
    @Override
    public Contact obtenirContactParId(Integer id) {
        for (Contact c : contacts) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Service de création ou de mise à jour d'un contact.
     *
     * @param contact    Les informations du contact à créer ou mettre à jour.
     * @return Le contact suite à la création ou mise à jour.
     * @see fr.sigl.imoe.tp3.delegate.ContactsServiceDelegate#sauvegarderContact(fr.sigl.imoe.tp3.dto.Contact)
     */
    @Override
    public Contact sauvegarderContact(Contact contact) {
        // Vérification de l'existence du contact
        Contact contactExistant = null;
        if (contact.getId() != null) {
            contactExistant = obtenirContactParId(contact.getId());
        }
        if (contactExistant != null) {
            // Mise à jour des données
            contactExistant.setNom(contact.getNom());
            contactExistant.setPrenom(contact.getPrenom());
            // Remplacement de tous les téléphones (on n'a pas besoin de l'identifiant pour le mock)
            contactExistant.setTelephones(contact.getTelephones());
        } else {
            // Création du nouveau contact
            contact.setId(generateurIdentifiantTechnique++);
            contacts.add(contact);
            contactExistant = contact;
        }
        return contactExistant;
    }

    /**
     * Service de suppression d'un contact.
     *
     * @param contact    LE contact à supprimer.
     * @see fr.sigl.imoe.tp3.delegate.ContactsServiceDelegate#supprimerContact(fr.sigl.imoe.tp3.dto.Contact)
     */
    @Override
    public void supprimerContact(Contact contact) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId().equals(contact.getId())) {
                contacts.remove(i);
                break;
            }
        }
    }
}
