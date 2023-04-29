package com.sample_keygen.sample_keygen.services;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;

import com.sample_keygen.sample_keygen.utils.TypeConvertor;

public abstract class Verifier {

    protected Signature signature;

    protected static Signature initVerify(String keyType, String algorithm, String publicKeyHex)
            throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {

        // 16進文字列の鍵をバイト配列に変換
        byte[] keyBytes = TypeConvertor.hexToBytes(publicKeyHex);

        // 与えられたアルゴリズムと鍵情報から公開鍵オブジェクトを生成
        KeyFactory factoty = KeyFactory.getInstance(keyType);
        KeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        PublicKey publicKey = factoty.generatePublic(keySpec);

        Signature signature = Signature.getInstance(algorithm);
        signature.initVerify(publicKey);

        return signature;
    }

    public boolean doVerify(String data, String sign) throws SignatureException {

        signature.update(data.getBytes());
        byte[] signBytes = TypeConvertor.hexToBytes(sign);

        return signature.verify(signBytes);
    }
}
