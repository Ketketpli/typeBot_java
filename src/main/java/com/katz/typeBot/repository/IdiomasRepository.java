package com.katz.typeBot.repository;

import com.katz.typeBot.model.Idiomas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdiomasRepository extends JpaRepository<Idiomas, Long> {
}
