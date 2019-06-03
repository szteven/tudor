package hu.elte.szgy.istvan.rest;

import hu.elte.szgy.istvan.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("tudakozasok")
@Transactional
public class TudakozasController {
    @Autowired
    private TemakorRepository temakorRepository;

    @Autowired
    private TudakozasRepository tudakozasRepository;

    @GetMapping
    public List<Tudakozas> getTudakozasok(
            @RequestParam(name = "temakor", required = false) Integer temakorId,
            @RequestParam(name = "megvalaszolt", required = false) String megvalaszolt) {
        if (temakorId != null && megvalaszolt != null) {
            boolean v = megvalaszolt == "true";
            return tudakozasRepository.findAllByTemakorContainsAndMegvalaszoltIs(temakorRepository.getOne(temakorId), v);
        }
        if (temakorId != null) {
            return tudakozasRepository.findAllByTemakorContains(temakorRepository.getOne(temakorId));
        }
        return tudakozasRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Tudakozas> getTudakoas(@PathVariable("id") Integer id) {
        Optional<Tudakozas> tudakozas = tudakozasRepository.findById(id);
        return tudakozas.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Tudakozas> createTudakozas(@RequestBody Tudakozas tudakozas) {
        tudakozasRepository.save(tudakozas);
        return new ResponseEntity<>(tudakozas, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/ertekeles")
    public ResponseEntity<Tudakozas> updateErtekeles(@RequestParam Integer ertekeles, @PathVariable("id") Integer id) {
        Optional<Tudakozas> tudakozas = tudakozasRepository.findById(id);
        if (tudakozas.isPresent()) {
            Tudakozas t = tudakozas.get();
            if (t.isMegvalaszolt()) {
                t.setErtekeles(ertekeles);
                tudakozasRepository.save(t);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/valasz")
    public ResponseEntity<Tudakozas> updateValasz(@RequestBody String valasz, @PathVariable("id") Integer id) {
        Optional<Tudakozas> tudakozas = tudakozasRepository.findById(id);
        if (tudakozas.isPresent()) {
            Tudakozas t = tudakozas.get();
            if (!t.isMegvalaszolt()) {
                t.setValasz(valasz);
                t.setValaszDatum(new Date());
                tudakozasRepository.save(t);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Tudakozas> deleteTudakozas(@PathVariable("id") Integer id) {
        try {
            tudakozasRepository.delete(tudakozasRepository.getOne(id));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
