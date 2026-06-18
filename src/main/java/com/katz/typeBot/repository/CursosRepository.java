package com.katz.typeBot.repository;

import com.katz.typeBot.model.CursosComplementares;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursosRepository extends JpaRepository<CursosComplementares, Long> {
}
