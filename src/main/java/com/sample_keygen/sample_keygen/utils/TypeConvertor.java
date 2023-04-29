package com.sample_keygen.sample_keygen.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class TypeConvertor {
    
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = Hex.encodeHex(bytes);
        return new String(hexChars).toUpperCase();
    }

    public static byte[] hexToBytes(String hex) {

        byte[] decoded = null;

        try {
            decoded = Hex.decodeHex(hex);
        } catch (DecoderException e) {
            e.printStackTrace();
        }

        return decoded;
    }
}
