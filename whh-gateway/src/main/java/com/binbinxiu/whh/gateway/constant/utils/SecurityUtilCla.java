package com.binbinxiu.whh.gateway.constant.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SM4;


/**
 * 新国密对称加密解密方法工具类
 *
 * @author c_suntao-031
 *
 */

public class SecurityUtilCla {

	static final String SECURITY_TYPE = "1234567890123456";

	static SM4 securityUtil = SmUtil.sm4(SECURITY_TYPE.getBytes());


	public static String encryptData(byte[] inData) {
		byte[] encrypt = securityUtil.encrypt(inData);
		return Base64.encode(encrypt);
	}

	public static String decryptData(String encryptData) {
		byte[] enbytes = Base64.decode(encryptData);
		byte[] decryptData = securityUtil.decrypt(enbytes);
		return new String(decryptData);
	}

	public static void main(String[] args) {
		String s = encryptData("123456".getBytes());
		System.out.println(s);
	}
}
