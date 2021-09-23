package com.henry.home.oauth.starter.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RSAUtil {
    private static final String KEY_ALGORITHM = "RSA";

    public static String serializePublicKey(final RSAPublicKey key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public static String serializePrivateKey(final RSAPrivateKey key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public static RSAPublicKey deSerializePublicKey(String publicKeyStr) {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyStr));

        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSAUtil.KEY_ALGORITHM);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception var4) {
            throw new RuntimeException("get rsa public key failed");
        }
    }

    public static RSAPrivateKey deSerializePrivateKey(String privateKeyStr) {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyStr));

        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSAUtil.KEY_ALGORITHM);
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (Exception var4) {
            throw new RuntimeException("get rsa private key failed");
        }
    }
}
