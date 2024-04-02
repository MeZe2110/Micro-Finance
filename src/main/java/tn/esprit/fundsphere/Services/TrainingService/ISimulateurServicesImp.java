package tn.esprit.fundsphere.Services.TrainingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entities.TrainigManagment.Simulateur;
import tn.esprit.fundsphere.Entities.TrainigManagment.TypeFinencement;

@Service
@Slf4j
@RequiredArgsConstructor
public class ISimulateurServicesImp{

    public double calculateRemboursementMensuel(Simulateur simulateur) {
        double tauxInteretMensuel = calculateTauxInteretMensuel(simulateur);
        int dureeEnMois = simulateur.getDuree();
        int montantTotal = simulateur.getPrixTotal() + simulateur.getAutreFinencement();
        return (montantTotal * tauxInteretMensuel) / (1 - Math.pow(1 + tauxInteretMensuel, -dureeEnMois));
    }

    public int calculateFinancementSollicite(Simulateur simulateur) {
        return simulateur.getPrixTotal() + simulateur.getAutreFinencement();
    }

    public double calculateTauxEndettementMensuel(Simulateur simulateur) {
        int revenuBrut = simulateur.getRevenuBrut();
        double remboursementMensuel = calculateRemboursementMensuel(simulateur);
        return (remboursementMensuel / revenuBrut) * 100;
    }

    private double calculateTauxInteretMensuel(Simulateur simulateur) {
        TypeFinencement typeFinencement = simulateur.getTypeFinencement();
        switch (typeFinencement) {
            case VIP:
                return 0.001;
            case NORMAL:
                return 0.0013;
            default:
                throw new IllegalArgumentException("Unknown financing type");
        }
    }

}
