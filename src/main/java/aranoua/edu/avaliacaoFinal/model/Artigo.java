package aranoua.edu.avaliacaoFinal.model;

//-----------imports utilizados-------------------------------------------------
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
//------------------------------------------------------------------------------

/*Notação para indicar que esta classe representa uma entidade que será
mapeada para uma tabela do banco de dados */
@Entity

//Definindo a classe Artigo que será a entidade mapeada
public class Artigo {

    //Marcando o campo id como chave primária da entidade
    @Id

    //Indicando que o valor de id será gerado automaticamente pelo banco
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //Declarando o campo id como um tipo Long privado
    private Long id;

    /* Especifica que o campo nome será mapeado para a coluna tituloArtigo
    no banco de dados e não pode ser nulo */
    @Column(name = "tituloArtigo", nullable = false)

    //Declarando o campo titulo como uma String privada
    private String titulo;

    /* Mapeando o campo ano para a coluna anoArtigo no banco
    de dados e não pode ser nulo */
    @Column(name = "anoArtigo", nullable = false)

    //Declara o campo ano como uma String privada
    private String ano;

    /* Fazendo a definição de muitos para muitos entre artigo e autores
     * onde um autor pode ter vários artigos e vice-versa */
    @ManyToMany

    //Instancia uma lista de autores que escreveram um artigo
    private List<Autor> autores = new ArrayList<>();

    /* Fazendo a definição de muitos para um entre artigo e revista
     * onde um artigo terá apenas uma revista */
    @ManyToOne

    //Declara um objeto revista do tipo RevistaCientifica
    private RevistaCientifica revista;

    /* Construtor sem parametros, necessário para que a JPA consiga
     * instanciar objetos da classe Autor ao realizar operações no BD*/
    public Artigo() {
    }

    // Construtor que permite instanciar um objeto Artigo
    public Artigo(Long id, String titulo, String ano, List<Autor> autores, RevistaCientifica revista) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
        this.autores = autores;
        this.revista = revista;
    }

    /* Metodo para retornar o valor do campo id */
    public Long getId() {
        return id;
    }

    /* Metodo para definir o valor do campo id */
    public void setId(Long id) {
        this.id = id;
    }

    /* Metodo para retornar o valor do campo titulo */
    public String getTitulo() {
        return titulo;
    }

    /* Metodo para definir o valor do campo titulo */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /* Metodo para retornar o valor do campo ano */
    public String getAno() {
        return ano;
    }

    /* Metodo para definir o valor do campo ano */
    public void setAno(String ano) {
        this.ano = ano;
    }

    /* Metodo para retornar a lista de autores associados ao artigo */
    public List<Autor> getAutores() {
        return autores;
    }

    /* Metodo que define a lista de autores associados ao artigo */
    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    /* Metodo que retorna o objeto do campo revista */
    public RevistaCientifica getRevista() {
        return revista;
    }

    /* Metodo que define o objeto do campo revista */
    public void setRevista(RevistaCientifica revista) {
        this.revista = revista;
    }
}
