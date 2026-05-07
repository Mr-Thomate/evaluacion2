package evaluacion2.evaluacion2.dto;

import lombok.Data;

@Data
public class ContratoDTO {
    private Integer idContrato;
    private String tipoCntrato;
    private String fechaInicio;
    private String fechaFin;
    private Integer sueldo;
    private Integer idEmpleado;
}
