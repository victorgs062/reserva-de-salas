package com.academico.api.repository;

import com.academico.api.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {

    @Query("SELECT d FROM Disciplina d WHERE d.professor.id_usuario = :idUsuario")
    List<Disciplina> findByProfessorId(@Param("idUsuario") int idUsuario);


}
