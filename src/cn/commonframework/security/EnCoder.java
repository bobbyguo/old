package cn.commonframework.security;

import java.security.MessageDigest;   

import javax.crypto.KeyGenerator;   
import javax.crypto.Mac;   
import javax.crypto.SecretKey;   
import javax.crypto.spec.SecretKeySpec;

  
import sun.misc.BASE64Decoder;   
import sun.misc.BASE64Encoder;   
  
/** *
 * ����������� 
 * @author Bobby_Guo  
 * @version 1.0  
 * @date   2009-6-19 ����05:16:01
 */  
public  class EnCoder {   
    public static final String KEY_SHA = "SHA";   
    public static final String KEY_MD5 = "MD5";   
  
    /** *//**  
     * MAC�㷨��ѡ���¶����㷨  
     *   
     * <pre>  
     * HmacMD5   
     * HmacSHA1   
     * HmacSHA256   
     * HmacSHA384   
     * HmacSHA512  
     * </pre>  
     */  
    public static final String KEY_MAC = "HmacMD5";   
  
    /** *//**  
     * BASE64����  
     *   
     * @param key  
     * @return  
     * @throws Exception  
     */  
    public static byte[] decryptBASE64(String key) throws Exception {   
        return (new BASE64Decoder()).decodeBuffer(key);   
    }   
  
    /** *//**  
     * BASE64����  
     *   
     * @param key  
     * @return  
     * @throws Exception  
     */  
    public static String encryptBASE64(byte[] key) throws Exception {   
        return (new BASE64Encoder()).encodeBuffer(key);   
    }   
  
    /** *//**  
     * MD5����  
     *   
     * @param data  
     * @return  
     * @throws Exception  
     */  
    public static byte[] encryptMD5(byte[] data) throws Exception {   
  
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);   
        md5.update(data);   
  
        return md5.digest();   
  
    }   
  
    /** *//**  
     * SHA����  
     *   
     * @param data  
     * @return  
     * @throws Exception  
     */  
    public static byte[] encryptSHA(byte[] data) throws Exception {   
  
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);   
        sha.update(data);   
  
        return sha.digest();   
  
    }   
  
    /** *//**  
     * ��ʼ��HMAC��Կ  
     *   
     * @return  
     * @throws Exception  
     */  
    public static String initMacKey() throws Exception {   
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);   
  
        SecretKey secretKey = keyGenerator.generateKey();   
        return encryptBASE64(secretKey.getEncoded());   
    }   
  
    /** *//**  
     * HMAC����  
     *   
     * @param data  
     * @param key  
     * @return  
     * @throws Exception  
     */  
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {   
  
        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);   
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());   
        mac.init(secretKey);   
  
        return mac.doFinal(data);   
  
    }   
    /**
     * �ֽ�����ת��Ϊʮ�������ַ���
     * @param data
     * @return
     */
    public static String toHex(byte[] data){
    	StringBuffer sb = new StringBuffer();
    	for(byte b : data){
    		String s = Integer.toHexString(0xff&b);
    		if(s.length()<2){
    			sb.append(0);
    		}
    		sb.append(s);
    		/*char hi = (char) ((b>>4)&0xff);
    		char lo = (char) (b&0xff);
    		sb.append(Character.digit(hi, 16)).append(Character.digit(lo, 16));*/
    	}
    	
    	return sb.toString();
    }
    /**
     * MD5����
     * @param s
     * @return
     * @throws Exception
     */
    public static String MD5Encoding(String s) throws Exception{
    	return toHex(encryptMD5(s.getBytes()));
    	
    }
    /**
     * SHA����
     * @param s
     * @return
     * @throws Exception
     */
    public static String SHAEncoding(String s) throws Exception{
    	return toHex(encryptSHA(s.getBytes()));
    }
    
   /* public static void main(String[] args) throws Exception{
    	String s = "986319";
    	String s2 = "986319";
    	String tem = encryptMD5(s.getBytes()).toString();
    	System.out.println("BASE64---"+(encryptBASE64(s.getBytes())));
    	System.out.println("MD5---"+toHex(encryptMD5(s.getBytes())));
    	System.out.println("MD5---"+MD5Encoding("zxgs"));
    	System.out.println("SHA---"+toHex(encryptSHA(s.getBytes())));
    	System.out.println(String.valueOf(decryptBASE64("OTg2MzE5")));
    	
    }*/
}  

