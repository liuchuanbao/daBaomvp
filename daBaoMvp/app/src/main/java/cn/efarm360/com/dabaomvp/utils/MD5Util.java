package cn.efarm360.com.dabaomvp.utils;

import java.security.MessageDigest;

/**
 * Created by Administrator on 2016/8/17 0017.
 */
public class MD5Util {
    //~ Instance fields ========================================================


    //~ Constructors ===========================================================


    public final static String MD5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            byte[] strTemp = s.getBytes("UTF-8");
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte b = md[i];
                //System.out.println((int)b);
                //��û����(int)b����˫�ֽڼ���
                str[k++] = hexDigits[b >> 4 & 0xf];
                str[k++] = hexDigits[b & 0xf];
            }
            return new String(str);
        } catch (Exception e) {return null;}
    }



    //	 public static String signature(String xml, String time){
//		 return MD5Util.MD5(MD5Util.MD5(Constants.appKey)+ "$" +
//					Constants.appCode + "$" + time + "$" + xml);
//	 }
    public static int getRandom(){
        java.util.Random r=new java.util.Random();
        return r.nextInt();
    }

}
