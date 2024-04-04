package tn.esprit.fundsphere.Services.PDF;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Repositories.AccountRepository.AccountRepository;
import tn.esprit.fundsphere.Services.AccountService.AccountServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
        Font font3 = new Font();
        Font font4 = new Font();
        Font font5 = new Font();

        font2.setColor(BaseColor.BLUE);
        font3.setColor(BaseColor.BLUE);
        font4.setColor(BaseColor.RED);
        font5.setColor(BaseColor.GREEN);




        String imagePath = "C://image22222//fundspheres.png/";

        Image logo = Image.getInstance(imagePath);
        logo.scaleAbsolute(100, 100); // Définir la taille de l'image
        logo.setAbsolutePosition(450, PageSize.A4.getHeight() - 100); // Positionner l'image

        document.add(logo);


        Paragraph paragraph0 = new Paragraph("CHECKING Account" , font3);
        paragraph0.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(paragraph0);

        Paragraph paragraph2 = new Paragraph("Date :"+formattedDate , font5);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(paragraph2);





        Paragraph paragraph1 = new Paragraph("les information de compte",  fontTiltle);
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph1.setSpacingAfter(20);
        document.add(paragraph1);






        for (Account account: accountList) {
            document.add(new Paragraph("l'dentifiant du compte : " + account.getIdAccount()));
            document.add(new Paragraph("le numero du compte : " +account.getNumAccount()));
            document.add(new Paragraph("le balance est  : " + account.getBalance()));
            document.add(new Paragraph("la date de creation est   : " + account.getDate()));
            document.add(new Paragraph("le rib est  : " + account.getRib()));
          //  document.add(new Paragraph("le utilisateur est   : " + account.getUser()));



                // e.printStackTrace();

        }

        Paragraph paragraph5 = new Paragraph("Page : 1 of 1",font2);
        paragraph5.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph5.setSpacingBefore(500);
        document.add(paragraph5);



        document.close();


    }
}