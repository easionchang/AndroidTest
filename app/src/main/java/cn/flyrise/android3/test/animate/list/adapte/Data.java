/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-11-7 ����5:22:23
 */
package cn.flyrise.android3.test.animate.list.adapte;

/**
 * �๦��������</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * �޸�ʱ�䣺</br>
 * �޸ı�ע��</br>
 */
public class Data{
    long id;
    String info;
    
    public Data(){
        
    }
    
    public Data(long id){
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    @Override
    public boolean equals(Object o) {
       if(id == ((Data)o).getId()){
           return true;
       }else{
           return false;
       }
    }
    
     
 }
