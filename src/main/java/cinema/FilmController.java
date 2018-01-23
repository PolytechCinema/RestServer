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
@RequestMapping(path="/film") // This means URL's start with /demo (after Application path)
public class FilmController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private FilmRepository filmRepository;
    public static final Logger logger = LoggerFactory.getLogger(ActeurController.class);


    @PostMapping(path="/") // Map ONLY POST Requests
    public ResponseEntity<?> addNewFilm (@RequestBody Film film, UriComponentsBuilder ucBuilder) {
        logger.info("create new film");

        if(filmRepository.existsByTitre(film.getTitre())){
            logger.info("Film already exists !");
            return new ResponseEntity("Film already exists", HttpStatus.CONFLICT);
        }
        logger.info("Film doesn't already exists, continuing...");
        filmRepository.save(film);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/film/{id}").buildAndExpand(film.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @GetMapping(path="/")
    public @ResponseBody Iterable<Film> getAllActeurs() {
        // This returns a JSON or XML with the users
        return filmRepository.findAll();
    }



    @GetMapping(path="/{id}")
    public ResponseEntity<?> getSingleActeur(@PathVariable("id") long id) {
        logger.info("Fetching Film with id {}", id);
        Film f = filmRepository.findOne(id);
        if(f == null){
            logger.error("Film with id {} not found.", id);
            return new ResponseEntity("Film not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Film>(f, HttpStatus.OK);
    }

    @PutMapping(path="/{id}") // Map ONLY PUT Requests
    public ResponseEntity<?> updateActeur (@RequestBody Film film, @PathVariable("id") long id) {
        logger.info("update film id {}", id);
        Film currentFilm = filmRepository.findOne(id);
        if(currentFilm == null){
            logger.error("Film with id {} not found.", id);
            return new ResponseEntity("Film not found", HttpStatus.NOT_FOUND);
        }
        film.setId(currentFilm.getId());

        filmRepository.save(film);
        currentFilm = filmRepository.findOne(id);
        return new ResponseEntity<Film>(currentFilm, HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<?> deleteSingleActeur(@PathVariable("id") long id) {
        logger.info("deleting Film with id {}", id);
        Film f = filmRepository.findOne(id);
        if(f == null){
            logger.error("Film with id {} not found.", id);
            return new ResponseEntity("Film doesn't exist, nothing to do.", HttpStatus.OK);
        }
        filmRepository.delete(id);
        return new ResponseEntity<String>("Film deleted", HttpStatus.OK);
    }

}