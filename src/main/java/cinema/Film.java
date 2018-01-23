package cinema;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Andzura on 23/01/2018.
 */

@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "nofilm")
    private long id;

    @Column(name = "titre")
    private String titre;

    @Column(name = "duree")
    private int duree;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "datesortie")
    private Date dateSortie;

    @Column(name = "budget")
    private int budget;

    @Column(name = "montantrecette")
    private int montantRecette;

    @ManyToOne
    @JoinColumn(name = "norea")
    private Realisateur realisateur;

    @ManyToOne
    @JoinColumn(name = "codecat")
    private Categorie categorie;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getMontantRecette() {
        return montantRecette;
    }

    public void setMontantRecette(int montantRecette) {
        this.montantRecette = montantRecette;
    }

    public Realisateur getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(Realisateur realisateur) {
        this.realisateur = realisateur;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}
