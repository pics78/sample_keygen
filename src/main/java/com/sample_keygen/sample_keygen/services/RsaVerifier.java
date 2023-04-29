package com.sample_keygen.sample_keygen.services;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;

import com.sample_keygen.sample_keygen.services.interfaces.Verifiable;

public class RsaVerifier extends Verifier implements Verifiable {

    private static final String RSA = "RSA";

    // コンストラクタの外部アクセス無効化
    private RsaVerifier(Signature signature) {
        this.signature = signature;
    }

    public static RsaVerifier init(String algorithm, String publicKeyHex)
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
    
        Signature signature = Verifier.initVerify(RSA, algorithm, publicKeyHex);
        return new RsaVerifier(signature);
    }
}
