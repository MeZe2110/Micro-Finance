package tn.esprit.fundsphere.Services.DeviseService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entities.DeviseManagement.Devise;
import tn.esprit.fundsphere.Repositories.DeviseRepository.DeviseRepository;

@Service
@AllArgsConstructor
public class DeviseService {


    private DeviseRepository deviseRepository;

    public double convertisseur(double montant, String deviseSource, String deviseDestination) {
        // Récupérer les devises correspondantes aux codes
        Devise deviseSourceTrouvee = deviseRepository.findBySymboleDevise(deviseSource);
        Devise deviseDestinationTrouvee = deviseRepository.findBySymboleDevise(deviseDestination);

        // Vérifier si les devises existent
        if (deviseSourceTrouvee == null || deviseDestinationTrouvee == null) {
            throw new IllegalArgumentException("Devise introuvable");
        }

        // Calculer le taux de change cumulé
        double tauxChangeCumule = deviseDestinationTrouvee.getTauxChange() / deviseSourceTrouvee.getTauxChange();

        // Appliquer le taux de change au montant
        return montant * tauxChangeCumule;
    }
}
