import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI/Views/LoginView.fxml"));
        primaryStage.setTitle("Arla KPI");
        primaryStage.getIcons().add(new Image("Resources/Images/ArlaLogo100x100.png"));
        Scene scene = new Scene(root,1270,720);
        primaryStage.setMinWidth(350);
        primaryStage.setMinHeight(470);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
