package aranoua.edu.avaliacaoFinal.controller;

//-----------imports utilizados-------------------------------------------------

import aranoua.edu.avaliacaoFinal.dto.RevistaCientificaInputDTO;
import aranoua.edu.avaliacaoFinal.dto.RevistaCientificaOutputDTO;
import aranoua.edu.avaliacaoFinal.model.RevistaCientifica;
import aranoua.edu.avaliacaoFinal.service.RevistaCientificaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
//------------------------------------------------------------------------------
//Notação para marcar a classe como um componente de controle
@RestController
//Notação para marcar o mapeamento das request
@RequestMapping("/api/revistacientifica")
public class RevistaCientificaController {

    //Injeção de dependência da camada de serviço
    @Autowired
    private RevistaCientificaService revistaCientificaService;

    /* Notação para mepear o metodo como End-point GET para listar todos as revistas*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RevistaCientificaOutputDTO>> list(){
        //Solicitado listagem da camada de serviço
        List<RevistaCientificaOutputDTO> revistasCientificas = revistaCientificaService.lista();

        //Verificação se a lista está avisa
        if (!revistasCientificas.isEmpty()){
            //Retorna a lista e o status
            return new ResponseEntity<>(revistasCientificas, HttpStatus.OK);
        }else{
            //Retorna apenas o status de Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /* Notação para mepear o metodo como End-point GET para pesquisar uma revista, com o parametro vindo pelo URI*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<RevistaCientificaOutputDTO>> getById(@PathVariable Long id, UriComponentsBuilder uriComponentsBuilder){

        //Solicitado pesquisa da camada de serviço
        RevistaCientificaOutputDTO revistaCientifica = revistaCientificaService.getById(id);

        //Verificação se encontrou o objeto
        if (revistaCientifica != null){
            //Construção de um objeto UriComponents, através do objeto uriComponentsBuilder
            UriComponents uriComponents = uriComponentsBuilder.path("/api/revistacientifica/{id}").buildAndExpand(revistaCientifica.getId());
            //Criando uma URI através do objeto uriComponents
            URI uri = uriComponents.toUri();

            //Criação dos links de HATEOS, começando pelo link de listagem
            Link allRevistaCientificaLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(RevistaCientificaController.class).list()
            ).withRel("All-RevistaCientifica");

            //Link de deleção do objeto
            Link deleteLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(RevistaCientificaController.class).delete(revistaCientifica.getId())
            ).withRel("delete-RevistaCientifica");

            //Criação de um objeto EntityModel, passando o objeto encontrado e os links
            EntityModel <RevistaCientificaOutputDTO> resource = EntityModel.of(revistaCientifica, allRevistaCientificaLink, deleteLink);
            //Response com o objeto encontrado com HATEOS
            return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(resource);
        }else{
            //Response com status Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }



    /* Notação para mepear o metodo como End-point POST para criar uma revista*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<RevistaCientificaOutputDTO>> create(@RequestBody RevistaCientificaInputDTO revistaCientificaInputDTO, UriComponentsBuilder uriComponentsBuilder){

        //Solicitado inserção da camada de serviço
        RevistaCientificaOutputDTO revistaSalva = revistaCientificaService.inserir(revistaCientificaInputDTO);

        //Verificação se salvou o objeto
        if(revistaSalva != null){

            //Construção de um objeto UriComponents, através do objeto uriComponentsBuilder
            UriComponents uriComponents = uriComponentsBuilder.path("/api/revistacientifica/{id}").buildAndExpand(revistaSalva.getId());
            //Criando uma URI através do objeto uriComponents
            URI uri = uriComponents.toUri();

            //Criação dos links de HATEOS, começando pelo link de pesquisa
            Link selfLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(RevistaCientificaController.class).getById(revistaSalva.getId(), uriComponentsBuilder)
            ).withSelfRel();

            //Link de listagem
            Link allRevistaCientificaLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(RevistaCientificaController.class).list()
            ).withRel("AllRevistaCientifica");


            //Link de deleção
            Link deleteLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(RevistaCientificaController.class).delete(revistaSalva.getId())
            ).withRel("deleteRevistaCientifica");


            //Criação de um objeto EntityModel, passando o objeto encontrado e os links
            EntityModel <RevistaCientificaOutputDTO> resource = EntityModel.of(revistaSalva, selfLink, allRevistaCientificaLink, deleteLink);
            //Response com o objeto encontrado com HATEOS
            return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(resource);
        }else {
            //Response com status Not Found
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /* Notação para mepear o metodo como End-point PUT para alterar uma revista, com o parametro vindo pelo URI e os dados no corpo da request*/
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<RevistaCientificaOutputDTO>> update(@PathVariable Long id, @RequestBody RevistaCientificaInputDTO revistaCientificaInputDTO, UriComponentsBuilder uriComponentsBuilder){

        //Solicitado inserção da camada de serviço
        RevistaCientificaOutputDTO revistaAlterada = revistaCientificaService.alterar(id, revistaCientificaInputDTO);

        //Verificação se salvou o objeto
        if(revistaAlterada !=null){
            //Construção de um objeto UriComponents, através do objeto uriComponentsBuilder
            UriComponents uriComponents = uriComponentsBuilder.path("/api/revistacientifica/{id}").buildAndExpand(revistaAlterada.getId());
            //Criando uma URI através do objeto uriComponents
            URI uri = uriComponents.toUri();

            //Criação dos links de HATEOS, começando pelo link de pesquisa
            Link selfLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(RevistaCientificaController.class).getById(revistaAlterada.getId(), uriComponentsBuilder)
            ).withSelfRel();

            //Link de listagem
            Link allRevistaCientificaLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(RevistaCientificaController.class).list()
            ).withRel("AllRevistaCientifica");


            //Link de deleção
            Link deleteLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(RevistaCientificaController.class).delete(revistaAlterada.getId())
            ).withRel("deleteRevistaCientifica");


            //Criação de um objeto EntityModel, passando o objeto encontrado e os links
            EntityModel <RevistaCientificaOutputDTO> resource = EntityModel.of(revistaAlterada, selfLink, allRevistaCientificaLink, deleteLink);
            //Response com o objeto encontrado com HATEOS
            return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(resource);

        }else {
            //Response com status Not Found
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    /* Notação para mepear o metodo como End-point DELETE para deletar uma revista, com o parametro vindo pelo URI*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        //Solicitado pesquisa da camada de serviço
        RevistaCientificaOutputDTO revistaCientifica = revistaCientificaService.getById(id);

        if(revistaCientifica != null) {
            //Solicitado deleção da camada de serviço
            revistaCientificaService.deletar(revistaCientifica.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}
