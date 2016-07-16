/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esharebackserver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anand Singh
 * 
 * Sends file to client side on its request
 */
public class FileSender {
     public static void main(String[] args) {
        (new FileSender()).mymain();
    }
    
    public void mymain()
    {
        //Create root Dir
        File rootDir = new File(Constants.ROOT_DIR);
        if(!rootDir.exists())
            rootDir.mkdir();
        //-- Create root dir
        
        //connect to client
        ServerSocket ss = null;
        String result = "";
        File dwnldFile = null;
        BufferedOutputStream bos;
        BufferedInputStream bis;
        byte[] fileBytes = new byte[Constants.BUFFER_SIZE]; //using 8k buffer size
        long  numOfBuffer;
        int readSize;
        String testFilePath = "F://",testFileName = "25_2_2016.mp4";
        
        try{
             ss = new ServerSocket(Constants.PORT_FILE_SEND);
             
             while(true){
                //Start Listening
                System.out.println("Waiting...");
                Socket skt = ss.accept();
                System.out.println("Connection Accepted");
                //-- Start Listening
                
                //Receiving File Request
//                BufferedReader br = new BufferedReader(new InputStreamReader(skt.getInputStream()));
//                result = "";
//                String temp = "";
//                while((temp = br.readLine()) != null){
//                    if(temp.contains(Constants.END_OF_MSG)){
//                        temp = temp.replace(Constants.END_OF_MSG, "");
//                        result+=temp;
//                        break;
//                    }
//                    result += temp;
//                }
//                System.out.println("Result: "+result);
//                //-- Receiving File Request
//                
//                //Decode Dir(get file name that to be send)
//                JSONObject main = new JSONObject(result);
//                String fileName = main.getString(Constants.JSON_FILE_DWNLD); 
//                //-- Decode Dir
                
                //Send file
                dwnldFile = new File(testFilePath+testFileName);
                
                if((dwnldFile.length()%Constants.BUFFER_SIZE)!=0)
                    numOfBuffer = (dwnldFile.length()/Constants.BUFFER_SIZE) + 1;
                else
                    numOfBuffer = (dwnldFile.length()/Constants.BUFFER_SIZE);
                
                bis = new BufferedInputStream(new FileInputStream(dwnldFile));
                bos =new BufferedOutputStream(skt.getOutputStream());
                while((readSize=bis.read(fileBytes))>0)
                {
                    bos.write(fileBytes,0,readSize);
                    bos.flush();
                }
                bos.close();
                skt.close();                
                //--Send file
                
                //--listen for other client
                
             } //end of while
                    
        }catch(IOException ex){
            String msg = "\n\n**"+Constants.ERR_PORT + Constants.PORT_FILE_SEND+"**";
            String sol = "SOLUTION: Find [PID_OF_PROCESS] running on port using command:"
                    + "\n\tlsof -i :"+Constants.PORT_FILE_SEND+" | grep LISTEN | cut -d' ' -f2"
                    + "\nAnd Kill Process using command:"
                    + "\n\tkill -9 [PID_OF_PROCESS]\n\n";
           Logger.getLogger(FileSender.class.getName()).log(Level.SEVERE, msg + "\n"+ sol);
        } 
//           catch (JSONException ex) {
//            String str = Constants.ERR_JSON + "\n\t" + result;
//            Logger.getLogger(DownloadFileServer.class.getName()).log(Level.SEVERE, str );
//        }
    }
   
}