package com.sample_keygen.sample_keygen.consts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum KeyType {
    RSA,
    EC,
    AES;

    public static KeyType ofType(String type) {
        for (KeyType keyType : KeyType.values()) {
            if (keyType.name().equals(type)) {
                return keyType;
            }
        }
        return null;
    }

    public static KeyType ofEncryptionAlgorithm(String algorithm) {
		Pattern pattern = Pattern.compile("^([^/]+)/.*$");
		Matcher matcher = pattern.matcher(algorithm);
		matcher.find();

		return KeyType.ofType(
                matcher.group(1)); // ex. RSA/ECB/... -> RSA
    }

    public static KeyType ofSignatureAlgorithm(String algorithm) {
		Pattern pattern = Pattern.compile("^.*with([A-Z]+)$");
		Matcher matcher = pattern.matcher(algorithm);
		matcher.find();

        // ECDSAの場合は鍵種別としてECに変換
        String type = matcher.group(1);
        if ("ECDSA".equals(type)) {
            type = EC.name();
        }

		return KeyType.ofType(type); // ex. SHA256withECDSA -> EC
    }
}
