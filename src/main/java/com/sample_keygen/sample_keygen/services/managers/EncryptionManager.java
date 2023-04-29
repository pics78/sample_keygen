package com.sample_keygen.sample_keygen.services.managers;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.NoSuchPaddingException;

import com.sample_keygen.sample_keygen.consts.KeyType;
import com.sample_keygen.sample_keygen.models.CipherData;
import com.sample_keygen.sample_keygen.services.AesDecryptor;
import com.sample_keygen.sample_keygen.services.AesEncryptor;
import com.sample_keygen.sample_keygen.services.Decryptor;
import com.sample_keygen.sample_keygen.services.Encryptor;
import com.sample_keygen.sample_keygen.services.RsaDecryptor;
import com.sample_keygen.sample_keygen.services.RsaEncryptor;

public class EncryptionManager {

    // インスタンス生成無効化
    private EncryptionManager() {}

    // 暗号化時に生成される各種パラメータを格納するための領域
    private static Map<String, String> ENCRYPTION_WORK_MAP = new HashMap<>();

    // 生成されたIVを指定するキー
    final private static String ENCRYPTION_WORK_KEY_IV = "ENCRYPTION_WORK_KEY_IV";

    public static Encryptor createEncryptor(CipherData cipherData)
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException, InvalidAlgorithmParameterException {

        String algorithm = cipherData.getAlgorithm();
        String privateKey = cipherData.getKey();

        // アルゴリズム名から鍵種別を取得
		KeyType algorithmType = KeyType
				.ofEncryptionAlgorithm(algorithm);

        if (algorithmType == KeyType.RSA) {
            return RsaEncryptor.init(algorithm, privateKey);

        } else if (algorithmType == KeyType.AES) {
            String ivHex = cipherData.getIv();

            if (ivHex != null && !ivHex.isEmpty()) {
                return AesEncryptor.init(algorithm, privateKey, ivHex);
            } else {
                AesEncryptor aesEncryptor = AesEncryptor.init(algorithm, privateKey);
                // 生成されたIVを取得
                setIv(aesEncryptor.getIvHex());

                return aesEncryptor;
            }

        } else {
            return null;
        }
    }

    public static Decryptor createDecryptor(CipherData cipherData)
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException, InvalidAlgorithmParameterException {

        String algorithm = cipherData.getAlgorithm();
        String publicKey = cipherData.getKey();

        // アルゴリズム名から鍵種別を取得
		KeyType algorithmType = KeyType
				.ofEncryptionAlgorithm(algorithm);

        if (algorithmType == KeyType.RSA) {
            return RsaDecryptor.init(algorithm, publicKey);

        } else if (algorithmType == KeyType.AES) {
            String ivHex = cipherData.getIv();
            return AesDecryptor.init(algorithm, publicKey, ivHex);

        } else {
            return null;
        }
    }

    public static boolean hasIv() {
        return ENCRYPTION_WORK_MAP
                .containsKey(ENCRYPTION_WORK_KEY_IV);
    }

    public static String getIv() {
        return ENCRYPTION_WORK_MAP
                .get(ENCRYPTION_WORK_KEY_IV);
    }

    private static void setIv(String iv) {
        ENCRYPTION_WORK_MAP
                .put(ENCRYPTION_WORK_KEY_IV, iv);
    }
}
