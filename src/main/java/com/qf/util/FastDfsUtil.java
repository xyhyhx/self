package com.qf.util;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

public class FastDfsUtil {
    TrackerClient trackerClient=null;
    TrackerServer trackerServer=null;
    StorageServer storageServer=null;
    StorageClient storageClient=null;
    public FastDfsUtil(){

        try {
            ClientGlobal.init("fastdfs.conf");

            trackerClient=new TrackerClient();
            trackerServer=trackerClient.getConnection();
            storageClient=new StorageClient(trackerServer,storageServer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }



    }
    public String[] upload(String local_name,String suffix){
        try {
            return storageClient.upload_file(local_name, suffix, null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;

    }

    public String[] upload(byte[] b,String ext){
        try {
            return storageClient.upload_file(b,ext,null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }
}
