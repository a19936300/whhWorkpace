package com.binbinxiu.whh.gateway.constant.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Base64;

/**
 * AES 加解密工具类
 *
 * @author wzd
 */
@Slf4j
public final class AESUtil {

    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM = "AES";

    /**
     * 加密/解密算法 / 工作模式 / 填充方式
     * Java 6支持PKCS5Padding填充方式
     * Bouncy Castle支持PKCS7Padding填充方式
     */
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";

    static {
        //如果是PKCS7Padding填充方式，则必须加上下面这行
        Security.addProvider(new BouncyCastleProvider());
    }

    private AESUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * AES加密ECB模式PKCS7Padding填充方式
     *
     * @param str      字符串
     * @param password 密钥
     * @return 加密字符串
     * @throws Exception 异常信息
     */
    public static String encrypt(String str, String password) throws Exception {
        if(StringUtils.isBlank(str)){
            return str;
        }
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyToBytes(password), KEY_ALGORITHM));
        byte[] doFinal = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
        return new String(Base64.getEncoder().encode(doFinal));
    }



    /**
     * AES解密ECB模式PKCS7Padding填充方式
     *
     * @param str      字符串
     * @param password 密钥
     * @return 解密字符串
     * @throws Exception 异常信息
     */

    public static String decrypt(String str, String password) throws Exception {
        if(StringUtils.isBlank(str)){
            return str;
        }
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyToBytes(password), KEY_ALGORITHM));
        byte[] doFinal = cipher.doFinal(Base64.getDecoder().decode(str));
        return new String(doFinal);
    }

    /**
     * 256位的密钥有jdk版本的jce问题
     * 密码转byte
     * @param password
     * @return
     */
    private static byte[] keyToBytes(String password) throws NoSuchAlgorithmException {
        String key = Md5Utils.hash(password).substring(0, 16);
        /*KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128, new SecureRandom(key.getBytes()));
        SecretKey secretKey = keyGen.generateKey();
        byte[] keyBytes = secretKey.getEncoded();*/
        return key.getBytes(StandardCharsets.UTF_8);
    }


}
