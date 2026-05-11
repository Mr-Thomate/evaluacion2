package evaluacion2.evaluacion2.dto;

import java.util.List;
import lombok.Data;

@Data
public class CategoriaDTO {
    private Integer id;
    private String nombre;
    private List<String> tituloLibros;
}
