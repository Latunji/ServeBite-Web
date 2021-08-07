/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

/**
 *
 * @author Habeeb O. Lawal
 */
import org.apache.commons.codec.binary.Base64;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
 
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author softmac
 */ 
public class Utility {
    
    public String stringToBase64(String str) {
        String result;
        byte[] bytes = Base64.encodeBase64(str.getBytes());
        result = new String(bytes);        
        return result;
    }
    
    public String base64ToString(byte[] hex) {
        String result;
        byte [] bytes = Base64.decodeBase64(hex);
        result = new String(bytes);
        return result;
    }
    
    public String base64Hashed(String plain) {
      String hashText = null;
        try {
            MessageDigest msgDigest = MessageDigest.getInstance("SHA-512");
            byte[] hashedBytes = msgDigest.digest(plain.getBytes());
            hashText = Base64.encodeBase64String(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.toString());
        }
        return hashText;
    }
    
    private static SecretKeySpec secretKey;
    private static byte[] key;
 
    public static void setKey(String myKey) 
    {
        MessageDigest sha;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); 
            secretKey = new SecretKeySpec(key, "AES");
        } 
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            System.out.println(e.toString());
        }
    }
    
    public String encryptToAES(String strToEncrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
    
    public String decryptFromAES(String strToDecrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt)));
        } 
        catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) 
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
    
   
 
    public static String generateMD5(String message) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return hashString(message, "MD5");
    }
 
    public static String generateSHA1(String message) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return hashString(message, "SHA-1");
    }
 
    public static String generateSHA256(String message) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return hashString(message, "SHA-256");
    }
 
    private static String hashString(String message, String algorithm) throws NoSuchAlgorithmException, UnsupportedEncodingException
             {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));
            return convertByteArrayToHexString(hashedBytes);
        
    }
 
    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayBytes.length; i++) {
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return stringBuffer.toString();
    }
}
