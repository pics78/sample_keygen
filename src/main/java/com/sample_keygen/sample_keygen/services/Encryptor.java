package com.sample_keygen.sample_keygen.services;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

import com.sample_keygen.sample_keygen.utils.TypeConvertor;

public abstract class Encryptor {

    protected Cipher encrypter;

    public String doEncrypt(String data)
            throws IllegalBlockSizeException, BadPaddingException {

        byte[] result = this.encrypter.doFinal(data.getBytes());
        return TypeConvertor.bytesToHex(result);
    }
}
