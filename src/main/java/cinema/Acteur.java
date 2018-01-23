package cinema;

/**
 * Created by Andzura on 23/01/2018.
 */
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
public class Acteur {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="noact")
    private long id;

    @Column(name = "nomact")
    private String nom;

    @Column(name = "prenact")
    private String prenom;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "datenaiss")
    private Date dateNaiss;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "datedeces")
    private Date dateDeces;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Date getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public Date getDateDeces() {
        return dateDeces;
    }

    public void setDateDeces(Date dateDeces) {
        this.dateDeces = dateDeces;
    }
}
