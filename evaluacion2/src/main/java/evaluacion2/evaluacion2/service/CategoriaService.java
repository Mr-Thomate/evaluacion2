package evaluacion2.evaluacion2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import evaluacion2.evaluacion2.dto.CategoriaDTO;
import evaluacion2.evaluacion2.model.Categoria;
import evaluacion2.evaluacion2.model.LibroCategoria;
import evaluacion2.evaluacion2.repository.CategoriaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> obtenerTodas() {
        List<CategoriaDTO> dtos = new ArrayList<>();
        for (Categoria c : categoriaRepository.findAll()) {
            dtos.add(convertirADTO(c));
        }
        return dtos;
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

    public void eliminar(Integer id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Error: No se puede eliminar, la categoria ID " + id + " no existe.");
        }
        categoriaRepository.deleteById(id);
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
