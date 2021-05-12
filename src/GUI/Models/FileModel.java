package GUI.Models;

import BLL.FileManager;
import javafx.scene.chart.XYChart;

public class FileModel {
    private FileManager fileManager;

    public FileModel(){
        fileManager = new FileManager();
    }

    public XYChart.Series getCSVData() throws Exception {
        return fileManager.getCSVData();
    }
}
