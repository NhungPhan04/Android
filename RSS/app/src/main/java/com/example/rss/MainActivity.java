package com.example.rss;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;

    ArrayList <String> arraytieude;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner=findViewById(R.id.spiner);
        arraytieude =new ArrayList<>();
        adapter= new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item,arraytieude);
        spinner.setAdapter(adapter);

        new ReadRSS().execute("https://aud.fxexchangerate.com/rss.xml");


    }
    private class ReadRSS extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader=new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line="";
                while((line = bufferedReader.readLine()) != null){
                    content.append(line);
                }
                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLDOMParser parser = new XMLDOMParser();

            Document document = parser.getDocument(s);

            NodeList nodeList = document.getElementsByTagName("item");
            String[] tieude;
            for(int i=0;i<nodeList.getLength();i++){
                Element element= (Element) nodeList.item(i);
                tieude =parser.getValue(element,"title").split("/") ;
                arraytieude.add(tieude[1]);

            }

             adapter.notifyDataSetChanged();



           // Toast.makeText(MainActivity.this, tieude, Toast.LENGTH_LONG).show();
        }
    }
}