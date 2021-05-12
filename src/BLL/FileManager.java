package BLL;

import DAL.DAO.FileDAO;
import javafx.scene.chart.XYChart;

public class FileManager {
    private FileDAO fileDao;

    public FileManager(){
        fileDao = new FileDAO();
    }

    public XYChart.Series getCSVData() throws Exception {
        return fileDao.getCSVData();
    }
}
