package utility;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.EncryptionException;

public class Encryptor {
	public static String encrypt(String s) {
		try {
			return ESAPI.encryptor().encrypt(s);
		} catch (EncryptionException e) {
			e.printStackTrace();
			return "";
		}
	}
	public static String decrypt(String s) {
		try {
			return ESAPI.encryptor().decrypt(s);
		} catch (EncryptionException e) {
			e.printStackTrace();
			return "";
		}
	}
	public static String hash(String s) {
		try {
			return ESAPI.encryptor().hash(s, "Recycly");
		} catch (EncryptionException e) {
			e.printStackTrace();
			return "";
		}
	}
}
