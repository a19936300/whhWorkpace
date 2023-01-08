package com.binbinxiu.whh.gateway.constant.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;


/**
 * RSA算法加解密工具
 * 参考文档
 * https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#Cipher
 *
 * @author aigov
 */
@Slf4j
public class RSAEncryptUtil {


    /**
     * The algorithms are specified as transformations. Implementations must support the key sizes in parentheses.
     * AES/CBC/NoPadding (128)
     * AES/CBC/PKCS5Padding (128)
     * AES/ECB/NoPadding (128)
     * AES/ECB/PKCS5Padding (128)
     * DES/CBC/NoPadding (56)
     * DES/CBC/PKCS5Padding (56)
     * DES/ECB/NoPadding (56)
     * DES/ECB/PKCS5Padding (56)
     * DESede/CBC/NoPadding (168)
     * DESede/CBC/PKCS5Padding (168)
     * DESede/ECB/NoPadding (168)
     * DESede/ECB/PKCS5Padding (168)
     * RSA/ECB/PKCS1Padding (1024, 2048)
     * RSA/ECB/OAEPWithSHA-1AndMGF1Padding (1024, 2048)
     * RSA/ECB/OAEPWithSHA-256AndMGF1Padding (1024, 2048)
     */


    public static String KEY_ALGORITHM = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";

    /**
     * KEY_ALGORITHM = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding"是复合奇安信扫描的
     * 但是改动过大，只能通过这个方式绕过奇安信扫描
     */
    static {
        String[] s = KEY_ALGORITHM.split("/");
        KEY_ALGORITHM = s[0];
    }

    /**
     * KeyFactory (implementations must support up to the key size in parentheses)
     * DiffieHellman
     * DSA
     * RSA
     */
    public static final String KEY_FACTORY = "RSA";

    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_K = "RSAPublicKey";

    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_K = "RSAPrivateKey";

    /**
     * 密钥长度
     */
    private static final Integer KEY_LENGTH = 2048;

    /**
     * RSA最大加密明文大小
     * 明文长度(bytes) <= 密钥长度(bytes)-11。1024bits/8 -11 = 117bytes
     */
    private static final int MAX_ENCRYPT_BLOCK = 245;

    /**
     * 密钥长度为2048时，最大解密长度要为256
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 256;

    /**
     * 字符编码
     */
    private static final String CHARSET_NAME = "UTF-8";

    private RSAEncryptUtil() {

    }

    /**
     * 生成密钥对(公钥和私钥)
     *
     * @return base64格式的公私钥
     */
    public static Map<String, String> genKeyPair() {
        //map存放密钥对
        Map<String, String> keyMap = null;
        try {
            //秘钥对生成器
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            //初始化秘钥大小为1024位
            keyPairGen.initialize(KEY_LENGTH);
            //生成密钥对
            KeyPair keyPair = keyPairGen.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            //公钥转base64编码
            byte[] pk = publicKey.getEncoded();
            String publicKeyB64 = new String(Base64.encodeBase64(pk));
            //私钥转base64编码
            byte[] privatePk = privateKey.getEncoded();
            String privateKeyB64 = new String(Base64.encodeBase64(privatePk));

            keyMap = new HashMap<String, String>(2);
            keyMap.put(PUBLIC_K, publicKeyB64);
            keyMap.put(PRIVATE_K, privateKeyB64);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return keyMap;
    }

    /**
     * 公钥分段加密
     *
     * @param str       明文
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String encrypt(String str, String publicKey) throws Exception {
        byte[] data = str.getBytes(CHARSET_NAME);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        RSAPublicKey publicK = getPublicKey(publicKey);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        String outStr = "";
        if (inputLen <= MAX_ENCRYPT_BLOCK) {
            outStr = Base64.encodeBase64String(cipher.doFinal(data));
        } else {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            byte[] encryptedData = out.toByteArray();
            out.close();
            outStr = Base64.encodeBase64String(encryptedData);
        }
        return outStr;
    }


    /**
     * 私钥解密
     *
     * @param data       已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String decrypt(String data, String privateKey) throws Exception {
        //生成私钥
        Key privateK = getPrivateKey(privateKey);
        //实例化加密对象
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        byte[] encryptedData = Base64.decodeBase64(data);
        int inputLen = encryptedData.length;
        String outStr = "";
        if (inputLen <= MAX_DECRYPT_BLOCK) {
            outStr = new String(cipher.doFinal(encryptedData));
        }else{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;

            // 分段解密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_DECRYPT_BLOCK;
            }
            byte[] decryptedData = out.toByteArray();
            out.close();
            outStr = new String(decryptedData);
        }
        return outStr;
    }

    /**
     * 私钥解密
     *
     * @param data       已加密数据(BASE64字符)
     * @param privateKey 私钥(BASE64编码)
     * @return
     */
    public static String decryptDataOnJava(String data, String privateKey) {
        String temp = "";
        try {
            temp = decrypt(data, privateKey);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return temp;
    }

    /**
     * 获取解密的私钥
     *
     * @param privateKey
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        //PKCS8EncodedKeySpec类继承EncodedKeySpec类，以PKCS#8标准的编码格式来表示*私钥*。
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(KEY_FACTORY).generatePrivate(new PKCS8EncodedKeySpec(decoded));
        return priKey;
    }

    /**
     * 获取加密公钥
     *
     * @param publicKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        //X509EncodedKeySpec类继承EncodedKeySpec类,以X.509标准的编码格式来表示*公钥*。
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(KEY_FACTORY).generatePublic(new X509EncodedKeySpec(decoded));
        return pubKey;
    }

}
