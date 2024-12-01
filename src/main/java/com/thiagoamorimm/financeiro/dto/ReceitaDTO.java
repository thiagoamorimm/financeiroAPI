package com.thiagoamorimm.financeiro.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ReceitaDTO {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    private String categoriaNome;
}