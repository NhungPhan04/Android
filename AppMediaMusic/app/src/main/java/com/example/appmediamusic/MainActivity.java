package com.example.appmediamusic;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.Annotation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView txtTitle, txtTimeSong, txtTimeTotal;
    SeekBar skSong;
    ImageView btnPrev, btnPlay, btnStop, btnNext,image;
    ArrayList<Song> arraySong;
    int position = 0;
    MediaPlayer mediaPlayer;
    Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Anhxa();
        //Taoj funciton
        AddSong();
        KhoiTaoMediaPlayer();
        animation = AnimationUtils.loadAnimation(this,R.anim.disc_rotate);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if (position > arraySong.size() - 1) {
                    position = 0;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                KhoiTaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.stop);
                ThoiGianTong();
                CapNhat();

            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if (position < 0) {
                    position = arraySong.size() - 1;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                KhoiTaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.stop);
                ThoiGianTong();
                CapNhat();

            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();//giai phong
                btnPlay.setImageResource(R.drawable.play);
                KhoiTaoMediaPlayer();
                ThoiGianTong();


            }
        });
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    //neu dang phat thi pause
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.play);
                } else {
                    //dang ngunf thi phat
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.stop);
                }
                ThoiGianTong();
                CapNhat();
                image.startAnimation(animation);

            }

        });
        skSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                    mediaPlayer.seekTo(skSong.getProgress());
            }
        });
    }
    private  void CapNhat(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhdanggio = new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(dinhdanggio.format(mediaPlayer.getCurrentPosition()));
                //cap nhat sksong
                skSong.setProgress(mediaPlayer.getCurrentPosition());

                //Kiem tra thoi gian bai hat neu ket thuc thi next
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if (position > arraySong.size() - 1) {
                            position = 0;
                        }
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                        }
                        KhoiTaoMediaPlayer();
                        mediaPlayer.start();
                        btnPlay.setImageResource(R.drawable.stop);
                        ThoiGianTong();
                        CapNhat();
                    }
                });
                handler.postDelayed(this,500);
            }
        },-100);
    }
         private void ThoiGianTong () {
        SimpleDateFormat dinhdanggio = new SimpleDateFormat("mm:ss");
        txtTimeTotal.setText(dinhdanggio.format(mediaPlayer.getDuration()));
        //gan max cua sksong=mediaPlayer.getDuration()
             skSong.setMax(mediaPlayer.getDuration());

        }

        private void KhoiTaoMediaPlayer () {
            mediaPlayer = new MediaPlayer().create(MainActivity.this, arraySong.get(position).getFile());
            txtTitle.setText(arraySong.get(position).getTitile());
        }

        private void AddSong () {
            arraySong = new ArrayList<>();

            arraySong.add(new Song("Chung ta khong thuoc ve nhau", R.raw.chungtakhongthuocvenhau_sontungmtp));
            arraySong.add(new Song("Con thuong rau dang moc sau he", R.raw.conthuongraudangmocsauhe_nhuquynh));
            arraySong.add(new Song("Cuoi di", R.raw.cuoidi_2tchangc));
            arraySong.add(new Song("Gac lai au lo", R.raw.gaclaialo_dalabmiule));
            arraySong.add(new Song("Gia gia muon lay chong", R.raw.gaigiamuonlaychong_ninhduonglanngoc));
            arraySong.add(new Song("Hay trao cho anh", R.raw.haytraochoanh_sontungmtpsnoopdogg));
            arraySong.add(new Song("Lo say Bye la Bye", R.raw.losaybyelabye_lemesechangg));
            arraySong.add(new Song("Nang tho", R.raw.nangtho_hoangdung));
            arraySong.add(new Song("Nay em gioi", R.raw.nayemgioi_nguyenkhoalangld));
            arraySong.add(new Song("Chi la khong cung nhau", R.raw.chilakhongcungnhaulive_tangphuctruongthaonhi));
            arraySong.add(new Song("Sai Gon dau long qua", R.raw.saigondaulongqua_huakimtuyenhoangduyen));
            arraySong.add(new Song("Ta la cua nhau", R.raw.talacuanhau_dongnhiongcaothang));
            arraySong.add(new Song("Thang may em nho anh", R.raw.thangmayemnhoanh_haanhtuan));
            arraySong.add(new Song("Chung ta sau nay", R.raw.chungtasaunay_6929586));
        }
        private void Anhxa () {
            txtTimeSong = (TextView) findViewById(R.id.Timestart);
            txtTimeTotal = (TextView) findViewById(R.id.Timelast);
            txtTitle = (TextView) findViewById(R.id.textviewTitle);
            skSong = (SeekBar) findViewById(R.id.seekBarSong);

            btnNext = (ImageView) findViewById(R.id.next);
            btnPlay = (ImageView) findViewById(R.id.play);
            btnPrev = (ImageView) findViewById(R.id.prev);
            btnStop = (ImageView) findViewById(R.id.stop);
            image = (ImageView) findViewById(R.id.cdmusic);
        }
    }
