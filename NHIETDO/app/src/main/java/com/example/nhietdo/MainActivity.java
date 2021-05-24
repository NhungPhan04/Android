package com.example.nhietdo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button chuyendoi;
    RadioGroup luachon;
    EditText dauvao,daura;
    TextView lichsu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chuyendoi= findViewById(R.id.btnChuyendoi);
        dauvao= findViewById(R.id.dauvao);
        daura= findViewById(R.id.daura);
        luachon = findViewById(R.id.groupbutton);
        lichsu = findViewById(R.id.textlichsu);

        chuyendoi.setOnClickListener(new View.OnClickListener() {
            double ketqua;
            String s ="",s1;
            int count =1;
            @Override
            public void onClick(View v) {
                float a= Float.parseFloat(dauvao.getText().toString());
                int idradiogroup= luachon.getCheckedRadioButtonId();
                if(idradiogroup==-1){
                    Toast.makeText(MainActivity.this,"Bạn chưa chọn loại chuyển đổi",Toast.LENGTH_LONG).show();
                    return;
                }
                //C to F
                if(idradiogroup==1){
                    s1="C TO F";
                    ketqua=(1.8*a+32);
                }
                //F TO C
                if(idradiogroup==2){
                    s1="F TO C";
                    ketqua=(double)(a-32)/1.8;
                }
                ketqua=(double)Math.round(ketqua*10/10);
                daura.setText(""+ketqua);
                s=s+count++ +"."+s1+dauvao.getText()+"-->"+daura.getText()+"\n\n";
                lichsu.setText(""+s);



            }
        });


    }
}