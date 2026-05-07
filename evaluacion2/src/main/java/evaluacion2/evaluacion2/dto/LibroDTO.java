package evaluacion2.evaluacion2.dto;

import lombok.Data;

@Data
public class LibroDTO {
    private Integer isbn;
    private String titulo;
    private String fecha_publicacion;
    private String categoria;
    private String editorial;
    private String autor;
    private Integer idPrestamo;
}
