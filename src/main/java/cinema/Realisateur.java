package cinema;

import javax.persistence.*;

/**
 * Created by Andzura on 23/01/2018.
 */

@Entity
public class Realisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "norea")
    private long id;

    @Column(name = "nomrea")
    private String nom;

    @Column(name = "prenrea")
    private String prenom;


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
}
