package hu.elte.szgy.istvan.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface TemakorRepository extends JpaRepository<Temakor, Integer>  {
    Temakor findByNev(String nev);
}
