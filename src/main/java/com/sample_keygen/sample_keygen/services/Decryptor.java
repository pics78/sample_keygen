package com.sample_keygen.sample_keygen.services;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

import com.sample_keygen.sample_keygen.utils.TypeConvertor;

public abstract class Decryptor {

    protected Cipher decrypter;

    public String doDecrypt(String data)
            throws IllegalBlockSizeException, BadPaddingException {

        // 暗号化データは16進文字列変換されていることを想定
        byte[] result = this.decrypter
                .doFinal(TypeConvertor.hexToBytes(data));

        return new String(result);
    }
}
