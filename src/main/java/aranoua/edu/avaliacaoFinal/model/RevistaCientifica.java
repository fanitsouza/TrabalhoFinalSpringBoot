package aranoua.edu.avaliacaoFinal.model;

//-----------imports utilizados-------------------------------------------------
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
//------------------------------------------------------------------------------

/*Notação para indicar que esta classe representa uma entidade que será
mapeada para uma tabela do banco de dados */
@Entity

//Definindo a classe RevistaCientifica que será a entidade mapeada
public class RevistaCientifica {

    //Marcando o campo id como chave primária da entidade
    @Id

    //Indicando que o valor de id será gerado automaticamente pelo banco
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //Declarando o campo id como um tipo Long privado
    private Long id;

    /* Especifica que o campo nome será mapeado para a coluna nomeRevista
    no banco de dados e não pode ser nulo */
    @Column(name = "nomeRevista", nullable = false)

    //Declarando o campo nome como uma String privada
    private String nome;

    /* Mapeando o campo issn para a coluna issnRevista no banco
    de dados e não pode ser nulo */
    @Column(name = "issnRevista", nullable = false, unique = true)

    //Declara o campo issn como uma String privada
    private String issn;

    /* Fazendo a definição de um para muitos entre artigo e revista
     * onde uma revista pode ter vários artigos e um artigo pode ter
     * apenas uma revista. E mapeando o atributo na classe artigo*/
    @OneToMany(mappedBy = "revista")

    //Instancia uma lista de artigos publicados pela revista
    private List<Artigo> artigos = new ArrayList<>();

    /* Construtor sem parametros, necessário para que a JPA consiga
     * instanciar objetos da classe RevistaCientifica ao realizar operações no BD*/
    public RevistaCientifica() {
    }

    // Construtor que permite instanciar um objeto RevistaCientifica
    public RevistaCientifica(Long id, String nome, String issn, List<Artigo> artigos) {
        this.id = id;
        this.nome = nome;
        this.issn = issn;
        this.artigos = artigos;
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

    /* Metodo para retornar o valor do campo issn */
    public String getIssn() {
        return issn;
    }

    /* Metodo para definir o valor do campo issn */
    public void setIssn(String issn) {
        this.issn = issn;
    }

    /* Metodo para retornar a lista de artigos associados a RevistaCientifica*/
    public List<Artigo> getArtigos() {
        return artigos;
    }

    /* Metodo que define a lista de artigos associados a RevistaCientifica */
    public void setArtigos(List<Artigo> artigos) {
        this.artigos = artigos;
    }
}
