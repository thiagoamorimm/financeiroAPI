package com.thiagoamorimm.financeiro.controller;

import com.thiagoamorimm.financeiro.dto.ReceitaDTO;
import com.thiagoamorimm.financeiro.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping
    public List<ReceitaDTO> listarTodas() {
        return receitaService.listarTodas();
    }

    @GetMapping("/{id}")
    public Optional<ReceitaDTO> buscarPorId(@PathVariable Long id) {
        return receitaService.buscarPorId(id);
    }

    @PostMapping
    public ReceitaDTO salvar(@RequestBody ReceitaDTO receitaDTO) {
        return receitaService.salvar(receitaDTO);
    }

    @PutMapping("/{id}")
    public ReceitaDTO atualizar(@PathVariable Long id, @RequestBody ReceitaDTO receitaDTO) {
        return receitaService.atualizar(id, receitaDTO);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        receitaService.excluir(id);
    }

    @GetMapping("/buscarPorData")
    public List<ReceitaDTO> buscarPorData(@RequestParam("inicio") String inicio, @RequestParam("fim") String fim) {
        LocalDate dataInicio = LocalDate.parse(inicio);
        LocalDate dataFim = LocalDate.parse(fim);
        return receitaService.buscarPorData(dataInicio, dataFim);
    }
}
