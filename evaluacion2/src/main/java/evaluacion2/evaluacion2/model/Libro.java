package evaluacion2.evaluacion2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "libro")
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer isbn;
    @NotBlank(message = "el libro debe tener un titulo")
    private String titulo;
    @NotBlank(message = "el libro debe tener una editorial")
    private String editorial;
    @NotBlank(message = "el libro debe tener un autor")
    private String autor;
    @NotBlank(message = "el libro debe tener una fecha de publicacion")
    private String fecha_publicacion;
    @NotBlank(message = "el libro debe tener una categoria")
    private String categoria;
}
