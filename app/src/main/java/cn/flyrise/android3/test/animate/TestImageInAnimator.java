package cn.flyrise.android3.test.animate;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.flyrise.android3.test.R;
import cn.flyrise.android3.test.util.DisplayUtil;

/**
 * Created by zms on 2014-12-23.
 */
public class TestImageInAnimator extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimpleAdapter myAdapter = new SimpleAdapter(this,getData(),R.layout.list_item_2,
                new String[]{"tip","img"},new int[]{R.id.key,R.id.re_touch_img});
        this.setListAdapter(myAdapter);

        this.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int location[] = new int[4];
                ImageView imgView = (ImageView)view.findViewById(R.id.re_touch_img);
                //相对屏幕的坐标，而不是父View；
                imgView.getLocationOnScreen(location);
                location[2] = imgView.getWidth();
                location[3] = imgView.getHeight();
                Log.e("Test","x="+location[0]+" y="+location[1]+" w="+location[2]+" h="+location[3]);
                Intent newIntent = new Intent(TestImageInAnimator.this,ImageInAnimatorActivity.class);
                newIntent.putExtra("XYWH",location);
                startActivity(newIntent);
            }
        });
    }

    private List<Map<String,Object>> getData(){
        List<Map<String,Object>> data = new ArrayList<Map<String, Object>>();
        for (int i=0;i<20;i++){
            Map<String,Object> dataMap = new HashMap<String, Object>();
            dataMap.put("tip","Hello"+i);
            dataMap.put("img", R.drawable.beach);
            data.add(dataMap);
        }

        return data;
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        DisplayUtil.init(this);
    }
}
