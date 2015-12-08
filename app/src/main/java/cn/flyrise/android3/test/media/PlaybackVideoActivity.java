package cn.flyrise.android3.test.media;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import cn.flyrise.android3.test.R;

/**
 * Created by Lenovo on 2015/12/3.
 */
public class PlaybackVideoActivity extends Activity implements SurfaceHolder.Callback,
        MediaPlayer.OnPreparedListener {
    private SurfaceHolder holder;

    MediaPlayer mediaPlayer1;
    MediaPlayer mediaPlayer2;
    MediaPlayer mediaPlayer3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_playback_video);

        //为视频准备界面
        SurfaceView surfaceView = (SurfaceView)findViewById(R.id.surface_view);
        holder = surfaceView.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);



        initPlayMp3();
        initPlayMp4FromLocal();
        initPlayMp4FromNet();

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.e("Video","onPrepared...............................");
        if(mediaPlayer3 != null){
            mediaPlayer3.start();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //TODO 准备好才能播放的
        Log.e("Video","surfaceCreated...............................");
//        mediaPlayer2 = MediaPlayer.create(PlaybackVideoActivity.this, R.raw.apple);
//        mediaPlayer2.setDisplay(holder);
//        mediaPlayer2.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.e("Video", "surfaceChanged...............................");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e("Video","surfaceDestroyed...............................");
    }

    private void initPlayMp4FromNet(){
        Button btn5 = (Button)findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//播放视频
                try {
                    mediaPlayer3 = new MediaPlayer();
                    mediaPlayer3.setDataSource("http://10.250.201.243:8080/apple.mp4");
                    mediaPlayer3.setDisplay(holder);
                    mediaPlayer3.setOnPreparedListener(PlaybackVideoActivity.this);
                    mediaPlayer3.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button btn6 = (Button)findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//停止播放
                if(mediaPlayer3 != null){
                    mediaPlayer3.release();
                    mediaPlayer3 = null;
                }
            }
        });
    }

    private void initPlayMp4FromLocal(){
        Button btn3 = (Button)findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//播放视频
                mediaPlayer2 = MediaPlayer.create(PlaybackVideoActivity.this, R.raw.apple);
                mediaPlayer2.setDisplay(holder);
                mediaPlayer2.start();
            }
        });

        Button btn4 = (Button)findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//停止播放
                if(mediaPlayer2 != null){
                    mediaPlayer2.release();
                    mediaPlayer2 = null;
                }
            }
        });
    }

    private void initPlayMp3(){
        Button btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//播放音频
                mediaPlayer1 = MediaPlayer.create(PlaybackVideoActivity.this, R.raw.love);
                mediaPlayer1.start();
            }
        });

        Button btn2 = (Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//停止播放
                if(mediaPlayer1 != null){
                    mediaPlayer1.release();
                    mediaPlayer1 = null;
                }
            }
        });
    }
}
