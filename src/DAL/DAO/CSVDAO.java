package DAL.DAO;

import com.opencsv.CSVReader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.io.*;
import java.util.Scanner;

public class CSVDAO {
    public CSVDAO() throws Exception {
        printCSV();
    }


    public XYChart.Series printCSV() throws Exception {
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
}



