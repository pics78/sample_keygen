package com.sample_keygen.sample_keygen.services.managers;

import java.security.InvalidParameterException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.crypto.KeyGenerator;

import com.sample_keygen.sample_keygen.consts.KeyType;
import com.sample_keygen.sample_keygen.models.KeyGenResult;
import com.sample_keygen.sample_keygen.models.KeygenData;
import com.sample_keygen.sample_keygen.utils.KeyFormatConverter;

public class KeyGenerationManager {

    public static KeyGenResult generateKey(KeygenData keygenData) {

        KeyType keyType = KeyType.ofType(keygenData.getKeyType());
		int keySize = keygenData.getKeyLength();
        KeyGenResult kgResult = null;

        try {
            if (keyType == KeyType.RSA) {
                KeyPairGenerator generator = KeyPairGenerator.getInstance(keyType.name());
                generator.initialize(keySize);

                // RSA鍵ペア生成
                KeyPair keyPair = generator.generateKeyPair();
                Map<String, String> keyPairMap = KeyFormatConverter.keypairToString(keyPair);

                kgResult = new KeyGenResult();
                kgResult.setPrivateKey(keyPairMap.get(KeyFormatConverter.PRIVATE_KEY));
                kgResult.setPublicKey(keyPairMap.get(KeyFormatConverter.PUBLIC_KEY));

            } else if (keyType == KeyType.EC) {
                KeyPairGenerator generator = KeyPairGenerator.getInstance(keyType.name());
                generator.initialize(keySize);

                // EC鍵ペア生成
                KeyPair keyPair = generator.generateKeyPair();
                Map<String, String> keyPairMap = KeyFormatConverter.keypairToString(keyPair);

                kgResult = new KeyGenResult();
                kgResult.setPrivateKey(keyPairMap.get(KeyFormatConverter.PRIVATE_KEY));
                kgResult.setPublicKey(keyPairMap.get(KeyFormatConverter.PUBLIC_KEY));

            } else if (keyType == KeyType.AES) {
                KeyGenerator generator = KeyGenerator.getInstance(keyType.name());
                generator.init(keySize);

                // AES鍵生成
                Key key = generator.generateKey();
                String commonKey = KeyFormatConverter.keyToString(key);

                kgResult = new KeyGenResult();
                kgResult.setPrivateKey(commonKey);
            }

        } catch (NoSuchAlgorithmException | InvalidParameterException e) {
            e.printStackTrace();
        }

        return kgResult;
    }
}
