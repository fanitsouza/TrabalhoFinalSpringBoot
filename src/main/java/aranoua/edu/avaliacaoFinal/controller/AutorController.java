package aranoua.edu.avaliacaoFinal.controller;

//-----------imports utilizados-------------------------------------------------
import aranoua.edu.avaliacaoFinal.dto.AutorInputDTO;
import aranoua.edu.avaliacaoFinal.dto.AutorOutputDTO;
import aranoua.edu.avaliacaoFinal.service.AutorService;
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
@RequestMapping("/api/autor")
public class AutorController {

    //Injeção de dependência da camada de serviço
    @Autowired
    private AutorService autorService;

    /* Notação para mepear o metodo como End-point GET para listar todos os autores*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AutorOutputDTO>> list(){
        //Solicitado listagem da camada de serviço
        List <AutorOutputDTO> autores = autorService.lista();

        //Verificação se a lista está vazia
        if (!autores.isEmpty()){
            //Retorna a lista e o status
            return new ResponseEntity<>(autores, HttpStatus.OK);
        }else{
            //Retorna apenas o status de Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /* Notação para mepear o metodo como End-point GET para pesquisar um autor, com o parametro vindo pelo URI*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<AutorOutputDTO>> getById(@PathVariable Long id, UriComponentsBuilder uriComponentsBuilder){

        //Solicitado pesquisa da camada de serviço
        AutorOutputDTO autor = autorService.getById(id);

        //Verificação se encontrou o objeto
        if (autor!= null){

            //Construção de um objeto UriComponents, através do objeto uriComponentsBuilder
            UriComponents uriComponents = uriComponentsBuilder.path("/api/autor/{id}").buildAndExpand(autor.getId());

            //Criando uma URI através do objeto uriComponents, ou seja, converte o objeto uriComponents em URI
            URI uri = uriComponents.toUri();

            //Criação dos links de HATEOS, começando pelo link de listagem
            Link allAutorLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(AutorController.class).list()
            ).withRel("AllAutor");

            //Link de deleção do objeto
            Link deleteLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(AutorController.class).delete(autor.getId())
            ).withRel("deleteAutor");

            //Criação de um objeto EntityModel, passando o objeto encontrado e os links
            EntityModel <AutorOutputDTO> resource = EntityModel.of(autor, allAutorLink, deleteLink);

            //Response com o objeto encontrado com HATEOS
            return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(resource);
        }else{
            //Response com status Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /* Notação para mepear o metodo como End-point POST para criar um autor*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<AutorOutputDTO>> create(@RequestBody AutorInputDTO autorInputDTO, UriComponentsBuilder uriComponentsBuilder){

        //Solicitado inserção da camada de serviço
        AutorOutputDTO autorSalvo = autorService.inserir(autorInputDTO);

        //Verificação se salvou o objeto
        if (autorSalvo != null){
            //Construção de um objeto UriComponents, através do objeto uriComponentsBuilder
            UriComponents uriComponents = uriComponentsBuilder.path("/api/autor/{id}").buildAndExpand(autorSalvo.getId());
            //Criando uma URI através do objeto uriComponents
            URI uri = uriComponents.toUri();

            //Criação dos links de HATEOS, começando pelo link de pesquisa
            Link selfLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(AutorController.class).getById(autorSalvo.getId(), uriComponentsBuilder)
            ).withSelfRel();

            //Link de listagem
            Link allAutorLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(AutorController.class).list()
            ).withRel("AllAutor");

            //Link de deleção do objeto
            Link deleteLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(AutorController.class).delete(autorSalvo.getId())
            ).withRel("deleteAutor");

            //Criação de um objeto EntityModel, passando o objeto encontrado e os links
            EntityModel <AutorOutputDTO> resource = EntityModel.of(autorSalvo, selfLink, allAutorLink, deleteLink);
            //Response com o objeto encontrado com HATEOS
            return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(resource);
        }else {
            //Response com status Not Found
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* Notação para mepear o metodo como End-point PUT para alterar um autor, com o parametro vindo pelo URI e os dados no corpo da request*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<AutorOutputDTO>> update(@PathVariable Long id, @RequestBody AutorInputDTO autorInputDTO, UriComponentsBuilder uriComponentsBuilder){

        //Solicitado alteração da camada de serviço
        AutorOutputDTO autorAlterado = autorService.alterar(id, autorInputDTO);

        //Verificação se salvou o objeto
        if (autorAlterado != null){
            //Construção de um objeto UriComponents, através do objeto uriComponentsBuilder
            UriComponents uriComponents = uriComponentsBuilder.path("/api/autor/{id}").buildAndExpand(autorAlterado.getId());
            //Criando uma URI através do objeto uriComponents
            URI uri = uriComponents.toUri();

            //Criação dos links de HATEOS, começando pelo link de pesquisa
            Link selfLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(AutorController.class).getById(autorAlterado.getId(), uriComponentsBuilder)
            ).withSelfRel();

            //Link de pesquisa
            Link allAutorLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(AutorController.class).list()
            ).withRel("AllAutor");

            //Link de deleção do objeto
            Link deleteLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(AutorController.class).delete(autorAlterado.getId())
            ).withRel("deleteAutor");

            //Criação de um objeto EntityModel, passando o objeto encontrado e os links
            EntityModel <AutorOutputDTO> resource = EntityModel.of(autorAlterado, selfLink, allAutorLink, deleteLink);
            //Response com o objeto encontrado com HATEOS
            return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(resource);
        }else {
            //Response com status Not Found
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* Notação para mepear o metodo como End-point DELETE para deletar um autor, com o parametro vindo pelo URI*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        //Solicitado pesquisa da camada de serviço
        AutorOutputDTO autor = autorService.getById(id);
        if(autor != null){
            //Solicitado deleção da camada de serviço
            autorService.deletar(autor.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
