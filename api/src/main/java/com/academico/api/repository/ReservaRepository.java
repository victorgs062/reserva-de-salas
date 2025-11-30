package com.academico.api.repository;

import com.academico.api.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    @Query("""
        SELECT COUNT(r) > 0 
        FROM Reserva r 
        WHERE r.sala.id_sala = :id_sala
          AND r.data = :data
          AND (
                (r.dataHoraInicio <= :dataHoraFim AND r.dataHoraFim >= :dataHoraInicio)
              )
    """)
    boolean verificarDiaReservado(
            @Param("id_sala") int id_sala,
            @Param("data") LocalDateTime data,
            @Param("dataHoraInicio") LocalDateTime dataHoraInicio,
            @Param("dataHoraFim") LocalDateTime dataHoraFim
    );

    @Query("""
        SELECT COUNT(r) > 0 
        FROM Reserva r 
        WHERE r.usuario.id_usuario = :id_usuario
          AND r.data = :data
          AND (
                (r.dataHoraInicio <= :dataHoraFim AND r.dataHoraFim >= :dataHoraInicio)
              )
    """)
    boolean verificarProfessor(
            @Param("id_usuario") int id_usuario,
            @Param("data") LocalDateTime data,
            @Param("dataHoraInicio") LocalDateTime dataHoraInicio,
            @Param("dataHoraFim") LocalDateTime dataHoraFim
    );
}
