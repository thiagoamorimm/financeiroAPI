package com.thiagoamorimm.financeiro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DespesaDTO {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    private String categoriaNome;

}
