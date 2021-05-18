package BLL;

import BE.FileInfo;
import BE.User;
import DAL.DAO.AdminDAO;
import DAL.DAO.FileDAO;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.util.List;

public class FileManager {
    private FileDAO fileDao;
    private AdminDAO adminDAO;
    private String fileType;

    public FileManager() throws IOException {
        fileDao = new FileDAO();
        adminDAO = new AdminDAO();
    }

    public XYChart.Series getCSVData(String fileURL) throws Exception {
        return fileDao.getCSVData(fileURL);
    }

    public String getPDFData(String fileURL) throws IOException {
        return fileDao.getPDFData(fileURL);
    }

    public String[][] getXLSXData(String fileURL) throws IOException {
        return fileDao.getXLSXData(fileURL);
    }
    public void addViewToUser(User user, String nameOfFile, String formatType, String fileURL){
        adminDAO.addViewToUser(user,nameOfFile,formatType,fileURL);
    }

    public void deleteViewFromUser(User user, String fileURL){
        adminDAO.deleteViewFromUser(user,fileURL);
    }

    public List<FileInfo> getAllViewsForUser(User user) {
        return fileDao.getAllViewsForUser(user);
    }

    public String getFileType(FileInfo fileInfo){
        fileType = fileInfo.getFileType();
        return fileType;
    }

    public void openSpecificFile(String fileType){
        //TODO
        switch (fileType){
            case "pdf":
                //code here
                break;
            case "csv":
                //code here
                break;
            case "html":
                //code here
                break;
            case "xlxs":
                //code here
                break;
        }
    }
}
