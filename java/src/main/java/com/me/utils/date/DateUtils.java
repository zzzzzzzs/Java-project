package com.me.utils.date;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Week;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.WeekFields;
import java.util.Calendar;


/**
 * @author zs
 *@date 2021/8/9.

 */
public class DateUtils {


    /**
     * 获取两个时间的相差天数
     *
     * @param begin 开始时间
     * @param end   结束时间
     * @return Long
     */
    public static Long getBetweenDays(Temporal begin, Temporal end) {
        return Math.abs(ChronoUnit.DAYS.between(begin, end));
    }




    /**
     * 计算两日期相差的周数 （日历逻辑）
     *
     * @param begin 开始时间
     * @param end   结束时间
     * @return Integer
     */
    public static Long getBetweenWeeks(Temporal begin, Temporal end) {
        if (end.getLong(ChronoField.EPOCH_DAY) - begin.getLong(ChronoField.EPOCH_DAY) < 0) {
            Temporal temp = begin;
            begin = end;
            end = temp;
        }
        int beginWeekDay = begin.get(ChronoField.DAY_OF_WEEK);
        long daysBetween = getBetweenDays(begin, end);
        long weeksBetween = daysBetween / 7;
        int offset = (daysBetween % 7 + beginWeekDay) > 7 ? 1 : 0;
        return offset + weeksBetween;
    }

    /**
     * 返回参数以年为单位的自然周
     *
     * @param date 格式：yyyy-MM-dd
     * @return
     */
    public static int weekOfYear(String date) {
        CharSequence c_seq = date;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate l_da = LocalDate.parse(c_seq);
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 4);
        return l_da.get(weekFields.weekOfWeekBasedYear());
    }

    /**
     * @author: zs
     * @Description
     * @Date 2021/8/10
     * @Param date 格式：yyyy-MM-dd
     * @return 返回参数的自然月
     **/
    public static int monthOfYear(String date) {
        return DateUtil.month(DateUtil.parse(date)) + 1;
    }

    /**
     * @author: zs
     * @Description 按照自然周计算，不满一周也算一周
     * @Date 2021/8/10
     * @Param start
     * @param: end
     * @return 返回差多少周
     **/
    public static Integer betweenWeek(String start, String end) {
        DateTime startParse = DateUtil.parseDate(start);
        DateTime endParse = DateUtil.parseDate(end);
        // TODO 换了hutool版本，还没来得及改
        // 此函数的结束时间必须是周日才算1周
//        int weekCount = DateUtil.weekCount(startParse, endParse);
        // 如果不满一周，也算一周
//        if (weekCount == 0) {
//            return 1;
//        }
//        // 如果结束时间不是周日，也算一周
//        if (weekCount != 0 && DateUtil.dayOfWeekEnum(endParse) != Week.SUNDAY) {
//            ++weekCount;
//        }
//        return weekCount;
        return 0;
    }
    /**
     * http://www.cnblogs.com/0201zcr/p/5000977.html
     * end 比 start 多的天数
     *
     * @param start
     * @param end
     * @return
     */
    public static int calcDayOffset(String start, String end) {

        DateTime date1 = DateUtil.parseDate(start);
        DateTime date2 = DateUtil.parseDate(end);

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {  //同一年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {  //闰年
                    timeDistance += 366;
                } else {  //不是闰年

                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else { //不同年
            return day2 - day1;
        }
    }

    /** https://blog.csdn.net/bfbx5173/article/details/53420750
     * end 比 start 多的周数
     * @param start
     * @param end
     * @return
     */
    public static int calcWeekOffset(String start, String end) {

        DateTime startTime = DateUtil.parseDate(start);
        DateTime endTime = DateUtil.parseDate(end);

        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        dayOfWeek = dayOfWeek - 1;
        if (dayOfWeek == 0) dayOfWeek = 7;

        int dayOffset = calcDayOffset(start, end);

        int weekOffset = dayOffset / 7;
        int a;
        if (dayOffset > 0) {
            a = (dayOffset % 7 + dayOfWeek > 7) ? 1 : 0;
        } else {
            a = (dayOfWeek + dayOffset % 7 < 1) ? -1 : 0;
        }
        weekOffset = weekOffset + a;
        return weekOffset;
    }

    /**
     * @author: zs
     * @Description 周一返回1
     * @Date 2021/8/10
     * @Param day
     * @return 返回星期
     **/
    public static int getWeek(String day){
        int week = DateUtil.dayOfWeek(DateUtil.parse(day));
        return ((week - 1) == 0) ? 7 : (week - 1);
    }

    public static void main(String[] args) {
        int week = calcDayOffset("2021-07-01", "2021-07-31");
        System.out.println(week);
    }
}
