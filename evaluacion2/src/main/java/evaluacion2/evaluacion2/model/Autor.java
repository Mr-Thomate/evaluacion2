package evaluacion2.evaluacion2.model;

import jakarta.validation.constraints.Size;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "autor")
@AllArgsConstructor
@NoArgsConstructor
public class Autor {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El autor/a debe tener nombre!")
    @Size(min = 3, max = 30, message = "El nombre debe estar entre 3 y 30 caracteres!")
    @Column(nullable = false, length = 30)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "autor_libro_id")
    private AutorLibro autorLibro;
}
