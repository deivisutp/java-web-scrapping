package br.com.deivisutp.brasileiraoapi.dto;

import br.com.deivisutp.brasileiraoapi.util.StatusPartida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartidaGoogleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private StatusPartida statusPartida;
    private String tempoPartida;

    private String nomeEquipeCasa;
    private String urlLogoEquipeCasa;
    private Integer placarEquipeCasa;
    private String golsEquipeCasa;
    private Integer placarEstendidoEquipeCasa;

    private String nomeEquipeVisitante;
    private String urlLogoEquipeVisitante;
    private Integer placarEquipeVisitante;
    private String golsEquipeVisitante;
    private Integer placarEstendidoEquipeVisitante;
}
