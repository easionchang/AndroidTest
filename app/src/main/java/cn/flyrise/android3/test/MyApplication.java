package cn.flyrise.android3.test;

import android.app.Application;
import android.content.Context;

import com.danikula.videocache.HttpProxyCacheServer;

/**
 * Created by Lenovo on 2015/12/15.
 */
public class MyApplication extends Application{

    private HttpProxyCacheServer proxy;


    public static HttpProxyCacheServer getProxy(Context context) {
        MyApplication app = (MyApplication) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer(this);
    }
}
