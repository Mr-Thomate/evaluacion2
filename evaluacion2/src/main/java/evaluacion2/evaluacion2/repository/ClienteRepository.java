package evaluacion2.evaluacion2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import evaluacion2.evaluacion2.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    // Busqueda de cliente mediante libros con prestamos
    @Query("SELECT c FROM Cliente c JOIN c.prestamo p JOIN p.libro l WHERE l.titulo = :tituloLibro")
    List<Cliente> findByTituloLibro(@Param("tituloLibro") String tituloLibro);

    // Busqueda de cliente mediante id de prestamos
    @Query("SELECT c FROM Cliente c JOIN c.prestamo p WHERE p.id = :idPrestamo")
    List<Cliente> findByIdPrestamo(@Param("idPrestamo") Integer idPrestamo);
}
