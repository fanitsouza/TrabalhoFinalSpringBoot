package aranoua.edu.avaliacaoFinal.service;

import aranoua.edu.avaliacaoFinal.dto.AfiliacaoInputDTO;
import aranoua.edu.avaliacaoFinal.dto.AfiliacaoOutputDTO;
import aranoua.edu.avaliacaoFinal.model.Afiliacao;
import aranoua.edu.avaliacaoFinal.repository.AfiliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AfiliacaoService {

    @Autowired
    private AfiliacaoRepository afiliacaoRepository;

    public List<AfiliacaoOutputDTO> lista(){
        List<Afiliacao> afiliacoes = afiliacaoRepository.findAll();
        List<AfiliacaoOutputDTO> afiliacaoOutputDTOS = new ArrayList<>();

        for(Afiliacao afiliacao: afiliacoes){
            afiliacaoOutputDTOS.add(new AfiliacaoOutputDTO(afiliacao));
        }

        return afiliacaoOutputDTOS;
    }

    public AfiliacaoOutputDTO inserir(AfiliacaoInputDTO afiliacaoInputDTO){
        try{
            Afiliacao afiliacao = afiliacaoInputDTO.build();
            Afiliacao afiliacaoInserida = afiliacaoRepository.save(afiliacao);
            return new AfiliacaoOutputDTO(afiliacaoInserida);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public AfiliacaoOutputDTO alterar(Long id, AfiliacaoInputDTO afiliacaoInputDTO){
        try {
            Optional <Afiliacao> afiliacaoAlterar = afiliacaoRepository.findById(id);

            if (afiliacaoAlterar.isPresent()){
                Afiliacao afiliacao = afiliacaoAlterar.get();
                afiliacao.setNome(afiliacaoInputDTO.getNome());
                afiliacao.setSigla(afiliacaoInputDTO.getSigla());
                afiliacao.setReferencia(afiliacaoInputDTO.getReferencia());

                Afiliacao afiliacaoAlterada = afiliacaoRepository.save(afiliacao);

                return new AfiliacaoOutputDTO(afiliacaoAlterada);
            }else{
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deletar(Long id){
        try {
            afiliacaoRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
