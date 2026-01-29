package Base;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;

public class PDFGenerator {
    PDDocument document;
    PDPageContentStream contentStream;
    float coverpagey=0;
     PDFGenerator() {
        document=new PDDocument();
    }
    public float addRow(String label, String value,float x,float y,float labelwidth, float valuewidth, float rowheight) throws IOException {
        value=value.replaceAll("\n"," ");
         String[] valueLines=tolines(value);
        if(valueLines.length!=1)
        {
            rowheight=rowheight*2;
        }
        contentStream.addRect(x,y-rowheight,labelwidth+valuewidth,rowheight);
        contentStream.stroke();
        contentStream.moveTo(x+labelwidth,y);
        contentStream.lineTo(x+labelwidth,y-rowheight);
        contentStream.stroke();
        contentStream.setFont(PDType1Font.TIMES_ROMAN,12);
        contentStream.beginText();
        contentStream.newLineAtOffset(x+5,y-10);
        contentStream.showText(label);
        contentStream.endText();
        float z=y-10;
        for(String line:valueLines)
        {
            contentStream.setFont(PDType1Font.TIMES_ROMAN,12);
            contentStream.beginText();
            contentStream.newLineAtOffset(x+5+labelwidth,z-10);
            contentStream.showText(line);
            contentStream.endText();
            z-=10;
        }
        return y-rowheight;
    }
    public void captureStep(String description, String expected, String actual, String status, String screenshotpath) throws IOException {
        if(contentStream!=null)
        {
            contentStream.close();
        }
        PDPage page=new PDPage(PDRectangle.A4);
        document.addPage(page);
        contentStream=new PDPageContentStream(document,page);
        float x=0;
        float y=page.getMediaBox().getHeight();
        float labelwidth=100;
        float valuewidth=400;
        float rowheight=25;
        y=addRow("Description",description,x+10,y-10,labelwidth,valuewidth,rowheight);
        y=addRow("Expected Result",expected,x+10,y,labelwidth,valuewidth,rowheight);
        y=addRow("Actual Result",actual,x+10,y,labelwidth,valuewidth,rowheight);
        y=addRow("Status",status,x+10,y,labelwidth,valuewidth,rowheight);
        y=addRow("TimeStamp",new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date()),x+10,y,labelwidth,valuewidth,rowheight);
        PDImageXObject screenshot=PDImageXObject.createFromFile(screenshotpath,document);
        contentStream.drawImage(screenshot,x+15,y-400,labelwidth+valuewidth-10,380);
        contentStream.addRect(x+10,y-400,labelwidth+valuewidth,400);
        contentStream.stroke();
    }
    public String[] tolines(String line)
    {
        return line.split("\\n");
    }
    public void save(String reportPath) throws IOException {
         if(contentStream!=null) {
             contentStream.close();
         }
        document.save(reportPath);
        document.close();
    }
}
