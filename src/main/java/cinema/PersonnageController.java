package cinema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.util.UriComponentsBuilder;


@Controller    // This means that this class is a Controller
@RequestMapping(path="/perso") // This means URL's start with /demo (after Application path)
public class PersonnageController {
    @Autowired
    private PersonnageRepository personnageRepository;
    @Autowired
    private ActeurRepository acteurRepository;
    @Autowired
    private FilmRepository filmRepository;
    public static final Logger logger = LoggerFactory.getLogger(ActeurController.class);


    @PostMapping(path="/") // Map ONLY POST Requests
    public ResponseEntity<?> addNewPersonnage (@RequestBody Personnage perso, UriComponentsBuilder ucBuilder) {
        logger.info("create new perso");

        if(personnageRepository.existsByActeurAndFilm(perso.getActeur(), perso.getFilm())){
            logger.info("perso already exists !");
            return new ResponseEntity("Personnage already exists", HttpStatus.CONFLICT);
        }
        logger.info("Perso doesn't already exists, continuing...");
        personnageRepository.save(perso);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/film/{actid}/{filmid}").buildAndExpand(perso.getActeur().getId(), perso.getFilm().getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @GetMapping(path="/")
    public @ResponseBody Iterable<Personnage> getAllPersos() {
        // This returns a JSON or XML with the users
        return personnageRepository.findAll();
    }



    @GetMapping(path="/{actid}/{filmid}")
    public ResponseEntity<?> getSinglePerso(@PathVariable("actid") long actid, @PathVariable("filmid") long filmid) {
        logger.info("Fetching Personnage with id {} {}", actid, filmid);
        Acteur a  = acteurRepository.findOne(actid);
        Film f = filmRepository.findOne(filmid);
        if(a == null || f == null){
            logger.error("Acteur or Film referenced doesn't exist");
            return new ResponseEntity("Personnage not found : Acteur or Film referenced doesn't exist", HttpStatus.NOT_FOUND);
        }
        Personnage p = personnageRepository.findOneByActeurAndFilm(a,f);
        if(p == null){
            logger.error("Personnage doesn't exist");
            return new ResponseEntity("Personnage not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Personnage>(p, HttpStatus.OK);
    }


    @PutMapping(path="/{actid}/{filmid}") // Map ONLY PUT Requests
    public ResponseEntity<?> updatePerso (@RequestBody Personnage perso, @PathVariable("actid") long actid, @PathVariable("filmid") long filmid) {
        Acteur a  = acteurRepository.findOne(actid);
        Film f = filmRepository.findOne(filmid);
        if(a == null || f == null){
            logger.error("Acteur or Film referenced doesn't exist");
            return new ResponseEntity("Personnage not found : Acteur or Film referenced doesn't exist", HttpStatus.NOT_FOUND);
        }
        Personnage p = personnageRepository.findOneByActeurAndFilm(a,f);
        if(p == null){
            logger.error("Personnage doesn't exist");
            return new ResponseEntity("Personnage not found.", HttpStatus.NOT_FOUND);
        }
        p.setNom(perso.getNom());

        personnageRepository.save(p);
        return new ResponseEntity<Personnage>(p, HttpStatus.OK);
    }

    @DeleteMapping(path="/{actid}/{filmid}")
    public ResponseEntity<?> deleteSinglePerso(@PathVariable("actid") long actid, @PathVariable("filmid") long filmid) {
        Acteur a  = acteurRepository.findOne(actid);
        Film f = filmRepository.findOne(filmid);
        if(a == null || f == null){
            logger.error("Acteur or Film referenced doesn't exist");
            return new ResponseEntity("Personnage not found : Acteur or Film referenced doesn't exist => nothing to do", HttpStatus.OK);
        }
        Personnage p = personnageRepository.findOneByActeurAndFilm(a,f);
        if(p == null){
            logger.error("Personnage doesn't exist");
            return new ResponseEntity("Personnage not found, Nothing to do.", HttpStatus.OK);
        }
        personnageRepository.delete(p);
        return new ResponseEntity<String>("Film deleted", HttpStatus.OK);
    }

}