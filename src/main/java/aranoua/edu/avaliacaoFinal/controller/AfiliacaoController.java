package aranoua.edu.avaliacaoFinal.controller;

import aranoua.edu.avaliacaoFinal.dto.AfiliacaoInputDTO;
import aranoua.edu.avaliacaoFinal.dto.AfiliacaoOutputDTO;
import aranoua.edu.avaliacaoFinal.dto.AutorOutputDTO;
import aranoua.edu.avaliacaoFinal.service.AfiliacaoService;
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

import javax.swing.text.html.parser.Entity;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/afiliacao")
public class AfiliacaoController {
    @Autowired
    private AfiliacaoService afiliacaoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AfiliacaoOutputDTO>> list(){
        List <AfiliacaoOutputDTO> afiliacoes = afiliacaoService.lista();

        if (!afiliacoes.isEmpty()){
            return new ResponseEntity<>(afiliacoes, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<AfiliacaoOutputDTO>> create(@RequestBody AfiliacaoInputDTO afiliacaoInputDTO, UriComponentsBuilder uriComponentsBuilder){
        AfiliacaoOutputDTO afiliacaoSalva = afiliacaoService.inserir(afiliacaoInputDTO);

        if (afiliacaoSalva != null){
            UriComponents uriComponents = uriComponentsBuilder.path("/api/afiliacao/{id}").buildAndExpand(afiliacaoSalva.getId());
            URI uri = uriComponents.toUri();

            Link selfLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(AfiliacaoController.class).list()
            ).withSelfRel();

            EntityModel <AfiliacaoOutputDTO> resource = EntityModel.of(afiliacaoSalva, selfLink);

            return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(resource);
        }else {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
