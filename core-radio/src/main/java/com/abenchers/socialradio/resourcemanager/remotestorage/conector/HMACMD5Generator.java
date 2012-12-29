package com.abenchers.socialradio.resourcemanager.remotestorage.conector;

import java.security.Key;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component("hmacmd5Generator")
public class HMACMD5Generator {

	public String getHmacMD5(final String privateKey, final String input,
			final String algorithm) throws Exception {
		byte[] keyBytes = privateKey.getBytes();
		Key key = new SecretKeySpec(keyBytes, 0, keyBytes.length, algorithm);
		Mac mac = Mac.getInstance(algorithm);
		mac.init(key);
		return byteArrayToHex(mac.doFinal(input.getBytes()));
	}

	protected String byteArrayToHex(byte[] a) {
		int hn, ln, cx;
		String hexDigitChars = "0123456789abcdef";
		StringBuffer buf = new StringBuffer(a.length * 2);
		for (cx = 0; cx < a.length; cx++) {
			hn = ((int) (a[cx]) & 0x00ff) / 16;
			ln = ((int) (a[cx]) & 0x000f);
			buf.append(hexDigitChars.charAt(hn));
			buf.append(hexDigitChars.charAt(ln));
		}
		return buf.toString();
	}

}
