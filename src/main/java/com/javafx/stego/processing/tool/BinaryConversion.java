package com.javafx.stego.processing.tool;


public class BinaryConversion {


	public static byte[] toByteArray(int length) {
		byte byte3 = (byte)((length & 0xFF000000) >>> 24);
		byte byte2 = (byte)((length & 0x00FF0000) >>> 16);
		byte byte1 = (byte)((length & 0x0000FF00) >>> 8 );
		byte byte0 = (byte)((length & 0x000000FF)	   );
		return(new byte[]{byte3,byte2,byte1,byte0});
	}

	public static byte[] toBitArray(byte value) {
		byte[] bits = new byte[] {0,0,0,0,0,0,0,0};
		if (value < 0) {
			value = (byte)(value + Byte.MAX_VALUE + 1);
			bits[7] = 1;
		}

		int i = 0;
		while (true) {
			bits[i] = (byte)(value % 2);
			if ( (value != 1) && (value != 0) ) {
				value /= 2;
				i++;
			} else {
				break;
			}
		}
		bits[i] = value;

		return bits;
}


	public static byte toByte(byte[] bitArray) {
		byte res = 0;
		if (bitArray.length > 8) {
			return res;
		}
		int r = 0;

		for (int i = 0; i < bitArray.length; i++) {
			r += bitArray[i]*Math.pow(2, i);
		}
		res = (byte)r;

		return res;
}

	public static int byteToPositive(byte b){
		int positiveInt = b;
		if(b<0){
			positiveInt = (b & 0xFF);
		}
		return  positiveInt;
	}

	 public static byte[] byteArrayToBitArray(byte[] bytes) {
		    byte[] bits = new byte [bytes.length*8] ;

		    for (int i = 0; i < bytes.length * 8; i++) {
		      if ((bytes[i / 8] & (1 << (7 - (i % 8)))) > 0)
		        bits[i] = 1;
		      else
		    	  bits[i] = 0;
		    }
		    return bits;
		  }

	 public static byte[] byteArrayIntToBitArray(byte[] bytes) {
		    byte[] bits = new byte [] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		    int m=32-(bytes.length*8);
		    for (int i = m; i < bytes.length * 8; i++) {
		      if ((bytes[i / 8] & (1 << (7 - (i % 8)))) > 0)
		        bits[i] = 1;
		      else
		    	  bits[i] = 0;
		    }
		    return bits;
		  }

	 public static byte[] bitArrayToByteArray(byte[] lenByte){
		 byte[] b = new byte[lenByte.length / 8];
			for (int i = 0; i < b.length; ++i) {
			    int c = 0;
			    for (int j = i * 8; j < (i + 1) * 8; ++j) {
			        c = c << 1;
			        c += lenByte[j];
			    }
			    b[i] = (byte)c;
			}
			return b;
	 }

	 public static char[] bitArrayToCharArray(byte [] messageByte){
		 char[] chars = new char[messageByte.length / 8];
			for (int i = 0; i < chars.length; ++i) {
			    int c = 0;
			    for (int j = i * 8; j < (i + 1) * 8; ++j) {
			        c = c << 1;
			        c += messageByte[j];
			    }
			    chars[i] = (char)c;
			}
			return chars;
	 }

}
