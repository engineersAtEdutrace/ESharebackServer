/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esharebackserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author sagar
 */
public class LsServer {
    public static void main(String[] args) {
        //System.out.println(Constants.ROOT_DIR);
        //System.out.println(new LsServer().listDir(""));
        //new LsServer();
    }
    
    public void myMain(){
        //Create root Dir
        File rootDir = new File(Constants.ROOT_DIR);
        if(!rootDir.exists())
            rootDir.mkdir();
        //-- Create root dir

        ServerSocket ss = null;
        String result = null;
        
        try {
            
            ss = new ServerSocket(Constants.LS_PORT);
            
            while(true){
                //Start Listening
                Socket skt = ss.accept();
                //-- Start Listening

                //Receiving Dir Request
                BufferedReader br = new BufferedReader(new InputStreamReader(skt.getInputStream()));
                result = "";
                String temp = "";
                while((temp = br.readLine()) != null){
                    result += temp;
                }
                //-- Receiving Dir Request

                //Decode Dir
                JSONObject main = null;
                String path = "";
                main = new JSONObject(result);
                path = main.getString(Constants.JSON_LIST_DIR); 
                //-- Decode Dir

                //Listing Files
                String response = listDir(path);
                //-- Listing Files

                //Sending Response
                PrintWriter out = new PrintWriter(
                            new BufferedWriter(
                                    new OutputStreamWriter(skt.getOutputStream())
                            ), true);
                out.println(response);
                out.flush();
                //-- Sending Response
            }
        
            
        } catch (IOException ex) {
            String msg = "\n\n**"+Constants.ERR_PORT + Constants.LS_PORT+"**";
            String sol = "SOLUTION: Find [PID_OF_PROCESS] running on port using command:"
                    + "\n\tlsof -i :"+Constants.LS_PORT+" | grep LISTEN | cut -d' ' -f2"
                    + "\nAnd Kill Process using command:"
                    + "\n\tkill -9 [PID_OF_PROCESS]\n\n";
            Logger.getLogger(FileReceiver.class.getName()).log(Level.SEVERE, msg + "\n"+ sol);
        }
        catch (JSONException ex) {
                String str = Constants.ERR_JSON + "\n\t" + result;
                Logger.getLogger(LsServer.class.getName()).log(Level.SEVERE, str );
        }
        finally{
            if(ss!=null){ 
                try {
                    ss.close();
                } catch (IOException ex) {
                    Logger.getLogger(LsServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
    private String listDir(String path){
        
        //Listing Dir
            JSONObject response = new JSONObject();
            JSONArray dirs = new JSONArray();
            JSONArray files = new JSONArray();
            
//            ArrayList<String> fileList=new ArrayList<String>();
//            ArrayList<String> dirList=new ArrayList<String>();
    
            File f = new File(Constants.ROOT_DIR + path);
            for(File innerf: f.listFiles()){
                if(innerf.isFile()){
                    files.put(innerf.getName());
                    //fileList.add(innerf.getName());
                }else{
                    dirs.put(innerf.getName());
                    //dirList.add(innerf.getName());
                }
            }
            
        try {
            response.put(Constants.JSON_DIRS, dirs);
            response.put(Constants.JSON_FILES, files);
        } catch (JSONException ex) {
            String str = Constants.ERR_JSON + "\n\t" + response;
            Logger.getLogger(LsServer.class.getName()).log(Level.SEVERE, str );
            Logger.getLogger(LsServer.class.getName()).log(Level.SEVERE, null, ex );
        }
//            files.put(fileList);
//            dirs.put(dirList);
            
//            try {
//                response.putOpt(Constants.JSON_DIRS, dirs);
//                response.putOpt(Constants.JSON_FILES, files);
//                
//            } catch (JSONException ex) {
//                Logger.getLogger(LsServer.class.getName()).log(Level.SEVERE, null, ex);
//            }
            //-- Listing Dir
        
        return response.toString();
    }
}
