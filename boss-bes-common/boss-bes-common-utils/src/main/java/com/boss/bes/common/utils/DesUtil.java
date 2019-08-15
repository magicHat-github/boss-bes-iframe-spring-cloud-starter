package com.boss.bes.common.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

/**
 * @author Song
 * @date 2019/8/13 22:28
 */
public final class DesUtil {

    /**
     * 随机生成密钥
     */
    public static byte[] generateKey(){
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue()).getEncoded();
        return key;
    }

    /**
     * 加密方法
     * @param content：需要加密的内容，key:密钥
     * @return  密文
     */
    public static  String encrypt(String content,byte[] key){

        /**
         * 构建des
         */
        DES des = SecureUtil.des(key);

        /**
         * 加密为16进制
         */
        String encryptHex = des.encryptHex(content);
        return encryptHex;
    }

    /**
     * 解密
     * @param encryptHex：密文  key:密钥
     * @return  解密得到的明文
     */

    public static String decrypt(String encryptHex, byte[] key){

        DES des = SecureUtil.des(key);
        //解密为源字符串
        String decryptStr = des.decryptStr(encryptHex);

        return decryptStr;

    }

}
