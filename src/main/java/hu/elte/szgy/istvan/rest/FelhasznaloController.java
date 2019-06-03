package hu.elte.szgy.istvan.rest;

import hu.elte.szgy.istvan.data.Felhasznalo;
import hu.elte.szgy.istvan.data.FelhasznaloRepository;
import hu.elte.szgy.istvan.data.TemakorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("felhasznalok")
@Transactional
public class FelhasznaloController {
    private static Logger log = LoggerFactory.getLogger(FelhasznaloController.class);

    @Autowired
    private FelhasznaloRepository felhasznaloRepository;

    @GetMapping
    public List<Felhasznalo> getByTipus(@RequestParam(required = false) Felhasznalo.Tipus tipus) {
        if (tipus != null) {
            return felhasznaloRepository.findAllByTipus(tipus);
        }
        else {
            return felhasznaloRepository.findAll();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Felhasznalo> getFelhasznalo(@PathVariable("id") Integer id) {
        Optional<Felhasznalo> temakor = felhasznaloRepository.findById(id);
        return temakor.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Felhasznalo> createFelhasznalo(@RequestBody Felhasznalo f) {
        felhasznaloRepository.save(f);
        return new ResponseEntity<>(f, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Felhasznalo> deleteFelhasznalo(@PathVariable("id") Integer id) {

        try {
            felhasznaloRepository.delete(felhasznaloRepository.getOne(id));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Felhasznalo> patchFelhasznalo(@RequestBody Felhasznalo puser, @PathVariable("id") Integer id) {
        Optional<Felhasznalo> tudor = felhasznaloRepository.findById(id);
        if (tudor.isPresent()) {
            Felhasznalo t = tudor.get();
            if (puser.getNev() != null) {
                t.setNev(puser.getNev());
            }
            if (puser.getBankszamla() != null) {
                t.setBankszamla(puser.getBankszamla());
            }
            if (puser.getEmail() != null) {
                t.setEmail(puser.getEmail());
            }
            if (puser.getSzuldatum() != null) {
                t.setSzuldatum(puser.getSzuldatum());
            }
            felhasznaloRepository.save(t);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/dispatch")
    public ResponseEntity<Void> dispatchUser() {
        // log.debug("Into URI: " + rr.getURI().toString() );
        SecurityContext cc = SecurityContextHolder.getContext();
        HttpHeaders headers = new HttpHeaders();
        if (cc.getAuthentication() != null) {
            Authentication a = cc.getAuthentication();
            try {
                headers.setLocation(
                        new URI("/" + felhasznaloRepository.findByFelhasznalonev(a.getName()).getTipus().toString().toLowerCase() + "_home.html"));
            } catch (URISyntaxException e) {
                log.warn("Dispatcher cannot redirect");
            }
        }

        return new ResponseEntity<Void>(headers, HttpStatus.FOUND);
    }
}
