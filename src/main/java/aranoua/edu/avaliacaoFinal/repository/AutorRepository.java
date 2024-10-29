package aranoua.edu.avaliacaoFinal.repository;

//-----------imports utilizados-------------------------------------------------
import aranoua.edu.avaliacaoFinal.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//------------------------------------------------------------------------------

/* Notação para indica que a interface é uma repository que interage com o banco de dados */
@Repository

/* Metodo interface que define como uma interface publica que estende da JpaRepository
* que significa que o AutorRepository herda os métodos */
public interface AutorRepository extends JpaRepository <Autor, Long>{

    //Anotação que permite escrever uma consulta personalizada usando o jpql
    @Query("select a from tableAutor a where a.nome = :name")

    /* Definindo um metodo findByName na interface que retorna um objeto autor
    * usando a notação @param para vincular o parametro name ao :name do JPQL*/
    Autor findByName(@Param("name") String name);

}
