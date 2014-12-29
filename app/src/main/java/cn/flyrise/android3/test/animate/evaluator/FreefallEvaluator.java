/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-10-30 下午2:52:51
 */
package cn.flyrise.android3.test.animate.evaluator;

import cn.flyrise.android3.test.animate.FreefallAnimator;

import android.animation.TypeEvaluator;
import android.util.Log;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class FreefallEvaluator implements TypeEvaluator<Float>{

    @Override
    public Float evaluate(float fraction, Float startValue, Float endValue) {
        float value =  (Float)startValue + (float)(FreefallAnimator.G2*Math.pow(fraction*getDuration(endValue-startValue), 2));
        Log.e("Test", "fraction="+fraction+" value="+ value);
        return value;
    }
    
    private float getDuration(float s){
        return (float)(Math.sqrt(2*s/FreefallAnimator.G));
    }
    
    public static int getDuration(float startValue,float endValue){
        return (int)(Math.sqrt(2*(endValue - startValue)/FreefallAnimator.G) * 1000);
    }

}
