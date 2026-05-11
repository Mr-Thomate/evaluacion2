package evaluacion2.evaluacion2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evaluacion2.evaluacion2.dto.CategoriaDTO;
import evaluacion2.evaluacion2.model.Categoria;
import evaluacion2.evaluacion2.model.LibroCategoria;
import evaluacion2.evaluacion2.repository.CategoriaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> obtenerTodas() {
        List<CategoriaDTO> dtos = new ArrayList<>();
        for (Categoria c : categoriaRepository.findAll()) {
            dtos.add(convertirADTO(c));
        }
        return dtos;
    }

    public List<CategoriaDTO> buscarLibrosEnCategoria(String nombreCategoria) {
        return categoriaRepository.findByLibroTitulo(nombreCategoria).stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public CategoriaDTO buscarPorId(Integer id) {
        return categoriaRepository.findById(id).map(this::convertirADTO).orElseThrow(() -> new RuntimeException("Error: No se encontró la categoria con el ID: " + id));
    }

    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria actualizar(Integer id, Categoria categoria) {
        Categoria cat = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: La categoría no existe."));
        if (categoria.getNombre() != null) {
            cat.setNombre(categoria.getNombre());
        }
        if (categoria.getLibroCategoria() != null) {
            cat.setLibroCategoria(categoria.getLibroCategoria());
        }
        return categoriaRepository.save(cat);
    }

    public String eliminar(Integer id) {
        try {
            Categoria categoria = categoriaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Error: No se encuentra la categoria"));
            categoriaRepository.delete(categoria);
            return "El libro " + categoria.getNombre() + " ha sido eliminada con exito.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
    
    private CategoriaDTO convertirADTO(Categoria ctr) {
        CategoriaDTO dto = new CategoriaDTO();

        dto.setId(ctr.getId());
        dto.setNombre(ctr.getNombre());
        List<String> librosEnCategoria = new ArrayList<>();

        if (ctr.getLibroCategoria() != null) {
            for (LibroCategoria libroCategoria : ctr.getLibroCategoria()) {
                librosEnCategoria.add(libroCategoria.getLibro().getTitulo());
            }
        } else {
            librosEnCategoria.add("La categoria no contiene libros");
        }
        dto.setTituloLibros(librosEnCategoria);
        return dto;
    }
}
