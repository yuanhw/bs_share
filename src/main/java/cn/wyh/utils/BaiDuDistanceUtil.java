package cn.wyh.utils;

/**
 * Created by WYH on 2018/2/13.
 */
public class BaiDuDistanceUtil {
    /**
     * 计算距离
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     */
    public static String distanceOfTwoPoints(double lat1, double lng1, double lat2, double lng2) {
        double _lat1 = (Math.PI / 180) * lat1;
        double _lat2 = (Math.PI / 180) * lat2;
        double _lon1 = (Math.PI / 180) * lng1;
        double _lon2 = (Math.PI / 180) * lng2;
        /**
         *  地球半径，单位米
         */
        double R = 6371000;
        double d =  Math.acos(Math.sin(_lat1) * Math.sin(_lat2) + Math.cos(_lat1) * Math.cos(_lat2) * Math.cos(_lon2-_lon1)) * R;
        if (d > 999) {
            return (int)(d / 1000) + "km";
        }
        return (int)d + "m";
    }

    /**
     *  距离格式化
     * @param d
     * @return
     */
    public static String distanceFormat(double d) {
        if (d > 999) {
            return (int)(d / 1000) + "km";
        }
        return (int)d + "m";
    }

    public static void main(String[] args) {
        System.out.println(distanceOfTwoPoints(34.502357980, 109.483932700, 22.520579000, 113.395469000));
    }
}
