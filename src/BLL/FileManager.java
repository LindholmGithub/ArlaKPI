package BLL;

import BE.User;
import DAL.DAO.AdminDAO;
import DAL.DAO.FileDAO;
import javafx.scene.chart.XYChart;

import java.io.IOException;

public class FileManager {
    private FileDAO fileDao;
    private AdminDAO adminDAO;

    public FileManager() throws IOException {
        fileDao = new FileDAO();
        adminDAO = new AdminDAO();
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
    public void addViewToUser(User user, String nameOfFile, String formatType, String fileURL){
        adminDAO.addViewToUser(user,nameOfFile,formatType,fileURL);
    }
}
