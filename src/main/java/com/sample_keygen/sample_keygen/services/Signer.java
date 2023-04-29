package com.sample_keygen.sample_keygen.services;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

import com.sample_keygen.sample_keygen.utils.TypeConvertor;

public abstract class Signer {

    protected Signature signature;

    protected static Signature initSignature(String keyType, String algorithm, String privateKeyHex)
            throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {

        // 16進文字列の鍵をバイト配列に変換
        byte[] keyBytes = TypeConvertor.hexToBytes(privateKeyHex);

        // 与えられたアルゴリズムと鍵情報から秘密鍵オブジェクトを生成
        KeyFactory factoty = KeyFactory.getInstance(keyType);
        KeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        PrivateKey privateKey = factoty.generatePrivate(keySpec);

        Signature signature = Signature.getInstance(algorithm);
        signature.initSign(privateKey);

        return signature;
    }

    public String doSign(String data) throws SignatureException {

        this.signature.update(data.getBytes());
        byte[] sign = signature.sign();
        
        return TypeConvertor.bytesToHex(sign);
    }
}
