package com.me.encrypt;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zs
 * @date 2021/9/9.
    一个敏感信息加密的demo，使用的AES加密模式选用ECB模式，无偏移量，以Base64的方式输出
 */
public class AES {
    public static byte[] HexStringToByte(String hex) {
        ByteBuffer bf = ByteBuffer.allocate(hex.length() / 2);
        for (int i = 0; i < hex.length(); i++) {
            String hexStr = hex.charAt(i) + "";
            i++;
            hexStr += hex.charAt(i);
            byte b = (byte) Integer.parseInt(hexStr, 16);
            bf.put(b);
        }
        return bf.array();
    }

    // 加密
    public static String Encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }

        // KeyGenerator kgen = KeyGenerator.getInstance("AES");

        byte[] enCodeFormat = HexStringToByte(sKey);
        SecretKeySpec skeySpec = new SecretKeySpec(enCodeFormat, "AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); // "算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

        return new Base64().encodeToString(encrypted); // 此处使用 BASE64 做转码功能，同时能起到 2 次加密的作用。
    }

    // 解密
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }

            byte[] enCodeFormat = HexStringToByte(sKey);
            SecretKeySpec skeySpec = new SecretKeySpec(enCodeFormat, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = new Base64().decode(sSrc); // 先用 base64 解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, "utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        /*
         * 此处使用 AES-128-ECB 加密模式，key 需要为 16 位。
         */
        String cKey = "00c61bd99857871bd39a7fb04b8cfcca24277abe5a4c7120";
        // 需要加密的字串
        String cSrc = "测试";
        System.out.println(cSrc);
        // 加密
        String enString = Encrypt(cSrc, cKey);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        System.out.println("时间：" + simpleDateFormat.format(new Date()) + "加密后的字串是：" + enString);
        // 解密
        String DeString = Decrypt(enString, cKey);
        System.out.println("时间：" + simpleDateFormat.format(new Date()) + "解密后的字串是：" + DeString);
    }

}
