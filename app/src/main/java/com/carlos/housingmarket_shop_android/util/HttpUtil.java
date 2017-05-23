package com.carlos.housingmarket_shop_android.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtil {

	
	/**
     * ��ʼ�� HttpURLConnection
     * @param url
     * @return
     * @throws Exception
     */
    public static HttpURLConnection initHttp(String url) throws Exception {
        URL realUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "text/xml");
        conn.setUseCaches(false);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(10000);
        return conn;
    }

    /**
     *
     * @param conn
     * @param bytes
     * @return
     * @throws Exception
     */
    public static InputStream doOutput(HttpURLConnection conn, byte[] bytes) throws Exception {
        OutputStream os = conn.getOutputStream();
        os.write(bytes);
        os.flush();
        os.close();
        return conn.getInputStream();
    }


    /**
     *  inputStream ת byte[]
     * @param inputStream
     * @return
     */
    public static byte[] toByteArray(InputStream inputStream) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[2048];
        int n = 0;
        try {
            while (-1 != (n = inputStream.read(buffer))) {
                output.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toByteArray();
    }
	
    public static byte[] HttpRequestPost(String url, byte[] bytes) throws Exception {
        return toByteArray(doOutput(initHttp(url), bytes));
    }
	
    public static String doPost(String url, String param) {
    	OutputStream os = null;
//    	InputStream is = null;
        BufferedReader in = null;
        PrintWriter out = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("charset", "utf-8");
            conn.setUseCaches(false);
            
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(10000);

            if (param != null && !param.trim().equals("")) {
                os = conn.getOutputStream();
                out = new PrintWriter(os);
                out.print(param);
//                os.write(param.getBytes("UTF-8"));
//                os.flush();
                out.flush();
            }
            
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
         while ((line = in.readLine()) != null) {
        	 result += line;
//        	 int i = 1;
//        while ((i = is.read()) != -1) {
//        	ByteArrayOutputStream   baos   =   new   ByteArrayOutputStream();
//        	baos.write(i);
//        	result += baos.toString();
          }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (in != null) {
                	in.close();
//                if (is != null) {
//                	is.close();	
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
	
	
}
