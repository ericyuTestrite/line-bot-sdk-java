package com.testritegroup.bot.luis.util;

public class StringUtil {
	
  	 
  	    /**
  	     * check String is not null and length >0
  	     * @param s String
  	     * @return boolean
  	     */
  	    public static boolean stringHasValue(String s) {
  	        return s != null && s.length() > 0;
  	    }

  	    /**
  	     *  字串的替換
  	     * @param str String
  	     * @param sFindIt String
  	     * @param sChangeTo String
  	     * @return String
  	     */
  	    public static String stringReplace(String str, String sFindIt,
  	                                       String sChangeTo) {
  	        String stemp = "";
  	        if (stringHasValue(str)) {
  	            while (str.indexOf(sFindIt) >= 0) {
  	                stemp = stemp + str.substring(0, str.indexOf(sFindIt)) + sChangeTo;
  	                str = str.substring(str.indexOf(sFindIt) + sFindIt.length());
  	            }
  	            stemp = stemp + str;
  	        }
  	        return stemp;
  	    }

  	  
  	  
	
  	    
}
