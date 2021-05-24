package com.example.bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Telephony;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.net.Uri;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   public static  String Thucuong=" ";
   public static  String Size=" ";
    public static String Topping=" ";
    ImageView vitri;
    Button btndathang;
    CheckBox tranchau,thachthuytinh,thachphomai,banhflan,cunang,hatbe;
    RadioGroup radiothucuong,radioSize;
    RadioButton radiobuttonthucuong,radiobuttonsize;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        CheckBox tranchau=findViewById(R.id.tranchau);
        CheckBox thachthuytinh=findViewById(R.id.thachthuytinh);
        CheckBox thachphomai=findViewById(R.id.thachphomai);
        CheckBox banhflan=findViewById(R.id.banhflan);
        CheckBox cunang=findViewById(R.id.cunang);
        CheckBox hatbe=findViewById(R.id.hatbe);

        btndathang=findViewById(R.id.dathang);
        btndathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                RadioGroup radiothucuong=findViewById(R.id.radiothucuong);
                int IdThucuong= radiothucuong.getCheckedRadioButtonId();
                if(IdThucuong==-1) {  // k chọn thì =-1, chọn radioButton thứ nhất thì =1, thứ 2 thì =2...
                    Toast.makeText(MainActivity.this, "Bạn chưa chọn thức  ", Toast.LENGTH_LONG).show();
                    return;
                }

                RadioGroup radioSize=findViewById(R.id.radioSize);
                int IdSize = radioSize.getCheckedRadioButtonId();
                if(IdSize==-1) {  // k chọn thì =-1, chọn radioButton thứ nhất thì =1, thứ 2 thì =2...
                    Toast.makeText(MainActivity.this, "Bạn chưa chọn Size  ", Toast.LENGTH_LONG).show();
                    return;
                }

                radiobuttonthucuong=findViewById(IdThucuong);
                Thucuong=radiobuttonthucuong.getText().toString();

                radiobuttonsize=findViewById(IdSize);
                Size=radiobuttonsize.getText().toString();

                if(tranchau.isChecked())
                    Topping = Topping  + tranchau.getText().toString() + "  ";
                if(thachphomai.isChecked())
                    Topping=Topping+thachphomai.getText().toString() + " ";
                if(thachthuytinh.isChecked())
                    Topping=Topping+thachthuytinh.getText().toString() + " ";
                if(banhflan.isChecked())
                    Topping=Topping+banhflan.getText().toString() + " ";
                if(cunang.isChecked())
                    Topping=Topping+cunang.getText().toString() + " ";
                if(hatbe.isChecked())
                    Topping =Topping+hatbe.getText().toString() + " ";

//                 Intent intent   = new Intent(MainActivity.this,ChiTietMonAnActivity.class );
                Intent intent = new Intent(MainActivity.this,ChiTietMonAnActivity.class);

                 startActivity(intent);
             }
        });

        vitri = findViewById(R.id.vitri);
        vitri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String linkVitri = "https://www.google.com/maps/place/273+An+D.+V%C6%B0%C6%A1ng,+Ph%C6%B0%E1%BB%9Dng+3,+H%E1%BB%93+Ch%C3%AD+Minh,+Th%C3%A0nh+ph%E1%BB%91+H%E1%BB%93+Ch%C3%AD+Minh,+Vi%E1%BB%87t+Nam/@10.7599748,106.6799254,17z/data=!3m1!4b1!4m5!3m4!1s0x31752f1b7e38d70f:0x9f2317bb653235f3!8m2!3d10.7599748!4d106.6821141?hl=vi-VN";

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(linkVitri));

                startActivity(intent);

            }
        });


    }
}