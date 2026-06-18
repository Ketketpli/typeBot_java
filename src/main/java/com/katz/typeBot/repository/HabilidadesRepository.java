package com.katz.typeBot.repository;

import com.katz.typeBot.model.HabilidadesTecnicas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabilidadesRepository extends JpaRepository<HabilidadesTecnicas, Long> {
}
