package fr.sigl.imoe.tp3.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de type DTO représentant un utilisateur.
 */
public class Contact implements Serializable {
    /**
     * Generated Serial Version UID.
     */
    private static final long serialVersionUID = -8462588088552735362L;

    /**
     * Identifiant technique.
     */
    private Integer id;

    /**
     * Nom du contact.
     */
    private String nom;

    /**
     * Prénom du contact.
     */
    private String prenom;

    /**
     * Liste des téléphones connus du contact.
     */
    private List<Telephone> telephones = new ArrayList<Telephone>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<Telephone> getTelephones() {
        return telephones;
    }

    public void setTelephones(List<Telephone> telephones) {
        this.telephones = telephones;
    }
}
