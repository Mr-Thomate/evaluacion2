package evaluacion2.evaluacion2.repository;

import org.springframework.stereotype.Repository;

import evaluacion2.evaluacion2.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;




@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{

}
