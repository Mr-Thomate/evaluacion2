package evaluacion2.evaluacion2.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evaluacion2.evaluacion2.dto.LibroDTO;
import evaluacion2.evaluacion2.model.Libro;
import evaluacion2.evaluacion2.repository.LibroRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<LibroDTO> obtenerTodos() {
        return libroRepository.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public LibroDTO buscarPorId(Integer id) {
        return libroRepository.findById(id).map(this::convertirADTO).orElseThrow(() -> new RuntimeException("Error, No se encontró el libro con el ID: " + id));
    }

    public List<LibroDTO> buscarPorAutor(String nombreAutor) {
        return libroRepository.findByNombreAutor(nombreAutor).stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public List<LibroDTO> buscarPorCategoria(String nombreCategoria) {
        return libroRepository.findByNombreCategoria(nombreCategoria).stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public List<LibroDTO> buscarPorEditorial(String nombreEditorial) {
        return libroRepository.findByNombreEditorial(nombreEditorial).stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public List<LibroDTO> buscarPorPrestamo(Integer idPrestamo) {
        return libroRepository.findByIdPrestamo(idPrestamo).stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public LibroDTO guardar(Libro libro) {
        return convertirADTO(libroRepository.save(libro));
    }

    public void eliminar(Integer id) {
        if (!libroRepository.existsById(id)) {
            throw new RuntimeException("Error, No se puede eliminar, el libro ID " + id + " no existe.");
        }
        libroRepository.deleteById(id);
    }

    private LibroDTO convertirADTO(Libro libro) {
        LibroDTO dto = new LibroDTO();
        dto.setIsbn(libro.getIsbn());
        dto.setTitulo(libro.getTitulo());
        dto.setFecha_publicacion(libro.getFecha_publicacion());
        if (libro.getLibroCategoria() != null && !libro.getLibroCategoria().isEmpty()) {
            dto.setCategoria(libro.getLibroCategoria().get(0).getCategoria().getNombre());
        }
        if (libro.getLibroEditorial() != null && !libro.getLibroEditorial().isEmpty()) {
            dto.setNombreEditorial(libro.getLibroEditorial().get(0).getEditorial().getNombre());
        }
        if (libro.getLibroAutor() != null && !libro.getLibroAutor().isEmpty()) {
            dto.setNombreAutor(libro.getLibroAutor().get(0).getAutor().getNombre());
        }
        if (libro.getPrestamo() != null) {
            dto.setNombreClientesPrestamo(libro.getPrestamo().stream()
                .map(p -> p.getCliente().getPnombre() + " " + p.getCliente().getPapellido())
                .collect(Collectors.toList()));
        }
        return dto;
    }
}