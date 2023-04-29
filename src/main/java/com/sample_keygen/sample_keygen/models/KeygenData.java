package com.sample_keygen.sample_keygen.models;

import java.io.Serializable;

public class KeygenData implements Serializable {

    private int keyLength;
    private String keyType;

    public int getKeyLength() {
        return this.keyLength;
    }

    public void setKeyLength(int keyLength) {
        this.keyLength = keyLength;
    }

    public String getKeyType() {
        return this.keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }
}
