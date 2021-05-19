package GUI.Models;

import BE.FileInfo;
import BE.User;
import BLL.FileManager;
import GUI.Controllers.AdminEditUserViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.util.List;

public class FileModel {
    private FileManager fileManager;
    private ObservableList<FileInfo> fileInfoObservableList;

    public FileModel() throws IOException {
        fileManager = new FileManager();
        fileInfoObservableList = FXCollections.observableArrayList();
    }

    public XYChart.Series getCSVData(String fileURL) throws Exception {
        return fileManager.getCSVData(fileURL);
    }

    public String getPDFData(String fileURL) throws IOException {
        return fileManager.getPDFData(fileURL);
    }

    public String[][] getXLSXData(String fileURL) throws IOException {
        return fileManager.getXLSXData(fileURL);
    }

    public void addViewToUser(User user, String nameOfFile, String formatType, String fileURL){
        fileManager.addViewToUser(user,nameOfFile,formatType,fileURL);
        AdminEditUserViewController.clearStaticList();
        fileInfoObservableList.clear();
        fileInfoObservableList.addAll(fileManager.getAllViewsForUser(user));
        AdminEditUserViewController.observableListOfViews.addAll(fileInfoObservableList);
    }

    public void deleteViewFromUser(User user, String fileURL){
        fileManager.deleteViewFromUser(user,fileURL);
        AdminEditUserViewController.clearStaticList();
        fileInfoObservableList.clear();
        fileInfoObservableList.addAll(fileManager.getAllViewsForUser(user));
        AdminEditUserViewController.observableListOfViews.addAll(fileInfoObservableList);
    }

    public ObservableList<FileInfo> getAllViewsForUser(User user){
        fileInfoObservableList.addAll(fileManager.getAllViewsForUser(user));
        return fileInfoObservableList;
    }

}
