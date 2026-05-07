package evaluacion2.evaluacion2.dto;

import lombok.Data;

@Data
public class PrestamoDTO {
    private Integer idPrestamo;
    private String fechaIncio;
    private String fechaFin;
    private String estado;
    private String nombreCliente;
    private String tituloLibro;
    private String nombreBiblioteca;
}
