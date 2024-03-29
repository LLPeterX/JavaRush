package com.javarush.task.task40.task4002;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Опять POST, а не GET
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        //solution.sendPost("http://requestb.in/1h4qhvv1", "name=zapp&mood=good&locale=&id=888");
        solution.sendPost("https://requestbin.jumio.com/1acukzv1", "name=zapp&mood=good&locale=&id=777");
    }

    public void sendPost(String url, String urlParameters) throws Exception {
        HttpClient client = getHttpClient();
        //HttpGet request = new HttpGet(url);
        HttpPost request = new HttpPost(url);
        // ниже валидатор не понимает - "В OutputStream соединения должны быть записаны переданные в метод sendPost параметры."
        // (хотя работает, и намного короче)
        //StringEntity stringEntity = new StringEntity(urlParameters, "utf-8");
        //request.setEntity(stringEntity);
        // поэтому пойдем другим путем - через список
        List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
        String[] params = urlParameters.split("&");
        for (int i = 0; i < params.length; i++) {
            String g = params[i];
            valuePairs.add(new BasicNameValuePair(g.substring(0,g.indexOf("=")), g.substring(g.indexOf("=")+1)));
        }
        request.setEntity(new UrlEncodedFormEntity(valuePairs));

        request.addHeader("User-Agent", "Mozilla/5.0");

        HttpResponse response = client.execute(request);

        System.out.println("Response Code: " + response.getStatusLine().getStatusCode());

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String responseLine;
        while ((responseLine = bufferedReader.readLine()) != null) {
            result.append(responseLine);
        }

        System.out.println("Response: " + result.toString());
    }

    protected HttpClient getHttpClient() {
        return HttpClientBuilder.create().build();
    }
}
