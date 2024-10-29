package aranoua.edu.avaliacaoFinal.service;

//-----------imports utilizados-------------------------------------------------
import aranoua.edu.avaliacaoFinal.dto.AutorInputDTO;
import aranoua.edu.avaliacaoFinal.dto.AutorOutputDTO;
import aranoua.edu.avaliacaoFinal.model.Autor;
import aranoua.edu.avaliacaoFinal.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//------------------------------------------------------------------------------

//Notação para marcar a classe como um componente de serviço
@Service
public class AutorService {

    //Injeçao de uma instancia de AutorRepository na classe AutorService
    @Autowired
    private AutorRepository autorRepository;

    //Metodo que retorna uma lista de todos os autores
    public List<AutorOutputDTO> lista(){

        //Chama o metodo findAll do repository para recuperar todos os autores do banco
        List<Autor> autores = autorRepository.findAll();

        //Cria uma lista vazia de AutorOutputDTO para armazenar os dados de saída
        List<AutorOutputDTO> autorOutputDTOS = new ArrayList<>();

        //Pega cada Autor da lista de autores e converte para um AutorOutputDTO
        //adicionando a autorOutputDTOS
        for(Autor autor: autores){
            autorOutputDTOS.add(new AutorOutputDTO(autor));
        }

        //Retorna a lista autorOutputDTO que contem os autores formatados como DTOS
        return autorOutputDTOS;
    }


    //Metodo para recuperar um autor especifico pelo id
    public AutorOutputDTO getById(Long id){

        //Usa o findById do repository para buscar o autor com o id fornecido retornando um opcional
        Optional<Autor> autorOptional = autorRepository.findById(id);

        //Converte o objeto Autor retornando um AutorOutputDTO
        return new AutorOutputDTO(autorOptional.get());

    }

    //Metodo de inserir um autor no banco de dados
    public AutorOutputDTO inserir(AutorInputDTO autorInputDTO){
        try {
            //criou um objeto autor a partir do inputDTO
            Autor autor = autorInputDTO.build();

            //Salvando e retorna o objeto salvo
            Autor autorInserido = autorRepository.save(autor);

            //Convertendo o objeto salvo para AutorOutputDTO e o retorna
            return new AutorOutputDTO(autorInserido);

        //Em caso de erro imprime o stack trace e retorna null
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //Metodo alterar para atualizar os dados de uma autor existente
    public AutorOutputDTO alterar(Long id, AutorInputDTO autorInputDTO){
        try {
            //Busca o autor pelo id
            Optional <Autor> autorAlterar = autorRepository.findById(id);

            //Verifica se autor existe
            if (autorAlterar.isPresent()){
                //Define os novos valores a partir do autorInputDTO
                Autor autor = autorAlterar.get();
                autor.setNome(autorInputDTO.getNome());
                autor.setAfiliacao(autorInputDTO.getAfiliacao());

                //Salva o autor atualizado
                Autor autorAlterado = autorRepository.save(autor);

                //Converte o autor atualizado para AutorOutputDTO e o retorna
                return new AutorOutputDTO(autorAlterado);

                //Se o autor não existir retorna null
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // Metodo deletar para excluir um autor com o id informado
    public void deletar(Long id){
        try {
            //Chama o metodo deleteById do repository para excluir o autor
            autorRepository.deleteById(id);

        //Em caso de erro imprime o stack trace
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
