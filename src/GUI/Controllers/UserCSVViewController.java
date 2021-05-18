package GUI.Controllers;

import GUI.Models.FileModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class UserCSVViewController implements Initializable {

    @FXML
    private BarChart CSVBarChart;
    private FileModel fileModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            fileModel = new FileModel();
            //XYChart.Series series1 = fileModel.getCSVData();
            //CSVBarChart.getData().addAll(series1);
            //CSVBarChart.setLegendVisible(false);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
