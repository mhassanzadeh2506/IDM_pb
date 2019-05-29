package sample;

import Models.File;
import Models.File.*;
import Models.Status;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

public class Controller_down {
ArrayList<Thread> t=new ArrayList<>();
    private File file;

    @FXML
    private ProgressIndicator pb;
    @FXML
    private Label lbl_name;
    @FXML
    private Button Paused;
    @FXML
    private Button resume;

    public void Paused(ActionEvent event) {
        file.setStatus(Status.paused);
    }

    public void resume(ActionEvent event) throws IOException {
        file.setStatus(Status.downloading);
        URL url = new URL(file.getAddress());
        URLConnection urlConnection = url.openConnection();

        java.io.File FileDownloaded = new java.io.File("E:\\Games\\"+file.getName());
        FileOutputStream fos = new FileOutputStream(FileDownloaded);//, FileDownloaded.exists());
        ReadableByteChannel rbc = Channels.newChannel(urlConnection.getInputStream());
        long downloaded = 0;
//            boolean OnDownload = true;

        while((downloaded = fos.getChannel().transferFrom(rbc, file.getDownloaded(), 1024)) > 0 && file.getStatus().equals(Status.downloading))
        {
            file.setDownloaded(file.getDownloaded()+downloaded);
            System.out.println(file.getDownloaded());
            //   file.downloaded += downloaded;
        }
    }

    public void passFileFunction(File file) {

        this.file = file;
        lbl_name.setText(file.getName());
       new Thread(new Runnable() {
            @Override
            public void run() {
                while (file.getDownloaded() < file.getSize()) {
                    System.out.println("CALLED");
                    try {
                        Thread.sleep(50);
                        Platform.runLater(() -> pb.setProgress((double) file.getDownloaded() / file.getSize()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

}