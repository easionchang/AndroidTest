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

        //Ϊ��Ƶ׼������
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
            public void onClick(View v) {//������Ƶ
                try {
                    if (!isInitOk) {
                        Toast.makeText(PlaybackFromNetActivity.this, "surface view ��δ��ʼ���ɹ������Ե�...", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    mediaPlayer1 = new MediaPlayer();
                    mediaPlayer1.setDataSource("http://10.250.201.243:8080/apple.mp4");
                    mediaPlayer1.setDisplay(holder);
                    //���粥����Ҫ���壬����Ҫ��׼���Ĺ���
                    mediaPlayer1.setOnPreparedListener(PlaybackFromNetActivity.this);
                    //����Ľ���
                    mediaPlayer1.setOnBufferingUpdateListener(PlaybackFromNetActivity.this);
                    //�첽��׼�����ţ�׼�������Ǻ�ʱ����������ʹ���첽����ֹ����������
                    //׼���ɹ����ص�OnPreparedListener.onPrepared
                    mediaPlayer1.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button btn6 = (Button)findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//ֹͣ����
                releaseMediaPlayer();
            }
        });
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.e("Video", "onPrepared...............................");
        //׼���ɹ���
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
        //TODO ׼���ò��ܲ��ŵ�
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
     * MediaPlayer����Դ���ǵ�����
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
