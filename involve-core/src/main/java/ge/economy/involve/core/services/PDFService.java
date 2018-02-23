package ge.economy.involve.core.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import ge.economy.involve.core.api.dto.InitiateDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by NINO on 2/19/2018.
 */
@Service
public class PDFService {


    public static final String FONT_PATH = "/usr/share/glassfish4/glassfish/domains/domain1/applications/involve/resources/fonts/bpg_dejavu_sans.ttf";
    public static final String FONT2 = "C:\\upload\\bpg_dejavu_sans.ttf";


    public void writePdf(OutputStream outputStream, InitiateDTO initiate) throws Exception {
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        PdfWriter.getInstance(document, outputStream);
        //PdfWriter.getInstance(document, new FileOutputStream(new File("C:\\upload\\test.pdf")));

        document.open();

        document.addTitle("Your Initiative PDF");
        BaseFont baseFont = BaseFont.createFont(FONT_PATH, BaseFont.IDENTITY_H, true);
        Font font = new Font(baseFont);
        Font boldFont = new Font(baseFont, 11f, Font.BOLD);


        Paragraph centerParagraph = new Paragraph();
        centerParagraph.setAlignment(Element.ALIGN_CENTER);
        centerParagraph.setFont(font);
        centerParagraph.add("თქვენი ინიციატივის შესახებ ინფორმაცია \n\n");
        document.add(centerParagraph);

        Paragraph paragraph = new Paragraph();
        paragraph.setFont(boldFont);
        paragraph.add("ინიციატივის დასახელება : ");
        document.add(paragraph);

        paragraph = new Paragraph();
        paragraph.setFont(font);
        paragraph.add(initiate.getName());
        document.add(paragraph);

        paragraph = new Paragraph();
        paragraph.setFont(boldFont);
        paragraph.add("სფერო : ");
        document.add(paragraph);

        paragraph = new Paragraph();
        paragraph.setFont(font);
        paragraph.add(initiate.getOtherSphereName() != null ? initiate.getSphereName() + " " + initiate.getOtherSphereName() : initiate.getSphereName());
        document.add(paragraph);

        paragraph = new Paragraph();
        paragraph.setFont(boldFont);
        paragraph.add("ინიციატივის მოკლე აღწერა : ");
        document.add(paragraph);

        paragraph = new Paragraph();
        paragraph.setFont(font);
        paragraph.add(initiate.getDescription());
        document.add(paragraph);

        paragraph = new Paragraph();
        paragraph.setFont(boldFont);
        paragraph.add("ინიციატივის საჭიროება : ");
        document.add(paragraph);

        paragraph = new Paragraph();
        paragraph.setFont(font);
        paragraph.add(initiate.getNecessity());
        document.add(paragraph);

        paragraph = new Paragraph();
        paragraph.setFont(boldFont);
        paragraph.add("შესაძლო დადებითი და უარყოფითი შედეგები : ");
        document.add(paragraph);

        paragraph = new Paragraph();
        paragraph.setFont(font);
        paragraph.add(initiate.getAdvantages());
        document.add(paragraph);


        document.close();
    }


    public static void main(String... args) throws Exception {

        int a = 5;
    }
}
