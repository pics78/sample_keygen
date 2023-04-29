package com.sample_keygen.sample_keygen.services;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.sample_keygen.sample_keygen.services.interfaces.Encryptable;
import com.sample_keygen.sample_keygen.utils.TypeConvertor;

public class AesEncryptor extends Encryptor implements Encryptable {

    private static final String AES = "AES";

    private String ivHex;

    // コンストラクタの外部アクセス無効化
    private AesEncryptor(Cipher encrypter, String ivHex) {
        this.encrypter = encrypter;
        this.ivHex = ivHex;
    }

    public static AesEncryptor init(String algorithm, String commonKeyHex)
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException, InvalidAlgorithmParameterException {

        // IVを生成
        SecureRandom secureRandom = new SecureRandom();
        byte[] ivBytes = new byte[16];
        secureRandom.nextBytes(ivBytes);

        return AesEncryptor.init(algorithm, commonKeyHex, TypeConvertor.bytesToHex(ivBytes));
    }

    public static AesEncryptor init(String algorithm, String commonKeyHex, String ivHex)
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException, InvalidAlgorithmParameterException {

        // 16進文字列の鍵とIVをバイト配列に変換
        byte[] keyBytes = TypeConvertor.hexToBytes(commonKeyHex);
        byte[] ivBytes = TypeConvertor.hexToBytes(ivHex);

        // 与えられたアルゴリズムと鍵情報から秘密鍵オブジェクトとIVオブジェクトを生成
        SecretKey secretKey = new SecretKeySpec(keyBytes, AES);
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        // 指定したアルゴリズムで暗号化するCipherオブジェクトを生成
        Cipher encrypter = Cipher.getInstance(algorithm);
        encrypter.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        return new AesEncryptor(encrypter, ivHex);
    }

    public String getIvHex() {
        return this.ivHex;
    }
}
