package evaluacion2.evaluacion2.dto;

import java.util.List;

import lombok.Data;

@Data
public class ClienteDTO {
    private Integer id;
    private String pnombre;
    private String snombre;
    private String papellido;
    private String sapellido;
    private String fechaNacimiento;
    private String sexo;
    private List<String> tituloLibroPrestamo; //mostrar el titulo de los libros que tiene en prestamo el cliente
}
