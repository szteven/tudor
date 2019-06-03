package hu.elte.szgy.istvan.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface TudakozasRepository extends JpaRepository<Tudakozas, Integer> {
    List<Tudakozas> findAllByTemakorContains(Temakor temakor);
    List<Tudakozas> findAllByTemakorContainsAndMegvalaszoltIs(Temakor temakor, boolean megvalaszolt);
}
