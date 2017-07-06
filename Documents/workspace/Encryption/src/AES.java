import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
 
public class AES {
 
    private static SecretKeySpec secretKey;
    private static byte[] key;
    Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) throws Exception 
    {
        final String secretKey = "krustykrab";
        
         
//        String originalString = "howtodoinjava.com";
//        String encryptedString = AES.encrypt(originalString, secretKey) ;
//        String decryptedString = AES.decrypt(encryptedString, secretKey) ;
//         
//        System.out.println(originalString);
//        System.out.println(encryptedString);
//        System.out.println(decryptedString);
        
        test2();
    }
    
    public void test() {
    	while(1==1) {
        	System.out.println("Do you want to encrypt or decrypt");
        	System.out.print(">");
        	String x = in.nextLine();
        	if(x.equals("encrypt")) {
        		System.out.println("Enter text to encrypt");
            	System.out.print(">");
            	String originalString = in.nextLine();
            	System.out.println("Enter AES Key");
            	System.out.print(">");
            	String secret = in.nextLine();
            	String encryptedString = AES.encrypt(originalString, secret);
            	System.out.println("Encrypted text: "+encryptedString);
        	} else if(x.equals("decrypt")) {
        		System.out.println("Enter text to decrypt");
            	System.out.print(">");
            	String encrypt = in.nextLine();
            	System.out.println("Enter AES Key");
            	System.out.print(">");
            	String secret = in.nextLine();
            	String decryptedString = AES.decrypt(encrypt, secret);
            	System.out.println("Decrypted text: "+decryptedString);
        	} else {
        		return;
        	}
        }
    }
    
    public static void test2() throws Exception {
    	SecretKey test = createKey();
    	System.out.println(test.getAlgorithm());
    	//SecretKey to String
    	String encodedKey = Base64.getEncoder().encodeToString(test.getEncoded());
    	System.out.println(encodedKey);
    	//String to SecretKey
    	byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
    	SecretKey originalKey = new SecretKeySpec(decodedKey,0,decodedKey.length,"AES");
    	System.out.println(originalKey.getAlgorithm());
    	System.out.println(Base64.getEncoder().encodeToString(originalKey.getEncoded()));
    }
    
    public static SecretKey createKey() throws Exception {
    	KeyGenerator generator = KeyGenerator.getInstance("AES");
    	generator.init(128);
    	SecretKey secKey = generator.generateKey();
    	return secKey;
    }
 
    public static void setKey(String myKey) 
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); 
            secretKey = new SecretKeySpec(key, "AES");
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    public static String encrypt(String strToEncrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
 
    public static String decrypt(String strToDecrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}