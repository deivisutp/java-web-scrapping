package br.com.deivisutp.brasileiraoapi.service;

import br.com.deivisutp.brasileiraoapi.dto.EquipeResponseDTO;
import br.com.deivisutp.brasileiraoapi.entities.Equipe;
import br.com.deivisutp.brasileiraoapi.exception.NotFoundException;
import br.com.deivisutp.brasileiraoapi.repository.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    public Equipe buscarEquipeId(Long id) {
        return equipeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nenhuma equipe encontrada com o id informado: " + id));
    }

    public EquipeResponseDTO listarEquipes() {
        EquipeResponseDTO equipes = new EquipeResponseDTO();
        equipes.setEquipes(equipeRepository.findAll());

        return equipes;
    }
}
