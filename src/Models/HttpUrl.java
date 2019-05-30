package Models;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;




public class HttpUrl {

    //    private
    private static final int BUFFER_SIZE = 4096;

    public static String downloadFile(String fileURL)
            throws IOException,NullPointerException, UnknownHostException
    {
        try {

            ArrayList<String> info = new ArrayList<>();
            URL url = new URL(fileURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            int responseCode = httpConn.getResponseCode();

            // always check HTTP response code first
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String fileName = "";
                String disposition = httpConn.getHeaderField("Content-Disposition");
//            String contentType = httpConn.getContentType();
                int contentLength = httpConn.getContentLength();

                if (disposition != null) {
                    // extracts file name from header field
                    int index = disposition.indexOf("filename=");
                    if (index > 0) {
                        fileName = disposition.substring(index + 10,
                                disposition.length() - 1);
                        return fileName;
                    }
                } else {
                    // extracts file name from URL
                    fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
                    return fileName + "&@&" + contentLength;
                }
            }
        }
        catch (Exception e){
            System.out.println("wrong url");
            System.out.println(e.getMessage());
        }
//
//
//
////            System.out.println("Content-Type = " + contentType);
//            System.out.println("Content-Disposition = " + disposition);
////            System.out.println("Content-Length = " + contentLength);
//            System.out.println("fileName = " + fileName);
//            info.add(fileName);
////            info.add(contentLength+"");
//            info.add(disposition);
//
//            // opens input stream from the HTTP connection
//            InputStream inputStream = httpConn.getInputStream();
//            String saveFilePath = saveDir + File.separator + fileName;
//
//            // opens an output stream to save into file
//            FileOutputStream outputStream = new FileOutputStream(saveFilePath);
//
//            int bytesRead = -1;
//            byte[] buffer = new byte[BUFFER_SIZE];
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                outputStream.write(buffer, 0, bytesRead);
//            }
//
//            outputStream.close();
//            inputStream.close();
//
//            System.out.println("done");
//            info.add("done");

        return null;
    }
}





