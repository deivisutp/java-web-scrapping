package br.com.deivisutp.brasileiraoapi.repository;

import br.com.deivisutp.brasileiraoapi.entities.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {
}
