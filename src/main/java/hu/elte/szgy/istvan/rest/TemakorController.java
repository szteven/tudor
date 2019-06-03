package hu.elte.szgy.istvan.rest;

import hu.elte.szgy.istvan.data.Temakor;
import hu.elte.szgy.istvan.data.TemakorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("temakorok")
@Transactional
public class TemakorController {
    @Autowired
    private TemakorRepository temakorRepository;

    @GetMapping
    public List<Temakor> getTemakorok() {
        return temakorRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Temakor> getTemakor(@PathVariable("id") Integer id) {
        Optional<Temakor> temakor = temakorRepository.findById(id);
        return temakor.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Temakor> createTemakor(@RequestBody Temakor t) {
        temakorRepository.save(t);
        return new ResponseEntity<>(t, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Temakor> deleteTemakor(@RequestBody Temakor t) {

        try {
            temakorRepository.delete(temakorRepository.getOne(t.getId()));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
