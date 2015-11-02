package fr.sigl.imoe.tp3.dto;

import java.io.Serializable;

/**
 * Classe de type DTO représentant un téléphone d'un contact.
 */
public class Telephone implements Serializable {
    /**
     * Generated Serial Version UID.
     */
    private static final long serialVersionUID = 591572819531136647L;

    /**
     * Identifiant technique.
     */
    private Integer id;

    /**
     * Type de téléphone.
     */
    private TypeTelephone type;

    /**
     * Numéro de téléphone.
     */
    private String valeur;

    /**
     * Constructeur par défaut.
     */
    public Telephone() {
        super();
    }

    /**
     * Création d'un nouveau téléphone à partir d'un type et d'une
     * valeur.
     *
     * @param type        Type de téléphone.
     * @param valeur    Numéro de téléphone.
     */
    public Telephone(TypeTelephone type, String valeur) {
        this();
        this.type = type;
        this.valeur = valeur;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypeTelephone getType() {
        return type;
    }

    public void setType(TypeTelephone type) {
        this.type = type;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }
}
