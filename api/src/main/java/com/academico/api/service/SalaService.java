package com.academico.api.service;

import com.academico.api.dto.SalaRequestDTO;
import com.academico.api.dto.SalaResponseDTO;
import com.academico.api.model.Sala;
import com.academico.api.model.TipoSala;
import com.academico.api.model.TipoSalaBloco;
import com.academico.api.repository.SalaRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaService {

    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository){
        this.salaRepository = salaRepository;
    }

    public List<SalaResponseDTO> criarComExcel(MultipartFile file) {
        List<SalaResponseDTO> salasCriadas = new ArrayList<>();

        try (InputStream is = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0); // pega a primeira aba

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // pular cabeçalho

                String nome = row.getCell(0).getStringCellValue();
                int capacidade = (int) row.getCell(1).getNumericCellValue();
                String recursos = row.getCell(2).getStringCellValue();
                String tipoSalaStr = row.getCell(3).getStringCellValue();
                String tipoSalaBlocoStr = row.getCell(4).getStringCellValue();

                TipoSala tipoSalaEnum;
                try {
                    tipoSalaEnum = TipoSala.valueOf(tipoSalaStr.toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException("Tipo de sala inválido na linha " + (row.getRowNum() + 1) + ": " + tipoSalaStr);
                }

                TipoSalaBloco tipoSalaBlocoEnum;
                try {
                    tipoSalaBlocoEnum = TipoSalaBloco.valueOf(tipoSalaBlocoStr.toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException("Tipo de bloco inválido na linha " + (row.getRowNum() + 1) + ": " + tipoSalaBlocoStr);
                }

                SalaRequestDTO dto = new SalaRequestDTO(
                        nome,
                        capacidade,
                        recursos,
                        tipoSalaEnum,
                        tipoSalaBlocoEnum
                );

                SalaResponseDTO criada = this.criar(dto);
                salasCriadas.add(criada);
            }

            workbook.close();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar o arquivo Excel: " + e.getMessage(), e);
        }

        return salasCriadas;
    }

    public SalaResponseDTO criar(SalaRequestDTO dto) {
        Sala sala = new Sala(dto);
        Sala salva = salaRepository.save(sala);
        return toResponseDTO(salva);
    }

    public List<SalaResponseDTO> listar() {
        return salaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public SalaResponseDTO buscarPorId(int id) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));
        return toResponseDTO(sala);
    }

    public SalaResponseDTO atualizar(int id, SalaRequestDTO dto) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));

        sala.atualizar(dto);
        Sala atualizada = salaRepository.save(sala);
        return toResponseDTO(atualizada);
    }

    public void deletar(int id) {
        if (!salaRepository.existsById(id)) {
            throw new RuntimeException("Sala não encontrada");
        }
        salaRepository.deleteById(id);
    }

    private SalaResponseDTO toResponseDTO(Sala sala) {
        return new SalaResponseDTO(
                sala.getId_sala(),
                sala.getNome(),
                sala.getCapacidade(),
                sala.getRecursos(),
                sala.getTipoSala(),
                sala.getTipoSalaBloco()
        );
    }
}
