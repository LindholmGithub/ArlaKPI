package BLL;

import DAL.DAO.CSVDAO;
import javafx.scene.chart.XYChart;

public class FileManager {
    private CSVDAO csvDao;

    public FileManager(){
        csvDao = new CSVDAO();
    }

    public XYChart.Series getCSVData() throws Exception {
        return csvDao.getCSVData();
    }
}
