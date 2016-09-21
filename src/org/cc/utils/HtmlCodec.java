package org.cc.utils;

/**
 * HTML编码工具类 <br/>
 * 1. NCR的编码解码 <br/>
 * 2. NCRX的编码解码 <br/>
 * 3. Unicode的编码解码 <br/>
 * @author CC11001100
 *
 */
public class HtmlCodec {
	
	/** numeric character reference **/
	public static final int NCR=1;
	/** numeric character reference **/
	public static final int NUMBERIC_CHARACTER_REFRERENCE=NCR;
	
	/** numeric character reference hex **/
	public static final int NCRX=2;
	/** numeric character reference hex **/
	public static final int NUMBERIC_CHARACTER_REFRERENCE_HEX=NCRX;
	
	/** character entity reference **/
	public static final int CNR=3;
	/** character entity reference **/
	public static final int CHARACTER_ENTITY_REFERENCER=CNR;
	
	/** unicode 编码(带\\u转义的那种)**/
	public static final int UNICODE=4;
	
	/**
	 * 对字符串进行编码
	 * @param s
	 * @param encodeType
	 * @return
	 */
	public static String encode(String s,int encodeType){
		
		String prefix=getPrefixByEncodeType(encodeType);
		String suffix=getSuffixByEncodeType(encodeType);
		
		//TODO 计算一下最长的长度，尽量避免数组扩展,等下写个方法来计算，先这样
//		StringBuilder sb=new StringBuilder(s.length()*(4+prefix.length()));
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<s.length();i++){
			sb.append(prefix).  //前缀
				append(intToStringByEncodeType(s.charAt(i),encodeType)). //主体
				append(suffix);  //后缀
		}
		
		return sb.toString();
	}
	
	/**
	 * 根据编码类型转换数据
	 * @param n
	 * @param encodeType
	 * @return
	 */
	private static String intToStringByEncodeType(int n,int encodeType){
		switch(encodeType){
			case UNICODE:
			case NCRX: 
				return Integer.toString(n,16);
			case NCR: return Integer.toString(n);
			default: return "";
		}
	}
	
	/**
	 * 对字符串进行解码
	 * @param s
	 * @param encodeType
	 * @return
	 */
	public static String decode(String s,int encodeType){
		//统一字符先
		s=s.toLowerCase();
		
		String prefix=escape(getPrefixByEncodeType(encodeType));
		String suffix=getSuffixByEncodeType(encodeType);
		
		//剥离前缀
		String ss[]=s.split(prefix);
		
		//如果可以基本确定的，应尽量提前计算(有问题)
		//TODO 长度确定的有问题
		StringBuilder sb=new StringBuilder(ss.length);
		for(int i=0;i<ss.length;i++){
			if(ss[i].length()>0){
				//剥离后缀
				String body=ss[i].substring(0,ss[i].length()-suffix.length());
				//解析主体
				sb.append((char)parseIntByEncodeType(body,encodeType));
			}
		}
		return sb.toString();
	}
	
	/**
	 * 根据编码类型来解码
	 * @param s 字符形式的数字
	 * @param encodeType
	 * @return
	 */
	private static int parseIntByEncodeType(String s,int encodeType){
		switch(encodeType){
			case UNICODE:
			case NCRX: 
				return Integer.parseInt(s,16);
			case NCR: return Integer.parseInt(s);
			default: return 0;
		}
	}
	
	/**
	 * 根据编码方式获得前缀
	 * @param encodeType
	 * @return
	 */
	private static String getPrefixByEncodeType(int encodeType){
		switch(encodeType){
			case NCRX: return "&#x";
			case NCR: return "&#";
			case CNR: return "&#name";
			case UNICODE: return "\\u";
			default: return "";
		}
	}
	
	/**
	 * 根据编码类型获得后缀
	 * @param encodeType
	 * @return
	 */
	private static String getSuffixByEncodeType(int encodeType){
		switch(encodeType){
			case NCRX:
			case NCR:
			case CNR:
				return ";";
			case UNICODE:
				return "";
			default: return "";
		}
	}
	
	/**
	 * 对一个字符串进行转义处理
	 * @param prefix
	 * @return
	 */
	private static String escape(String s){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<s.length();i++){
			char c=s.charAt(i);
			sb.append(c);
			if(c=='\\') sb.append(c);
		}
		return sb.toString();
	}
	
}