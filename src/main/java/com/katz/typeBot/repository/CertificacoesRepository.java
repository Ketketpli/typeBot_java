package com.katz.typeBot.repository;

import com.katz.typeBot.model.Certificacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificacoesRepository extends JpaRepository<Certificacoes, Long> {
}
