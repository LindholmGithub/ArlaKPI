package GUI.Models;

import BE.User;
import BLL.FileManager;
import javafx.scene.chart.XYChart;

import java.io.IOException;

public class FileModel {
    private FileManager fileManager;

    public FileModel() throws IOException {
        fileManager = new FileManager();
    }

    public XYChart.Series getCSVData() throws Exception {
        return fileManager.getCSVData();
    }

    public void getPDFData() throws IOException {
        fileManager.getPDFData();
    }

    public String[][] getXLSXData() throws IOException {
        return fileManager.getXLSXData();
    }

    public void addViewToUser(User user, String nameOfFile, String formatType, String fileURL){
        fileManager.addViewToUser(user,nameOfFile,formatType,fileURL);
    }

}
