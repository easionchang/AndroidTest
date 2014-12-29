/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-12-29 ����03:58:02
 */
package cn.flyrise.android3.test.json;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * �๦��������</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * �޸�ʱ�䣺</br>
 * �޸ı�ע��</br>
 */
public class TestJsonActivity extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GsonBuilder builder = new GsonBuilder();   
        // ��ת��û�� @Expose ע����ֶ�   
        //builder.excludeFieldsWithoutExposeAnnotation();  
        Gson gson = builder.create();   
          
        //1������תstring  
        SubReply stu = new SubReply();  
        stu.setContent("ccccc");
        stu.setSendUser("zms");
        
        
        
        
        List<Attachment> attachments = new ArrayList<Attachment>();
        for(int i=0;i<2;i++){
            Attachment att = new Attachment();
            att.setName(""+i);
            att.setType("tttt");
            attachments.add(att);
        }
        
        stu.setAttachments(attachments);
        String stuStr = gson.toJson(stu);  
        //Log.e("Test", "=================>"+stuStr); 
        Reply user4 = gson.fromJson(stuStr, Reply.class); 
        //Log.e("Test", "=================>"+user4.getAttachments().get(0).getName());
        
        
        
        
        
        CommonRsp<LoginQuery> login = new CommonRsp<LoginQuery>();
        
        LoginQuery query = new LoginQuery();
        query.setUserId("111");
        query.setUserName("zms");
        login.setQuery(query);
        login.setNamespace("Login");
        
        Log.e("Test", "=================>"+gson.toJson(login)); ;
        
        Class x;
        TypeToken t =  new TypeToken<CommonRsp<LoginQuery>>(){
            
        };
        
        CommonRsp<LoginQuery> login2 = gson.fromJson(gson.toJson(login), login.getClass());
        Log.e("Test", "=================>"+gson.toJson(login)); ;
        
//        LoginQuery rsp2 = gson.fromJson(gson.toJson(login), CommonRsp.class);
//        Log.e("Test", "=================>"+rsp2.getNamespace()); 
    }

}
