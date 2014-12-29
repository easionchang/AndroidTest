/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-12-30 上午10:24:19
 */
package cn.flyrise.android3.test.json;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
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
//        Type type=c.getGenericSuperclass();//返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。 
//        ParameterizedType parametType=null; 
//        if (type instanceof ParameterizedType) { 
//            parametType=(ParameterizedType)type;//注意此处type必须是有泛型参数 
//        }else{ 
//            try { 
//                throw new Exception("not find ParameterizedType!"); 
//            } catch (Exception e) { 
//                // TODO Auto-generated catch block 
//                e.printStackTrace(); 
//            } 
//        } 
//        Type[] types=parametType.getActualTypeArguments();//返回表示此类型实际类型参数的 Type 对象的数组 
//        return (Class)types[0]; 
//    } 
    
   public static Type getType(){
       return new CommonRsp<CommonQuery>(){}.getClass().getGenericSuperclass();
   }
    
}
