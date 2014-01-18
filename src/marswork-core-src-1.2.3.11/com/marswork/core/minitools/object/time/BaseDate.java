package com.marswork.core.minitools.object.time;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import com.marswork.core.exceptions.object.datetime.NotDateTimeException;
import com.marswork.core.minitools.object.BaseString;

/**
 * <P>
 * 日期类
 * <P>
 * 为应用程序提供相关静态方法
 * 
 * @author MarsDJ
 * @since 2010-12-31
 * @version 1.0
 */
public class BaseDate {
	int year, month, day;

	Date dt;

	private BaseDate() {
	}

	/**
	 * 得到当前日期
	 * 
	 * @return 返回当前日期
	 * 
	 */
	public static String getDate() {

		SimpleDateFormat d = new SimpleDateFormat();
		d.applyPattern("yyyy-MM-dd");
		Date nowdate = new Date();
		return String.valueOf(d.format(nowdate));
	}

	/**
	 * 得到当前日期时间
	 * 
	 * @return 返回当前日期时间
	 */
	public static String getDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return sdf.format(Calendar.getInstance().getTime());
	}

	/**
	 * 根据时区，得到当前时间
	 * 
	 * @param timeZone
	 *            时区
	 * @return 返回当前时间
	 */
	public static String getDateTime(String timeZone) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
		return sdf.format(Calendar.getInstance().getTime());
	}

	/**
	 * 得到当前日期时间，Date形式
	 * 
	 * @return
	 */
	public static Timestamp getDateTimeTimestamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return Timestamp.valueOf(sdf.format(Calendar.getInstance().getTime()));
	}

	/**
	 * 根据时区，得到当前日期时间，Date形式
	 * 
	 * @param timeZone
	 *            时区
	 * @return
	 */
	public static Timestamp getDateTimeTimestamp(String timeZone) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
		return Timestamp.valueOf(sdf.format(Calendar.getInstance().getTime()));
	}

	/**
	 * 得到当前日期(汉字)
	 * 
	 * @return 得到当前日期 (2009年3月27日)
	 * 
	 */
	public static String getCNDate() {
		return BaseDate.getYear() + "年" + BaseDate.getMonth() + "月" + BaseDate.getDay() + "日";
	}

	/**
	 * 得到当前年份
	 * 
	 * @return 返回当前年份
	 * 
	 */
	public static int getYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 得到当前月份
	 * 
	 * @return 返回当前月份
	 * 
	 */
	public static int getMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 得到当前周
	 * 
	 * @return 返回当前周
	 * 
	 */
	public static int getWeek() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取给定时间所在周的第一天(Sunday)的日期
	 * 
	 * @param String
	 *            给定时间
	 * @return String 所在周的第一天日期
	 */
	public static Date getSunday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 得到当天是这周的第几天
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		// 减去dayOfWeek,得到第一天的日期，因为Calendar用０－６代表一周七天，所以要减一
		calendar.add(Calendar.DAY_OF_WEEK, -(dayOfWeek - 1));
		return calendar.getTime();
	}

	/**
	 * 获取给定时间所在周的最后一天(Saturday)的日期
	 * 
	 * @param String
	 *            给定时间
	 * @return String 所在周的最后一天日期
	 */
	public static Date getSaturday(Date date) {
		date = getSunday(date);
		return relativeDate(date, 6);
	}

	/**
	 * 得到当前周几（数字）
	 * 
	 * @return 返回当前周几
	 * 
	 */
	public static int getWeekDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 得到当前星期几(汉字)
	 * 
	 * @return 返回当前星期几
	 * 
	 */
	public static String getCNWeek() {
		String[] weeks = { "0", "日", "一", "二", "三", "四", "五", "六" };
		Calendar calendar = Calendar.getInstance();
		return "星期" + String.valueOf(weeks[calendar.get(Calendar.DAY_OF_WEEK)]);
	}

	/**
	 * 得到当前天数
	 * 
	 * @return 返回当前天数
	 * 
	 */
	public static int getDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 
	 * 一天中的小时（24小时制）
	 * 
	 * @return 返回一天中的小时
	 * 
	 */
	public static int getHour() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.HOUR_OF_DAY);

	}

	/**
	 * 
	 * 一小时中的分钟
	 * 
	 * @return 返回一小时中的分钟
	 * 
	 */
	public static int getMinute() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 
	 * 一分钟中的秒
	 * 
	 * @return 返回一分钟中的秒
	 * 
	 */
	public static int getSecond() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 
	 * 得到指定日期 相差天数的新日期
	 * 
	 * @param int 相差的天数
	 * @return String 新日期
	 * 
	 */

	public static Date relativeDate(Date sourceDate, int days) {

		Calendar now = Calendar.getInstance();
		now.setTime(sourceDate);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + days);
		return now.getTime();
	}

	/**
	 * 
	 * 得到当前日期 相差天数的新日期
	 * 
	 * @param int 相差的天数
	 * @return String 新日期
	 * 
	 */

	public static String relativeDate(int Days) {

		SimpleDateFormat d = new SimpleDateFormat();
		d.applyPattern("yyyy-MM-dd");

		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.add(GregorianCalendar.DATE, Days);

		return d.format(cal.getTime());
	}

	/**
	 * 检查日期的相关值合法性.(三个之中有空值则不校验)
	 * 
	 * @param (String)year 年 4位
	 * @param (String)month 月 2位
	 * @param (String)day 日 2位.
	 * 
	 * @return (boolean) true 合法 false 不合法.
	 * 
	 */
	public static final boolean isDate(String year, String month, String day) {
		if (year.trim().length() == 0 || month.trim().length() == 0 || day.trim().length() == 0)
			return true;
		int y, m, d;
		try {
			y = Integer.parseInt(year);
			m = Integer.parseInt(month);
			d = Integer.parseInt(day);
		} catch (NumberFormatException e) {
			return false;
		}
		return isCalender(y, m, d);
	}

	/**
	 * 检查时间的相关值合法性.
	 * 
	 * @param (String)hour (String)minut (String)sec.
	 * @return (boolean) true 合法 false 不合法.
	 * 
	 */

	public static final boolean isTime(String hour, String minute, String secs) {
		int h, m, s;
		try {
			h = Integer.parseInt(hour);
			m = Integer.parseInt(minute);
			s = Integer.parseInt(secs);
		} catch (NumberFormatException e) {
			return false;
		}
		if (h < 0 || h > 23)
			return false;
		if (m < 0 || m > 59)
			return false;
		if (s < 0 || s > 59)
			return false;
		return true;
	}

	/**
	 * 检查年月日是否合法
	 * 
	 * @param y
	 *            年份
	 * @param m
	 *            月份
	 * @param d
	 *            日期
	 * @return 是否合法
	 */
	private static boolean isCalender(int y, int m, int d) {
		// 时间值越界；
		if ((y < 1900) || (y > 4712)) {
			return false;
		}
		if ((m < 1) || (m > 12)) {
			return false;
		}
		if ((d < 1) || (d > 31)) {
			return false;
		}
		// 判断天是否正确
		switch (m) {
		case 2:
			if (isLeapYear(y)) {
				if (d > 29)
					return false;
			} else {
				if (d > 28)
					return false;
			}
			;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			if (d > 30)
				return false;
			break;
		default:
			return true;
		}
		return true;
	}

	/**
	 * 判断给定的年份是否为闰年
	 * 
	 * @param 年份
	 * @return 闰年 返回 true 否则返回 false
	 * 
	 */
	private static boolean isLeapYear(int y) {
		if (((y % 4 == 0) && (y % 100 != 0)) || (y % 400 == 0))
			return true; // 是闰年;
		else
			return false;
	}

	/**
	 * 获取指定时间的相关时间
	 * 
	 * @param d
	 *            指定时间
	 * @param day
	 *            偏移量
	 * @return 结果时间
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	/**
	 * 将时间格式化为字符串<br>
	 * 格式化到秒，形式是yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            需要格式化的日期
	 * @return 格式化后的字符串
	 */
	public static String formatDateToSeconds(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/**
	 * 将时间格式化为字符串<br>
	 * 格式化到分钟，形式是yyyy-MM-dd HH:mm
	 * 
	 * @param date
	 *            需要格式化的日期
	 * @return 格式化后的字符串
	 */
	public static String formatDateToMinites(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
	}

	/**
	 * 将时间格式化为字符串<br>
	 * 格式化到日期，形式是yyyy-MM-dd
	 * 
	 * @param date
	 *            需要格式化的日期
	 * @return 格式化后的字符串
	 */
	public static String formatDateToDays(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	/**
	 * 获取指定日期当月的第一天
	 * 
	 * @param date
	 *            指定日期
	 * @return 当月的第一天日期
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	/**
	 * 获取指定日期当月的最后一天
	 * 
	 * @param date
	 *            指定日期
	 * @return 当月的最后一天日期
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, 1);
		c.roll(Calendar.DATE, -1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}

	public static Date parseDate(Object stringTime) throws NotDateTimeException {
		return SmartDate.parseDate(BaseString.cleanUp(stringTime));
	}
}