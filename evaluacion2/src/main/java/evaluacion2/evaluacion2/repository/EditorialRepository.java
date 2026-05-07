package evaluacion2.evaluacion2.repository;

import org.springframework.stereotype.Repository;

import evaluacion2.evaluacion2.model.Editorial;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
@Repository
public interface EditorialRepository extends JpaRepository<Editorial, Integer>{
    // Busqueda de editorial por libro
    @Query("SELECT e FROM Editorial e JOIN e.libroEditorial le JOIN le.libro l WHERE l.titulo = :tituloLibro")
    List<Editorial> findByLibroTitulo(@Param("tituloLibro") String tituloLibro);
}
