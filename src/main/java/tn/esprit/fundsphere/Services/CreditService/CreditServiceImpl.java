package tn.esprit.fundsphere.Services.CreditService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entites.CrediMangment.Credit;
import tn.esprit.fundsphere.Repositories.CreditRepository.CreditRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CreditServiceImpl implements ICreditService{

    private CreditRepository creditRepository;

    @Override
    public Credit addCredit(Credit credit) {
        return creditRepository.save(credit);
    }

    @Override
    public void deleteCredit(Long idCredit) {
         creditRepository.deleteById(idCredit);

    }

    @Override
    public Credit updateCredit(Credit credit) {
        return creditRepository.save(credit);
    }

    @Override
    public List<Credit> getAllCredits() {
        return creditRepository.findAll();    }

    @Override
    public Credit getCredit(Long idCredit) {
        return creditRepository.findById(idCredit).get();
    }
}
