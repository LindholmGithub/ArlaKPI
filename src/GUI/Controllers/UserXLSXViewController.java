package GUI.Controllers;

import GUI.Models.FileModel;
import com.gembox.spreadsheet.ExcelColumnCollection;
import com.gembox.spreadsheet.SpreadsheetInfo;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserXLSXViewController implements Initializable {

    @FXML
    private TableView excelTable;
    private FileModel fileModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");

        try {
            fileModel = new FileModel();
            //String [][] datasource = fileModel.getXLSXData();
            //fillTable(datasource);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    public void fillTable(String[][] dataSource){
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
            excelTable.getColumns().add(column);
        }

    }
}
