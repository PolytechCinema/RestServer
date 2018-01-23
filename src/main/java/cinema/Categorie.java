package cinema;

/**
 * Created by Andzura on 23/01/2018.
 */
import javax.persistence.*;

@Entity
public class Categorie {

    @Id
    @Column(name = "codecat")
    private String code;

    @Column(name = "libellecat")
    private String libelle;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
