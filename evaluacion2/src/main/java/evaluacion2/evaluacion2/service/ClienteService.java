package evaluacion2.evaluacion2.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import evaluacion2.evaluacion2.dto.ClienteDTO;
import evaluacion2.evaluacion2.model.Cliente;
import evaluacion2.evaluacion2.repository.ClienteRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> obtenerTodos() {
        List<ClienteDTO> dtos = new ArrayList<>();
        for (Cliente c : clienteRepository.findAll()) {
            dtos.add(convertirADTO(c));
        }
        return dtos;
    }

    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente actualizar(Integer id, Cliente cliente) {
        Cliente cli = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: El cliente no existe."));
        if (cliente.getPnombre() != null) {
            cli.setPnombre(cliente.getPnombre());
        }
        if (cliente.getSnombre() != null) {
            cli.setSnombre(cliente.getSnombre());
        }
        if (cliente.getPapellido() != null) {
            cli.setPapellido(cliente.getPapellido());
        }
        if (cliente.getSapellido() != null) {
            cli.setSapellido(cliente.getSapellido());
        }
        if (cliente.getFechaNacimiento() != null) {
            cli.setFechaNacimiento(cliente.getFechaNacimiento());
        }
        if (cliente.getPrestamo() != null) {
            cli.setPrestamo(cliente.getPrestamo());
        }
        return clienteRepository.save(cli);
    }

    public void eliminar(Integer id) {
        clienteRepository.deleteById(id);
    }

    public ClienteDTO buscarPorId(Integer id) {
        return clienteRepository.findById(id).map(this::convertirADTO).orElseThrow(() -> new RuntimeException("Error: No se encontró el cliente con el ID: " + id));
    }

    public ClienteDTO buscarPorTituloLibro(String tituloLibro) {
        Cliente cliente = (Cliente) clienteRepository.findByTituloLibro(tituloLibro);
        if (cliente == null) {
            throw new RuntimeException("Error: No se encontró un cliente con préstamo del libro: " + tituloLibro);
        }
        return convertirADTO(cliente);
    }

    public ClienteDTO buscarPorIdPrestamo(Integer idPrestamo) {
        Cliente cliente = (Cliente) clienteRepository.findByIdPrestamo(idPrestamo);
        if (cliente == null) {
            throw new RuntimeException("Error: No existe un cliente asociado al préstamo ID: " + idPrestamo);
        }
        return convertirADTO(cliente);
    }

    private ClienteDTO convertirADTO(Cliente c) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(c.getId());
        dto.setPnombre(c.getPnombre());
        dto.setSnombre(c.getSnombre());
        dto.setPapellido(c.getPapellido());
        dto.setSapellido(c.getSapellido());
        dto.setFechaNacimiento(c.getFechaNacimiento());
        dto.setSexo(c.getSexo());

        dto.setTituloLibroPrestamo(new ArrayList<>());
        return dto;
    }
}
