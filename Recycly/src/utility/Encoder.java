package utility;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.OracleCodec;
import org.owasp.esapi.crypto.PlainText;
import org.owasp.esapi.errors.EncryptionException;

public class Encoder {

	public static String encodeHTML(String data) {
		return ESAPI.encoder().encodeForHTML(data);
	}
	public static String encodeSQL(String data) {
		return ESAPI.encoder().encodeForSQL(new OracleCodec(), data);
	}
}