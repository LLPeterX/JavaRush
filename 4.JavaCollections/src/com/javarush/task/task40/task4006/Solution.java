package com.javarush.task.task40.task4006;

import java.io.*;
import java.net.Socket;
import java.net.URL;

/* 
Отправка GET-запроса через сокет
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        try {
            Socket socket = new Socket(url.getHost(),url.getDefaultPort());
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            String request = "GET "+url.getFile()+" HTTP/1.1\r\n" + "Host: "+url.getHost()+"\r\n";
            request += "User-Agent: \"Mozilla/5.0\"\r\n";
            request += "Accept: text/html\r\n";
            request += "Connection: close\r\n";
            request += "\r\n";
            OutputStreamWriter writer = new OutputStreamWriter(os);
            writer.write(request);
            writer.flush();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String responseLine;

            while ((responseLine = bufferedReader.readLine()) != null) {
                System.out.println(responseLine);
            }
            bufferedReader.close();
            socket.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}