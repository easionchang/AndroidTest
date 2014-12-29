/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-3-8 上午10:32:02
 */

package cn.flyrise.android3.test.https;

import cn.flyrise.android3.test.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.io.InputStream;
import java.security.KeyStore;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class HttpsActivity extends Activity {
    /** Called when the activity is first created. */

    private Button testButton;
    private String httpsUrl = "https://10.62.5.225:8443/servlet/mobileServlet";
    HttpClient hc = new DefaultHttpClient();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.https);

        testButton = (Button)findViewById(R.id.btn);
        testButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                new MyThread().start();
            }
        });
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            try {
                initKey();
            } catch (Exception e) {
                Log.e("Test", e.getMessage(), e);
            }

            try {
                String result = getData(httpsUrl);
                Log.e("Test", "result=" + result);
            } catch (Exception e) {
                Log.e("Test", e.getMessage(), e);
            }

        }
    }

    private void initKey() throws Exception {
        KeyStore trustStore = KeyStore.getInstance("BKS");
        trustStore.load(
                getBaseContext().getResources().openRawResource(
                        R.raw.server_trust), "password".toCharArray());
        SSLSocketFactory socketFactory = new SSLSocketFactory(trustStore);
        Scheme sch = new Scheme("https", socketFactory, 8443);
        hc.getConnectionManager().getSchemeRegistry().register(sch);

    }

    private String getData(String url) throws Exception {
        HttpUriRequest hr = new HttpGet(url);
        HttpResponse hres = hc.execute(hr);
        HttpEntity he = hres.getEntity();
        InputStream is = he.getContent();
        StringBuffer sb = new StringBuffer();
        byte[] bytes = new byte[1024];
        for (int len = 0; (len = is.read(bytes)) != -1;) {
            sb.append(new String(bytes, 0, len, "utf-8"));
        }
        return sb.toString();
    }
}
