package com.thiagoamorimm.financeiro.controller;

import com.thiagoamorimm.financeiro.dto.DespesaDTO;
import com.thiagoamorimm.financeiro.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @GetMapping
    public List<DespesaDTO> listarTodas() {
        return despesaService.listarTodas();
    }

    @GetMapping("/{id}")
    public Optional<DespesaDTO> buscarPorId(@PathVariable Long id) {
        return despesaService.buscarPorId(id);
    }

    @PostMapping
    public DespesaDTO salvar(@RequestBody DespesaDTO despesaDTO) {
        return despesaService.salvar(despesaDTO);
    }

    @PutMapping("/{id}")
    public DespesaDTO atualizar(@PathVariable Long id, @RequestBody DespesaDTO despesaDTO) {
        return despesaService.atualizar(id, despesaDTO);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        despesaService.excluir(id);
    }

    @GetMapping("/buscarPorData")
    public List<DespesaDTO> buscarPorData(@RequestParam("inicio") String inicio, @RequestParam("fim") String fim) {
        LocalDate dataInicio = LocalDate.parse(inicio);
        LocalDate dataFim = LocalDate.parse(fim);
        return despesaService.buscarPorData(dataInicio, dataFim);
    }
}
