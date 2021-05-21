package GUI.Models;

import BE.FileInfo;
import BE.User;
import BLL.FileManager;
import GUI.Controllers.AdminEditUserViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.io.IOException;

public class FileModel {
    private FileManager fileManager;
    private ObservableList<FileInfo> fileInfoObservableList;

    /**
     * This is the constructor for the FileModel class.
     * @throws IOException
     */
    public FileModel() throws IOException {
        fileManager = new FileManager();
        fileInfoObservableList = FXCollections.observableArrayList();
    }

    /**
     * This method retrieves data from a CSV document.
     * @param fileURL
     * @return
     * @throws Exception
     */
    public XYChart.Series getCSVData(String fileURL) throws Exception {
        return fileManager.getCSVData(fileURL);
    }

    /**
     * This method retrieves data from a PDF document.
     * @param fileURL
     * @return
     * @throws IOException
     */
    public String getPDFData(String fileURL) throws IOException {
        return fileManager.getPDFData(fileURL);
    }

    /**
     * This method retrieves data from a XLSX document.
     * @param fileURL
     * @return
     * @throws IOException
     */
    public String[][] getXLSXData(String fileURL) throws IOException {
        return fileManager.getXLSXData(fileURL);
    }

    /**
     * This method adds a view to a selected user.
     * Then empties and fills the static lists again to trigger the listener, to refresh the lists in the GUI.
     * @param user
     * @param nameOfFile
     * @param formatType
     * @param fileURL
     */
    public void addViewToUser(User user, String nameOfFile, String formatType, String fileURL){
        fileManager.addViewToUser(user,nameOfFile,formatType,fileURL);
        AdminEditUserViewController.clearStaticList();
        fileInfoObservableList.clear();
        fileInfoObservableList.addAll(fileManager.getAllViewsForUser(user));
        AdminEditUserViewController.observableListOfViews.addAll(fileInfoObservableList);
    }

    /**
     * This method deletes a view from a selected user.
     * Then empties and fills the static lists again to trigger the listener, to refresh the lists in the GUI.
     * @param user
     * @param fileURL
     */
    public void deleteViewFromUser(User user, String fileURL){
        fileManager.deleteViewFromUser(user,fileURL);
        AdminEditUserViewController.clearStaticList();
        fileInfoObservableList.clear();
        fileInfoObservableList.addAll(fileManager.getAllViewsForUser(user));
        AdminEditUserViewController.observableListOfViews.addAll(fileInfoObservableList);
    }

    /**
     * This method retrieves the view list for a selected user.
     * @param user
     * @return
     */
    public ObservableList<FileInfo> getAllViewsForUser(User user){
        fileInfoObservableList.addAll(fileManager.getAllViewsForUser(user));
        return fileInfoObservableList;
    }

}
