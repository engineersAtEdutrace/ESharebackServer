/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esharebackserver;

import java.io.IOException;
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
            ServerSocket ss = new ServerSocket(Constants.FILE_RECV_PORT);
            
            Socket skt = ss.accept();
            
            
            
        } catch (IOException ex) {
            String msg = "\n\n**"+Constants.ERR_PORT + Constants.FILE_RECV_PORT+"**";
            String sol = "SOLUTION: Find [PID_OF_PROCESS] running on port using command:"
                    + "\n\tlsof -i :"+Constants.FILE_RECV_PORT+" | grep LISTEN | cut -d' ' -f2"
                    + "\nAnd Kill Process using command:"
                    + "\n\tkill -9 [PID_OF_PROCESS]\n\n";
            Logger.getLogger(FileReceiver.class.getName()).log(Level.SEVERE, msg + "\n"+ sol);
        }
        
    }
    
    
}
