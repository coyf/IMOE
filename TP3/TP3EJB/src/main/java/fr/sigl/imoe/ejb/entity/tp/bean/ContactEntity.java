package fr.sigl.imoe.ejb.entity.tp.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by flo on 02/11/15.
 */


@Entity
@Table(name = "CONTACT")
public class ContactEntity implements Serializable{
    private String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
