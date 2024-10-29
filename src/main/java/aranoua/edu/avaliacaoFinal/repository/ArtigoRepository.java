package aranoua.edu.avaliacaoFinal.repository;

//-----------imports utilizados-------------------------------------------------
import aranoua.edu.avaliacaoFinal.model.Artigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//------------------------------------------------------------------------------

/* Notação para indica que a interface é uma repository que interage com o banco de dados */
@Repository

/* Metodo interface que define como uma interface publica que estende da JpaRepository
 * que significa que o AutorRepository herda os métodos */
public interface ArtigoRepository extends JpaRepository<Artigo, Long> {
}
