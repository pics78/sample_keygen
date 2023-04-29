package com.sample_keygen.sample_keygen.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample_keygen.sample_keygen.consts.Result;
import com.sample_keygen.sample_keygen.models.CipherData;
import com.sample_keygen.sample_keygen.models.KeyGenResult;
import com.sample_keygen.sample_keygen.models.KeygenData;
import com.sample_keygen.sample_keygen.models.ResponseData;
import com.sample_keygen.sample_keygen.models.SignatureData;
import com.sample_keygen.sample_keygen.services.Decryptor;
import com.sample_keygen.sample_keygen.services.Encryptor;
import com.sample_keygen.sample_keygen.services.Signer;
import com.sample_keygen.sample_keygen.services.Verifier;
import com.sample_keygen.sample_keygen.services.handlers.ErrorHandler;
import com.sample_keygen.sample_keygen.services.managers.EncryptionManager;
import com.sample_keygen.sample_keygen.services.managers.KeyGenerationManager;
import com.sample_keygen.sample_keygen.services.managers.SignatureManager;

@RestController
@RequestMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
public class AppApiController {

	ResponseData responseData = new ResponseData();

	@PostMapping("/exec-keygen")
	public String execKeygen(@RequestBody KeygenData keygenData) {
		try {
			// 鍵生成実行
			KeyGenResult kgResult = KeyGenerationManager.generateKey(keygenData);

			if (kgResult == null) {
				return responseData.toErrorString("鍵生成に失敗しました。");
			}

			return responseData
					.setResult(Result.OK)
					.setPrivateKey(kgResult.getPrivateKey())
					.setPublicKey(kgResult.getPublicKey())
					.toString();

		} catch (Exception e) {
			String errMessage = ErrorHandler.createMessageByError(e);
			return responseData.toErrorString(errMessage);
		}
	}

	@PostMapping("/exec-encrypt")
	public String execEncrypt(@RequestBody CipherData cipherData) {
		try {

			Encryptor encryptor = EncryptionManager.createEncryptor(cipherData);

			if (encryptor == null) {
				return responseData.toErrorString("対象外の鍵種別を使用したアルゴリズムです。");
			}

			String encryptResult = encryptor.doEncrypt(cipherData.getData());

			// レスポンスの生成
			ResponseData res = (new ResponseData())
					.setResult(Result.OK)
					.setEncrypted(encryptResult);

			if (EncryptionManager.hasIv()) {
				// IVが生成されていた場合はレスポンスに含める
				res.setIv(EncryptionManager.getIv());
			}

			return res.toString();

		} catch (Exception e) {
			String errMessage = ErrorHandler.createMessageByError(e);
			return responseData.toErrorString(errMessage);
		}
	}

	@PostMapping("/exec-decrypt")
	public String execDecrypt(@RequestBody CipherData cipherData) {
		try {

			Decryptor decryptor = EncryptionManager.createDecryptor(cipherData);

			if (decryptor == null) {
				return responseData.toErrorString("対象外の鍵種別を使用したアルゴリズムです。");
			}

			String decryptResult = decryptor.doDecrypt(cipherData.getData());

			return (new ResponseData())
					.setResult(Result.OK)
					.setDecrypted(decryptResult)
					.toString();

		} catch (Exception e) {
			String errMessage = ErrorHandler.createMessageByError(e);
			return responseData.toErrorString(errMessage);
		}
	}

	@PostMapping("/exec-sign")
	public String execSign(@RequestBody SignatureData signatureData) {
		try {

			Signer signer = SignatureManager.createSigner(signatureData);

			if (signer == null) {
				return responseData.toErrorString("対象外の鍵種別を使用したアルゴリズムです。");
			}

			String signatureResult = signer.doSign(signatureData.getData());

			return (new ResponseData())
					.setResult(Result.OK)
					.setSignature(signatureResult)
					.toString();

		} catch (Exception e) {
			String errMessage = ErrorHandler.createMessageByError(e);
			return responseData.toErrorString(errMessage);
		}
	}

	@PostMapping("/exec-verify")
	public String execVerify(@RequestBody SignatureData signatureData) {
		try {

			Verifier verifier = SignatureManager.createVerifier(signatureData);

			if (verifier == null) {
				return responseData.toErrorString("対象外の鍵種別を使用したアルゴリズムです。");
			}

			boolean verifyResult = verifier.doVerify(
						signatureData.getData(), signatureData.getSign());

			return (new ResponseData())
					.setResult(Result.OK)
					.setVerifyResult(verifyResult ? Result.OK : Result.NG)
					.toString();

		} catch (Exception e) {
			String errMessage = ErrorHandler.createMessageByError(e);
			return responseData.toErrorString(errMessage);
		}
	}
}