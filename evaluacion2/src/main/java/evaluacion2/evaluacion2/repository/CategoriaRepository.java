package evaluacion2.evaluacion2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import evaluacion2.evaluacion2.model.Categoria;




@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
    // Busqueda de categoria por libro
    @Query("SELECT c FROM Categoria c JOIN c.libroCategoria lc JOIN lc.libro l WHERE l.titulo = :tituloLibro")
    List<Categoria> findByLibroTitulo(@Param("tituloLibro") String tituloLibro);
}
