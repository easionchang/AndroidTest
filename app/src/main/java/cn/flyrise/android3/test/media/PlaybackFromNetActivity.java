package cn.flyrise.android3.test.media;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.ref.WeakReference;

import cn.flyrise.android3.test.R;
import cn.flyrise.android3.test.util.StringUtils;

/**
 * Created by Lenovo on 2015/12/3.
 */
public class PlaybackFromNetActivity extends Activity implements SurfaceHolder.Callback,
        MediaPlayer.OnPreparedListener,MediaPlayer.OnBufferingUpdateListener,SeekBar.OnSeekBarChangeListener {
    private boolean isInitOk = false;
    private SurfaceHolder holder;
    MediaPlayer mediaPlayer1;
    private Button startBtn;
    private Button stopBtn;
    private SeekBar progress;
    private TextView durationText;
    private TextView consumeText;

    private int mediaDuration;
    private MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_playback_video);
        startBtn = (Button)findViewById(R.id.start);
        stopBtn = (Button)findViewById(R.id.stop);
        progress = (SeekBar)findViewById(R.id.progress);
        durationText = (TextView)findViewById(R.id.duration);
        consumeText = (TextView)findViewById(R.id.consume);

        //为视频准备界面
        SurfaceView surfaceView = (SurfaceView)findViewById(R.id.surface_view);
        holder = surfaceView.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        initPlayMp4FromNet();

        myHandler = new MyHandler(this);
        progress.setOnSeekBarChangeListener(this);
    }

    private void initPlayMp4FromNet(){
        startBtn.setOnClickListener(new View.OnClickListener() {
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

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//停止播放
                releaseMediaPlayer();
            }
        });
    }

    private void play(){
        if(mediaPlayer1 != null){
            mediaPlayer1.start();
            mediaDuration = mediaPlayer1.getDuration();
            durationText.setText(StringUtils.getHumanTime(mediaDuration/1000));
            myHandler.sendEmptyMessageDelayed(0, 1000);
            progress.setProgress(0);
        }
    }

    private void changeSeekBarPosition(int position){
        progress.setProgress(position*100/mediaDuration);
        consumeText.setText(StringUtils.getHumanTime(position/1000));
    }

    private void changePlayPosition(int percent){
        Log.e("Video","percent*mediaDuration/10="+percent*mediaDuration/10);
        mediaPlayer1.seekTo(percent*mediaDuration/100);
    }

    static class MyHandler extends Handler{
        private WeakReference<PlaybackFromNetActivity> weakReference;
        public MyHandler(PlaybackFromNetActivity activity){
            weakReference = new WeakReference<>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            PlaybackFromNetActivity activity = weakReference.get();
            if(activity != null && activity.mediaPlayer1 != null){
                //Log.e("Video", "activity.mediaPlayer1.getCurrentPosition()="+activity.mediaPlayer1.getCurrentPosition());
                activity.myHandler.sendEmptyMessageDelayed(0,1000);
                activity.changeSeekBarPosition(activity.mediaPlayer1.getCurrentPosition());
            }else{
                Log.e("Video","weakReference.get()="+weakReference.get());
            }
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.e("Video", "onPrepared...............................");
        //准备成功了
        play();
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        Log.e("Video", "onBufferingUpdate percent==" + percent + "...........................");
        progress.setSecondaryProgress(percent);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //TODO 准备好才能播放的
        Log.e("Video", "surfaceCreated...............................");
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
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.e("Video","-----------------fromUser="+fromUser);
        if(fromUser){
            changePlayPosition(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

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
