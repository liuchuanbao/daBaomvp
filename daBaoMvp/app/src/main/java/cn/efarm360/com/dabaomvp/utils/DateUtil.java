package cn.efarm360.com.dabaomvp.utils;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DateUtil
{
	/**
	 * 本日日期 YYYY-MM-DD
	 * 
	 * @return
	 */
	public static void main(String args[]){
		System.out.println(DateUtil.isMonthEnd("2010-12-31"));
		System.out.println(DateUtil.isMonthEnd("2010-12-30"));
		System.out.println(DateUtil.isMonthEnd("2010-11-30"));
		
		System.out.println(DateUtil.getDate(DateUtil.parseDate("2010-10-33"),"MM月dd日"));

		System.out.println(getToday());
		System.out.println(DateUtil.stringTocalendar(getToday()) + " " + DateUtil.stringTotime(getTime()));
	}
	public static String getToday()
	{
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMdd");
		java.util.Date date = new java.util.Date();
		return sFormat.format(date);
	}
	public static String getTime()
	{
		SimpleDateFormat sFormat = new SimpleDateFormat("HHmmss");
		java.util.Date time = new java.util.Date();
		return sFormat.format(time);
	}
	/**
	 * 将字符串类型的日期转换成 YYYY-MM-dd
	 *
	 * @return
	 */

	public static String stringTocalendar(String s1)
	{
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMdd");
		try
		{
			Date odate = sFormat.parse(s1);
			sFormat = new SimpleDateFormat("yyyy-MM-dd");
			s1 = sFormat.format(odate);
		} catch (Exception e)
		{
			return "";
		}
		return s1;
	}

	/**
	 * 将字符串类型的时间转换成 hh:mm:ss
	 *
	 * @return
	 */

	public static String stringTotime(String s1)
	{
		SimpleDateFormat sFormat = new SimpleDateFormat("HHmmss");
		try
		{
			Date odate = sFormat.parse(s1);
			sFormat = new SimpleDateFormat("HH:mm:ss");
			s1 = sFormat.format(odate);
		} catch (Exception e)
		{
			return "";
		}
		return s1;
	}

	/**
	 * 自定义格式输出日期 yyyyMMdd
	 */
	public static String getToday(String sFormatStr)
	{
		SimpleDateFormat sFormat = new SimpleDateFormat(sFormatStr);
		java.util.Date date = new java.util.Date();
		return sFormat.format(date);
	}

	public static String getToday4LocaleDate()
	{
		Date date=new Date();
		String dd=date.toLocaleString();
		dd=dd.substring(dd.indexOf(":")-2);
		if(dd.charAt(0)==' '){
			dd="0"+dd.substring(1);
		}
		return dd;
	}
	public static String getToday4LocaleTime()
	{
		java.util.Date date = new java.util.Date();
		String dd=date.toLocaleString();
		String[] dat=dd.split(":");
		return null;
	}
	/**
	 * getDate
	 * @param pdate
	 * @param pattern
	 * @return
	 */
	public static final String getDate(Date pdate, String pattern)
	{
		if( pattern == null )
			pattern ="yyyyMMdd";
		return (new SimpleDateFormat(pattern)).format(pdate);

	}
	/**
	 * 用于日历方式的日期格式
	 *
	 * @param date
	 * @return
	 */
	public static String getDate(java.util.Date date)
	{
		return getDate(date,"yyyy-MM-dd");
	}



	/**
	 * YYYY-MM
	 *
	 * @return
	 */
	public static String getYearMonth()
	{
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM");
		java.util.Date date = new java.util.Date();
		return sFormat.format(date);
	}



	/**
	 * 日
	 *
	 * @return
	 */
	public static String getDay()
	{
		SimpleDateFormat sFormat = new SimpleDateFormat("dd");
		java.util.Date date = new java.util.Date();
		return sFormat.format(date);
	}

	/**
	 * 本日日期和时间YYYY-MM-DD HH:MM:SS
	 *
	 * @return
	 */
	public static String getTodayTime()
	{
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		return sFormat.format(date);
	}

	/**
	 * 将整型星期转为中文星期
	 *
	 * @return
	 */
	public static String chgWeekToGB(int iWeek)
	{
		switch (iWeek) {
		case 1:
			return "星期日";
		case 2:
			return "星期一";
		case 3:
			return "星期二";
		case 4:
			return "星期三";
		case 5:
			return "星期四";
		case 6:
			return "星期五";
		case 7:
			return "星期六";
		default:
			return null;
		}
	}

	public static String chgWeekToZhou(int iWeek)
	{
		switch (iWeek) {
		case 1:
			return "周日";
		case 2:
			return "周一";
		case 3:
			return "周二";
		case 4:
			return "周三";
		case 5:
			return "周四";
		case 6:
			return "周五";
		case 7:
			return "周六";
		default:
			return null;
		}
	}

	/**
	 * 将整型星期转为英文星期
	 *
	 * @param iWeek
	 * @return
	 */
	public static String chgWeekToEN(int iWeek)
	{
		int i = 0;
		switch (iWeek) {
		case 1:
			return (i == 0) ? "SUNDAY" : "SUN";
		case 2:
			return (i == 0) ? "MONDAY" : "MON";
		case 3:
			return (i == 0) ? "TUESDAY" : "TUE";
		case 4:
			return (i == 0) ? "WEDNESDAY" : "WED";
		case 5:
			return (i == 0) ? "THURSDAY" : "THU";
		case 6:
			return (i == 0) ? "FRIDAY" : "FRI";
		case 7:
			return (i == 0) ? "SATURDAY" : "SAT";
		default:
			return null;
		}
	}

	/**
	 * 将中文日期转为整型
	 *
	 * @param iWeek
	 * @return
	 */
	public static int chgWeekToInt(String sWeek)
	{
		if (sWeek.equals("星期日"))
			return 1;
		else if (sWeek.equals("星期一"))
			return 2;
		else if (sWeek.equals("星期二"))
			return 3;
		else if (sWeek.equals("星期三"))
			return 4;
		else if (sWeek.equals("星期四"))
			return 5;
		else if (sWeek.equals("星期五"))
			return 6;
		else if (sWeek.equals("星期六"))
			return 7;
		else
			return 0;
	}

	/**
	 * 返回当前日期是星期几
	 *
	 * @return
	 */
	public static int getDayOfWeek()
	{
		return getDayOfWeek(getToday());
	}

	/**
	 * 返回指定日期是星期几
	 *
	 * @param sDate
	 * @return
	 */
	public static int getDayOfWeek(String sDate)
	{
		Calendar cal = getCalendar(sDate);
		return cal.get(Calendar.DAY_OF_WEEK);
	}




	/**
	 * 取得指定生产月份的结束日期
	 *
	 * @param sMonth
	 */
	public static String getMonthEnd(String sMonth)
	{
		return sMonth + "-25";
	}

	public static String getMonthEnd()
	{
		return getMonthEnd(getYearMonth());
	}

	/**
	 * 从"YYYY-MM-DD"日期字符串得到日期类
	 *
	 * @param sDate
	 * @return
	 */
	public static Date parseDate(String sDate)
	{
		if( sDate==null || sDate.trim().length()==0)
			return null;
		try
		{
			String format = "yyyy-MM-dd";
			if( sDate.length()==8 )
			{
				format="yyyyMMdd";
			}
			Date d1 = new SimpleDateFormat(format).parse(sDate);
			return d1;
		} catch (Exception ex)
		{
			return null;
		}
	}

	public static Date getDate(String sDate, String sFormat)
	{
		try
		{
			Date d1 = new SimpleDateFormat(sFormat).parse(sDate);
			return d1;
		} catch (Exception ex)
		{
			return null;
		}
	}

	/**
	 * 形如YYYY-MM-DD的日期字符串转为 Calendar 型
	 *
	 * @param sDate
	 * @param i
	 * @return
	 */
	public static Calendar getCalendar(String sDate)
	{
		Date date = parseDate(sDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// cal.setTimeInMillis(date.getTime());
		return cal;
	}

	public static Calendar getCalendar(String sDate, String sFormat)
	{
		Date date = getDate(sDate, sFormat);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// cal.setTimeInMillis(date.getTime());
		return cal;
	}

	/**
	 * 指定日期+或-iTs天
	 *
	 * @param sDate
	 * @param iTs
	 * @return
	 */
	public static String calendarAdd(String sDate, int iTs)
	{
		String pattern = "yyyyMMdd";
		if( sDate.length()==10 )
			pattern = "yyyy-MM-dd";
		Calendar cal = getCalendar( sDate, pattern );
		cal.add(Calendar.DATE, iTs);
		return calendarToDate(cal);
	}

	/**
	 * 指定日期+或-iTs天 是Time
	 */
	public static String calendarTimeAdd(String sDateTime, int iTs)
	{
		Calendar cal = getCalendar(sDateTime, "yyyy-MM-dd HH:mm:ss");
		cal.add(Calendar.DATE, iTs);
		return calendarToTime(cal);
	}

	/**
	 * 日历转换为 yyyy-MM-dd HH:mm:ss的字符串
	 */
	public static String calendarToDate(Calendar cal)
	{
		SimpleDateFormat sDateFormat = null;
		// java.util.Date date = new java.util.Date( cal.getTimeInMillis() );
		java.util.Date date = new java.util.Date(cal.getTime().getTime());
		sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return sDateFormat.format(date);
	}

	/**
	 * 日历转换为 yyyy-MM-dd HH:mm:ss的字符串
	 */
	public static String calendarToTime(Calendar cal)
	{
		SimpleDateFormat sDateFormat = null;
		// java.util.Date date = new java.util.Date( cal.getTimeInMillis() );
		java.util.Date date = new java.util.Date(cal.getTime().getTime());
		sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sDateFormat.format(date);
	}

	/**
	 * 得到 sBdate 与 sEdate 之间的天数(按实际天数计)
	 *
	 * @param sBdate
	 * @param sEdate
	 * @return
	 */
	public static int getDays(String sBdate, String sEdate)
	{
		Date dateB = parseDate(sBdate);
		Date dateE = parseDate(sEdate);
		if(dateB == null || dateB == null)
			return 99999;
		return (int) ((dateE.getTime() - dateB.getTime()) / (3600 * 24 * 1000));
	}

	/**
	 * 取得指定年份月份的天数
	 */
	public static int getDaysMonth(int iYear, int iMonth)
	{
		int iDays = 0;
		int iFebDays = (iYear % 4 == 0 && (iYear % 100 != 0 || iYear % 400 == 0)) ? 29
				: 28; // 闰年二月为29天
		int[] iDaysMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		iDaysMonth[1] = iFebDays;
		if (iMonth < 1 || iMonth > 12)
			return iDaysMonth[0];
		else
			return iDaysMonth[iMonth - 1];
	}

	public static int getDaysMonth(Calendar cal)
	{
		int iYear = cal.get(Calendar.YEAR); // 年
		int iMonth = cal.get(Calendar.MONTH) + 1; // 月
		return getDaysMonth(iYear, iMonth);
	}

	public static int getDaysMonth(String sDate)
	{
		return getDaysMonth(getCalendar(sDate));
	}





	  /**
	   * 获取当前日期的年份
	   */
	  public static String getYear() {
	    SimpleDateFormat sFormat = new SimpleDateFormat("yyyy");
	    java.util.Date date = new java.util.Date();
	    return sFormat.format( date );
	  }

	  /**
	   * 获取当前日期的月份
	   */
	  public static String getMonth() {
	    SimpleDateFormat sFormat = new SimpleDateFormat("MM");
	    java.util.Date date = new java.util.Date();
	    return sFormat.format( date );
	  }

	  /**
	   * pdate：日期字符串
	   * fpat:	
	   */
		public static final String formatDate(String pdate, String fpat, String tpat)
		{
			if (pdate == null)
				return null;
			SimpleDateFormat formatter = new SimpleDateFormat(fpat);
			Date tmp;
			try
			{
				tmp = formatter.parse(pdate);
			} catch (ParseException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			formatter.applyPattern(tpat);
			return formatter.format(tmp);
		}
		/**
		 * 后退n年
		 * 
		 * @param sDate  yyyyMMdd
		 * @param nm
		 * @return       yyyyMMdd
		 */
		public static String GetpriNYe(String sDate, int n) {
			String year = sDate.substring(0, 4);
			int iYear = new Integer(year).intValue();
			if (iYear < 1900 || iYear > 2100)
				iYear = new Integer(getYear()).intValue();
			iYear = iYear-n;
			String sRet=new StringBuffer()
							.append(String.valueOf(iYear))
							.append(sDate.substring(4,6))
							.append(sDate.substring(6,8))
							.toString();
			return sRet;

		}
		
		/**  
	     * 计算两个日期之间的月份列表yyyyMM  
	     * @param startTime   yyyy-MM-dd  
	     * @param endTime     yyyy-MM-dd  
	     * @return 之间的月份列表  
	    */
		public static List<String> getyyyyMMList(String startTime, String endTime) {
	           
	        int syear = Integer.valueOf(startTime.substring(0, 4));
	        int eyear = Integer.valueOf(endTime.substring(0, 4));
	        int smonth = Integer.valueOf(startTime.substring(5, 7));
	        int emonth = Integer.valueOf(endTime.substring(5, 7));
	  
	        List<String> yyyymm = new ArrayList<String>();
	  
	        if (eyear == syear) {   
	  
	            if (emonth > smonth) {   
	                for (int i = smonth; i <= emonth; i++) {   
	                    String ti = "" + i;
	                    if (ti.length() < 2) {   
	                        ti = "0" + ti;   
	                    }   
	                    yyyymm.add(eyear + ti);   
	                }   
	            } else {
	                yyyymm.add((eyear + "") + emonth);   
	            }
	            
	        } else if (eyear > syear) {   
	  
	            for (int yy = syear; yy <= eyear; yy++) {   
	  
	                if (yy < eyear) {   
	  
	                    if (yy == syear) {   
	                        for (int i = smonth; i <= 12; i++) {   
	  
	                            String ti = "" + i;
	                            if (ti.length() < 2) {   
	                                ti = "0" + ti;   
	                            }   
	                            yyyymm.add((yy + "") + ti);
	                        }   
	                    } else {   
	                        for (int i = 1; i <= 12; i++) {   
	                            String ti = "" + i;
	                            if (ti.length() < 2) {   
	                                ti = "0" + ti;   
	                            }   
	                            yyyymm.add((yy + "") + ti);   
	                        }   
	                    }   
	  
	                }   
	  
	                if (yy == eyear) {   
	                    for (int i = 1; i <= emonth; i++) {   
	                        String ti = "" + i;
	                        if (ti.length() < 2) {   
	                            ti = "0" + ti;   
	                        }
	                        yyyymm.add((yy + "") + ti);   
	                    }   
	                }
	            }   
	        } else {//eyear < syear 
	        	yyyymm.add("错误：起始年份小于结束年份"); 
	        }   
	        return yyyymm;   
	    }  

		public static boolean isMonthEnd(Date d)
		{
			Calendar c= Calendar.getInstance();
			c.setTime(d);
			int dayOfMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			int day = c.get(Calendar.DATE);
			
			return day==dayOfMonth;			
		}
		
		public static boolean isMonthEnd(String d)
		{
			
			Calendar c= Calendar.getInstance();
			c.setTime(DateUtil.parseDate(d));
			int dayOfMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			int day = c.get(Calendar.DATE);
			
			return day==dayOfMonth;			
		}
}

