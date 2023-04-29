package com.sample_keygen.sample_keygen.services.interfaces;

import java.security.SignatureException;

public interface Signable {

    public String doSign(String data) throws SignatureException;
}
