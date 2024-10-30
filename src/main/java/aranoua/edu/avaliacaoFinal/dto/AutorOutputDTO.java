package aranoua.edu.avaliacaoFinal.dto;

//-----------imports utilizados-------------------------------------------------
import aranoua.edu.avaliacaoFinal.model.Artigo;
import aranoua.edu.avaliacaoFinal.model.Autor;

import java.util.ArrayList;
import java.util.List;
//------------------------------------------------------------------------------

//Define a classe AutorInputDTO
public class AutorOutputDTO {

    //Declara o atributo id do tipo Long
    private Long id;

    //Declara o atributo nome do tipo String
    private String nome;

    //Declara o atributo afiliacao do tipo String
    private String afiliacao;

    //Instancia uma lista com titulo dos artigos do tipo String associados ao autor
    private List<String> artigos = new ArrayList<>();

    //Permite a criação de um objeto AutorOutputDTO
    public AutorOutputDTO() {
    }

    //Construtor que ao receber um objeto do tipo Autor constroi um objeto do tipo AutorOutputDTO
    public AutorOutputDTO(Autor autor) {
        this.id = autor.getId();
        this.nome = autor.getNome();
        this.afiliacao = autor.getAfiliacao().getNome();

        //Instancia uma lista de titulos de artigos do tipo String
        List<String> artigos = new ArrayList<>();

        //Pega a lista de artigos do autor para adicionar os titulos na lista de artigos
        for (Artigo artigo: autor.getArtigo()){
            artigos.add(artigo.getTitulo());
        }
        this.artigos = artigos;
    }

    //Metodo retorno o valor do atributo id
    public Long getId() {
        return id;
    }

    //Metodo define o valor do atributo id
    public void setId(Long id) {
        this.id = id;
    }

    //Metodo retorno o valor do atributo nome
    public String getNome() {
        return nome;
    }

    //Metodo define o valor do atributo nome
    public void setNome(String nome) {
        this.nome = nome;
    }

    //Metodo retorno o valor do atributo afiliacao
    public String getAfiliacao() {
        return afiliacao;
    }

    //Metodo define o valor do atributo afiliacao
    public void setAfiliacao(String afiliacao) {
        this.afiliacao = afiliacao;
    }

    //Metodo que retorna uma lista de titulos de artigos
    public List<String> getArtigos() {
        return artigos;
    }

    //Metodo que define a lista de titulos de artigos
    public void setArtigos(List<String> artigos) {
        this.artigos = artigos;
    }
}
