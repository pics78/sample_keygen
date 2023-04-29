package com.sample_keygen.sample_keygen.models;

/**
 * 生成された鍵を格納するクラス
 */
public class KeyGenResult {

    private String privateKey;
    private String publicKey;

    public String getPrivateKey() {
        return this.privateKey != null
                ? this.privateKey : "";
    }

    public void setPrivateKey(final String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return this.publicKey != null
                ? this.publicKey : "";
    }

    public void setPublicKey(final String publicKey) {
        this.publicKey = publicKey;
    }
}
