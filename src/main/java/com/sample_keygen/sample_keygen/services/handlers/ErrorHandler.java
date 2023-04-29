package com.sample_keygen.sample_keygen.services.handlers;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class ErrorHandler {

    public static String createMessageByError(Exception e) {

        // スタックトレースを出力
        e.printStackTrace();

        // エラーに応じたメッセージを取得
        String errMessage = "";
        if (e instanceof InvalidKeyException || e instanceof InvalidKeySpecException) {
            errMessage = "鍵が正しくありません。";
        } else if (e instanceof NoSuchAlgorithmException || e instanceof NoSuchPaddingException) {
            errMessage = "アルゴリズムが正しくありません。";
        } else if (e instanceof InvalidAlgorithmParameterException) {
            errMessage = "IVが正しくありません。";
        } else if (e instanceof IllegalBlockSizeException) {
            errMessage = "データサイズが正しくありません。パディングを行うアルゴリズムを指定してください。";
        } else if (e instanceof BadPaddingException) {
            errMessage = "パディング指定が正しくありません。";
        } else if (e instanceof SignatureException) {
            errMessage = "署名に失敗しました。";
        } else {
            errMessage = "予期せぬエラーが発生しました。";
        }

        return errMessage;
    }
}
