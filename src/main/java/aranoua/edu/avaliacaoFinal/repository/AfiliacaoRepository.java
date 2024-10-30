package aranoua.edu.avaliacaoFinal.repository;

import aranoua.edu.avaliacaoFinal.model.Afiliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AfiliacaoRepository extends JpaRepository <Afiliacao, Long> {

    @Query("select a from Afiliacao a where a.nome = :name")
    Afiliacao findByName(@Param("name") String name);

}
