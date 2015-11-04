package fr.sigl.imoe.ejb.entity.tp.bean;



import fr.sigl.imoe.tp3.dto.TypeTelephone;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by flo on 02/11/15.
 */


@Entity
@Table(name = "TELEPHONE")
public class TelephoneEntity implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String valeur;

    @Enumerated
    private TypeTelephone type;

    public TelephoneEntity(){
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }
}
