package GUI.Models;

import BLL.FileManager;
import javafx.scene.chart.XYChart;

import java.io.IOException;

public class FileModel {
    private FileManager fileManager;

    public FileModel(){
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
}
