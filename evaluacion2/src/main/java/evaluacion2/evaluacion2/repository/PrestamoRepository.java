package evaluacion2.evaluacion2.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import evaluacion2.evaluacion2.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer>{
    // Busqueda de prestamo por biblioteca asociada
    @Query("SELECT p FROM Prestamo p JOIN p.biblioteca b WHERE b.id = :idBiblioteca")
    List<Prestamo> findByIdBiblioteca(@Param("idBiblioteca") Integer idBiblioteca);
    
    // Busqueda de prestamo por cliente asociado
    @Query("SELECT p FROM Prestamo p JOIN p.cliente c WHERE c.id = :idCliente")
    List<Prestamo> findByIdCliente(@Param("idCliente") Integer idCliente);

    // Busqueda de prestamo por libro asociado
    @Query("SELECT p FROM Prestamo p JOIN p.libro l WHERE l.isbn = :libroIsbn")
    List<Prestamo> findByIsbn(@Param("libroIsbn") Integer libroIsbn);
}
