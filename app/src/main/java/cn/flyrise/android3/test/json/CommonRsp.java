/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-12-30 ����10:24:19
 */
package cn.flyrise.android3.test.json;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * �๦��������</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * �޸�ʱ�䣺</br>
 * �޸ı�ע��</br>
 */
public class CommonRsp<T extends CommonQuery> {
    private String namespace;
    
  
    private T query;



    public String getNamespace() {
        return namespace;
    }



    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }



    public T getQuery() {
        return query;
    }



    public void setQuery(T query) {
        this.query = query;
    }
    
    
//    public static Class getParameterizedType(Class c){ 
//        Type type=c.getGenericSuperclass();//���ر�ʾ�� Class ����ʾ��ʵ�壨�ࡢ�ӿڡ��������ͻ� void����ֱ�ӳ���� Type�� 
//        ParameterizedType parametType=null; 
//        if (type instanceof ParameterizedType) { 
//            parametType=(ParameterizedType)type;//ע��˴�type�������з��Ͳ��� 
//        }else{ 
//            try { 
//                throw new Exception("not find ParameterizedType!"); 
//            } catch (Exception e) { 
//                // TODO Auto-generated catch block 
//                e.printStackTrace(); 
//            } 
//        } 
//        Type[] types=parametType.getActualTypeArguments();//���ر�ʾ������ʵ�����Ͳ����� Type ��������� 
//        return (Class)types[0]; 
//    } 
    
   public static Type getType(){
       return new CommonRsp<CommonQuery>(){}.getClass().getGenericSuperclass();
   }
    
}
