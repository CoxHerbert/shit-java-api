package com.ruoyi.common.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

public class CryptoUtils {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String SECRET_KEY = "Caghd9Um8MNHVI1RRX3RhA=="; // Base64编码密钥
    private static final String IV = "ThisIsAnIV123456"; // 16字节IV

    public static void main(String[] args) {
        try {
            String plaintext = "admin222";
            String encrypted = encryptTxt(plaintext);
            System.out.println("加密结果: " + encrypted);

            String decrypted = decryptTxt(encrypted);
            System.out.println("解密结果: " + decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加密
     * @param txt 明文
     * @return 加密后的Base64字符串
     * @throws Exception 异常信息
     */
    public static String encryptTxt(String txt) throws Exception {
        // 解析密钥和IV
        SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(SECRET_KEY), ALGORITHM);
        IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

        // 加密处理
        byte[] encryptedBytes = cipher.doFinal(txt.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * 解密
     * @param txt 加密后的Base64字符串
     * @return 解密后的明文
     * @throws Exception 异常信息
     */
    public static String decryptTxt(String txt) throws Exception {
        // 解析密钥和IV
        SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(SECRET_KEY), ALGORITHM);
        IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

        // 解密处理
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(txt));
        return new String(decryptedBytes, "UTF-8");
    }
}
