package GUI.Controllers;

import BE.FileInfo;
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
    private FileInfo selectedFileInfo;
    private String filePath;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            fileModel = new FileModel();
            selectedFileInfo = UserSelectViewController.getSelectedFileInfo();
            filePath = selectedFileInfo.getFilePath();
            XYChart.Series series1 = fileModel.getCSVData(filePath);
            CSVBarChart.getData().addAll(series1);
            CSVBarChart.setLegendVisible(false);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
