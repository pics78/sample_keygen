package com.sample_keygen.sample_keygen.utils;

import java.security.Key;
import java.security.KeyPair;
import java.util.Map;

public class KeyFormatConverter {

    public static final String PRIVATE_KEY = "PRIVATE_KEY";
    public static final String PUBLIC_KEY  = "PUBLIC_KEY";
    
    public static Map<String, String> keypairToString(KeyPair keyPair) {

        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        byte[] publicKeyBytes  = keyPair.getPublic().getEncoded();

        return Map.of(
                PRIVATE_KEY, TypeConvertor.bytesToHex(privateKeyBytes),
                PUBLIC_KEY,  TypeConvertor.bytesToHex(publicKeyBytes));
    }

    public static String keyToString(Key key) {
        byte[] keyBytes = key.getEncoded();
        return TypeConvertor.bytesToHex(keyBytes);
    }
}
