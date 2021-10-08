package br.com.deivisutp.brasileiraoapi.util;

import br.com.deivisutp.brasileiraoapi.dto.PartidaGoogleDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ScrappingUtil {

    private static final String PARTIDA_NAO_INICIADA = "div[class=imso_mh__vs-at-sep imso_mh__team-names-have-regular-font]";
    private static final String JOGO_ROLANDO = "div[class=imso_mh__lv-m-stts-cont]";
    private static final String PARTIDA_ENCERRADA = "span[class=imso_mh__ft-mtch imso-medium-font imso_mh__ft-mtchc]";

    private static final Logger LOGGER = LoggerFactory.getLogger(ScrappingUtil.class);
    private static final String BASE_URL_GOOGLE = "https://www.google.com.br/search?q=";
    private static final String COMPLEMENTO_URL_GOOGLE = "&hl=pt-BR";

    public static void main(String[] args) {
        String url = BASE_URL_GOOGLE + "letônia+x+Holanda" + COMPLEMENTO_URL_GOOGLE;

                //"letônia+x+Holanda" + COMPLEMENTO_URL_GOOGLE;
                //"brasil+x+venezuela" + COMPLEMENTO_URL_GOOGLE;


        ScrappingUtil scrappingUtil = new ScrappingUtil();
        scrappingUtil.obtemInformacoesPartida(url);
    }

    public PartidaGoogleDTO obtemInformacoesPartida(String url) {
        PartidaGoogleDTO partidaGoogleDTO = new PartidaGoogleDTO();

        try {
            Document document = Jsoup.connect(url).get();

            String title = document.title();
            LOGGER.info("Titulo da pagina: {}", title);

            obterStatusPartida(document);
            obtemTempoPartida(document);
        } catch (IOException e) {
            LOGGER.error("ERRO AO TENTAR CONECTAR A PAGINA JSOUP -> {}", e.getMessage());
        }
        return partidaGoogleDTO;
    }

    public StatusPartida obterStatusPartida(Document document) {
        StatusPartida statusPartida = StatusPartida.PARTIDA_NAO_INICIADA;

        // 2 - jogo rolando ou intervalo
        boolean isTempoPartida = document.select(JOGO_ROLANDO).isEmpty();
        if (!isTempoPartida) {
            String tempoPartida = document.select("div[class=imso_mh__lv-m-stts-cont]").first().text();
            statusPartida = StatusPartida.PARTIDA_EM_ANDAMENTO;

            if (tempoPartida.contains("Pênaltis")) {
                statusPartida = StatusPartida.PARTIDA_PENALTIS;
            }
            return statusPartida;
        }

        // 3 - jogo encerrado
        isTempoPartida = document.select(PARTIDA_ENCERRADA).isEmpty();
        if (!isTempoPartida) {
            statusPartida = StatusPartida.PARTIDA_ENCERRADA;
            return statusPartida;
        }

        return statusPartida;
    }

    public String obtemTempoPartida(Document document) {
        // situações
        // 1 - Consulta antes do inicio partida
        String tempoPartida = null;
        boolean isTempoPartida = document.select(PARTIDA_NAO_INICIADA).isEmpty();
        if (!isTempoPartida) {
            tempoPartida = document.select(PARTIDA_NAO_INICIADA).first().text();
        }

        // 2 - jogo rolando ou intervalo
        isTempoPartida = document.select(JOGO_ROLANDO).isEmpty();
        if (!isTempoPartida) {
            tempoPartida = document.select(JOGO_ROLANDO).first().text();
        }

        // 3 - jogo encerrado
        isTempoPartida = document.select(PARTIDA_ENCERRADA).isEmpty();
        if (!isTempoPartida) {
            tempoPartida = document.select(PARTIDA_ENCERRADA).first().text();
        }

        LOGGER.info(corrigeTempoPartida(tempoPartida));
        return corrigeTempoPartida(tempoPartida);
    }

    private static String corrigeTempoPartida(String tempo) {
        String tempoPartida = "";
        if (tempo.contains("'")) {
            tempoPartida = tempo.replace(" ", "");
            tempoPartida = tempoPartida.replace("'", "").concat(" min");
            return  tempoPartida;
        }
        if (tempo.contains("+")) {
            return tempo.replace(" ", "").concat(" min");
        }
        return tempo;
    }

}
