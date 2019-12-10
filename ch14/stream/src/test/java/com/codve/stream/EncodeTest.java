package com.codve.stream;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author admin
 * @date 2019/12/10 17:20
 */
public class EncodeTest {

    @Test
    void shaTest() throws NoSuchAlgorithmException {
        String msg = "hello, world";
        String result = Hashing.sha256()
                .hashString(msg, UTF_8)
                .toString();
        System.out.println(result);
    }


    @Test
    void md5Test2() {
        String msg = "hello, world";
        SecretKey key = new SecretKeySpec("secret".getBytes(UTF_8), "HmacMD5");
        String result = Hashing.hmacMd5(key)
                .hashString(msg, UTF_8)
                .toString();
        System.out.println(result);
    }
    @Test
    void md5Test() throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("md5");
        String msg = "hello, world";

        digest.update(msg.getBytes(UTF_8));
        byte[] bytes = digest.digest();
        String result = DatatypeConverter.printHexBinary(bytes);
        System.out.println(result);
    }
}
