package evaluacion2.evaluacion2.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evaluacion2.evaluacion2.dto.ComunaDTO;
import evaluacion2.evaluacion2.model.Comuna;
import evaluacion2.evaluacion2.repository.ComunaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    public List<ComunaDTO> obtenerTodas() {
        return comunaRepository.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public ComunaDTO buscarPorId(Integer id) {
        return comunaRepository.findById(id).map(this::convertirADTO).orElseThrow(() -> new RuntimeException("Error: No se encontró la comuna con el ID: " + id));
    }

    public ComunaDTO guardar(Comuna comuna) {
        Comuna guardada = comunaRepository.save(comuna);
        return convertirADTO(guardada);
    }

    public void eliminar(Integer id) {
        if (!comunaRepository.existsById(id)) {
            throw new RuntimeException("Error: No se puede eliminar, la comuna ID " + id + " no existe.");
        }
        comunaRepository.deleteById(id);
    }

    private ComunaDTO convertirADTO(Comuna comuna) {
        ComunaDTO dto = new ComunaDTO();
        dto.setIdComuna(comuna.getIdComuna());
        dto.setNombreComuna(comuna.getNombreComuna());
        if (comuna.getRegion() != null) {
            dto.setRegion(comuna.getRegion().getNombreRegion());
        }
        if (comuna.getBiblioteca() != null) {
            dto.setNombreBibliotecas(comuna.getBiblioteca().stream().map(bib -> bib.getNombreBiblioteca()).collect(Collectors.toList()));
        }

        return dto;
    }
}