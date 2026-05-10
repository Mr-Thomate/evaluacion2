package evaluacion2.evaluacion2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import evaluacion2.evaluacion2.dto.PrestamoDTO;
import evaluacion2.evaluacion2.model.Prestamo;
import evaluacion2.evaluacion2.service.PrestamoService;

@RestController
@RequestMapping("/api/v1/prestamos")
public class PrestamoController {
    @Autowired
    private PrestamoService prestamoService;

    // Metodos
    @GetMapping
    public ResponseEntity<List<PrestamoDTO>> obtenerTodosPrestamos() {
        List<PrestamoDTO> resultado = prestamoService.obtenerTodos();
        if (resultado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoDTO> buscarPorId(@PathVariable Integer id) {
        try {
            PrestamoDTO resultado = prestamoService.buscarPorId(id);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Prestamo> agregarPrestamo(@RequestBody Prestamo prestamo) {
        try {
            Prestamo guardado = prestamoService.guardar(prestamo);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Prestamo> editarPrestamo(@PathVariable Integer id, @RequestBody Prestamo prestamo) {
        try {
            Prestamo editado = prestamoService.actualizar(id, prestamo);
            return new ResponseEntity<>(editado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> actualizarPrestamo(@PathVariable Integer id, @RequestBody Prestamo prestamo) {
        try {
            Prestamo actualizado = prestamoService.actualizar(id, prestamo);
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPrestamo(@PathVariable Integer id) {
        String resultado = prestamoService.eliminar(id);

        if (resultado.contains("exito")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }
}
