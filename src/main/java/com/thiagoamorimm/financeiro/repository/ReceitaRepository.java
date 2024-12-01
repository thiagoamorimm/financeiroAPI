package com.thiagoamorimm.financeiro.repository;

import com.thiagoamorimm.financeiro.model.Despesa;
import com.thiagoamorimm.financeiro.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    List<Receita> findByDataBetween(LocalDate inicio, LocalDate fim);
}
