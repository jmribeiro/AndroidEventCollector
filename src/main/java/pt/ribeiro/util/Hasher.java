package pt.ribeiro.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
	
	public static byte[] hashText(String text){
		byte[] hash = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			hash =  md.digest(text.getBytes());
		} catch (NoSuchAlgorithmException e) {
			//NOT SUPPOSE TO HAPPEN
		}
		return hash;	
	}
	
	public static byte[] hashFile(String filePath) throws IOException{
		byte[] hash = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			InputStream is = Files.newInputStream(Paths.get(filePath));
		    @SuppressWarnings("unused")
			DigestInputStream dis = new DigestInputStream(is, md);
		    hash = md.digest();  
		} catch (NoSuchAlgorithmException e) {
			//NOT SUPPOSE TO HAPPEN
		}
		return hash;
	}
	
	public static String byteArrayToString(byte[] bytes) {
		
		char[] hexArray = "0123456789ABCDEF".toCharArray();
	    char[] hexChars = new char[bytes.length * 2];
	    
	    for ( int i = 0; i < bytes.length; i++ ) {
	        int v = bytes[i] & 0xFF;
	        hexChars[i * 2] = hexArray[v >>> 4];
	        hexChars[i * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	public static byte[] hexStringToByteArray(String hexaDecimalString) {
	    
		int length = hexaDecimalString.length();
	    
		byte[] data = new byte[length / 2];
	    for (int i = 0; i < length; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(hexaDecimalString.charAt(i), 16) << 4)
	                             + Character.digit(hexaDecimalString.charAt(i+1), 16));
	    }
	    return data;
}
}
