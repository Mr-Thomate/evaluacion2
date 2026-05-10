package evaluacion2.evaluacion2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evaluacion2.evaluacion2.dto.AutorDTO;
import evaluacion2.evaluacion2.model.Autor;
import evaluacion2.evaluacion2.model.LibroAutor;
import evaluacion2.evaluacion2.repository.AutorRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<AutorDTO> obtenerTodos() {
        return autorRepository.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public List<AutorDTO> buscarPorTituloLibro(String tituloLibro) {
        List<Autor> autores = autorRepository.findByLibroTitulo(tituloLibro);
        if (autores.isEmpty()) {
            throw new RuntimeException("Error: No se encontraron autores para el libro: " + tituloLibro);
        }
        return autores.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public Autor guardar(Autor autor) {
        return autorRepository.save(autor);
    }

    public Autor actualizar(Integer id, Autor autor) {
        Autor aut = autorRepository.findById(id).orElseThrow(() -> new RuntimeException("Error: El autor no existe."));
        if (autor.getNombre() != null) {
            aut.setNombre(autor.getNombre());
        }
        if (autor.getLibroAutor() != null) {
            aut.setLibroAutor(autor.getLibroAutor());
        }
        return autorRepository.save(aut);
    }

    public void eliminar(Integer id) {
        if (!autorRepository.existsById(id)) {
            throw new RuntimeException("Error: El autor con ID " + id + " no existe.");
        }
        autorRepository.deleteById(id);
    }

    private AutorDTO convertirADTO(Autor Atr) {
        AutorDTO dto = new AutorDTO();
        dto.setId(Atr.getId());
        dto.setNombre(Atr.getNombre());
        List<String> librosEscritos = new ArrayList<>();
        if (Atr.getLibroAutor() != null) {
            for (LibroAutor libroAutor : Atr.getLibroAutor()) {
                librosEscritos.add(libroAutor.getLibro().getTitulo());
            }
        } else {
            librosEscritos.add("El Autor no tiene libros publicados");
        }
        dto.setTituloLibros(librosEscritos);
        return dto;
    }
}
