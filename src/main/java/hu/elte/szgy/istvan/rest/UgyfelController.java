package hu.elte.szgy.istvan.rest;

import hu.elte.szgy.istvan.data.Felhasznalo;
import hu.elte.szgy.istvan.data.FelhasznaloRepository;
import hu.elte.szgy.istvan.data.TemakorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("ugyfelek")
@Transactional
public class UgyfelController {
    @Autowired
    private TemakorRepository temakorRepository;

    @Autowired
    private FelhasznaloRepository felhasznaloRepository;


}
