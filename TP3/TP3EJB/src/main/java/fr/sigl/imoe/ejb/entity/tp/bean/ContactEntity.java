package fr.sigl.imoe.ejb.entity.tp.bean;



import fr.sigl.imoe.tp3.dto.Telephone;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by flo on 02/11/15.
 */


@Entity
@Table(name = "CONTACT")
public class ContactEntity implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nom;
    private String prenom;

    @ElementCollection
    private List<Integer> telephoneIds;

    public ContactEntity(){
        super();
    }

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

    public List<Integer> getTelephoneIds() {
        return telephoneIds;
    }

    public void setTelephoneIds(List<Integer> telephoneIds) {
        this.telephoneIds = telephoneIds;
    }
}
