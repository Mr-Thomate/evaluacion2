package evaluacion2.evaluacion2.dto;

import lombok.Data;

@Data
public class BibliotecaDTO {
    private Integer id;
    private String nombreBiblioteca;
    private String direccion;
    private String nombreComuna;
    private Integer idPrestamo;
    private Integer idEmpleado;
}
