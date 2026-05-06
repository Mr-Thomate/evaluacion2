package evaluacion2.evaluacion2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "region")
@AllArgsConstructor
@NoArgsConstructor
public class Region {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idRegion;

    @NotBlank(message="No puede estar vacio")
    private String nombreRegion;

    @OneToMany
    @JoinColumn(name = "comuna_id")
    private Comuna comuna;
}