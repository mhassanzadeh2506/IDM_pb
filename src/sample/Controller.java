package sample;

import Models.File;
import Models.HttpUrl;
import Models.MultiThread;
import Models.Status;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import static sample.Main.*;


public class Controller implements Initializable {

    @FXML
    private TableView<File> tbl1_downloaded;
    @FXML
    private TableColumn<File, String> col_tbl1_name;
    @FXML
    private TableColumn<File, Integer> col_tbl1_size;
    @FXML
    private TableColumn<File, Status> col_tbl1_status;
    @FXML
    private TableColumn<File, String> col_tbl1_date;
    @FXML
    private TableColumn<File, String> col_tbl1_Address;
    @FXML
    private TableColumn<File, String> col_tbl1_Suffix;

    @FXML
    private TextField txt_url;
    @FXML
    private Button btn_download;
    @FXML
    private Button ppt;

    public void download(ActionEvent event) {

        Stage primaryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/each_down.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Controller_down ctl = fxmlLoader.getController();

        //"http://strony.ug.edu.pl/~matjs/Wyklady/Systemy/Systop1_UG.ppt"

        String tmp = null;
        try {
            tmp = HttpUrl.downloadFile(txt_url.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert tmp != null;
            String[] info = tmp.split("&@&");
            System.out.println(info[0] + "   " + info[1]);
            String[] str = info[0].split("[.]");
            int size = str.length;
//    String name, int size, Status status, String date, String address, long downloaded, String suffix
            File newfile = new File(str[1], Integer.parseInt(info[1]), Status.downloading, Main.time(), txt_url.getText(), str[size - 1]);

            System.out.println(newfile.getStatus());
            Main.fileObservableList.add(newfile);
            ctl.passFileFunction(newfile);
            System.out.println(newfile.getDate());
            primaryStage.setTitle(newfile.getName());
            primaryStage.setScene(new Scene(root, 233, 214));
            primaryStage.show();
            Thread download = new Thread(new MultiThread(newfile));
            download.setDaemon(true);
            download.start();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

//    private void UpdateTable(){
//        tbl1_downloaded.getItems().clear();
//        Main.fileObservableList.forEach((file) -> {
//            tbl1_downloaded.getItems().add(new File(file));
//        });
//    }

    public void ppt(ActionEvent event) {
        for (int i = 0; i < Main.fileObservableList.size(); i++) {
            if (Main.fileObservableList.get(i).getSuffix().equals("ppt")) {
                PPt.add(Main.fileObservableList.get(i));
            }
        }
        col_tbl1_name.setCellValueFactory(new PropertyValueFactory<File, String>("col_tbl1_name"));
        col_tbl1_size.setCellValueFactory(new PropertyValueFactory<File, Integer>("col_tbl1_size"));
        col_tbl1_status.setCellValueFactory(new PropertyValueFactory<File, Status>("col_tbl1_status"));
        col_tbl1_date.setCellValueFactory(new PropertyValueFactory<File, String>("col_tbl1_date"));
        col_tbl1_Address.setCellValueFactory(new PropertyValueFactory<File, String>("col_tbl1_Address"));
        col_tbl1_Suffix.setCellValueFactory(new PropertyValueFactory<File, String>("col_tbl1_date"));
//        col_tbl1_name.setCellValueFactory(cellData -> cellData.getValue().getName());
//        col_tbl1_size.setCellValueFactory(cellData -> cellData.getValue().getSize());
//        col_tbl1_date.setCellValueFactory(cellData -> cellData.getValue().getStatus());
//        for (File f:Main.PPt) {
//            tbl1_downloaded.getItems().add(f);
//        }
        tbl1_downloaded.setItems(PPt);

    }

    public void pdf(ActionEvent event) {
        for (int i = 0; i < Main.fileObservableList.size(); i++) {
            if (Main.fileObservableList.get(i).getSuffix().equals("pdf")) {
                Pdf.add(Main.fileObservableList.get(i));
            }
        }
        tbl1_downloaded.setItems(Pdf);

    }

    public void doc(ActionEvent event) {
        for (int i = 0; i < Main.fileObservableList.size(); i++) {
            if (Main.fileObservableList.get(i).getSuffix().equals("doc")) {
                Doc.add(Main.fileObservableList.get(i));
            }
        }
        tbl1_downloaded.setItems(Doc);
    }
    public void docx(ActionEvent event){
        for (int i = 0; i < Main.fileObservableList.size(); i++) {
            if (Main.fileObservableList.get(i).getSuffix().equals("docx")) {
                Docx.add(Main.fileObservableList.get(i));
            }
        }
        tbl1_downloaded.setItems(Docx);
    }
   public void rar(ActionEvent event){
        for (int i = 0; i < Main.fileObservableList.size(); i++) {
            if (Main.fileObservableList.get(i).getSuffix().equals("rar")) {
                RAR.add(Main.fileObservableList.get(i));
            }
        }
        tbl1_downloaded.setItems(RAR);
    }

   public void zip(ActionEvent event){
        for (int i = 0; i < Main.fileObservableList.size(); i++) {
            if (Main.fileObservableList.get(i).getSuffix().equals("zip")) {
                Zip.add(Main.fileObservableList.get(i));
            }
        }
        tbl1_downloaded.setItems(Zip);
    }
   public void mp4(ActionEvent event){
        for (int i = 0; i < Main.fileObservableList.size(); i++) {
            if (Main.fileObservableList.get(i).getSuffix().equals("mp4")) {
                Mp4.add(Main.fileObservableList.get(i));
            }
        }
        tbl1_downloaded.setItems(Mp4);
    }
   public void mp3(ActionEvent event){
        for (int i = 0; i < Main.fileObservableList.size(); i++) {
            if (Main.fileObservableList.get(i).getSuffix().equals("mp3")) {
                Mp3.add(Main.fileObservableList.get(i));
            }
        }
        tbl1_downloaded.setItems(Mp3);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

//    String fileURL = "Ydw";
//    String saveDir = "http://strony.ug.edu.pl/~matjs/Wyklady/Systemy/Systop1_UG.ppt";


}
