package DAL.DAO;

import com.opencsv.CSVReader;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

public class FileDAO {

    public XYChart.Series getCSVData() throws Exception {
        XYChart.Series series = new XYChart.Series();
        try (CSVReader csvReader = new CSVReader(new FileReader("src/Resources/Files/ArlaKPI.csv"))){
            String[] nextLine;
            while((nextLine = csvReader.readNext()) != null){
                String products = String.valueOf(nextLine[0]);
                int sales = Integer.parseInt(nextLine[1]);
                series.getData().add(new XYChart.Data(products,sales));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return series;
    }

    public void getPDFData() throws IOException {
        PDDocument pdf = PDDocument.load(new File("src/Resources/Files/Compulsory_AsignmentOS.pdf"));
        PDFRenderer pdfRenderer = new PDFRenderer(pdf);
        BufferedImage bim = pdfRenderer.renderImageWithDPI(0,300, ImageType.RGB);
        ImageIO.write(bim,"JPG",new File("src/Resources/Temp/temp.jpg"));
        pdf.close();
    }

}