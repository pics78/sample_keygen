package com.sample_keygen.sample_keygen.services;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import com.sample_keygen.sample_keygen.services.interfaces.Decryptable;
import com.sample_keygen.sample_keygen.utils.TypeConvertor;

public class RsaDecryptor extends Decryptor implements Decryptable {

    private static final String RSA = "RSA";

    // コンストラクタの外部アクセス無効化
    private RsaDecryptor(Cipher decrypter) {
        this.decrypter = decrypter;
    }

    public static RsaDecryptor init(String algorithm, String publicKeyHex)
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException {
    
        // 16進文字列の鍵をバイト配列に変換
        byte[] keyBytes = TypeConvertor.hexToBytes(publicKeyHex);

        // 与えられたアルゴリズムと鍵情報から公開鍵オブジェクトを生成
        KeyFactory factoty = KeyFactory.getInstance(RSA);
        KeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        RSAPublicKey publicKey = (RSAPublicKey) factoty.generatePublic(keySpec);

        // 指定したアルゴリズムで復号するCipherオブジェクトを生成
        Cipher decrypter = Cipher.getInstance(algorithm);
        decrypter.init(Cipher.DECRYPT_MODE, publicKey);

        return new RsaDecryptor(decrypter);
    }
}
