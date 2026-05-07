package evaluacion2.evaluacion2.repository;

import org.springframework.stereotype.Repository;

import evaluacion2.evaluacion2.model.Comuna;
import org.springframework.data.jpa.repository.JpaRepository;




@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Integer>{

}
