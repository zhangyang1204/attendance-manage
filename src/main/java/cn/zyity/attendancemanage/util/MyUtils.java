package cn.zyity.attendancemanage.util;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyUtils {

    public static double subDouble(double v) {
        String s = String.valueOf(v);
        if (s.length() > 4) {
            s = s.substring(0, 4);
        }
        return Double.parseDouble(s);
    }

    public static Timestamp getStampByString(String date) {
        try {
            return new Timestamp(new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(date).getTime());
        } catch (ParseException e) {
           e.printStackTrace();
            return null;
        }
    }
    //格式化时间
    public static String getTimes(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return format.format(date);
    }


//    返回yyyy年MM月dd日 hh时mm分
    public static String getTimes(Timestamp ts) {

        return ts.toString().split("\\.")[0]
                .replaceFirst("-", "年")
                .replaceFirst("-", "月")
                .replaceFirst(" ", "日 ")
                .replaceFirst(":", "时")
                .replaceFirst(":", "分")
                .substring(0,18);
    }

    public static void replaceList(List from, List to) {
        if (from == null || to == null) {
            return;
        }

        from.clear();
        for (Object o :
                to) {
            from.add(o);
        }
        to = null;
    }


    public static ArrayList getNonNullList(ArrayList list) {
        if (list == null) {
            return new ArrayList();
        }
        return list;
    }


}
