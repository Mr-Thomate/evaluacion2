package evaluacion2.evaluacion2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "comuna")
@AllArgsConstructor
@NoArgsConstructor
public class Comuna {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idComuna;

    @NotBlank(message="No puede estar vacio")
    private String nombreComuna;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany
    @JoinColumn(name = "biblioteca_id")
    private Biblioteca biblioteca;
}