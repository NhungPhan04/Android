package com.example.country;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Handler {
    private static final String TAG = Handler.class.getSimpleName();

    public Handler() {
    }
    public String httpServiceCall(String requestUrl){
           String result=null;

        try {
            URL url=new URL(requestUrl);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream=new BufferedInputStream(connection.getInputStream());
            result=convertResultToString(inputStream);
            } catch (MalformedURLException e) {
               e.printStackTrace();
           } catch(ProtocolException e){
           e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return result;

    }

    private String convertResultToString(InputStream inputStream) {
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder StringBuilder=new StringBuilder();
        String li;

        while (true) {
            try {
                if (!((li= bufferedReader.readLine()) !=null)) {
                    StringBuilder.append("\n");


                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return StringBuilder.toString();
        }




    }

}
