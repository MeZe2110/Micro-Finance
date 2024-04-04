package tn.esprit.fundsphere.Services.EXCEL;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entities.CrediMangment.Tranche;
import tn.esprit.fundsphere.Repositories.CreditRepository.TrancheRepository;

@Service
public class ExcelGenerator {

    @Autowired
    private TrancheRepository trancheRepository;

    public void generateExcel(List<Tranche> trancheList, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Tranches");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID Tranche");
        headerRow.createCell(1).setCellValue("Status");
        headerRow.createCell(2).setCellValue("Amount");
        headerRow.createCell(3).setCellValue("Rate Recovery");
        headerRow.createCell(4).setCellValue("ID Credit");

        int rowNum = 1;
        for (Tranche tranche : trancheList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(tranche.getIdTranche());
            row.createCell(1).setCellValue(tranche.isStatus());
            row.createCell(2).setCellValue(tranche.getAmount());
            row.createCell(3).setCellValue(tranche.getRateRecovery());
            row.createCell(4).setCellValue(tranche.getCredit().getIdCredit());
        }

        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
    }
}