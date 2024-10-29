package aranoua.edu.avaliacaoFinal.dto;

//-----------imports utilizados-------------------------------------------------
import aranoua.edu.avaliacaoFinal.model.Artigo;
import aranoua.edu.avaliacaoFinal.model.Autor;

import java.util.ArrayList;
import java.util.List;
//------------------------------------------------------------------------------

//Define a classe AutorInputDTO
public class ArtigoOutputDTO {

    //Declara o atributo id do tipo Long
    private Long id;

    //Declara o atributo titulo do tipo String
    private String titulo;

    //Declara o atributo ano do tipo String
    private String ano;

    //Instancia uma lista com nome dos autores do tipo String associados ao artigo
    private List<String> autores = new ArrayList<>();

    //Declara o objeto revista do tipo String
    private String revista;

    //Permite a criação de um objeto ArtigoOutputDTO
    public ArtigoOutputDTO() {
    }

    //Construtor que ao receber um objeto do tipo Artigo constroi um objeto do tipo ArtigoOutputDTO
    public ArtigoOutputDTO(Artigo artigo) {
        this.id = artigo.getId();
        this.titulo = artigo.getTitulo();
        this.ano = artigo.getAno();

        //Instancia uma lista de autores de artigo do tipo String
        List<String> autores = new ArrayList<>();

        //Pega a lista de autores para adicionar os nomes na lista String de autores
        for (Autor autor: artigo.getAutores()){
            autores.add(autor.getNome());
        }
        this.autores = autores;

        //Pega o nome da revista
        this.revista = artigo.getRevista().getNome();
    }

    //Metodo retorno o valor do atributo id
    public Long getId() {
        return id;
    }

    //Metodo define o valor do atributo id
    public void setId(Long id) {
        this.id = id;
    }

    //Metodo retorno o valor do atributo titulo
    public String getTitulo() {
        return titulo;
    }

    //Metodo define o valor do atributo titulo
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    //Metodo retorno o valor do atributo ano
    public String getAno() {
        return ano;
    }

    //Metodo define o valor do atributo ano
    public void setAno(String ano) {
        this.ano = ano;
    }

    //Metodo que retorna uma lista de autores
    public List<String> getAutores() {
        return autores;
    }

    //Metodo que define a lista de autores
    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    //Metodo retorno a revista
    public String getRevista() {
        return revista;
    }

    //Metodo define a revista
    public void setRevista(String revista) {
        this.revista = revista;
    }
}
