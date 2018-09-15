package com.javafx.stego.processing.tool;

import org.jasypt.util.binary.BasicBinaryEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordProcessing {

	 private static Logger log = LoggerFactory.getLogger(PasswordProcessing.class);

	public static byte[] encryptMessageBytes(byte[] msgBytes, String password){
			BasicBinaryEncryptor bbe = new BasicBinaryEncryptor();
			bbe.setPassword(password);
			return bbe.encrypt(msgBytes);
	}


	public static byte[] decryptMessageBytes(byte[] encodeMessage, String password){
		BasicBinaryEncryptor bbe = new BasicBinaryEncryptor();
		bbe.setPassword(password);
		byte[] message = null;
		try {
			message = bbe.decrypt(encodeMessage);
		}
		catch(Exception e)
		{
			log.error("Problem in decrypting message");
		}

		return message;
	}


}
