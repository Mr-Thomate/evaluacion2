package evaluacion2.evaluacion2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evaluacion2.evaluacion2.dto.EmpleadoDTO;
import evaluacion2.evaluacion2.model.Empleado;
import evaluacion2.evaluacion2.repository.EmpleadoRepository;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<EmpleadoDTO> obtenerTodos() {
        List<EmpleadoDTO> listaDTO = new ArrayList<>();
        for (Empleado emp : empleadoRepository.findAll()) {
            listaDTO.add(convertirADTO(emp));
        }
        return listaDTO;
    }

    public EmpleadoDTO guardar(Empleado nuevoEmpleado) {
        Empleado guardado = empleadoRepository.save(nuevoEmpleado);
        return convertirADTO(guardado);
    }

    private EmpleadoDTO convertirADTO(Empleado emp) {
        EmpleadoDTO dto = new EmpleadoDTO();

        dto.setIdEmpleado(emp.getId());
        dto.setPnombre(emp.getPnombre());
        dto.setSnombre(emp.getSnombre());
        dto.setPapellido(emp.getPapellido());
        dto.setSapellido(emp.getSapellido());

        if (emp.getBiblioteca() != null) {
            dto.setNombreBiblioteca(emp.getBiblioteca().getNombreBiblioteca());
        }
        if (emp.getContrato() != null) {
            dto.setEstadoContrato("Con contrato");
        }else {
            dto.setEstadoContrato("Sin contrato");
        }

        return dto;
    }
}
