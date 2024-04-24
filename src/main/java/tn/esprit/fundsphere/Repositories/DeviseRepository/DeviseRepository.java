package tn.esprit.fundsphere.Repositories.DeviseRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.fundsphere.Entities.DeviseManagement.Devise;


@Repository

public interface DeviseRepository extends JpaRepository<Devise,Integer> {
    Devise findBySymboleDevise(String deviseSource);
}
