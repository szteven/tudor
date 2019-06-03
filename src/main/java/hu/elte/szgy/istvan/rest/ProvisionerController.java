package hu.elte.szgy.istvan.rest;

import hu.elte.szgy.istvan.data.Felhasznalo;
import hu.elte.szgy.istvan.data.FelhasznaloRepository;
import hu.elte.szgy.istvan.data.Temakor;
import hu.elte.szgy.istvan.data.TemakorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("provision")
@Transactional
public class ProvisionerController {
    @Autowired
    private TemakorRepository temakorRepository;

    @Autowired
    private FelhasznaloRepository felhasznaloRepository;

    @GetMapping
    public String doProvisioning() {

        List<Temakor> temakorList = Arrays.asList(
                new Temakor("Fizika"),
                new Temakor("Matematika"),
                new Temakor("Informatika"),
                new Temakor("Politika")
        );

        temakorRepository.deleteAllInBatch();
        temakorList.forEach(t -> temakorRepository.save(t));


        felhasznaloRepository.deleteAllInBatch();
        Felhasznalo admin = new Felhasznalo("Johny Server", new Date(), "1234-1234-1234-1234", "admin", "johny@tudor.hu", "password", Felhasznalo.Tipus.ADMIN);

        List<Felhasznalo> tudorList = Arrays.asList(
                new Felhasznalo("Elso Tudor", new Date(), "2342-2342-4382-2222", "tudor1", "elso.tudor@tudor.hu", "pass1", Felhasznalo.Tipus.TUDOR),
                new Felhasznalo("Masodik Tudor", new Date(), "2342-2342-0000-2222", "tudor2", "masodik.tudor@tudor.hu", "pass2", Felhasznalo.Tipus.TUDOR),
                new Felhasznalo("Harmadik Tudor", new Date(), "3456-2342-4382-2222", "tudor3", "harmadik.tudor@tudor.hu", "pass3", Felhasznalo.Tipus.TUDOR)
        );

        List<Felhasznalo> ugyfelList = Arrays.asList(
                new Felhasznalo("Elso Ugyfel", new Date(), "2342-2342-4382-2222", "ugyfel1", "elso.ugyfel@tudor.hu", "pass1", Felhasznalo.Tipus.UGYFEL),
                new Felhasznalo("Masodik Ugyfel", new Date(), "2342-2342-0000-2222", "ugyfel2", "masodik.ugyfel@tudor.hu", "pass2", Felhasznalo.Tipus.UGYFEL),
                new Felhasznalo("Harmadik Ugyfel", new Date(), "3456-2342-4382-2222", "ugyfel3", "harmadik.ugyfel@tudor.hu", "pass3", Felhasznalo.Tipus.UGYFEL)
        );

        felhasznaloRepository.save(admin);
        tudorList.forEach(t -> felhasznaloRepository.save(t));
        ugyfelList.forEach(u -> felhasznaloRepository.save(u));

        return "Provisioned";
    }
}
