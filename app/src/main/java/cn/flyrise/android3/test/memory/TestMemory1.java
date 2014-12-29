/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2014-4-8 下午4:36:40
 */
package cn.flyrise.android3.test.memory;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class TestMemory1 extends Activity {
    int k = 1;
    int[] i;
    
    static Leaky leaky = null;
    
    class Leaky {
        void doSomething() {
            System.out.println("Wheee!!!");
        }
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if(leaky == null){
            leaky = new Leaky();
        }
        
        ActivityManager aManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        Log.e("Test", "aManager.getMemoryClass()=="+aManager.getMemoryClass());//96M
        Log.e("Test", "aManager.getLargeMemoryClass()=="+aManager.getLargeMemoryClass());//96M
        
        
        LinearLayout lly = new LinearLayout(this);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        lly.setLayoutParams(llp);
        
        Button btn = new Button(this);
        btn.setText("ADD");
        LinearLayout.LayoutParams llp2 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lly.addView(btn, llp2);
        
        this.setContentView(lly);
        //int[] i = new int[90*1024*1024];
        
        
        btn.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                i = new int[k*1024*1024];
                k++;
            }
        });
    }

}
