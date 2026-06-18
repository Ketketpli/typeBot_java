package com.katz.typeBot.repository;

import com.katz.typeBot.model.Experiencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienciasRepository extends JpaRepository<Experiencias, Long> {
}
