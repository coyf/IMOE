package fr.sigl.imoe.tp3.delegate;

import java.io.Serializable;
import java.util.List;

import fr.sigl.imoe.tp3.dto.Contact;

/**
 * Interface pour le delegate permettant d'accéder aux services de gestion
 * des contacts.
 */
public interface ContactsServiceDelegate extends Serializable {
    /**
     * Service permettant de lister l'ensemble des contacts
     * disponibles dans l'application.
     *
     * @return La liste des contacts.
     */
    List<Contact> listerTousContacts();

    /**
     * Service de récupération d'un contact à partir de son
     * identifiant technique.
     *
     * @param id    Identifiant technique du contact à rechercher.
     * @return Le contact correspondant.
     */
    Contact obtenirContactParId(Integer id);

    /**
     * Service de création ou de mise à jour d'un contact.
     *
     * @param contact    Les informations du contact à créer ou mettre à jour.
     * @return Le contact suite à la création ou mise à jour.
     */
    Contact sauvegarderContact(Contact contact);

    /**
     * Service de suppression d'un contact.
     *
     * @param contact    LE contact à supprimer.
     */
    void supprimerContact(Contact contact);
}
