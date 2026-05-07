package evaluacion2.evaluacion2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evaluacion2.evaluacion2.dto.EmpleadoDTO;
import evaluacion2.evaluacion2.model.Empleado;
import evaluacion2.evaluacion2.repository.EmpleadoRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<EmpleadoDTO> obtenerTodos() {
        log.info("Consultando todos los empleados");
        List<EmpleadoDTO> listaDTO = new ArrayList<>();
        for (Empleado emp : empleadoRepository.findAll()) {
            listaDTO.add(convertirADTO(emp));
        }
        return listaDTO;
    }

    public EmpleadoDTO guardar(Empleado nuevoEmpleado) {
        log.info("Guardando empleado: {}", nuevoEmpleado.getPnombre());
        Empleado guardado = empleadoRepository.save(nuevoEmpleado);
        return convertirADTO(guardado);
    }

    private EmpleadoDTO convertirADTO(Empleado emp) {
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setIdEmpleado(emp.getId());
        dto.setNombreCompleto(emp.getPnombre() + " " + emp.getPapellido());
        if (emp.getBiblioteca() != null) {
            dto.setNombreBiblioteca(emp.getBiblioteca().getNombreBiblioteca());
        } else {
            dto.setNombreBiblioteca("No asignada");
        }
        if (emp.getContrato() != null) {
            dto.setTipoContrato(emp.getContrato().getTipoContrato());
        } else {
            dto.setTipoContrato("Sin contrato");
        }
        
        return dto;
    }
}