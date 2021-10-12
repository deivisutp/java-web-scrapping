package br.com.deivisutp.brasileiraoapi.service;

import br.com.deivisutp.brasileiraoapi.dto.PartidaGoogleDTO;
import br.com.deivisutp.brasileiraoapi.entities.Partida;
import br.com.deivisutp.brasileiraoapi.util.ScrappingUtil;
import br.com.deivisutp.brasileiraoapi.util.StatusPartida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScrappingService {

    @Autowired
    private ScrappingUtil scrappingUtil;

    @Autowired
    private PartidaService partidaService;

    public void verificaPartidaPeriodo() {
        Integer quantidadePartida = partidaService.buscarQuantidadePartidasPeriodo();

        if (quantidadePartida > 0) {
            List<Partida> partidas = partidaService.listarPartidasPeriodo();

            partidas.forEach(partida -> {
                String urlPartida = scrappingUtil.montaUrlGoogle(
                        partida.getEquipeCasa().getNomeEquipe(),
                        partida.getEquipeVisitante().getNomeEquipe());

                PartidaGoogleDTO partidaGoogle = scrappingUtil.obtemInformacoesGoogle(urlPartida);

                if (partidaGoogle.getStatusPartida() != StatusPartida.PARTIDA_NAO_INICIADA) {
                    partidaService.atualizaPartida(partida, partidaGoogle);
                }
            });
        }
    }
}
