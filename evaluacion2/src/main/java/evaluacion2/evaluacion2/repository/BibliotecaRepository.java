package evaluacion2.evaluacion2.repository;

import org.springframework.stereotype.Repository;

import evaluacion2.evaluacion2.model.Biblioteca;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;




@Repository
public interface BibliotecaRepository extends JpaRepository<Biblioteca, Integer>{
    // Busqueda de biblioteca por prestamo asociado
    @Query("SELECT b FROM Biblioteca b JOIN b.prestamo p WHERE p.id = :idPrestamo")
    List<Biblioteca> findByIdPrestamo(@Param("idPrestamo") Integer idPrestamo);

    // Busqueda de biblioteca por comuna asociada
    @Query("SELECT b FROM Biblioteca b JOIN b.comuna c WHERE c.id = :idComuna")
    List<Biblioteca> findByIdComuna(@Param("idComuna") Integer idComuna);

    // Busqueda de biblioteca por empleado asociada
    @Query("SELECT b FROM Biblioteca b JOIN b.empleado e WHERE e.id = :idEmpleado")
    List<Biblioteca> findByIdEmpleado(@Param("idEmpleado") Integer idEmpleado);
}
