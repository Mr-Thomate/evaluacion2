package evaluacion2.evaluacion2.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evaluacion2.evaluacion2.dto.EditorialDTO;
import evaluacion2.evaluacion2.model.Editorial;
import evaluacion2.evaluacion2.repository.EditorialRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;
    
    public List<EditorialDTO> obtenerTodas() {
        return editorialRepository.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public EditorialDTO buscarPorId(Integer id) {
        return editorialRepository.findById(id).map(this::convertirADTO).orElseThrow(() -> new RuntimeException("Error: No se encontró la editorial con el ID: " + id));
    }

    public List<EditorialDTO> buscarPorTituloLibro(String tituloLibro) {
        List<Editorial> editoriales = editorialRepository.findByLibroTitulo(tituloLibro);
        
        if (editoriales.isEmpty()) {
            throw new RuntimeException("Error: No se encontraron editoriales para el libro: " + tituloLibro);
        }
        
        return editoriales.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public EditorialDTO guardar(Editorial editorial) {
        Editorial guardada = editorialRepository.save(editorial);
        return convertirADTO(guardada);
    }

    public void eliminar(Integer id) {
        if (!editorialRepository.existsById(id)) {
            throw new RuntimeException("Error: No se puede eliminar, la editorial ID " + id + " no existe.");
        }
        editorialRepository.deleteById(id);
    }

    private EditorialDTO convertirADTO(Editorial editorial) {
        EditorialDTO dto = new EditorialDTO();
        dto.setIdEditorial(editorial.getId());
        dto.setNombre(editorial.getNombre());
        if (editorial.getLibroEditorial() != null) {
            dto.setTituloLibros(editorial.getLibroEditorial().stream().map(le -> le.getLibro().getTitulo()).collect(Collectors.toList()));
        }
        return dto;
    }
}