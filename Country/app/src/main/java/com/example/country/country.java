package com.example.country;



import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class country extends AppCompatActivity {
    ProgressDialog progressDialog;
    TextView tenquocgia , danso , dientich,countryCode  ;
    Button btnQuayLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);

        new GetInf().execute("http://api.geonames.org/countryInfoJSON?username=btandroid2");

        ClickQuayLai();
    }

    private class GetInf extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // display a progress dialog for good user experiance
            progressDialog = new ProgressDialog(country.this);
            progressDialog.setMessage("Đang lấy dữ liệu...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();

            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";

                while ( (line = bufferedReader.readLine()) != null ){
                    stringBuilder.append(line);
                }

                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Intent intent = getIntent();
            String nationalName = intent.getStringExtra("national_name");
            //Tắt progress dialog ở onPreExcute
            progressDialog.dismiss();
            try {
                //Khởi tạo đối tượng Json nhận được
                JSONObject jsonObject = new JSONObject(s);
                //Khởi tạo JSONArray, truyền vào name JSONArray muốn lấy
                JSONArray jsonGeonames = jsonObject.getJSONArray("geonames");
                //Vòng lặp gán dữ liệu
                for (int i = 0; i < jsonGeonames.length(); i++){
                    JSONObject object = jsonGeonames.getJSONObject(i);

                    String countryName = object.getString("countryName");

                    if ( countryName.equals(nationalName) ){
                        tenquocgia = findViewById(R.id.tenquocgia_giatri);
                        danso = findViewById(R.id.danso_giatri);
                        dientich = findViewById(R.id.dientich_giatri);

                        tenquocgia.setText(object.getString("countryName"));
                        danso.setText(object.getString("population") + " người");
                        dientich.setText(object.getString("areaInSqKm") + " km2");

                        String urlImage = "https://img.geonames.org/flags/m/" + object.getString("countryCode").toLowerCase() + ".png" ;

                        new ImageTask( (ImageView) findViewById(R.id.picture)).execute(urlImage);

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    //Class Load image từ url
    private class ImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public ImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);

        }
    }

    public void ClickQuayLai(){
        btnQuayLai = findViewById(R.id.back);
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
