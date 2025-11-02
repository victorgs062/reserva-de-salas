package com.academico.api.repository;

import com.academico.api.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {
}
