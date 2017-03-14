package cn.efarm360.com.dabaomvp.utils;

import java.text.SimpleDateFormat;
import java.util.UUID;

public class Tool {
 public static String getUUID(){
	 StringBuffer uuid=new StringBuffer();
	 String strs[]= UUID.randomUUID().toString().split("-");
     //去掉“-”符号 
	 for(String s:strs){
		 uuid.append(s);
		 
	 }
	 return uuid.toString();
 }
 public static String getTime(String str){
	 SimpleDateFormat sdf=new SimpleDateFormat(str);
	  return  sdf.format(new java.util.Date());
 }
}
