package com.sample_keygen.sample_keygen.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sample_keygen.sample_keygen.consts.Result;

public class ResponseData {

    // レスポンスJSONキー名
    final private static String RESKEY_RESULT           = "result";
    final private static String RESKEY_MESSAGE          = "message";
    final private static String RESKEY_KEY              = "key";
    final private static String RESKEY_PRIVATE          = "private";
    final private static String RESKEY_PUBLIC           = "public";
    final private static String RESKEY_ENCRYPTED        = "encrypted";
    final private static String RESKEY_DECRYPTED        = "decrypted";
    final private static String RESKEY_SIGNATURE        = "signature";
    final private static String RESKEY_VERIFY_RESULT    = "verify_result";
    final private static String RESKEY_IV               = "iv";

    private ObjectNode response;

    public ResponseData() {
        ObjectMapper mapper = new ObjectMapper();
        response = mapper.createObjectNode();
    }

    public ResponseData setResult(Result result) {
        this.response.put(RESKEY_RESULT, result.name());
        return this;
    }

    public ResponseData setMessage(String message) {
        this.response.put(RESKEY_MESSAGE, message);
        return this;
    }

    public ResponseData setPrivateKey(String privateKey) {
        ObjectNode keyNode = (ObjectNode) this.response.get(RESKEY_KEY);
        
        if (keyNode == null) {
            ObjectMapper mapper = new ObjectMapper();
            keyNode = mapper.createObjectNode();
            this.response.set(RESKEY_KEY, keyNode);
        }

        keyNode.put(RESKEY_PRIVATE, privateKey);
        return this;
    }

    public ResponseData setPublicKey(String publicKey) {
        ObjectNode keyNode = (ObjectNode) this.response.get(RESKEY_KEY);
        
        if (keyNode == null) {
            ObjectMapper mapper = new ObjectMapper();
            keyNode = mapper.createObjectNode();
            this.response.set(RESKEY_KEY, keyNode);
        }

        keyNode.put(RESKEY_PUBLIC, publicKey);
        return this;
    }

    public ResponseData setEncrypted(String encryptedData) {
        this.response.put(RESKEY_ENCRYPTED, encryptedData);
        return this;
    }

    public ResponseData setDecrypted(String decryptedData) {
        this.response.put(RESKEY_DECRYPTED, decryptedData);
        return this;
    }

    public ResponseData setSignature(String signature) {
        this.response.put(RESKEY_SIGNATURE, signature);
        return this;
    }

    public ResponseData setVerifyResult(Result result) {
        this.response.put(RESKEY_VERIFY_RESULT, result.name());
        return this;
    }

    public ResponseData setIv(String iv) {
        this.response.put(RESKEY_IV, iv);
        return this;
    }

    @Override
    public String toString() {
        return this.response.toString();
    }

    public String toErrorString(String errorMessage) {
        return this
                .setResult(Result.NG)
                .setMessage(errorMessage)
                .toString();
    }
}
