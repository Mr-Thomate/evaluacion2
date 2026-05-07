package evaluacion2.evaluacion2.dto;

import java.util.List;

import lombok.Data;

@Data
public class RegionDTO {
    private Integer idRegion;
    private String nombreRegion;
    private List<String> nombreComunas; //mostrar el nombre de las comunas que se encuentran en la region
}
