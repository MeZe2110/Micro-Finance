package tn.esprit.fundsphere.Services.CreditService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entites.CrediMangment.Tranche;
import tn.esprit.fundsphere.Repositories.CreditRepository.TrancheRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TrancheServiceImpl implements ITrancheService{

    private TrancheRepository trancheRepository ;

    @Override
    public Tranche addTranche(Tranche tranche) {
        return trancheRepository.save(tranche);
    }

    @Override
    public void deleteTranche(Long idTranche) {
        trancheRepository.deleteById(idTranche);


    }

    @Override
    public Tranche updateTranche(Tranche tranche) {
        return trancheRepository.save(tranche);
    }

    @Override
    public List<Tranche> getAllTranches()
    {
        return trancheRepository.findAll();
    }

    @Override
    public Tranche getTranche(Long idTranche)
    {
        return trancheRepository.findById(idTranche).get();
    }
}
