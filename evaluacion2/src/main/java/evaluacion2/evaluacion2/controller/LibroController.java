package evaluacion2.evaluacion2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import evaluacion2.evaluacion2.dto.LibroDTO;
import evaluacion2.evaluacion2.service.LibroService;

@RestController
@RequestMapping("api/v1/libros")
public class LibroController {
    @Autowired
    private LibroService libroService;

    // Retornar lista libros
    @GetMapping
    public ResponseEntity<List<LibroDTO>> obtenerTodosLibros() {
        List<LibroDTO> listaLibros = libroService.obtenerTodos();
        if (listaLibros.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listaLibros, HttpStatus.OK);
    }

    // Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<LibroDTO> obtenerPorId(@PathVariable Integer id) {
        try {
            LibroDTO libro = libroService.buscarPorId(id);
            return new ResponseEntity<>(libro, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar por autor
    @GetMapping("/autor/{autor}")
    public ResponseEntity<List<LibroDTO>> buscarPorAutor(@PathVariable String autor) {
        List<LibroDTO> listaLibros = libroService.buscarPorAutor(autor);
        if (listaLibros.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listaLibros, HttpStatus.OK);
    }
    // Buscar por categoria
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<LibroDTO>> buscarPorCategoria(@PathVariable String categoria) {
        List<LibroDTO> listaLibros = libroService.buscarPorCategoria(categoria);
        if (listaLibros.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listaLibros, HttpStatus.OK);
    }
    // Buscar por editorial
    @GetMapping("/editorial/{editorial}")
    public ResponseEntity<List<LibroDTO>> buscarPorEditorial(@PathVariable String editorial) {
        List<LibroDTO> listaLibros = libroService.buscarPorEditorial(editorial);
        if (listaLibros.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listaLibros, HttpStatus.OK);
    }
    // Buscar por prestamo
    @GetMapping("/prestamo/{id}")
    public ResponseEntity<List<LibroDTO>> buscarPorIdPrestamo(@PathVariable Integer id) {
        List<LibroDTO> listaLibros = libroService.buscarPorPrestamo(id);
        if (listaLibros.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listaLibros, HttpStatus.OK);
    }



    

}
