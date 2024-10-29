package aranoua.edu.avaliacaoFinal.dto;

//-----------imports utilizados-------------------------------------------------
import aranoua.edu.avaliacaoFinal.model.Artigo;
import aranoua.edu.avaliacaoFinal.model.Autor;
import aranoua.edu.avaliacaoFinal.repository.AutorRepository;
import aranoua.edu.avaliacaoFinal.repository.RevistaCientificaRepository;

import java.util.ArrayList;
import java.util.List;
//------------------------------------------------------------------------------

//Define a classe ArtigoInputDTO
public class ArtigoInputDTO {

    /* Declara três atributos privados do tipo String, titulo, ano e revista
    Esses campos serão preenchidos com os dados fornecidos pelo usuário
    para criar ou atualizar o Artigo*/
    private String titulo;
    private String ano;

    // Instancia uma lista com os nomes de autores do tipo String
    private List<String>autores;

    // Declara a revista do tipo String pois so é necessário o nome e não o objeto inteiro
    private String revista;

    //Permite a criação de um objeto ArtigoInputDTO
    public ArtigoInputDTO() {
    }

    // Construtor com parametros para criar um objeto ArtigoInputDTO
    public ArtigoInputDTO(String titulo, String ano, List<String> autores, String revista) {
        this.titulo = titulo;
        this.ano = ano;
        this.autores = autores;
        this.revista = revista;
    }

    //Metodo que retorna o valor do atributo titulo
    public String getTitulo() {
        return titulo;
    }

    //Metodo que define o valor do atributo titulo
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    //Metodo que retorna o valor do atributo ano
    public String getAno() {
        return ano;
    }

    //Metodo que define o valor do atributo ano
    public void setAno(String ano) {
        this.ano = ano;
    }

    //Metodo que retorna uma lista com o nome dos autores
    public List<String> getAutores() {
        return autores;
    }

    //Metodo que define uma lista com o nome dos autores
    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    //Metodo que retorna o nome da revista
    public String getRevista() {
        return revista;
    }

    //Metodo que define o nome de revista
    public void setRevista(String revista) {
        this.revista = revista;
    }

    // Constroi e retorna um objeto do tipo Artigo que será persistido no Banco
    //Passa as dependencias como parametro para que a injeçao de dependencias
    public Artigo build(RevistaCientificaRepository revistaCientificaRepository, AutorRepository autorRepository){
        Artigo artigo = new Artigo();
        artigo.setTitulo(this.titulo);
        artigo.setAno(this.ano);

        //Cria uma lista de objetos do tipo Autor
        List<Autor> autores = new ArrayList<>();
        /*Através da lista de nome dos autores, é realizado uma pesquisa
        e é adicionado o autor encontrado na lista autores*/
        for (String autor: this.autores){
            autores.add(autorRepository.findByName(autor));
        }
        artigo.setAutores(autores);
        //Pesquisa a revista atraves do seu nome e adiciona ao artigo
        artigo.setRevista(revistaCientificaRepository.findByName(this.revista));
        //Retorna o objeto artigo
        return artigo;
    }
}
