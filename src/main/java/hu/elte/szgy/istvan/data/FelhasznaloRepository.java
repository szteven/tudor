package hu.elte.szgy.istvan.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface FelhasznaloRepository extends JpaRepository<Felhasznalo, Integer> {
    List<Felhasznalo> findAllByTipus(Felhasznalo.Tipus tipus);
    List<Felhasznalo> findAllByKompetenciakContaining(Temakor temakor);
    Felhasznalo findByFelhasznalonev(String felhasznalonev);
}
