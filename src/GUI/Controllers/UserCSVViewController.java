package GUI.Controllers;

import BE.FileInfo;
import GUI.Models.FileModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;
public class UserCSVViewController implements Initializable {

    @FXML
    private BarChart CSVBarChart;
    private FileModel fileModel;
    private FileInfo selectedFileInfo;
    private String filePath;
    private XYChart.Series series1;
    private final int sleepTime = 300000;

    /**
     * This is the initialize method.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            fileModel = new FileModel();
            selectedFileInfo = UserSelectViewController.getSelectedFileInfo();
            filePath = selectedFileInfo.getFilePath();
            series1 = fileModel.getCSVData(filePath);
            CSVBarChart.getData().addAll(series1);
            CSVBarChart.setLegendVisible(false);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        try {
                            series1 = fileModel.getCSVData(filePath);
                            CSVBarChart.getData().clear();
                            CSVBarChart.getData().addAll(series1);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                };
                while (true) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
