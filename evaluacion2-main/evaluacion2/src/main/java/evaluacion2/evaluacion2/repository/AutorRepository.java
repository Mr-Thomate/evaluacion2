package evaluacion2.evaluacion2.repository;

import evaluacion2.evaluacion2.model.Autor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer>{

}
