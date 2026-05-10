package evaluacion2.evaluacion2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import evaluacion2.evaluacion2.dto.RegionDTO;
import evaluacion2.evaluacion2.model.Region;
import evaluacion2.evaluacion2.service.RegionService;

@RestController
@RequestMapping("api/v1/regiones")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping
    public ResponseEntity<List<RegionDTO>> obtenerTodas() {
        List<RegionDTO> regiones = regionService.obtenerTodas();
        if (regiones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(regiones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionDTO> obtenerPorId(@PathVariable Integer id) {
        try {
            RegionDTO region = regionService.buscarPorId(id);
            return new ResponseEntity<>(region, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Region> guardarRegion(@RequestBody Region region) {
        try {
            Region guardada = regionService.guardar(region);
            return new ResponseEntity<>(guardada, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRegion(@PathVariable Integer id) {
        try {
            regionService.eliminar(id);
            return new ResponseEntity<>("La región ha sido eliminada exitosamente.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}