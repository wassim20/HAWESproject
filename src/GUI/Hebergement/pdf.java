package GUI.Hebergement;


import com.itextpdf.text.Chunk;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import com.itextpdf.text.Image;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import Entités.hebergement;
import java.awt.Font;
import java.io.FileInputStream;

import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import javafx.scene.text.FontPosture;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class pdf {

    public boolean makepdf(String nom, String price, String adress, String etoile, String imagepath) {
        try {
            OutputStream file = new FileOutputStream(new File("Test.pdf"));
            Document document = new Document();
 
            // You need PdfWriter to generate PDF document
            PdfWriter.getInstance(document, file);
 
            // Opening document for writing PDF
            document.open();
 
            // Writing content
            String txt="<!-- #######  YAY, I AM THE SOURCE EDITOR! #########-->\n" +
"<h1 style=\"color: #5e9ca0;\"> <span style=\"color: #2b2301;\"></span> </h1>\n" +
"<h2 style=\"color: #2e6c80;\"></h2>\n" +
"<p> <br /></p>\n" +
"<p> <span style=\"background-color: #2b2301; color: #fff; display: inline-block; padding: 3px 10px; font-weight: bold; border-radius: 5px;\"></span> </p>\n" +
"<h2 style=\"color: #2e6c80;\"></h2>\n" +
"<ol style=\"list-style: none; font-size: 14px; line-height: 32px; font-weight: bold;\">\n" +
"<li style=\"clear: both;\">&nbsp;</li>\n" +
"</ol>\n" +
"<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>\n" +
"<h2 style=\"color: #2e6c80;\"></h2>\n" +
"<p>&nbsp;</p>\n" +
"<p><strong > </strong><br /><strong>Enjoy!</strong></p>\n" +
"<p><strong>&nbsp;</strong></p>";
           document.setHtmlStyleClass(txt);
           
            document.add(new Paragraph("Hello Dear User, We hope that u are enjoying our Application"));
            document.add(new Paragraph("---------------------------------------------------------------------------------"));
            
            
            document.add(new Paragraph("for the hotel "+nom+"you can just show up and you will be more than welcome"));
            
           Image image = Image.getInstance(imagepath);
            //doc.add(image);
            //document.setPageSize(new Rectangle(595,452));
            //document.newPage();
            document.add(image);
            
            
            
            
            
            
            document.add(new Paragraph("this hotel is a "+etoile+"star hotel located in "+adress+""));
            document.add(new Paragraph("with a reasonable price and u can get a discount if u show up with this PDF"));
            document.add(new Paragraph("PRICE "+price+""));
            
            document.add(new Paragraph(new Date(new java.util.Date().getTime()).toString()));
 
                   // Add meta data information to PDF file
            document.addCreationDate();
            document.addAuthor("Javarevisited");
            document.addTitle("How to create PDF document in Java");
            document.addCreator("Thanks to iText, writing into PDF is easy");
 
 
            // close the document
            document.close();
 
            System.out.println("Your PDF File is succesfully created");
 
            file.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
 
        } 
 
            // closing FileOutputStream
            
                
            
    }
}
        
 
    

    
