package GUI.Controllers;

import BE.FileInfo;
import GUI.Models.FileModel;
import com.gembox.spreadsheet.ExcelColumnCollection;
import com.gembox.spreadsheet.SpreadsheetInfo;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserXLSXViewController implements Initializable {

    @FXML
    private TableView excelTable;
    private FileModel fileModel;
    private FileInfo selectedFileInfo;
    private String filePath;
    private final int sleepTime = 300000;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
        try {
            fileModel = new FileModel();
            selectedFileInfo = UserSelectViewController.getSelectedFileInfo();
            filePath = selectedFileInfo.getFilePath();
            String [][] datasource = fileModel.getXLSXData(filePath);
            fillTable(datasource);
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No file was found");
            alert.showAndWait();
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String [][] datasource = fileModel.getXLSXData(filePath);
                            fillTable(datasource);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                };
                while (true) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
    public void fillTable(String[][] dataSource) {
        excelTable.getColumns().clear();
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        for (String[] row : dataSource){
            data.add(FXCollections.observableArrayList(row));
        }
        excelTable.setItems(data);

        for (int i = 0; i < dataSource[0].length;i++){
            final int currentColumn = i;
            TableColumn<ObservableList<String>, String> column = new TableColumn<>(ExcelColumnCollection.columnIndexToName(currentColumn));
            column.setSortable(false);
            column.setReorderable(false);
            column.setCellValueFactory(param ->  new ReadOnlyObjectWrapper<>(param.getValue().get(currentColumn)));
            column.setEditable(true);
            column.setCellFactory(TextFieldTableCell.forTableColumn());
            column.setOnEditCommit(
                    (TableColumn.CellEditEvent<ObservableList<String>, String> t) -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).set(t.getTablePosition().getColumn(), t.getNewValue());
                    });
            excelTable.setColumnResizePolicy((param) -> true);
            excelTable.setSelectionModel(null);
            excelTable.getColumns().add(column);
        }
    }
}
