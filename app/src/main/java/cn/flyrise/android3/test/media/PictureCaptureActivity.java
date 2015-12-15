package cn.flyrise.android3.test.media;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import cn.flyrise.android3.test.R;

/**
 * Created by Lenovo on 2015/12/10.
 */
public class PictureCaptureActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_capture_video_image);
        Button btn = (Button)findViewById(R.id.capture_img);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1, null);
            }
        });

        Button btn2 = (Button)findViewById(R.id.capture_video);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(intent,1,null);
            }
        });
    }
}
