package com.sample_keygen.sample_keygen.services;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;

import com.sample_keygen.sample_keygen.services.interfaces.Signable;

public class RsaSigner extends Signer implements Signable {

    private static final String RSA = "RSA";

    // コンストラクタの外部アクセス無効化
    private RsaSigner(Signature signature) {
        this.signature = signature;
    }

    public static RsaSigner init(String algorithm, String privateKeyHex)
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
    
        Signature signature = Signer.initSignature(RSA, algorithm, privateKeyHex);
        return new RsaSigner(signature);
    }
}
