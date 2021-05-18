package GUI.Models;

import BE.FileInfo;
import BE.User;
import BLL.FileManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.util.List;

public class FileModel {
    private FileManager fileManager;
    private ObservableList<FileInfo> fileInfoObservableList = FXCollections.observableArrayList();

    public FileModel() throws IOException {
        fileManager = new FileManager();
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
    }

    public void deleteViewFromUser(User user, String fileURL){
        fileManager.deleteViewFromUser(user,fileURL);
    }

    public ObservableList<FileInfo> getAllViewsForUser(User user){
        fileInfoObservableList.addAll(fileManager.getAllViewsForUser(user));
        return fileInfoObservableList;
    }

}
