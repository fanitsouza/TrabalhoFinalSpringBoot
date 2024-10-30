package aranoua.edu.avaliacaoFinal.model;

//-----------imports utilizados-------------------------------------------------
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
//------------------------------------------------------------------------------

/*Notação para indicar que esta classe representa uma entidade que será
mapeada para uma tabela do banco de dados */
@Entity(name = "tableAutor")

//Definindo a classe Autor que será a entidade mapeada
public class Autor {

    //Marcando o campo id como chave primária da entidade
    @Id

    //Indicando que o valor de id será gerado automaticamente pelo banco
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //Declarando o campo id como um tipo Long privado
    private Long id;

    /* Especifica que o campo nome será mapeado para a coluna nomeAutor
    no banco de dados e não pode ser nulo */
    @Column(name = "nomeAutor", nullable = false)

    //Declarando o campo nome como uma String privada
    private String nome;

    /* Mapeando o campo afiliação para a coluna afiliacaoAutor no banco
    de dados e não pode ser nulo */

    //Declara o campo afiliação como uma String privada
    @ManyToOne
    private Afiliacao afiliacao;

    /* Fazendo a definição de muitos para muitos entre artigo e autores
    * onde um autor pode ter vários artigos e vice-versa. E mapeando o
    * atributo na classe artigo*/
    @ManyToMany(mappedBy = "autores")

    //Instancia uma lista de artigos que um autor escreveu
    private List<Artigo> artigo = new ArrayList<>();

    /* Construtor sem parametros, necessário para que a JPA consiga
    * instanciar objetos da classe Autor ao realizar operações no BD*/
    public Autor() {
    }

    // Construtor que permite instanciar um objeto Autor

    public Autor(Long id, String nome, Afiliacao afiliacao, List<Artigo> artigo) {
        this.id = id;
        this.nome = nome;
        this.afiliacao = afiliacao;
        this.artigo = artigo;
    }

    /* Metodo para retornar o valor do campo id */
    public Long getId() {
        return id;
    }

    /* Metodo para definir o valor do campo id */
    public void setId(Long id) {
        this.id = id;
    }

    /* Metodo para retornar o valor do campo nome */
    public String getNome() {
        return nome;
    }

    /* Metodo para retornar o valor do campo nome */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /* Metodo para retornar o valor do campo afiliacao */

    public Afiliacao getAfiliacao() {
        return afiliacao;
    }

    /* Metodo para definir o valor do campo afiliacao */
    public void setAfiliacao(Afiliacao afiliacao) {
        this.afiliacao = afiliacao;
    }



    /* Metodo para retornar a lista de artigos associados ao autor */
    public List<Artigo> getArtigo() {
        return artigo;
    }

    /* Metodo que define a lista de artigos associados ao autor */
    public void setArtigo(List<Artigo> artigo) {
        this.artigo = artigo;
    }
}
