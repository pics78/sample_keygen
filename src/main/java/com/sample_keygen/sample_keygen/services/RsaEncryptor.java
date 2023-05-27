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

import com.sample_keygen.sample_keygen.services.interfaces.Encryptable;
import com.sample_keygen.sample_keygen.utils.TypeConvertor;

public class RsaEncryptor extends Encryptor implements Encryptable {

    private static final String RSA = "RSA";

    // コンストラクタの外部アクセス無効化
    private RsaEncryptor(Cipher encrypter) {
        this.encrypter = encrypter;
    }

    public static RsaEncryptor init(String algorithm, String privateKeyHex)
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException {
    
        // 16進文字列の鍵をバイト配列に変換
        byte[] keyBytes = TypeConvertor.hexToBytes(privateKeyHex);

        // 与えられたアルゴリズムと鍵情報から秘密鍵オブジェクトを生成
        KeyFactory factoty = KeyFactory.getInstance(RSA);
        KeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        RSAPublicKey publicKey = (RSAPublicKey) factoty.generatePublic(keySpec);

        // 指定したアルゴリズムで暗号化するCipherオブジェクトを生成
        Cipher encrypter = Cipher.getInstance(algorithm);
        encrypter.init(Cipher.ENCRYPT_MODE, publicKey);

        return new RsaEncryptor(encrypter);
    }
}
