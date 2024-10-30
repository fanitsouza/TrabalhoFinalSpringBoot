package aranoua.edu.avaliacaoFinal.dto;

import aranoua.edu.avaliacaoFinal.model.Afiliacao;

public class AfiliacaoInputDTO {
    private String nome;
    private String sigla;
    private String referencia;

    public AfiliacaoInputDTO() {
    }

    public AfiliacaoInputDTO(String nome, String sigla, String referencia) {
        this.nome = nome;
        this.sigla = sigla;
        this.referencia = referencia;
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

    public Afiliacao build(){
        Afiliacao afiliacao = new Afiliacao();
        afiliacao.setNome(this.nome);
        afiliacao.setSigla(this.sigla);
        afiliacao.setReferencia(this.referencia);
        return afiliacao;
    }
}
