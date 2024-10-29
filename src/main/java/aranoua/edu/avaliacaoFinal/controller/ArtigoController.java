package aranoua.edu.avaliacaoFinal.controller;

//-----------imports utilizados-------------------------------------------------

import aranoua.edu.avaliacaoFinal.dto.ArtigoInputDTO;
import aranoua.edu.avaliacaoFinal.dto.ArtigoOutputDTO;
import aranoua.edu.avaliacaoFinal.service.ArtigoService;
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
@RequestMapping("/api/artigo")
public class ArtigoController {

    //Injeção de dependência da camada de serviço
    @Autowired
    private ArtigoService artigoService;

    /* Notação para mepear o metodo como End-point GET para listar todos os artigos*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ArtigoOutputDTO>> list(){

        //Solicitado listagem da camada de serviço
        List <ArtigoOutputDTO> artigos = artigoService.lista();

        //Verificação se a lista está avisa
        if (!artigos.isEmpty()){
            //Retorna a lista e o status
            return new ResponseEntity<>(artigos, HttpStatus.OK);
        }else{
            //Retorna apenas o status de Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /* Notação para mepear o metodo como End-point GET para pesquisar um artigo, com o parametro vindo pelo URI*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<ArtigoOutputDTO>> getById(@PathVariable Long id, UriComponentsBuilder uriComponentsBuilder){

        //Solicitado pesquisa da camada de serviço
        ArtigoOutputDTO artigo = artigoService.getById(id);

        //Verificação se encontrou o objeto
        if (artigo != null){

            //Construção de um objeto UriComponents, através do objeto uriComponentsBuilder
            UriComponents uriComponents = uriComponentsBuilder.path("/api/autor/{id}").buildAndExpand(artigo.getId());

            //Criando uma URI através do objeto uriComponents
            URI uri = uriComponents.toUri();

            //Criação dos links de HATEOS, começando pelo link de listagem
            Link allArtigoLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(ArtigoController.class).list()
            ).withRel("AllArtigo");

            //Link de deleção do objeto
            Link deleteLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(ArtigoController.class).delete(artigo.getId())
            ).withRel("deleteArtigo");

            //Criação de um objeto EntityModel, passando o objeto encontrado e os links
            EntityModel <ArtigoOutputDTO> resource = EntityModel.of(artigo, allArtigoLink, deleteLink);
            //Response com o objeto encontrado com HATEOS
            return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(resource);
        } else {
            //Response com status Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    /* Notação para mepear o metodo como End-point POST para criar um artigo*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<ArtigoOutputDTO>> create(@RequestBody ArtigoInputDTO artigoInputDTO, UriComponentsBuilder uriComponentsBuilder){

        //Solicitado inserção da camada de serviço
        ArtigoOutputDTO artigoSalvo = artigoService.inserir(artigoInputDTO);

        //Verificação se salvou o objeto
        if (artigoSalvo != null){

            //Construção de um objeto UriComponents, através do objeto uriComponentsBuilder
            UriComponents uriComponents = uriComponentsBuilder.path("/api/artigo/{id}").buildAndExpand(artigoSalvo.getId());

            //Criando uma URI através do objeto uriComponents
            URI uri = uriComponents.toUri();

            //Criação dos links de HATEOS, começando pelo link de listagem
            Link allArtigoLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(ArtigoController.class).list()
            ).withRel("AllArtigo");

            //Link de deleção
            Link deleteLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(ArtigoController.class).delete(artigoSalvo.getId())
            ).withRel("deleteArtigo");

            //Criação de um objeto EntityModel, passando o objeto encontrado e os links

            EntityModel <ArtigoOutputDTO> resource = EntityModel.of(artigoSalvo, allArtigoLink, deleteLink);
            //Response com o objeto encontrado com HATEOS
            return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(resource);
        }else {
            //Response com status Not Found
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* Notação para mepear o metodo como End-point PUT para alterar um artigo, com o parametro vindo pelo URI e os dados no corpo da request*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<ArtigoOutputDTO>> update(@PathVariable Long id, @RequestBody ArtigoInputDTO artigoInputDTO, UriComponentsBuilder uriComponentsBuilder){

        //Solicitado alteração da camada de serviço
        ArtigoOutputDTO artigoAlterado = artigoService.alterar(id, artigoInputDTO);

        //Verificação se salvou o objeto
        if (artigoAlterado != null){
            //Construção de um objeto UriComponents, através do objeto uriComponentsBuilder
            UriComponents uriComponents = uriComponentsBuilder.path("/api/autor/{id}").buildAndExpand(artigoAlterado.getId());
            //Criando uma URI através do objeto uriComponents
            URI uri = uriComponents.toUri();

            //Criação dos links de HATEOS, começando pelo link de listagem
            Link allArtigoLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(ArtigoController.class).list()
            ).withRel("AllArtigo");

            //Link de deleção
            Link deleteLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(ArtigoController.class).delete(artigoAlterado.getId())
            ).withRel("deleteArtigo");

            //Criação de um objeto EntityModel, passando o objeto encontrado e os links
            EntityModel <ArtigoOutputDTO> resource = EntityModel.of(artigoAlterado, allArtigoLink, deleteLink);
            //Response com o objeto encontrado com HATEOS
            return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(resource);
        }else {
            //Response com status Not Found
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /* Notação para mepear o metodo como End-point DELETE para deletar um artigo, com o parametro vindo pelo URI*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        //Solicitado pesquisa da camada de serviço
        ArtigoOutputDTO artigo = artigoService.getById(id);

        if(artigo != null){
            //Solicitado deleção da camada de serviço
            artigoService.deletar(artigo.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
