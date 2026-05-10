package evaluacion2.evaluacion2.repository;

import org.springframework.stereotype.Repository;

import evaluacion2.evaluacion2.model.Comuna;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;




@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Integer>{
    // Busqueda de comuna mediante region asociada
    @Query("SELECT c FROM Comuna c JOIN c.region r WHERE r.id = :idRegion")
    List<Comuna> findByIdRegion(@Param("idRegion") Integer idRegion);
}
