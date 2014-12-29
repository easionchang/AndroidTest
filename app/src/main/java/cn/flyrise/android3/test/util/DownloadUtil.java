/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-12-7 ����07:41:36
 */
package cn.flyrise.android3.test.util;


import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * �๦��������</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * �޸�ʱ�䣺</br>
 * �޸ı�ע��</br>
 */
public class DownloadUtil {
    
    
//    protected void downloadFile(String url) {
//        try {
//            
//            if (!Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
//                InputStream is = getInputStream(url);
//                imageInfo.image = PhotoUtil.decodeSampledBitmapFromHttp(is,width,height);
//                return imageInfo;
//            }
//            int start = urlPath.lastIndexOf("/");
//            String fileName = null;
//            if (start != -1) {
//                fileName = urlPath.substring(start + 1, urlPath.length());
//            }
//            File folder = new File(FileHelper.getContactsPath());
//            if (!folder.exists()) {
//                folder.mkdirs();
//            }
//            File file = new File(folder.getPath() + "/" + fileName);
//            File sdcardFile = repaceFileSuffixCode(file, ".", "_");
//            if (sdcardFile.exists()) {// ���SDcard���Ƿ����������Ƭ������Ѿ����˾�ֱ����SDcard�д��ڵĶ��������¼���
//                imageInfo.image = changeToBitmap(sdcardFile);
//                return imageInfo;
//            } else {
//                InputStream is = getInputStream(urlPath);
//                imageInfo.image = writeToSDCard(is, file);
//                return imageInfo;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    
    
    private InputStream getInputStream(String urlPath) throws MalformedURLException, IOException {
        URL url = new URL(urlPath);
        HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
        // ���ó�ʱʱ��
        urlConn.setConnectTimeout(5000);
        InputStream is = urlConn.getInputStream();
        return is;
    }

}
