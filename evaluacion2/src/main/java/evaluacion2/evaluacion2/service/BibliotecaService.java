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

    public Biblioteca guardar(Biblioteca biblioteca) {
        return bibliotecaRepository.save(biblioteca);
    }

    public Biblioteca actualizar(Integer id, Biblioteca biblioteca) {
        Biblioteca bib = bibliotecaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: La biblioteca no existe."));
        if (biblioteca.getNombreBiblioteca() != null) {
            bib.setNombreBiblioteca(biblioteca.getNombreBiblioteca());
        }
        if (biblioteca.getDireccion() != null) {
            bib.setDireccion(biblioteca.getDireccion());
        }
        if (biblioteca.getComuna() != null) {
            bib.setComuna(biblioteca.getComuna());
        }
        if (biblioteca.getEmpleado() != null) {
            bib.setEmpleado(biblioteca.getEmpleado());
        }
        return bibliotecaRepository.save(bib);
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
