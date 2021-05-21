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

    /**
     * constructor for the FileManager class to instantiate our Data Access Objects
     * @throws IOException
     */
    public FileManager() throws IOException {
        fileDao = new FileDAO();
        adminDAO = new AdminDAO();
    }

    /**
     * gets the CSV data from the FileDAO
     * @param fileURL
     * @return
     * @throws Exception
     */
    public XYChart.Series getCSVData(String fileURL) throws Exception {
        return fileDao.getCSVData(fileURL);
    }

    /**
     * gets the PDF data from the FileDAO
     * @param fileURL
     * @return
     * @throws IOException
     */
    public String getPDFData(String fileURL) throws IOException {
        return fileDao.getPDFData(fileURL);
    }

    /**
     * gets the excel data from the FileDAO
     * @param fileURL
     * @return
     * @throws IOException
     */
    public String[][] getXLSXData(String fileURL) throws IOException {
        return fileDao.getXLSXData(fileURL);
    }

    /**
     * adds a view to a given non admin user from a filename, file format and file URL
     * @param user
     * @param nameOfFile
     * @param formatType
     * @param fileURL
     */
    public void addViewToUser(User user, String nameOfFile, String formatType, String fileURL){
        adminDAO.addViewToUser(user,nameOfFile,formatType,fileURL);
    }

    /**
     * deletes a view from a given user and its url to identify the correct file to delete
     * @param user
     * @param fileURL
     */
    public void deleteViewFromUser(User user, String fileURL){
        adminDAO.deleteViewFromUser(user,fileURL);
    }

    /**
     * gets all users and puts them in a list
     * @param user
     * @return
     */
    public List<FileInfo> getAllViewsForUser(User user) {
        return fileDao.getAllViewsForUser(user);
    }

}
