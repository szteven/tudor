package hu.elte.szgy.istvan.rest;

import hu.elte.szgy.istvan.data.Felhasznalo;
import hu.elte.szgy.istvan.data.FelhasznaloRepository;
import hu.elte.szgy.istvan.data.Temakor;
import hu.elte.szgy.istvan.data.TemakorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("tudorok")
@Transactional
public class TudorController {
    @Autowired
    private TemakorRepository temakorRepository;

    @Autowired
    private FelhasznaloRepository felhasznaloRepository;

    @GetMapping
    public List<Felhasznalo> getTudorok(@RequestParam(name = "temakor", required = false) Integer temakorId) {
        if (temakorId != null) {
            return felhasznaloRepository.findAllByKompetenciakContaining(temakorRepository.getOne(temakorId));
        }
        else {
            return felhasznaloRepository.findAll();
        }
    }

    @PutMapping("/{id}/kompetenciak")
    public ResponseEntity<Felhasznalo> updateKompetenciak(@RequestBody List<String> kompList, @PathVariable("id") Integer id) {
        Optional<Felhasznalo> tudor = felhasznaloRepository.findById(id);
        if (tudor.isPresent()) {
            Felhasznalo t = tudor.get();
            t.setKompetenciak(kompList.stream().map(s -> temakorRepository.findByNev(s)).collect(Collectors.toList()));
            felhasznaloRepository.save(t);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
