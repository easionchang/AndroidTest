
package cn.flyrise.android3.test.actionbar;

import cn.flyrise.android3.test.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/**
 * �๦��������</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * �޸�ʱ�䣺</br>
 * �޸ı�ע��</br>
 */
public class ActionBarProvideActivity extends Activity{
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_provide, menu);
        return true;
    }
    
    // public static classһ��Ҫ��̬�ģ�����
    public static class MyActionbarProvide extends ActionProvider{
        private static final Intent sSettingsIntent = new Intent(Settings.ACTION_SETTINGS);
        private Context mContext;
        
        /**
         * @param context
         */
        public MyActionbarProvide(Context context) {
            super(context);
            this.mContext = context;
        }

        @Override
        public View onCreateActionView() {
            View view = LayoutInflater.from(mContext).inflate(R.layout.action_bar_settings_action_provider, null);
            ImageButton btn = (ImageButton)view.findViewById(R.id.button);
            btn.setOnClickListener(new OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    mContext.startActivity(sSettingsIntent);
                    
                }
            });
            return view;
        }
        
        
        /**
         * ��Item������������˵�ʱ����ఴť�У������˵�������
         */
        public boolean onPerformDefaultAction() {
            mContext.startActivity(sSettingsIntent);
            return true;
        }
        
    }

}
