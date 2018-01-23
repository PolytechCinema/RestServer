package cinema;

import javax.persistence.*;

/**
 * Created by Andzura on 23/01/2018.
 */
@Entity
public class Personnage {

    @EmbeddedId
    private PersonnagePK id;

    @ManyToOne
    @MapsId("acteurId")
    @JoinColumn(name="noact", insertable = false, updatable = false, nullable=false)
    private Acteur acteur;

    @ManyToOne
    @MapsId("filmId")
    @JoinColumn(name="nofilm", insertable = false, updatable = false, nullable=false)
    private Film film;

    @Column(name = "nompers")
    private String nom;

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
        if(id == null){
            id = new PersonnagePK();
        }
        id.setFilmId(film.getId());
    }

    public Acteur getActeur() {
        return acteur;
    }

    public void setActeur(Acteur acteur) {
        this.acteur = acteur;
        if(id == null){
            id = new PersonnagePK();
        }
        id.setActeurId(acteur.getId());
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
