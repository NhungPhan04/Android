package com.example.mycamera;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.app.Notification;
import android.app.NotificationManager;

import androidx.core.app.ActivityCompat;
import  androidx.core.app.NotificationCompat;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {
    ImageView btnTake,picture;
    Button btnNoti;
    NotificationCompat.Builder mBuilder;
    NotificationManager mNotifyMgr;
    int mNotificationId = 001;
    String strContent;
    EditText title,content;
    private NotificationManagerCompat notificationManagerCompat;
    int request_code_camera=123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = (EditText) findViewById(R.id.title);
        content = (EditText) findViewById(R.id.content);

        btnTake = (ImageView) findViewById(R.id.camera);
        picture = (ImageView) findViewById(R.id.picture);
        Intent intent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,request_code_camera);
        btnTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},request_code_camera);

            }

        });




        btnNoti=(Button) findViewById(R.id.btnNoti);

//        btnNoti.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mBuilder =
//                        new NotificationCompat.Builder(getApplicationContext())
//                                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
//                                .setContentTitle("My notification")
//                                .setContentText(strContent);
//
//
//                // Sets id cho notification
//                // Gets an instance of the NotificationManager service
//                mNotifyMgr =
//                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                // Builds the notification and issues it.
//                mNotifyMgr.notify(mNotificationId, mBuilder.build());
//
//
//                Intent resultIntent = new Intent(getApplicationContext(), detail.class);
//                resultIntent.putExtra("Hay luu lai khoang khac dang nho", strContent);
//
//                PendingIntent resultPendingIntent =
//                        PendingIntent.getActivity(
//                                getApplicationContext(),
//                                0,
//                                resultIntent,
//                                PendingIntent.FLAG_UPDATE_CURRENT
//                        );
//                // Set content intent;
//                mBuilder.setContentIntent(resultPendingIntent);
//            }
//        });
//

        this.btnNoti.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                sendOnChannel1(  );
            }
        });
        this.notificationManagerCompat = NotificationManagerCompat.from(this);
    }



    public void sendOnChannel1()  {
        String title = this.title.getText().toString();
        String message = this.content.getText().toString();
        Intent intent = new Intent(this, detail.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification = new NotificationCompat.Builder(this, NotificationApp.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle("Camera")
                .setContentText("Hay cung luu giu nhung khoanh khac dang nho")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        int notificationId = 1;
        this.notificationManagerCompat.notify(notificationId, notification);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == request_code_camera && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            picture.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

      if(requestCode == request_code_camera && grantResults.length>0&& grantResults[0]== PackageManager.PERMISSION_GRANTED){
          Intent intent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
          startActivityForResult(intent,request_code_camera);
      }else{
          Toast.makeText(this ,"Ban khong cho phep mo camera",Toast.LENGTH_LONG).show();
      }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}