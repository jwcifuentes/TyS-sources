package co.com.mintrabajo.tys.commons.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class CheckUtil {

	public static String checkNullString(final String input) {
		return input == null ? "" : input;
	}

	public static Long checkNullLong(final Object input) {
		return input == null ? 0L : Long.parseLong(input.toString());
	}
	
	public static String checkNullDate(final Date input) {
		return input == null ? "" : DateUtil.getDateToString(input);
	}

	public static  String checkNullLongToString(final Long input){
		return input == null ? "" : Long.toString(input);
		
	}
	
	public static  String checkTransFormUTF8(final String input){
		if(input == null) {
			return "";
		}		
		try {
			byte[] ptext = input.getBytes("ISO-8859-1"); 
			String value = new String(ptext, "UTF-8");
			return value;	
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		} 
			
	}
	
}
