package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
            primaryStage.setTitle("DrawApp");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
