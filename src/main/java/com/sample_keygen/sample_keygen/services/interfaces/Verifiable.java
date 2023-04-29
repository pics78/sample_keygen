package com.sample_keygen.sample_keygen.services.interfaces;

import java.security.SignatureException;

public interface Verifiable {

    public boolean doVerify(String data, String sign) throws SignatureException;
}
