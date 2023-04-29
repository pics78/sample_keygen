package com.sample_keygen.sample_keygen.services;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.sample_keygen.sample_keygen.services.interfaces.Decryptable;
import com.sample_keygen.sample_keygen.utils.TypeConvertor;

public class AesDecryptor extends Decryptor implements Decryptable {

    private static final String AES = "AES";

    // コンストラクタの外部アクセス無効化
    private AesDecryptor(Cipher decrypter) {
        this.decrypter = decrypter;
    }

    public static AesDecryptor init(String algorithm, String commonKeyHex, String ivHex)
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException, InvalidAlgorithmParameterException {

        // 16進文字列の鍵とIVをバイト配列に変換
        byte[] keyBytes = TypeConvertor.hexToBytes(commonKeyHex);
        byte[] ivBytes = TypeConvertor.hexToBytes(ivHex);

        // 与えられたアルゴリズムと鍵情報から秘密鍵オブジェクトとIVオブジェクトを生成
        SecretKey secretKey = new SecretKeySpec(keyBytes, AES);
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        // 指定したアルゴリズムで復号するCipherオブジェクトを生成
        Cipher decrypter = Cipher.getInstance(algorithm);
        decrypter.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        return new AesDecryptor(decrypter);
    }
}
