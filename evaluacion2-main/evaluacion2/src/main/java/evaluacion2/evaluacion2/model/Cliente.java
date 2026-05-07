package evaluacion2.evaluacion2.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cliente")
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El cliente debe tener primer nombre")
    @Size(min = 3, max = 15, message = "El primer nombre debe tener entre 3 y 15 caracteres.")
    @Column(nullable = false, length = 15)
    private String pnombre;

    @Column(nullable = true, length = 15)
    private String snombre;

    @NotBlank(message = "El cliente debe tener primer apellido")
    @Size(min = 3, max = 15, message = "El primer apellido debe tener entre 3 y 15 caracteres.")
    @Column(nullable = false, length = 15)
    private String papellido;

    @Column(nullable = true, length = 15)
    private String sapellido;

    @NotBlank(message = "El cliente debe registrar su fecha de nacimiento")
    @Size(min = 10, max = 10, message = "La fecha de nacimiento debe estar en formato 'dd-mm-yyyy'")
    @Column(nullable = false, length = 10)
    private String fechaNacimiento;

    @NotBlank(message = "El cliente debe determinar su sexo")
    @Size(min = 8, max = 20, message = "El sexo debe tener entre 8 y 20 caracteres")
    @Column(nullable = false, length = 20)
    private String sexo;

    @OneToMany(mappedBy = "cliente")
    private List<Prestamo> prestamo;
}
