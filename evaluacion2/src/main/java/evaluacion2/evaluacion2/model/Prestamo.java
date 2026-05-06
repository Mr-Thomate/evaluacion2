package evaluacion2.evaluacion2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "prestamo")
@NoArgsConstructor
@AllArgsConstructor
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "El prestamo debe tener fecha de inicio")
    @Size(min = 10, max = 10, message = "La fecha de inicio debe tener formato 'dd-mm-yyyy'")
    @Column(nullable = false, length = 10)
    private String fechaInicio;

    @Size(min = 10, max = 10, message = "La fecha de devolucion debe tener formato 'dd-mm-yyyy'")
    @Column(nullable = true, length = 10)
    private String fechaDevolucion;

    @NotBlank(message = "El prestamo debe tener registrado su estado")
    @Size(min = 3, max = 15, message = "El estado debe tener entre 3 y 15 caracteres")
    @Column(nullable = false, length = 15)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "biblioteca_id")
    private Biblioteca biblioteca;
}
