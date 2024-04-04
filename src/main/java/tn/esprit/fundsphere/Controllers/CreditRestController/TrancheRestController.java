package tn.esprit.fundsphere.Controllers.CreditRestController;


import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.fundsphere.Entities.ClaimsManagment.Claims;
import tn.esprit.fundsphere.Entities.CrediMangment.Tranche;
import tn.esprit.fundsphere.Entities.TransactionManagment.Transaction;
import tn.esprit.fundsphere.Services.CreditService.TrancheServiceImpl;
import tn.esprit.fundsphere.Repositories.CreditRepository.TrancheRepository;

import tn.esprit.fundsphere.Services.EXCEL.ExcelGenerator;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/tranche")
public class TrancheRestController {

    public TrancheRepository trancheRepository;
    public ExcelGenerator excelGenerator;

    public TrancheServiceImpl trancheService;

    @PostMapping(path = "/add-tranche")
    public Tranche addTranche(@RequestBody Tranche claims) {
        return trancheService.addTranche(claims);
    }

    @GetMapping("/show-tranche")
    public List<Tranche> getAllTranches() {
        List<Tranche> listTranches = trancheService.getAllTranches();
        return listTranches;
    }

    @DeleteMapping(path = "/delete-tranche/{id}")
    public void deleteTranche(@PathVariable("id") Long idTranche) {
        trancheService.deleteTranche(idTranche);
    }

    @DeleteMapping(path = "/delete-tranches")
    public void deleteTranches() {
        trancheService.deleteTranches();
    }



    @PutMapping(path = "/update-tranche")
    public Tranche updateTranche(@RequestBody Tranche t) {

        Tranche tranches = trancheService.updateTranche(t);
        return tranches;
    }

    @GetMapping("/verif-tranche")
    public void verifyTrancheAmountInAccount() throws ChangeSetPersister.NotFoundException {
        trancheService.verifyTrancheAmountInAccount();
    }

    @GetMapping("/genererTranchesExcel/{idCredit}")
    public String genererTranchesExcel(@PathVariable("idCredit") int idCredit) {
        try {
            List<Tranche> tranches = trancheRepository.findByCreditIdCredit(idCredit);
            if (tranches.isEmpty()) {
                return "Aucune tranche trouvée pour ce crédit";
            }

            String filePath = "TranchesCredit_" + idCredit + ".xlsx";
            // ExcelGenerator.generateExcel(tranches, filePath);
            excelGenerator.generateExcel(tranches, filePath);
            return "Fichier Excel des tranches généré avec succès";
        } catch (IOException e) {
            e.printStackTrace();
            return "Erreur lors de la génération du fichier Excel";
        }
    }

}

