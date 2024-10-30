package aranoua.edu.avaliacaoFinal.dto;

import aranoua.edu.avaliacaoFinal.model.Afiliacao;

public class AfiliacaoOutputDTO {
    private Long id;
    private String nome;
    private String sigla;
    private String referencia;

    //private List<String> autores = new ArrayList<>();


    public AfiliacaoOutputDTO() {
    }

    public AfiliacaoOutputDTO(Afiliacao afiliacao) {
        this.id = afiliacao.getId();
        this.nome = afiliacao.getNome();
        this.sigla = afiliacao.getSigla();
        this.referencia = afiliacao.getReferencia();

        //List<String> autores = new ArrayList<>();
        //for(Autor autor: referencia.getAutor()){
        //autores.add(autor.getNome());
        //}
    }
    //this.autores = autores;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
