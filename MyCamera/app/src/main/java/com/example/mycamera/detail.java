package com.example.mycamera;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class detail extends AppCompatActivity {

    TextView txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);


        txtContent = (TextView) findViewById(R.id.content);

        Intent intent = getIntent();
        String msg = intent.getStringExtra("Hay luu lai khoah khac dang nho");
        txtContent.setText(msg);

    }

}
