package cn.flyrise.android3.test.media;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import cn.flyrise.android3.test.R;

/**
 * Created by Lenovo on 2015/12/3.
 */
public class PlaybackFromNetActivity extends Activity implements SurfaceHolder.Callback,
        MediaPlayer.OnPreparedListener,MediaPlayer.OnBufferingUpdateListener {
    private boolean isInitOk = false;
    private SurfaceHolder holder;
    MediaPlayer mediaPlayer1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_playback_video);

        //为视频准备界面
        SurfaceView surfaceView = (SurfaceView)findViewById(R.id.surface_view);
        holder = surfaceView.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        initPlayMp4FromNet();
    }

    private void initPlayMp4FromNet(){
        Button btn5 = (Button)findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//播放视频
                try {
                    if (!isInitOk) {
                        Toast.makeText(PlaybackFromNetActivity.this, "surface view 还未初始化成功，请稍等...", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    mediaPlayer1 = new MediaPlayer();
                    mediaPlayer1.setDataSource("http://10.250.201.243:8080/apple.mp4");
                    mediaPlayer1.setDisplay(holder);
                    //网络播放需要缓冲，所有要有准备的过程
                    mediaPlayer1.setOnPreparedListener(PlaybackFromNetActivity.this);
                    //缓冲的进度
                    mediaPlayer1.setOnBufferingUpdateListener(PlaybackFromNetActivity.this);
                    //异步的准备播放，准备播放是耗时操作，所以使用异步，防止阻塞主进程
                    //准备成功后会回调OnPreparedListener.onPrepared
                    mediaPlayer1.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button btn6 = (Button)findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//停止播放
                releaseMediaPlayer();
            }
        });
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.e("Video", "onPrepared...............................");
        //准备成功了
        if(mediaPlayer1 != null){
            mediaPlayer1.start();
        }
    }


    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        Log.e("Video", "onBufferingUpdate percent==" + percent + "...........................");
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //TODO 准备好才能播放的
        Log.e("Video","surfaceCreated...............................");
        isInitOk = true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.e("Video", "surfaceChanged...............................");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e("Video", "surfaceDestroyed...............................");
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaPlayer();
        doCleanUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
        doCleanUp();
    }

    /**
     * MediaPlayer耗资源，记得销毁
     */
    private void releaseMediaPlayer() {
        if (mediaPlayer1 != null) {
            mediaPlayer1.release();
            mediaPlayer1 = null;
        }
    }

    private void doCleanUp(){
        isInitOk = false;
    }
}
