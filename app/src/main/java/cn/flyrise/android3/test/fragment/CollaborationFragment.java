/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-3-4 下午3:53:19
 */
package cn.flyrise.android3.test.fragment;

import cn.flyrise.android3.test.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ScrollView;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class CollaborationFragment extends Activity{
    
    private View view;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_collaboration);
        
        final ScrollView scrollView = (ScrollView)findViewById(R.id.detail_sclv);
        
        
        final View contentview = findViewById(R.id.content_fragment);
        
        
       
        
        ImageButton iBtn = (ImageButton)findViewById(R.id.to_attachment);
        iBtn.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                scrollView.smoothScrollTo(0, contentview.getHeight());
            }
        });
        
        ImageButton iBtn2 = (ImageButton)findViewById(R.id.to_content);
        iBtn2.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                scrollView.smoothScrollTo(0, 0);
            }
        });
    }
    
    
    
   

}
