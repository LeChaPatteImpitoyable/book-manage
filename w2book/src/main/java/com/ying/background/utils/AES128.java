package com.ying.background.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.util.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidParameterSpecException;

/**
 * 
 * @author carro
 * @since 2014.09.25
 * @version AES128 加密解密算法
 * 
 */
@Slf4j
public class AES128 {
	
	 static {
	        //BouncyCastle是一个开源的加解密解决方案，主页在http://www.bouncycastle.org/
	        Security.addProvider(new BouncyCastleProvider());
	 }


	public static String encode(byte[] content, byte[] password)
	{

		String result = null; // 加密最终返回的变量result
		/**
		 * 1.函数参数验证 变量content ，password的判断 .try catch块对语句中出现的异常，调用时写入相应的日志文件中
		 */
		try
		{
			if (content != null && content.length > 0 && password != null
					&& password.length > 0)
			{

				/**
				 * 2.使用KeyGenerator类 根据AES算法生成密钥，即生成keyGenerator 对象
				 * 用指定参数集和用户提供的随机源初始化此密钥生成器 并将其转换为字节数组保存
				 */
				KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
				if (keyGenerator != null)
				{
					keyGenerator.init(128, new SecureRandom(password));
					SecretKey secretKey = keyGenerator.generateKey();
					if (secretKey != null)
					{
						byte[] enCodeFormat = secretKey.getEncoded(); // 将密钥转换为字节数组保存
						SecretKeySpec secretKeySpec = new SecretKeySpec(
								enCodeFormat, "AES");
						if (secretKeySpec != null)
						{
							/**
							 * 3.使用Cipher类
							 * 根据AES算法创建密码器，初始化后，调用doFinal()方法结束加密操作。
							 * 
							 */
							Cipher cipher = Cipher.getInstance("AES");// 创建密码器
							if (cipher != null)
							{
								byte[] byteContent = content;
								cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);// 初始化
								result = byte2HexStr(cipher
										.doFinal(byteContent));
							}
						}
					}
				}
			}
		}
		/**
		 * 4.出现异常，使用log4j jar包 根据properties配置文件，将异常信息写入日志中。
		 * 
		 */
		catch (Exception e)
		{

			log.error("@出现异常啦@", e);
		}

		return result;
	}
	
	/**
	 * 解密
	 * @param content 解密内容
	 * @param key 密钥
	 * @param iv 偏移量
	 * @param encodingFormat 解密后编码格式
	 * @return
	 */
	public static String decode(String content, String key, String iv, String encodingFormat) {
		byte[] contentByte = Base64.decodeBase64(content);
		byte[] keyByte = Base64.decodeBase64(key);
		byte[] ivByte = Base64.decodeBase64(iv);
		String result = null;
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");// 创建密码器
			SecretKeySpec secretKeySpec = new SecretKeySpec(keyByte, "AES");
			AlgorithmParameters paramenters = AlgorithmParameters.getInstance("AES");
			paramenters.init(new IvParameterSpec(ivByte));
			if (cipher != null){
				cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, paramenters);// 初始化
				byte[] resByte = cipher.doFinal(contentByte);
				if(resByte != null && resByte.length > 0) {
					result = new String(resByte, encodingFormat);
				}
			}
			 return result;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InvalidParameterSpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param content
	 *            需要解密的内容
	 * @param password
	 *            解密是用的密钥
	 * @return
	 */
	public static String decode(String content, byte[] password)
	{
		String result = null;
		byte[] byteContent = hexStr2Byte(content);
		// byteContent = Base64Util.decode(new String(byteContent));
		/**
		 * 1.函数参数验证 变量content ，password的判断 try catch块对语句中出现的异常，调用时写入相应的日志文件中
		 */
		try
		{
			if (content != null && content.length() > 0 && password != null
					&& password.length > 0)
			{

				/**
				 * 2.使用KeyGenerator类 根据AES算法生成密钥，即生成keyGenerator 对象
				 * 用指定参数集和用户提供的随机源初始化此密钥生成器 并将其转换为字节数组保存
				 */

				KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

				if (keyGenerator != null)
				{
					keyGenerator.init(128, new SecureRandom(password));
					SecretKey secretKey = keyGenerator.generateKey();
					if (secretKey != null)
					{
						byte[] enCodeFormat = secretKey.getEncoded();
						/**
						 * 3.使用Cipher类 根据AES算法创建密码器，初始化后，调用doFinal()方法结束解密操作。
						 * 
						 */
						SecretKeySpec secretKeySpec = new SecretKeySpec(
								enCodeFormat, "AES");
						if (secretKey != null)
						{
							Cipher cipher = Cipher.getInstance("AES");// 创建密码器
							if (cipher != null)
							{
								cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);// 初始化
								result = new String(cipher.doFinal(byteContent));
							}
						}
					}
				}
			}
		}
		/**
		 * 4.出现异常，使用log4j jar包 根据properties配置文件，将异常信息写入日志中。
		 * 
		 */
		catch (Exception e)
		{

			log.error("@出现异常啦@", e);

		}

		return result;
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 */
	public static String byte2HexStr(byte bufferString[])
	{
		String result = null;// 最终返回的结果变量。
		/**
		 * 1.函数参数的验证 bufferString不为空，存在
		 */
		try
		{
			if (bufferString != null && bufferString.length > 0)
			{
				/**
				 * 2.使用StringBuffer类 将得到的字节数组连接成字符串，并作为结果返回
				 * 同时，在拼接字符时，将字节数组都与OXff与运算 读取传入值得低位 如果字节数组长度为1，添加0字符拼接
				 * 最终将所有字符转换成大写。
				 */
				StringBuffer stringBuffer = new StringBuffer();
				for (int i = 0; i < bufferString.length; i++)
				{
					String hex = Integer.toHexString(bufferString[i] & 0xFF);
					if (hex.length() == 1)
					{
						hex = '0' + hex;
					}
					stringBuffer.append(hex.toUpperCase());
				}
				result = stringBuffer.toString();
			}
		}
		catch (Exception e)
		{
			log.error("@出现异常啦@", e);
		}
		return result;
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */

	public static byte[] hexStr2Byte(String hexStr)
	{
		byte[] result = null;
		/**
		 * 1.方法参数验证 hexStr 不为空 且存在
		 */
		try
		{
			if (hexStr != null && hexStr.length() > 0)
			{
				/**
				 * 2.使用count变量将传进的字符长度减半记录，使用result字节数组动态创建，截取字符串的高位和低位
				 * 将得到的值进行运算并存储到result字节数组中 最后参数返回。
				 */
				int count = hexStr.length() / 2;
				result = new byte[count];
				for (int i = 0; i < count; i++)
				{

					int high = Integer.parseInt(
							hexStr.substring(i * 2, i * 2 + 1), 16);
					int low = Integer.parseInt(
							hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
					result[i] = (byte) (high * 16 + low);
				}
			}
		}
		catch (Exception e)
		{
			log.error("@出现异常啦@", e);
		}

		return result;
	}

	/*
	 * public static void main(String[] args) { try { byte[] content =
	 * "[mobile:18714747851],[password:111111],[IP:0:0:0:0:0:0:0:1],[time:1417688635234]"
	 * .getBytes(); byte[] password = "jessica@".getBytes(); // 加密 byte[]
	 * encryptResult = encode(content, password); String encryptResultStr =
	 * byte2HexStr(encryptResult); System.out.println("加密后：" +
	 * encryptResultStr); // 解密 byte[] decryptFrom =
	 * hexStr2Byte(encryptResultStr); byte[] decryptResult = decode(decryptFrom,
	 * password); String message = new String(decryptResult);
	 * System.out.println("解密后：" + message); } catch (Exception e) {
	 * log.error("@出现异常啦@", e); } }
	 */

	private final static String encoding = "UTF-8";

	/**
	 * AES加密
	 * 
	 * @param content
	 * @param password
	 * @return
	 */
	public static String encryptAES(String content, String password)
	{

		byte[] encryptResult = encrypt(content, password);
		String encryptResultStr = parseByte2HexStr(encryptResult);
		// BASE64位加密
		encryptResultStr = ebotongEncrypto(encryptResultStr);
		return encryptResultStr;
	}

	/**
	 * AES解密
	 * 
	 * @param encryptResultStr
	 * @param password
	 * @return
	 */
	public static String decrypt(String encryptResultStr, String password)
	{
		// BASE64位解密
		byte[] decryptResult = null;
		if (encryptResultStr != null && encryptResultStr.length() > 0
				&& password != null && password.length() > 0)
		{
			String decrpt = ebotongDecrypto(encryptResultStr);
			byte[] decryptFrom = parseHexStr2Byte(decrpt);
			decryptResult = decrypt(decryptFrom, password);
		}
		return new String(decryptResult);
	}

	public static String encodeBase64(String s) {
		try {
			return encodeBase64(s.getBytes(Constants.UTF8));
		} catch (UnsupportedEncodingException e) {
			return encodeBase64(s.getBytes());
		}
	}

	/**
	 *
	 * @param b
	 * @return
	 */
	public static String encodeBase64(byte[] b) {
		byte[] b2 = Base64.encodeBase64(b);

		try {
			return new String(b2, Constants.UTF8);
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
			return new String(b2);
		}
	}
	
	/**
	 * 加密字符串
	 */
	public static String ebotongEncrypto(String str)
	{
		String result = str;
		if (str != null && str.length() > 0)
		{
			try
			{
				byte[] encodeByte = str.getBytes(encoding);
				result=encodeBase64(encodeByte);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		// base64加密超过一定长度会自动换行 需要去除换行符
		return result.replaceAll("\r\n", "").replaceAll("\r", "")
				.replaceAll("\n", "");
	}

	/**
	 * 解密字符串
	 */
	public static String ebotongDecrypto(String str)
	{
		byte[] encodeByte = null;
		if (str != null && str.length() > 0)
		{
			encodeByte	=decodeBase64(str);
		}
		return new String(encodeByte);
	}

	public static byte[] decodeBase64(String s) {
		if (StringUtils.isEmpty(s)) {
			return null;
		}

		byte[] b;
		try {
			b = s.getBytes(Constants.UTF8);
		} catch (UnsupportedEncodingException e) {
			b = s.getBytes();
		}

		if (!Base64.isArrayByteBase64(b)) {
			return null;
		}

		return Base64.decodeBase64(b);
	}

	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	private static byte[] encrypt(String content, String password)
	{
		try
		{
			if (password != null && password.length() > 0)
			{

				KeyGenerator kgen = KeyGenerator.getInstance("AES");
				// 防止linux下 随机生成key
				SecureRandom secureRandom = SecureRandom
						.getInstance("SHA1PRNG");
				secureRandom.setSeed(password.getBytes());
				kgen.init(128, secureRandom);
				// kgen.init(128, new SecureRandom(password.getBytes()));
				SecretKey secretKey = kgen.generateKey();
				byte[] enCodeFormat = secretKey.getEncoded();
				SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
				Cipher cipher = Cipher.getInstance("AES");// 创建密码器
				byte[] byteContent = content.getBytes("utf-8");
				cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
				byte[] result = cipher.doFinal(byteContent);
				return result; // 加密
			}
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchPaddingException e)
		{
			e.printStackTrace();
		}
		catch (InvalidKeyException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		catch (IllegalBlockSizeException e)
		{
			e.printStackTrace();
		}
		catch (BadPaddingException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	private static byte[] decrypt(byte[] content, String password)
	{
		try
		{
			if (password != null && password.length() > 0)
			{

				KeyGenerator kgen = KeyGenerator.getInstance("AES");
				// 防止linux下 随机生成key
				SecureRandom secureRandom = SecureRandom
						.getInstance("SHA1PRNG");
				secureRandom.setSeed(password.getBytes());
				kgen.init(128, secureRandom);
				// kgen.init(128, new SecureRandom(password.getBytes()));
				SecretKey secretKey = kgen.generateKey();
				byte[] enCodeFormat = secretKey.getEncoded();
				SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
				Cipher cipher = Cipher.getInstance("AES");// 创建密码器
				cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
				byte[] result = cipher.doFinal(content);
				return result; // 加密
			}
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchPaddingException e)
		{
			e.printStackTrace();
		}
		catch (InvalidKeyException e)
		{
			e.printStackTrace();
		}
		catch (IllegalBlockSizeException e)
		{
			e.printStackTrace();
		}
		catch (BadPaddingException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[])
	{
		StringBuffer sb = new StringBuffer();
		if (buf != null && buf.length > 0)
		{
			for (int i = 0; i < buf.length; i++)
			{
				String hex = Integer.toHexString(buf[i] & 0xFF);
				if (hex.length() == 1)
				{
					hex = '0' + hex;
				}
				sb.append(hex.toUpperCase());
			}
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr)
	{
		byte[] result = null;
		if (hexStr != null && hexStr.length() > 0)
		{
			if (hexStr.length() < 1)
				return null;
			result = new byte[hexStr.length() / 2];
			for (int i = 0; i < hexStr.length() / 2; i++)
			{
				int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1),
						16);
				int low = Integer.parseInt(
						hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
				result[i] = (byte) (high * 16 + low);
			}
		}
		return result;
	}
}
