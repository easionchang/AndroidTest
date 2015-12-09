package cn.flyrise.android3.test.media;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import cn.flyrise.android3.test.R;

/**
 * Created by Lenovo on 2015/12/3.
 */
public class PlayByVideoViewActivity extends Activity {

    private VideoView videoView;

    private MediaController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_play_video_view);
        videoView = (VideoView)findViewById(R.id.video_view);

        controller = new MediaController(this);

        Uri uri = Uri.parse("http://10.250.201.243:8080/apple.mp4");
        videoView.setVideoURI(uri);
        videoView.setMediaController(controller);
        videoView.requestFocus();
        videoView.start();
        //触摸下videoView出现控制器
    }

}
