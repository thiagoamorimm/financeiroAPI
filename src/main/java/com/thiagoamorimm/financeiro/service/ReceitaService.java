package com.thiagoamorimm.financeiro.service;

import com.thiagoamorimm.financeiro.dto.ReceitaDTO;
import com.thiagoamorimm.financeiro.model.Categoria;
import com.thiagoamorimm.financeiro.model.Receita;
import com.thiagoamorimm.financeiro.repository.CategoriaRepository;
import com.thiagoamorimm.financeiro.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<ReceitaDTO> listarTodas() {
        return receitaRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<ReceitaDTO> buscarPorId(Long id) {
        return receitaRepository.findById(id).map(this::convertToDTO);
    }

    public ReceitaDTO salvar(ReceitaDTO receitaDTO) {
        Receita receita = convertToEntity(receitaDTO);
        return convertToDTO(receitaRepository.save(receita));
    }

    public ReceitaDTO atualizar(Long id, ReceitaDTO receitaDTO) {
        return receitaRepository.findById(id)
                .map(receita -> {
                    receita.setDescricao(receitaDTO.getDescricao());
                    receita.setValor(receitaDTO.getValor());
                    receita.setData(receitaDTO.getData());
                    receita.setCategoria(categoriaRepository.findByNome(receitaDTO.getCategoriaNome()));
                    return convertToDTO(receitaRepository.save(receita));
                })
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));
    }

    public void excluir(Long id) {
        receitaRepository.deleteById(id);
    }

    public List<ReceitaDTO> buscarPorData(LocalDate inicio, LocalDate fim) {
        return receitaRepository.findByDataBetween(inicio, fim).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ReceitaDTO convertToDTO(Receita receita) {
        ReceitaDTO receitaDTO = new ReceitaDTO();
        receitaDTO.setId(receita.getId());
        receitaDTO.setDescricao(receita.getDescricao());
        receitaDTO.setValor(receita.getValor());
        receitaDTO.setData(receita.getData());
        receitaDTO.setCategoriaNome(receita.getCategoria().getNome());
        return receitaDTO;
    }

    private Receita convertToEntity(ReceitaDTO receitaDTO) {
        Receita receita = new Receita();
        receita.setDescricao(receitaDTO.getDescricao());
        receita.setValor(receitaDTO.getValor());
        receita.setData(receitaDTO.getData());
        Categoria categoria = categoriaRepository.findByNome(receitaDTO.getCategoriaNome());
        receita.setCategoria(categoria);
        return receita;
    }
}