package com.millionaire.millionaireclientweb.util;



import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

    /**
     DES加密介绍
     DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。DES加密算法出自IBM的研究，
     后来被美国政府正式采用，之后开始广泛流传，但是近些年使用越来越少，因为DES使用56位密钥，以现代计算能力，
     24小时内即可被破解。虽然如此，在某些简单应用中，我们还是可以使用DES加密算法，本文简单讲解DES的JAVA实现
     。
     注意：DES加密和解密过程中，密钥长度都必须是8的倍数
     */
    public class DESUtil {
        /**
         * 加密数据
         * @param encryptString
         * @param encryptKey
         * @return
         * @throws Exception
         */
        public static String encryptDES(String encryptString, String encryptKey) throws Exception {
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(getKey(encryptKey), "DES"));
            byte[] encryptedData = cipher.doFinal(encryptString.getBytes("UTF-8"));
            return Base64.encodeBase64String(encryptedData);
        }

        /**
         * key  不足8位补位
         * @param keyRule
         */
        public static byte[] getKey(String keyRule) {
            Key key = null;
            byte[] keyByte = keyRule.getBytes();
            // 创建一个空的八位数组,默认情况下为0
            byte[] byteTemp = new byte[8];
            // 将用户指定的规则转换成八位数组
            for (int i = 0; i < byteTemp.length && i < keyByte.length; i++) {
                byteTemp[i] = keyByte[i];
            }
            key = new SecretKeySpec(byteTemp, "DES");
            return key.getEncoded();
        }

        /***
         * 解密数据
         * @param decryptString
         * @param decryptKey
         * @return
         * @throws Exception
         */

        public static String decryptDES(String decryptString, String decryptKey) throws Exception {

            byte[] sourceBytes = Base64.decodeBase64(decryptString);
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(getKey(decryptKey), "DES"));
            byte[] decoded = cipher.doFinal(sourceBytes);
            return new String(decoded, "UTF-8");

        }

        public static void main(String[] args) throws Exception {
            String clearText = "测试";
            String key = "atnuomi";//密钥
            System.out.println("明文："+clearText+"\n密钥："+key);
            String encryptText = encryptDES(clearText, key);
            System.out.println("加密后："+encryptText);
            String decryptText = decryptDES(encryptText, key);
            System.out.println("解密后："+decryptText);
        }


}
