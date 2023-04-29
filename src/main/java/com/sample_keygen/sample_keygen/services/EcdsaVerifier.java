package com.sample_keygen.sample_keygen.services;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;

import com.sample_keygen.sample_keygen.services.interfaces.Verifiable;

public class EcdsaVerifier extends Verifier implements Verifiable {

    private static final String EC = "EC";

    // コンストラクタの外部アクセス無効化
    private EcdsaVerifier(Signature signature) {
        this.signature = signature;
    }

    public static EcdsaVerifier init(String algorithm, String publicKeyHex)
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
    
        Signature signature = Verifier.initVerify(EC, algorithm, publicKeyHex);
        return new EcdsaVerifier(signature);
    }
}
