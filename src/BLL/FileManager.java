package BLL;

import DAL.DAO.FileDAO;
import javafx.scene.chart.XYChart;

import java.io.IOException;

public class FileManager {
    private FileDAO fileDao;

    public FileManager(){
        fileDao = new FileDAO();
    }

    public XYChart.Series getCSVData() throws Exception {
        return fileDao.getCSVData();
    }

    public void getPDFData() throws IOException {
        fileDao.getPDFData();
    }

    public String[][] getXLSXData() throws IOException {
        return fileDao.getXLSXData();
    }
}
