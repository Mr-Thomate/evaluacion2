package evaluacion2.evaluacion2.repository;

import org.springframework.stereotype.Repository;
import evaluacion2.evaluacion2.model.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface EditorialRepository extends JpaRepository<Editorial, Integer>{

}
