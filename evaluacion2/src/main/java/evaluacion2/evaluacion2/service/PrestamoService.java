package evaluacion2.evaluacion2.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import evaluacion2.evaluacion2.dto.PrestamoDTO;
import evaluacion2.evaluacion2.model.Prestamo;
import evaluacion2.evaluacion2.repository.PrestamoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    public List<PrestamoDTO> obtenerTodos() {
        return prestamoRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public PrestamoDTO buscarPorId(Integer id) {
        return prestamoRepository.findById(id)
                .map(this::convertirADTO)
                .orElseThrow(() -> new RuntimeException("Error: No se encontró el préstamo con el ID: " + id));
    }

    public Prestamo guardar(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    public Prestamo actualizar(Integer id, Prestamo prestamo) {
        Prestamo p = prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: El préstamo no existe."));
        
        if (prestamo.getFechaInicio() != null) p.setFechaInicio(prestamo.getFechaInicio());
        if (prestamo.getFechaDevolucion() != null) p.setFechaDevolucion(prestamo.getFechaDevolucion());
        if (prestamo.getEstado() != null) p.setEstado(prestamo.getEstado());
        if (prestamo.getLibro() != null) p.setLibro(prestamo.getLibro());
        if (prestamo.getCliente() != null) p.setCliente(prestamo.getCliente());
        if (prestamo.getBiblioteca() != null) p.setBiblioteca(prestamo.getBiblioteca());
        
        return prestamoRepository.save(p);
    }

    public String eliminar(Integer id) {
        try {
            Prestamo prestamo = prestamoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Error: No se puede eliminar el préstamo, pues no existe."));
            prestamoRepository.delete(prestamo);
            return "El préstamo con ID " + id + " ha sido eliminado con exito.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    private PrestamoDTO convertirADTO(Prestamo prestamo) {
        PrestamoDTO dto = new PrestamoDTO();
        dto.setIdPrestamo(prestamo.getId());
        dto.setFechaIncio(prestamo.getFechaInicio()); // Mapeo a 'fechaIncio' del DTO
        dto.setFechaFin(prestamo.getFechaDevolucion()); // Mapeo a 'fechaFin' del DTO
        dto.setEstado(prestamo.getEstado());
        
        if (prestamo.getLibro() != null) dto.setTituloLibro(prestamo.getLibro().getTitulo());
        if (prestamo.getCliente() != null) {
            dto.setNombreCliente(prestamo.getCliente().getPnombre() + " " + prestamo.getCliente().getPapellido());
        }
        if (prestamo.getBiblioteca() != null) dto.setNombreBiblioteca(prestamo.getBiblioteca().getNombreBiblioteca());
        
        return dto;
    }
}
