package evaluacion2.evaluacion2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import evaluacion2.evaluacion2.model.Categoria;




@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
