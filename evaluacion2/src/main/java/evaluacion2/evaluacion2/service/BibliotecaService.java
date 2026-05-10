package evaluacion2.evaluacion2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evaluacion2.evaluacion2.dto.BibliotecaDTO;
import evaluacion2.evaluacion2.model.Biblioteca;
import evaluacion2.evaluacion2.repository.BibliotecaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class BibliotecaService {

    @Autowired
    private BibliotecaRepository bibliotecaRepository;


    public List<BibliotecaDTO> obtenerTodas() {
        List<BibliotecaDTO> listaDTO = new ArrayList<>();
        for (Biblioteca bib : bibliotecaRepository.findAll()) {
            listaDTO.add(convertirADTO(bib));
        }
        return listaDTO;
    }
    public BibliotecaDTO buscarPorId(Integer id) {
        Biblioteca bib = bibliotecaRepository.findById(id).orElse(null);
        return (bib != null) ? convertirADTO(bib) : null;
    }
    public BibliotecaDTO guardar(Biblioteca biblioteca) {
        Biblioteca guardada = bibliotecaRepository.save(biblioteca);
        return convertirADTO(guardada);
    }
    public boolean eliminar(Integer id) {
        if (bibliotecaRepository.existsById(id)) {
            bibliotecaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private BibliotecaDTO convertirADTO(Biblioteca bib) {
        BibliotecaDTO dto = new BibliotecaDTO();
        dto.setId(bib.getIdBiblioteca());
        dto.setNombreBiblioteca(bib.getNombreBiblioteca());
        dto.setDireccion(bib.getDireccion());
        if (bib.getComuna() != null) {
            dto.setNombreComuna(bib.getComuna().getNombreComuna());
        }
        if (bib.getEmpleado() != null) {
            dto.setNombreEmpleados(bib.getEmpleado().stream().map(emp -> emp.getPnombre() + " " + emp.getPapellido()).collect(Collectors.toList()));
        }

        return dto;
    }
}