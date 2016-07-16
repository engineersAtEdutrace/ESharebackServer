/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esharebackserver;

import esharebackserver.FileDownload.DownloadFileClient;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sagar
 */
public class FileReceiver {
    
    public static void main(String[] args) {
        new FileReceiver().myMain();
    }
    
    public void myMain(){
    
        try {
            ServerSocket ss = new ServerSocket(Constants.PORT_FILE_RECV);
            Socket skt = ss.accept();
            byte[] fileByte = new byte[8192];
            InputStream is;
            FileOutputStream fos;
            int readSize;
            String filePath="E://",fileName="newfile.mp4";
            try{   
            skt = new Socket("127.0.0.1",Constants.PORT_FILE_SEND);
            
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
            Logger.getLogger(FileReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        } catch (IOException ex) {
            String msg = "\n\n**"+Constants.ERR_PORT + Constants.PORT_FILE_RECV+"**";
            String sol = "SOLUTION: Find [PID_OF_PROCESS] running on port using command:"
                    + "\n\tlsof -i :"+Constants.PORT_FILE_RECV+" | grep LISTEN | cut -d' ' -f2"
                    + "\nAnd Kill Process using command:"
                    + "\n\tkill -9 [PID_OF_PROCESS]\n\n";
            Logger.getLogger(FileReceiver.class.getName()).log(Level.SEVERE, msg + "\n"+ sol);
        }
        
    }
    
    
}
