package cn.promptness.blog.support.utils;

import org.springframework.util.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Random;

/**
 * @author : Lynn
 * @date : 2019-04-19 09:10
 */
public class HashUtils {


    private static final String SHA_1 = "SHA-1";
    private static final String MD5 = "MD5";
    private static final String HMAC_SHA256 = "HmacSHA256";
    private static final String BASE_SOURCE = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final String BASE_NUM = "0123456789";

    /**
     * 系统获取密码
     *
     * @param plainPassword 明文
     * @param salt          盐
     * @return 密文
     */
    public static String encryptPassword(String plainPassword, String salt) {
        byte[] digest = digest(plainPassword.getBytes(), SHA_1, salt.getBytes(), 1024);
        return parseByteToHexStr(digest);
    }

    /**
     * 系统验证密码(盐值是八位的字符串)
     *
     * @param plainPassword 明文
     * @param password      密文
     * @return result
     */
    public static boolean validatePassword(String plainPassword, String password, String saltStr) {
        if (StringUtils.isEmpty(plainPassword) || StringUtils.isEmpty(password) || StringUtils.isEmpty(saltStr)) {
            return false;
        }
        byte[] salt = saltStr.getBytes();
        byte[] hashPassword = digest(plainPassword.getBytes(), SHA_1, salt, 1024);
        return password.equals(parseByteToHexStr(hashPassword));
    }

    /**
     * 获取随机盐值
     */
    public static String getRandomSalt(int length) {
        return getString(length, BASE_SOURCE);
    }

    /**
     * 获取随机盐值
     */
    public static String getRandomCode(int length) {
        return getString(length, BASE_NUM);
    }

    private static String getString(int length, String base) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * byte字节数组转换为Hex字符串
     */
    private static String parseByteToHexStr(byte[] buf) {
        // Create Hex String
        StringBuilder hexString = new StringBuilder();
        for (byte aBuf : buf) {
            String hex = Integer.toHexString(aBuf & 0xFF);
            if (hex.length() == 1) {
                hexString.append(0);
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * Hex字符串转换为byte字节数组
     */
    private static byte[] parseHexStrToByte(String hexStr) {
        if (hexStr.length() < 1) {
            return new byte[]{};
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int value = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 2), 16);
            result[i] = (byte) value;
        }
        return result;
    }


    /**
     * 对字符串进行散列, 支持md5与sha1算法.
     *
     * @param input      明文
     * @param salt       盐
     * @param iterations hash次数
     * @return 密文
     */
    private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
        byte[] result = new byte[]{};
        try {

            MessageDigest digest = MessageDigest.getInstance(algorithm);

            if (salt != null) {
                digest.update(salt);
            }

            result = digest.digest(input);

            for (int i = 1; i < iterations; i++) {
                digest.reset();
                result = digest.digest(result);
            }
            return result;
        } catch (GeneralSecurityException e) {
            return result;
        }
    }

    /**
     * 生成 MD5
     *
     * @param data 待处理数据
     * @return MD5结果
     */
    public static String md5(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance(MD5);
        byte[] array = md.digest(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 生成 HMACSHA256
     *
     * @param data 待处理数据
     * @param key  密钥
     * @return 加密结果
     * @throws Exception
     */
    public static String hmacSHA256(String data, String key) throws Exception {
        Mac hmacSHA256 = Mac.getInstance(HMAC_SHA256);
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), HMAC_SHA256);
        hmacSHA256.init(secretKey);
        byte[] array = hmacSHA256.doFinal(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
        }
        return sb.toString().toUpperCase();
    }

}
