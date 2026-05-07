package evaluacion2.evaluacion2.dto;

import java.util.List;

import lombok.Data;

@Data
public class EditorialDTO {
    private Integer idEditorial;
    private String nombre;
    private List<String> tituloLibros; //mostrar el titulo de los libros que tiene la editorial
}
