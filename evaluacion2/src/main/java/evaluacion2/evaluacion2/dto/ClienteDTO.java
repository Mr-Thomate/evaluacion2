package evaluacion2.evaluacion2.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private Integer id;
    private String pnombre;
    private String snombre;
    private String papellido;
    private String sapellido;
    private String fechaNacimiento;
    private String sexo;
    private Integer idPrestamo;
}
