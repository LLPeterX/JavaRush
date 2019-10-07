package com.javarush.task.task40.task4001;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/* 
POST, а не GET
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        //solution.sendPost(new URL("http://requestb.in/1cse9qt1"), "name=zapp&mood=good&locale=&id=777");
        solution.sendPost(new URL("https://requestbin.jumio.com/1acukzv1"), "name=zapp&mood=good&locale=&id=777");
    }

    public void sendPost(URL url, String urlParameters) throws Exception {
        // ВАЖНО! Валидатор принимает только Http, а сайт де-факто работает с https
        // Поэтому для тестирования надо использовать HttpsURLConnection, а валидатору подавать HttpURLConnection!
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.setDoInput(true); // нужен для POST
        connection.setDoOutput(true); // нужен для записи в stream
        // устанавливаем параметры POST - значения полей
        OutputStream os = connection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
        writer.write(urlParameters); // так нельзя!
        writer.flush();
        writer.close();
        os.close();

        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String responseLine;
        StringBuilder response = new StringBuilder();

        while ((responseLine = bufferedReader.readLine()) != null) {
            response.append(responseLine);
        }
        bufferedReader.close();

        System.out.println("Response: " + response.toString());
    }
}
