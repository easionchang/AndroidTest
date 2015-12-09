package cn.flyrise.android3.test.util;

/**
 * Created by Lenovo on 2015/12/9.
 */
public class StringUtils {

    public static String getHumanTime(int duration){
        StringBuffer timeStr = new StringBuffer();
        int hour = duration/3600;
        int minute = (duration%3600)/60;
        int second = duration % 60;

        if (hour != 0) {
            timeStr.append(hour).append(":");
        }


        if (minute < 10) {
            timeStr.append("0").append(minute).append(":");
        } else {
            timeStr.append(minute).append(":");
        }

        if (second < 10) {
            timeStr.append("0").append(second);
        } else {
            timeStr.append(second);
        }

        return timeStr.toString();
    }
}
