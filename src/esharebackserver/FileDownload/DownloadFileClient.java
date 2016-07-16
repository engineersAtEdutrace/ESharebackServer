/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esharebackserver.FileDownload;

//import com.sun.corba.se.impl.orbutil.closure.Constant;
import esharebackserver.Constants;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anand Singh
 */
public class DownloadFileClient {
    public static void main(String[] args) {
        (new DownloadFileClient()).myMain();
    }
    
    void myMain()
    {
        Socket skt = null;
        byte[] fileByte = new byte[8192];
        InputStream is;
        FileOutputStream fos;
        int readSize;
        String filePath="E://",fileName="newfile.mp4";
        try{   
            skt = new Socket("127.0.0.1",Constants.PORT_DWNLD_FILE);
            
            
            
//            int bytesRead = br.readLine();
//            int current = bytesRead;
//            
//            do{
//               bytesRead = br.read(fileByte, current, (fileByte.length-current));
//                if(bytesRead >= 0) 
//                    current += bytesRead;
//            }while(bytesRead > -1);

            //
            is = skt.getInputStream();
            fos = new FileOutputStream(filePath+"s.mp4");//---actual fileName
            
            while((readSize=is.read(fileByte))>0)
            {
                fos.write(fileByte, 0, readSize);
                fos.flush();
            }
            fos.close();
            skt.close();
            } catch (IOException ex) {
            Logger.getLogger(DownloadFileClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
