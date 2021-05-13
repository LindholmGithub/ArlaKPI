package DAL.DAO;

import com.gembox.spreadsheet.CellValueType;
import com.gembox.spreadsheet.ExcelCell;
import com.gembox.spreadsheet.ExcelFile;
import com.gembox.spreadsheet.ExcelWorksheet;
import com.opencsv.CSVReader;
import javafx.scene.chart.XYChart;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

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
        BufferedImage bim = pdfRenderer.renderImageWithDPI(0,100, ImageType.RGB);
        ImageIO.write(bim,"JPG",new File("src/Resources/Temp/temp.jpg"));
        pdf.close();
    }

    public String[][] getXLSXData() throws IOException {

        ExcelFile excelFile = ExcelFile.load("src/Resources/Files/Data2.xlsx");
        ExcelWorksheet excelWorksheet = excelFile.getWorksheet(0);
        int maxColumns = excelWorksheet.calculateMaxUsedColumns();
        int maxRows = excelWorksheet.getRows().size();
        String[][] sourceData = new String[maxRows][maxColumns];
        for (int row = 0; row < sourceData.length; row++){
            for (int column = 0;column < sourceData[row].length;column++){
                ExcelCell cell = excelWorksheet.getCell(row, column);
                if (cell.getValueType() != CellValueType.NULL){
                    sourceData[row][column] = cell.getValue().toString();
                }
            }
        }
        return sourceData;
    }
}