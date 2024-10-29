package aranoua.edu.avaliacaoFinal.dto;

//-----------imports utilizados-------------------------------------------------
import aranoua.edu.avaliacaoFinal.model.RevistaCientifica;
//------------------------------------------------------------------------------

//Define a classe RevistaCientificaInpuDTO
public class RevistaCientificaInputDTO {

    /* Declara dois atributos privados do tipo String, o nome e issn
   Esses campos serão preenchidos com os dados fornecidos pelo usuário
   para criar ou atualizar o RevistaCientifica*/
    private String nome;
    private String issn;

    //Permite a criação de um objeto RevistaCientificaDTO
    public RevistaCientificaInputDTO() {
    }

    // Construtor com parâmetros para criar um objeto RevistaCientificaInputDTO
    public RevistaCientificaInputDTO(String nome, String issn) {
        this.nome = nome;
        this.issn = issn;
    }

    //Metodo que retorna o valor do atributo nome
    public String getNome() {
        return nome;
    }

    //Metodo que define o valor do atributo nome
    public void setNome(String nome) {
        this.nome = nome;
    }

    //Metodo que retorna o valor do atributo issn
    public String getIssn() {
        return issn;
    }

    //Metodo que define o valor do atributo issn
    public void setIssn(String issn) {
        this.issn = issn;
    }

    // Constroi e retorna um objeto do tipo RevistaCientifica que será persistido no Banco
    public RevistaCientifica build(){
        RevistaCientifica revistaCientifica = new RevistaCientifica();
        revistaCientifica.setNome(this.nome);
        revistaCientifica.setIssn(this.issn);
        return revistaCientifica;
    }
}
