package evaluacion2.evaluacion2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import evaluacion2.evaluacion2.dto.CategoriaDTO;
import evaluacion2.evaluacion2.model.Categoria;
import evaluacion2.evaluacion2.service.CategoriaService;

@RestController
@RequestMapping("api/v1/Categorias")
public class CategoriaController {

    private CategoriaService categoriaService;


    // Retornar lista Categorias
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> obtenerTodasCategorias() {
        List<CategoriaDTO> listaCategorias = categoriaService.obtenerTodas();
        if (listaCategorias.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listaCategorias, HttpStatus.OK);
    }

    // Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> obtenerPorId(@PathVariable Integer id) {
        try {
            CategoriaDTO categoria = categoriaService.buscarPorId(id);
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Guardar nueva categoria
    @PostMapping
    public ResponseEntity<Categoria> guardarcategoria(@RequestBody Categoria categorianueva) {
        try {
            Categoria guardada = categoriaService.guardar(categorianueva);
            return new ResponseEntity<>(guardada, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Editar Categoria
    @PatchMapping("/{id}")
    public ResponseEntity<Categoria> editarCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        try {
            Categoria editada = categoriaService.guardar(categoria);
            return new ResponseEntity<>(editada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // actualizar Categoria
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        try {
            Categoria editada = categoriaService.actualizar(id, categoria);
            return new ResponseEntity<>(editada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Integer id) {
        String resultado = categoriaService.eliminar(id);
        if (resultado.contains("exito")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }

    // Buscar libros en categoria
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<CategoriaDTO>> buscarLibrosEnCategoria(@PathVariable String categoria) {
        List<CategoriaDTO> listaLibros = categoriaService.buscarLibrosEnCategoria(categoria);
        if (listaLibros.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listaLibros, HttpStatus.OK);
    }
}
