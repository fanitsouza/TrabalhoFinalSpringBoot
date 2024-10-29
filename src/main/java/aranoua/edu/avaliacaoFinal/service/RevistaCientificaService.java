package aranoua.edu.avaliacaoFinal.service;

//-----------imports utilizados-------------------------------------------------
import aranoua.edu.avaliacaoFinal.dto.RevistaCientificaInputDTO;
import aranoua.edu.avaliacaoFinal.dto.RevistaCientificaOutputDTO;
import aranoua.edu.avaliacaoFinal.model.RevistaCientifica;
import aranoua.edu.avaliacaoFinal.repository.RevistaCientificaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//------------------------------------------------------------------------------

//Notação para marcar a classe como um componente de serviço
@Service
public class RevistaCientificaService {

    //Injeçao de uma instancia de RevistaCientificaRepository na classe ArtigoService
    @Autowired
    private RevistaCientificaRepository revistaCientificaRepository;

    //Metodo que retorna uma lista de todas as revistas
    public List<RevistaCientificaOutputDTO> lista(){

        //Chama o metodo findAll do repository para recuperar todas as revistas do banco
        List<RevistaCientifica> revistasCientificas = revistaCientificaRepository.findAll();

        //Cria uma lista vazia de RevistaCientificaOutputDTO para armazenar os dados de saída
        List<RevistaCientificaOutputDTO> revistaCientificaOutputDTOS = new ArrayList<>();

        //Pega cada Revista da lista de revistas e converte para um RevistaCientificaOutputDTO
        //adicionando a revistaCientificaOutputDTOS
        for(RevistaCientifica revistaCientifica: revistasCientificas){
            revistaCientificaOutputDTOS.add(new RevistaCientificaOutputDTO(revistaCientifica));
        }

        //Retorna a lista revistaCientificaOutputDTO que contem as revistas formatados como DTOS
        return revistaCientificaOutputDTOS;
    }

    //Metodo para recuperar uma revista especifica pelo id
    public RevistaCientificaOutputDTO getById(Long id){

        //Usa o findById do repository para buscar a revista com o id fornecido retornando um opcional
        Optional<RevistaCientifica> revistaCientificaOptional = revistaCientificaRepository.findById(id);

        //Converte o objeto RevistaCientifica retornando um RevistaCientificaOutputDTO
        return new RevistaCientificaOutputDTO(revistaCientificaOptional.get());
    }

    //Metodo de inserir uma revisra no banco de dados
    public RevistaCientificaOutputDTO inserir(RevistaCientificaInputDTO revistaCientificaInputDTO){
        try {
            //Construindo um objeto revista passando como parametro as dependencias necessárias
            RevistaCientifica revistaCientifica = revistaCientificaInputDTO.build();

            //Salvando o objeto no banco e retornando o objeto salvo (revistaCientificaInserida)
            RevistaCientifica revistaCientificaInserida = revistaCientificaRepository.save(revistaCientifica);

            //Convertendo o objeto que foi salvo em um DTO para ser retornado
            return new RevistaCientificaOutputDTO(revistaCientificaInserida);


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Metodo alterar para alterar uma revista
    public RevistaCientificaOutputDTO alterar(Long id, RevistaCientificaInputDTO revistaCientificaInputDTO){
        try {
            //Pesquisando um objeto do tipo artigo através do id
            Optional <RevistaCientifica> possivelRevista = revistaCientificaRepository.findById(id);

            //Se o objeto estiver presente
            if(possivelRevista.isPresent()){

                //Através dos dados vindo do artigoInputDTO serão feitas as alterações
                RevistaCientifica revistaCientifica = possivelRevista.get();
                revistaCientifica.setNome(revistaCientificaInputDTO.getNome());
                revistaCientifica.setIssn(revistaCientificaInputDTO.getIssn());

                //Salvando o objeto no banco e retornando o objeto salvo (revistaAlterado)
                RevistaCientifica revistaAlterada = revistaCientificaRepository.save(revistaCientifica);

                //Convertendo o objeto que foi salvo em um DTO para ser retornado
                return new RevistaCientificaOutputDTO(revistaAlterada);
            }else{
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Metodo deletar para excluir um revista com o id informado
    public void deletar(Long id){
        try {
            //Chama o metodo deleteById do repository para excluir a revista
            revistaCientificaRepository.deleteById(id);

        //Em caso de erro imprime o stack trace
        }catch (Exception e){
            e.printStackTrace();
                    }
    }
}
