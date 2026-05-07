package evaluacion2.evaluacion2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import evaluacion2.evaluacion2.model.Libro;
import evaluacion2.evaluacion2.model.LibroCategoria;
import evaluacion2.evaluacion2.model.LibroEditorial;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer>{
    @Query("SELECT a FROM Autor a WHERE a.nombre = :nombreAutor")
    List<Libro> findByLibroAutor(String nombreAutor);

    List<Libro> findByLibroEditorial(LibroEditorial libroEditorial);

    List<Libro> findByLibroCategoria(LibroCategoria libroCategoria);

    

}
