package evaluacion2.evaluacion2.dto;

import java.util.List;

import lombok.Data;

@Data
public class LibroDTO {
    private Integer isbn;
    private String titulo;
    private String fecha_publicacion;
    private String categoria;
    private String nombreEditorial;
    private String nombreAutor;
    private List<String> nombreClientesPrestamo; //mostrar el nombre de los clientes que tienen en prestamo el libro
}
