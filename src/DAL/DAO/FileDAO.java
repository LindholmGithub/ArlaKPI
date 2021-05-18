package DAL.DAO;

import BE.FileInfo;
import BE.User;
import DAL.DBConnection.JDBCConnectionPool;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FileDAO {

    private final JDBCConnectionPool connectionPool;

    public FileDAO() throws IOException {
        connectionPool = JDBCConnectionPool.getInstance();
    }

    public XYChart.Series getCSVData(String fileURL) throws Exception {
        XYChart.Series series = new XYChart.Series();
        try (CSVReader csvReader = new CSVReader(new FileReader(fileURL))){
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

    public String getPDFData(String fileURL) throws IOException {
        PDDocument pdf = PDDocument.load(new File(fileURL));
        String pdfSavePath = "src/Resources/Temp/" + fileURL.substring(fileURL.lastIndexOf("\\")+1,fileURL.lastIndexOf(".")) + ".jpg";
        PDFRenderer pdfRenderer = new PDFRenderer(pdf);
        BufferedImage bim = pdfRenderer.renderImageWithDPI(0,100, ImageType.RGB);
        ImageIO.write(bim,"JPG", new File(pdfSavePath));
        pdf.close();
        return pdfSavePath;
    }

    public String[][] getXLSXData(String fileURL) throws IOException {

        ExcelFile excelFile = ExcelFile.load(fileURL);
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

    public List<FileInfo> getAllViewsForUser(User user){
        ArrayList<FileInfo> allFiles = new ArrayList<>(){};
        int userID = user.getId();
        try (Connection con = connectionPool.checkOut()){
            String sql = "SELECT * FROM UserView WHERE UserView.UserID = " + userID + ";";
            Statement statement = con.createStatement();
            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                while(resultSet.next()){
                    String fileName = resultSet.getString("NameOfFile");
                    String formatType = resultSet.getString("FormatType");
                    String fileURI = resultSet.getString("FileURL");
                    FileInfo fileInfo = new FileInfo(fileName,formatType,fileURI);
                    allFiles.add(fileInfo);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return allFiles;
    }

}