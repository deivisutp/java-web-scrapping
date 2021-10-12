package br.com.deivisutp.brasileiraoapi.repository;

import br.com.deivisutp.brasileiraoapi.entities.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {

    @Query(name = "buscar_quantidade_partidas_periodo",
            value = "select count(*) from partida as p "
                    + "where p.data_hora_partida between dateadd(hour, -3, current_timestamp) and current_timestamp "
                    + "and ifnull(p.tempo_partida, 'Vazio') != 'Encerrado' ",
            nativeQuery = true)
    Integer buscarQuantidadePartidasPeriodo();

    @Query(name = "listar_partidas_periodo",
            value = "select * from partida as p "
                    + "where p.data_hora_partida between dateadd(hour, -3, current_timestamp) and current_timestamp "
                    + "and ifnull(p.tempo_partida, 'Vazio') != 'Encerrado' ",
            nativeQuery = true)
    List<Partida> listarPartidasPeriodo();
}
