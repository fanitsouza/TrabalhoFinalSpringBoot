package aranoua.edu.avaliacaoFinal.dto;

//-----------imports utilizados-------------------------------------------------
import aranoua.edu.avaliacaoFinal.model.Autor;
//------------------------------------------------------------------------------

//Define a classe AutorInputDTO
public class AutorInputDTO {

    /* Declara dois atributos privados do tipo String, o nome e afiliação
    Esses campos serão preenchidos com os dados fornecidos pelo usuário
    para criar ou atualizar o autor*/
    private String nome;
    private String afiliacao;

    //Permite a criação de um objeto AutorInputDTO
    public AutorInputDTO() {
    }

    // Construtor com parâmetros para criar um objeto AutorInputDTO
    public AutorInputDTO(String nome, String afiliacao) {
        this.nome = nome;
        this.afiliacao = afiliacao;
    }

    //Metodo que retorna o valor do atributo nome
    public String getNome() {
        return nome;
    }

    //Metodo que define o valor do atributo nome
    public void setNome(String nome) {
        this.nome = nome;
    }

    //Metodo retorna o valor do atributo afiliacao
    public String getAfiliacao() {
        return afiliacao;
    }

    //Metodo que define o valor do atributo afiliacao
    public void setAfiliacao(String afiliacao) {
        this.afiliacao = afiliacao;
    }

    // Constroi e retorna um objeto do tipo Autor que será persistido no Banco
    public Autor build(){
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setAfiliacao(this.afiliacao);
        return autor;
    }
}
