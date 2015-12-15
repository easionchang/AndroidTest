package cn.flyrise.android3.test.media;

import android.app.Activity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.danikula.videocache.HttpProxyCacheServer;

import cn.flyrise.android3.test.MyApplication;
import cn.flyrise.android3.test.R;

/**
 * Created by Lenovo on 2015/12/3.
 */
public class VideoCacheActivity extends Activity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.media_play_video_view);
        HttpProxyCacheServer proxy = getProxy();
        VideoView videoView = (VideoView)findViewById(R.id.video_view);


        MediaController controller = new MediaController(this);

        String proxyUrl = proxy.getProxyUrl("http://www.androidbegin.com/tutorial/AndroidCommercial.3gp");
        videoView.setVideoPath(proxyUrl);
        videoView.setMediaController(controller);
        videoView.requestFocus();
        videoView.start();
    }

    private HttpProxyCacheServer getProxy() {
        // should return single instance of HttpProxyCacheServer shared for whole app.
        return MyApplication.getProxy(this);
    }

}
