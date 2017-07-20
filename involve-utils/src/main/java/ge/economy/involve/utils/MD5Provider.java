package ge.economy.involve.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by NINO on 7/20/2017.
 */
public class MD5Provider {

    public static String md5(String input) {

        String md5;

        if (null == input || input.isEmpty()) {
            return null;
        }
        try {
            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return md5;
    }

    public static String doubleMd5(String text) {
        return md5(md5(text));
    }
}
