package esharebackserver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sagar
 */
public class Constants {
    
    public static final String ROOT_DIR = System.getProperty("user.home")+"/EShareback/";
    
    public static final int FILE_RECV_PORT = 8110;
    public static final int LS_PORT = 8103;
    
    public static final String JSON_LIST_DIR = "path";
    public static final String JSON_DIRS = "dirs";
    public static final String JSON_FILES = "files";
            
    public final static String ERR_PORT = "Port Not Available: ";
    public final static String ERR_JSON = "Improper JSON format: ";
}
