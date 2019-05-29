package sample;

import Models.File;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Main extends Application {
    public static ObservableList<File>fileObservableList= FXCollections.observableArrayList();
    public static ObservableList<File>Downloaded= FXCollections.observableArrayList();
    public static ArrayList<Thread>threads=new ArrayList<>();
    public static String time(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("YY:mm:dd");
        return sdf.format(cal.getTime()) ;
    }

    @Override
    public void start(Stage zzz) throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../Views/Main.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 907, 658));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
