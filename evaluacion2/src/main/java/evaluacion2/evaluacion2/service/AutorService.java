package evaluacion2.evaluacion2.service;

import java.util.ArrayList;
import java.util.List;

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

    private AutorDTO convertirADTO(Autor Atr) {
        AutorDTO dto = new AutorDTO();

        dto.setId(Atr.getId());
        dto.setNombre(Atr.getNombre());

        List<String> librosEscritos = new ArrayList<>();
        if(Atr.getLibroAutor() != null){
            for (LibroAutor libroAutor : Atr.getLibroAutor()) {
                librosEscritos.add(libroAutor.getLibro().getTitulo());
            }
        }else {
            librosEscritos.add("El Autor no tiene libros publicados");
        }
        dto.setTituloLibros(librosEscritos);

        return dto;
    }
}
