package com.sample_keygen.sample_keygen.services;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;

import com.sample_keygen.sample_keygen.services.interfaces.Signable;

public class EcdsaSigner extends Signer implements Signable {

    private static final String EC = "EC";

    // コンストラクタの外部アクセス無効化
    private EcdsaSigner(Signature signature) {
        this.signature = signature;
    }

    public static EcdsaSigner init(String algorithm, String privateKeyHex)
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
    
        Signature signature = Signer.initSignature(EC, algorithm, privateKeyHex);
        return new EcdsaSigner(signature);
    }
}
