package cn.edu.nju.nowcode.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by cong on 2018-05-18.
 */
public class SecurityUtil {

    /**
     * md5加密字符串
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String md5(String str,String salt)  {
        str+=salt;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(str.getBytes());
        return new BigInteger(1,md.digest()).toString(16);
    }

}
