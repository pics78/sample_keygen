package com.sample_keygen.sample_keygen.services.managers;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.sample_keygen.sample_keygen.consts.KeyType;
import com.sample_keygen.sample_keygen.models.SignatureData;
import com.sample_keygen.sample_keygen.services.EcdsaSigner;
import com.sample_keygen.sample_keygen.services.EcdsaVerifier;
import com.sample_keygen.sample_keygen.services.RsaSigner;
import com.sample_keygen.sample_keygen.services.RsaVerifier;
import com.sample_keygen.sample_keygen.services.Signer;
import com.sample_keygen.sample_keygen.services.Verifier;

public class SignatureManager {

    // インスタンス生成無効化
    private SignatureManager() {}

    public static Signer createSigner(SignatureData signatureData)
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {

        String algorithm = signatureData.getAlgorithm();
        String privateKey = signatureData.getKey();

        // アルゴリズム名から鍵種別を取得
		KeyType algorithmType = KeyType
				.ofSignatureAlgorithm(algorithm);

        if (algorithmType == KeyType.RSA) {
            return RsaSigner.init(algorithm, privateKey);

        } else if (algorithmType == KeyType.EC) {
            return EcdsaSigner.init(algorithm, privateKey);

        } else {
            return null;
        }
    }

    public static Verifier createVerifier(SignatureData signatureData)
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {

        String algorithm = signatureData.getAlgorithm();
        String publicKey = signatureData.getKey();

        // アルゴリズム名から鍵種別を取得
		KeyType algorithmType = KeyType
				.ofSignatureAlgorithm(algorithm);

        if (algorithmType == KeyType.RSA) {
            return RsaVerifier.init(algorithm, publicKey);

        } else if (algorithmType == KeyType.EC) {
            return EcdsaVerifier.init(algorithm, publicKey);

        } else {
            return null;
        }
    }
}
