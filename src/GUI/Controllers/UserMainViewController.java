package GUI.Controllers;

import DAL.DAO.CSVDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class UserMainViewController implements Initializable {

    @FXML
    private BarChart CSVBarChart;
    private CSVDAO csvdao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            csvdao = new CSVDAO();
            XYChart.Series series1 = csvdao.printCSV();
            CSVBarChart.getData().addAll(series1);
            CSVBarChart.setLegendVisible(false);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
