package br.com.deivisutp.brasileiraoapi.repository;

import br.com.deivisutp.brasileiraoapi.entities.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {
}
