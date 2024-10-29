package aranoua.edu.avaliacaoFinal.service;

//-----------imports utilizados-------------------------------------------------
import aranoua.edu.avaliacaoFinal.dto.*;
import aranoua.edu.avaliacaoFinal.model.Artigo;
import aranoua.edu.avaliacaoFinal.model.Autor;
import aranoua.edu.avaliacaoFinal.repository.ArtigoRepository;
import aranoua.edu.avaliacaoFinal.repository.AutorRepository;
import aranoua.edu.avaliacaoFinal.repository.RevistaCientificaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//------------------------------------------------------------------------------

//Notação para marcar a classe como um componente de serviço
@Service
public class ArtigoService {

    //Injeçao de uma instancia de ArtigoRepository na classe ArtigoService
    @Autowired
    private ArtigoRepository artigoRepository;

    //Injeçao de uma instancia de AutorRepository na classe ArtigoService
    @Autowired
    private AutorRepository autorRepository;

    //Injeçao de uma instancia de RevistaCientificaRepository na classe ArtigoService
    @Autowired
    private RevistaCientificaRepository revistaCientificaRepository;

    //Metodo que retorna uma lista de todos os artigos
    public List<ArtigoOutputDTO> lista(){

        //Chama o metodo findAll do repository para recuperar todos os artigos do banco
        List<Artigo> artigos = artigoRepository.findAll();

        //Cria uma lista vazia de artigoOutputDTO para armazenar os dados de saída
        List<ArtigoOutputDTO> artigoOutputDTOS = new ArrayList<>();

        //Pega cada artigo da lista de artigos e converte para um ArtigoOutputDTO
        //adicionando a artigoOutputDTOS
        for(Artigo artigo: artigos){
            artigoOutputDTOS.add(new ArtigoOutputDTO(artigo));
        }

        //Retorna a lista artigoOutputDTO que contem os artigos formatados como DTOS
        return artigoOutputDTOS;

    }

    //Metodo para recuperar um artigo especifico pelo id
    public ArtigoOutputDTO getById(Long id){

        //Usa o findById do repository para buscar o artigo com o id fornecido retornando um opcional
        Optional<Artigo> artigoOptional = artigoRepository.findById(id);

        //Converte o objeto Artigo retornando um ArtigoOutputDTO
        return new ArtigoOutputDTO(artigoOptional.get());

    }

    //Metodo de inserir um artigo no banco de dados
    public ArtigoOutputDTO inserir(ArtigoInputDTO artigoInputDTO){
        try {

            //Construindo um objeto artigo passando como parametro as dependencias necessárias
            Artigo artigo= artigoInputDTO.build(revistaCientificaRepository, autorRepository);

            //Salvando o objeto no banco e retornando o objeto salvo (artigoInserido)
            Artigo artigoInserido = artigoRepository.save(artigo);

            //Convertendo o objeto que foi salvo em um DTO para ser retornado
            return new ArtigoOutputDTO(artigoInserido);


        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    //Metodo alterar para alterar um artigo
    public ArtigoOutputDTO alterar(Long id, ArtigoInputDTO artigoInputDTO){
        try {
            //Pesquisando um objeto do tipo artigo através do id
            Optional <Artigo> artigoAlterar = artigoRepository.findById(id);

            //Se o objeto estiver presente
            if (artigoAlterar.isPresent()){

                //Através dos dados vindo do artigoInputDTO serão feitas as alterações
                Artigo artigo = artigoAlterar.get();
                artigo.setTitulo(artigoInputDTO.getTitulo());
                artigo.setAno(artigoInputDTO.getAno());

                //Criando uma lista de autores
                List<Autor> autores = new ArrayList<>();

                //Através dos nomes vindos do artigoInputDTO esta sendo feita uma pesquisa que
                //retornam objetos do tipo Autor para ser adicionados na lista autores
                for (String autor: artigoInputDTO.getAutores()){
                    autores.add(autorRepository.findByName(autor));
                }
                artigo.setAutores(autores);

                //Pesquisando a revista para ser definida as alterações
                artigo.setRevista(revistaCientificaRepository.findByName(artigoInputDTO.getRevista()));

                //Salvando o objeto no banco e retornando o objeto salvo (artigoAlterado)
                Artigo artigoAlterado = artigoRepository.save(artigo);

                //Convertendo o objeto que foi salvo em um DTO para ser retornado
                return new ArtigoOutputDTO(artigoAlterado);

            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Metodo deletar para excluir um artigo com o id informado
    public void deletar(Long id){
        try {
            //Chama o metodo deleteById do repository para excluir o artigo
            artigoRepository.deleteById(id);

        //Em caso de erro imprime o stack trace
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
