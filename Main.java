package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

import sample.*;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

    Parent homeRoot = FXMLLoader.load(getClass().getResource("sample.fxml"));
    Scene scene = new Scene(homeRoot);

    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}