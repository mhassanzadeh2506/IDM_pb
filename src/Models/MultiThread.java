package Models;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.Main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Objects;

public class MultiThread implements Runnable {

    private Models.File file;
    public MultiThread(Models.File file) {
        this.file=file;
    }

    @Override
    public void run() {
        try {//"http://strony.ug.edu.pl/~matjs/Wyklady/Systemy/Systop1_UG.ppt"
            URL url = new URL(file.getAddress());
            URLConnection urlConnection = url.openConnection();

            java.io.File FileDownloaded = new File("E:\\Games\\"+file.getName()+"."+file.getSuffix());
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

            if (file.getSize()==file.getDownloaded())
            file.setStatus(Status.downloaded);
            else if (file.getDownloaded()<file.getSize())
                file.setStatus(Status.paused);

            System.out.println(file.getStatus());
            fos.close();
            rbc.close();

        } catch (IOException e) {
//            e.printStackTrace();
            file.setStatus(Status.failed);

            System.out.println(e.getMessage());

        }
    }























//    @FXML private TextField txt_url;
//    @FXML private TextField txt_location;
//    String url;
//    String location;
//
//    public MultiThread(String url, String location) {
//        this.url = url;
//        this.location = location;
//    }
//
//@Override
//    public void run() {
//
////    String fileURL = "http://strony.ug.edu.pl/~matjs/Wyklady/Systemy/Systop1_UG.ppt";
////    String saveDir = "E:\\Games";
//
//        try {
//            ArrayList<String>info=new ArrayList<>();
//           // download_info.addAll(Objects.requireNonNull(HttpUrl.downloadFile(this.url, this.location)));
//            HttpUrl.downloadFile(this.url, this.location);
//            info.addAll(HttpUrl.downloadFile(this.url, this.location));
//            for (String a:info) {
//                System.out.println(a);
//
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            System.out.println(ex.getMessage());
//        }
//
//}
}
