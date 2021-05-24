package com.example.bai2;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.bai2.R.id.textopping;

public class ChiTietMonAnActivity  extends AppCompatActivity {
    TextView thucuong,Size,Topping;
    Button btnxacnhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietmonan);

        thucuong =findViewById(R.id.textthucuong);
        thucuong.setText("Thức uống : " + MainActivity.Thucuong);

        Size=findViewById(R.id.textsize);
        Size.setText("Size : "+MainActivity.Size);

        Topping = findViewById(textopping);
        Topping.setText("Topping : "+MainActivity.Topping);

        btnxacnhan = findViewById(R.id.xacnhan);
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("191", null,
                        "Tôi muốn 1 ly trà sữa - sms message",
                        null, null);
            }
        });
    }



}
