package com.hui.mvptest.util;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by walke.Z on 2017/8/4.
 */

public class HttpConnectionUtil {

    public static String getData(String tagUrl) {
        HttpURLConnection con = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is = null;
        try {
            URL url = new URL(tagUrl);
            // 打开连接
            con = (HttpURLConnection) url.openConnection();
            // 设置超时时间
            con.setConnectTimeout(5 * 1000);
            // 连接
            con.connect();
            // 获得图片大小总字节数
            double totalCount = con.getContentLength();

            Log.d("qianfeng", "totalCount:" + totalCount);
            // 解析InputStream
            if (con.getResponseCode() == 200) {
                is = con.getInputStream();
                int len = 0;
                byte[] buf = new byte[1024];
                while ((len = is.read(buf)) != -1) {
                    baos.write(buf, 0, len);
                    baos.flush();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        byte[] bytes = baos.toByteArray();
        return new String(bytes);
    }


}
