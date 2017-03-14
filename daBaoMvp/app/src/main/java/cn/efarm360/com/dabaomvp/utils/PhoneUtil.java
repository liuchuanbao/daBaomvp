package cn.efarm360.com.dabaomvp.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

public class PhoneUtil {

	/**
	 * 根据imsi串号判断是否电信用户
	 */
	public static boolean isCtcUserByImsi(Context context) {
		try {
			TelephonyManager telManager = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			String imsi = telManager.getSubscriberId();
			if (imsi != null) {
				if (imsi.startsWith("46003") || imsi.startsWith("45502")) {// 455-02
																			// 中國電信澳門
					// 中国电信
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 是否移动号码
	 * 
	 * @param num
	 * @return
	 */
	public static boolean isCmccMobile(String num) {
		if (StringUtil.isNull(num)) {
			return false;
		}
		if (!isMobile(num)) {
			return false;
		}
		String phone = num.substring(num.length() - 11, num.length());
		String regex = "^(134|135|136|137|138|139|147|150|151|152|157|158|159|182|183|187|188)[0-9]{8}$";
		if (phone.matches(regex)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否联通号码
	 * 
	 * @param num
	 * @return
	 */
	public static boolean isUnicomMobile(String num) {
		if (StringUtil.isNull(num)) {
			return false;
		}
		if (!isMobile(num)) {
			return false;
		}
		String phone = num.substring(num.length() - 11, num.length());
		String regex = "^(130|131|132|155|156|186|185)[0-9]{8}$";
		if (phone.matches(regex)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * 是否联通号码
	 * 
	 * @param num
	 * @return
	 */
	public static boolean isCtc(String num) {
		String phone = num.substring(num.length() - 11, num.length());
		String regex = "^(133|153|180|181|189|1770)[0-9]{8}$";
		if (phone.matches(regex)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 删除字符串中的空白符
	 * @param content
	 * @return String
	 */
	public static String removeBlanks(String content){
		if(content==null){              
			return null;
		}
		StringBuffer buff = new StringBuffer();
		buff.append(content);
		for(int i = buff.length()-1; i >= 0;i--){
			if(' ' == buff.charAt(i)||('\n' == buff.charAt(i))||('\t' == buff.charAt(i))||('\r' == buff.charAt(i))){
				buff.deleteCharAt(i);
			}
		}
		return buff.toString();
	}

	/**
	 * 验证是否是手机号码
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMobile(String str) {
		str = removeBlanks(str);
		String NUM = "+86";
		boolean flag = false;
		if (StringUtil.isNull(str)) {
			return flag;
		} else {
			if (str.indexOf(NUM) > -1) {
				str = str.substring(NUM.length(), str.length());
			}
			if (str.charAt(0) == '0') {
				str = str.substring(1, str.length());
			}
			String rex = "^1[3,4,5,8]\\d{9}$";
			// String rex = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
			if (str.matches(rex)) {
				flag = true;
			}
			return flag;
		}
	}
}
