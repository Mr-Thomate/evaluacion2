package evaluacion2.evaluacion2.repository;

import org.springframework.stereotype.Repository;

import evaluacion2.evaluacion2.model.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;




@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer>{

}
