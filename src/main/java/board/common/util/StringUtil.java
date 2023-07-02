package board.common.util;


public class StringUtil {

	
	public static boolean isNumber(char c) {
		if (c < '0' || c > '9')
			return false;
		return true;
	}

	
	public static boolean isNumber(String str) {
		if (str == null)
			return false;

		str = str.trim();
		int len = str.length();
		if (len == 0)
			return false;

		for (int i = 0; i < len; i++) {
			if (!isNumber(str.charAt(i)))
				return false;
		}
		return true;
	}

	
	public static boolean isNull(String str) {
		return str == null || "null".equalsIgnoreCase(str);
	}
	public static boolean isEmpty(String str){
		return (str==null || "".equals(str.trim()));
	}

	public static String evl(String str, String value){
		return isEmpty(str)?value:str.trim();
	}

	
	public static boolean isNotNull(String str) {
		return !isNull(str);
	}

	
	public static boolean parseBoolean(String str) {;
		return "true".equalsIgnoreCase(str);
	}

	
	public static boolean equals(String str1, String str2) {
		if(isNull(str1)){
			return isNull(str2);
		}
		return str1.equals(str2);
	}

	
	public static boolean equalsIgnoreCase(String str1, String str2) {
		if(isNull(str1)){
			return isNull(str2);
		}
		return str1.equalsIgnoreCase(str2);
	}

	
	public static String nvl(String str) {
		if (isNull(str)){
			str = "";
		}
		return str;
	}

	
	public static String nvl(String str, String chstr) {
		if (isNull(str)){
			return chstr;
		}
		return str;
	}

	
	public static long strToLong(String str) {
		return strToLong(str, 0);
	}

	
	public static long strToLong(String str, long value) {
		long result = 0L;
		try {
			result = Long.parseLong(str);
		} catch (Exception e) {
			result = value;
		}
		return result;
	}

	
	public static int strToInt(String str) {
		long result = strToLong(str);
		return (int) result;
	}

	
	public static int strToInt(String str, int value) {
		long result = strToLong(str, value);
		return (int) result;
	}

	
	public static double strToDouble(String str) {
		return strToDouble(str, 0);
	}

	
	public static double strToDouble(String str, double value) {
		double result = 0;
		try {
			result = Double.parseDouble(str);
		} catch (Exception e) {
			result = value;
		}
		return result;
	}

	
	public static String lpad(String str, int len, String addStr) {
		StringBuffer result = new StringBuffer();
		int templen = len - str.length();
		for (int i = 0; i < templen; i++) {
			result.append(addStr);
		}
		result.append(str);
		return result.toString();
	}
	public static String lpad(int dd, int size) {
		return null;
	}

	
	public static String stripTags(String text) {
		if (text != null) {
			return text.replaceAll("\\<.*?>", "").trim();
		}
		return "";
	}

	public static String escape(String instr) {
		if(instr==null){
			return "";
		}
		String str = instr.trim();
		if ("".equals(str)) {
			return "";
		}
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("\'", "&#039;"); //=&#x27;
		return str;
	}

	public static int nvlInt(String year) {
		// TODO Auto-generated method stub
		return 0;
	}
}
