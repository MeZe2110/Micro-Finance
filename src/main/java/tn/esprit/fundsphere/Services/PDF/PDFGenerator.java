package tn.esprit.fundsphere.Services.PDF;

import java.awt.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Repositories.AccountRepository.AccountRepository;
import tn.esprit.fundsphere.Services.AccountService.AccountServiceImpl;

@Service
@AllArgsConstructor
public class PDFGenerator {

    public AccountRepository accountRepository;
    public AccountServiceImpl accountService;


    List<Long> myList = new ArrayList<Long>();

    public PDFGenerator() {

    }

    public void generate(List<Account> accountList, HttpServletResponse response ) throws DocumentException, IOException {

//creation document
        Document document = new Document(PageSize.A4);
//
        PdfWriter.getInstance(document, response.getOutputStream());
//ouvrir document
        document.open();

        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = today.format(formatter);


        Font font2 = new Font();
        // font2.setColor(Color.GREEN);
        Paragraph paragraph0 = new Paragraph("CHECKING Account");
        paragraph0.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(paragraph0);
        Paragraph paragraph5 = new Paragraph("Page : 1 of 1",font2);
        paragraph5.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(paragraph5);

        Paragraph paragraph2 = new Paragraph("Date :"+formattedDate);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(paragraph2);
        Paragraph paragraph3 = new Paragraph("fundsphere ");
        paragraph3.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(paragraph3);
        Paragraph paragraph1 = new Paragraph("liste des comptes", fontTiltle);
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph1);

        for (Account account: accountList) {
            document.add(new Paragraph("l'dentifiant du compte : " + account.getIdAccount()));
            document.add(new Paragraph("le numero du compte : " +account.getNumAccount()));
            document.add(new Paragraph("le balance est  : " + account.getBalance()));
            document.add(new Paragraph("la date de creation est   : " + account.getDate()));
            document.add(new Paragraph("le rib est  : " + account.getRib()));
            document.add(new Paragraph("le utilisateur est   : " + account.getUser()));



                // e.printStackTrace();

        }
        document.close();


    }
}