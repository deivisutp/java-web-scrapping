package br.com.deivisutp.brasileiraoapi.dto;

import br.com.deivisutp.brasileiraoapi.entities.Partida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartidaResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Partida> partidas;
}
