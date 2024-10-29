package aranoua.edu.avaliacaoFinal.dto;

//-----------imports utilizados-------------------------------------------------
import aranoua.edu.avaliacaoFinal.model.Artigo;
import aranoua.edu.avaliacaoFinal.model.RevistaCientifica;

import java.util.ArrayList;
import java.util.List;
//------------------------------------------------------------------------------

//Define a classe RevistaCientificaOutputDTO
public class RevistaCientificaOutputDTO {
    //Declara o atributo id do tipo Long
    private Long id;

    //Declara o atributo nome do tipo String
    private String nome;

    //Declara o atributo issn do tipo String
    private String issn;

    //Instancia uma lista com titulo dos artigos do tipo String associados a revista
    private List<String> artigos = new ArrayList<>();

    //Permite a criação de um objeto RevistaCientificaOutputDTO
    public RevistaCientificaOutputDTO() {
    }

    //Construtor que ao receber um objeto do tipo RevistaCientifica constroi um objeto do tipo RevistaCientificaOutputDTO
    public RevistaCientificaOutputDTO(RevistaCientifica revistaCientifica) {
        this.id = revistaCientifica.getId();
        this.nome = revistaCientifica.getNome();
        this.issn = revistaCientifica.getIssn();

        //Instancia uma lista de titulos de artigos do tipo String
        List<String> artigos = new ArrayList<>();

        //Pega a lista de artigos da revista para adicionar os titulos na lista de artigos
        for (Artigo artigo: revistaCientifica.getArtigos()){
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

    //Metodo retorno o valor do atributo issn
    public String getIssn() {
        return issn;
    }

    //Metodo define o valor do atributo issn
    public void setIssn(String issn) {
        this.issn = issn;
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
