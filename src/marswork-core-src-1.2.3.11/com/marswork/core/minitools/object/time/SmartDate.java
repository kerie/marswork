/**
 * 
 */
package com.marswork.core.minitools.object.time;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.marswork.core.exceptions.object.datetime.NotDateTimeException;
import com.marswork.core.minitools.object.BasicUtils;

/**
 * <p>
 * 美制时间字符串转换器
 * <p>
 * 将美制时间字符串转换成时间对象或者时间字符串<br>
 * 例如 字符串 Wed Sep 09 14:00:00 CDT 1988。
 * 
 * @author MarsDJ
 * @since 2011-11-22
 * @version 1.0
 */
public class SmartDate {

	private enum Months {
		Jan("01"), Feb("02"), Mar("03"), Apr("04"), May("05"), Jun("06"), Jul("07"), Aug("08"), Sep(
				"09"), Oct("10"), Nov("11"), Dec("12");

		private String displayName;

		private Months(String displayName) {
			this.displayName = displayName;
		}

		public String getDisplayName() {
			return displayName;
		}
	}

	/**
	 * 美制时间字符串转换为Date对象
	 * 
	 * @param stringTime
	 *            时间字符串，满足这样的格式 Wed Sep 09 14:00:00 CDT 1988。<br>
	 *            如果字符串为空，将返回null值<br>
	 *            如果不满足或者转换失败，将抛出非时间格式例外
	 * @return 转换后的时间对象
	 * @throws NotDateTimeException
	 *             非时间格式例外
	 */
	public static Date parseDate(String stringTime) throws NotDateTimeException {
		if (BasicUtils.isTrimBlank(stringTime)) {
			return null;
		} else {
			try {
				// 先尝试正常的中文格式时间转换
				SimpleDateFormat fomatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					return fomatter.parse(AttachDateTime.attachDateTime(stringTime));
				} catch (Exception e) {
				}
				// 中文格式时间转换失败，尝试将字符串按美制时间格式分析
				return fomatter.parse(AttachDateTime.attachDateTime(smartConvert(stringTime)));
			} catch (Exception e) {
			}
		}
		throw new NotDateTimeException(stringTime);
	}

	public static String smartConvert(String stringTime) throws NotDateTimeException {
		String year = "";
		String month = "";
		String day = "";
		String time = "";
		String[] units = stringTime.split(" ");
		for (String unit : units) {
			if (unit.matches("\\d{2}:\\d{2}:\\d{2}")) {
				time = unit;
				continue;
			}
			try {
				month = Months.valueOf(unit).getDisplayName();
				continue;
			} catch (Exception e) {
			}
			if (unit.matches("\\d{4}") && Integer.parseInt(unit) >= 1970
					&& Integer.parseInt(unit) <= 2100 && BasicUtils.isTrimBlank(year)) {
				year = unit;
				continue;
			}
			if (unit.matches("\\d{2}") && Integer.parseInt(unit) >= 1
					&& Integer.parseInt(unit) <= 31) {
				day = unit;
				continue;
			}
		}
		if (BasicUtils.isTrimBlank(year) || BasicUtils.isTrimBlank(month)
				|| BasicUtils.isTrimBlank(day) || BasicUtils.isTrimBlank(time)) {
			throw new NotDateTimeException(stringTime);
		}
		return year + "-" + month + "-" + day + " " + time;
	}
}
