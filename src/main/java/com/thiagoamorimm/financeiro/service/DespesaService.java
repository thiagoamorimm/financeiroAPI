package com.thiagoamorimm.financeiro.service;

import com.thiagoamorimm.financeiro.dto.DespesaDTO;
import com.thiagoamorimm.financeiro.model.Categoria;
import com.thiagoamorimm.financeiro.model.Despesa;
import com.thiagoamorimm.financeiro.repository.CategoriaRepository;
import com.thiagoamorimm.financeiro.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<DespesaDTO> listarTodas() {
        return despesaRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<DespesaDTO> buscarPorId(Long id) {
        return despesaRepository.findById(id).map(this::convertToDTO);
    }

    public DespesaDTO salvar(DespesaDTO despesaDTO) {
        Despesa despesa = convertToEntity(despesaDTO);
        return convertToDTO(despesaRepository.save(despesa));
    }

    public DespesaDTO atualizar(Long id, DespesaDTO despesaDTO) {
        return despesaRepository.findById(id)
                .map(despesa -> {
                    despesa.setDescricao(despesaDTO.getDescricao());
                    despesa.setValor(despesaDTO.getValor());
                    despesa.setData(despesaDTO.getData());
                    despesa.setCategoria(categoriaRepository.findByNome(despesaDTO.getCategoriaNome()));
                    return convertToDTO(despesaRepository.save(despesa));
                })
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada"));
    }

    public void excluir(Long id) {
        despesaRepository.deleteById(id);
    }

    public List<DespesaDTO> buscarPorData(LocalDate inicio, LocalDate fim) {
        return despesaRepository.findByDataBetween(inicio, fim).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private DespesaDTO convertToDTO(Despesa despesa) {
        DespesaDTO despesaDTO = new DespesaDTO();
        despesaDTO.setId(despesa.getId());
        despesaDTO.setDescricao(despesa.getDescricao());
        despesaDTO.setValor(despesa.getValor());
        despesaDTO.setData(despesa.getData());
        despesaDTO.setCategoriaNome(despesa.getCategoria().getNome());
        return despesaDTO;
    }

    private Despesa convertToEntity(DespesaDTO despesaDTO) {
        Despesa despesa = new Despesa();
        despesa.setDescricao(despesaDTO.getDescricao());
        despesa.setValor(despesaDTO.getValor());
        despesa.setData(despesaDTO.getData());
        Categoria categoria = categoriaRepository.findByNome(despesaDTO.getCategoriaNome());
        despesa.setCategoria(categoria);
        return despesa;
    }
}
