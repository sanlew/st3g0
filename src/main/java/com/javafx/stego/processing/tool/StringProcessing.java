package com.javafx.stego.processing.tool;
import java.nio.charset.Charset;
import java.util.Optional;

public class StringProcessing {


	private final static Charset UTF8_CHARSET = Charset.forName("UTF-8");

	public static String  decodeUTF8(byte[] bytes) {
	    return new String(bytes, UTF8_CHARSET);
	}

	public static String  decodeUTF8(char[] bytes) {
	    return new String(bytes);
	}

	public static byte[] encodeUTF8(String string) {
	    return string.getBytes(UTF8_CHARSET);
	}

}
