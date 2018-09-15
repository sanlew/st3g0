package com.javafx.stego.processing;

import com.javafx.stego.alert.AlertPanel;

public class LSB {

	public static byte[] encodeText(byte[]  image, byte[] addition, int offset) {

		int jump =1;

		if(offset>0){
			jump = (image.length - offset)/(addition.length*8);
		}

		//loop through each addition byte
		for (int i=0; i < addition.length; i++) {
			// loop through stego
			int byteVal = addition[i];

			for (int j=7; j >= 0; j--) { // loop through 8 bits of stego byte

				int bitVal = (byteVal >>> j) & 1;
				// change last bit of image byte to be the stego bit
				image[offset] = (byte)((image[offset] & 0xFE) | bitVal);
				offset+=jump;

			}
		}
		return image;

	}

	public static byte[] decodeText(byte[] image)
	{
		int length = 0;
		int offset  = 32;

		//loop through 32 bytes of data to determine text length
		for(int i=0; i<32; ++i) //i=24 will also work, as only the 4th byte contains real data
		{
			length = (length << 1) | (image[i] & 1);
		}
		if(length<0){
			AlertPanel.showAlertError("Error", "There is no hidding message", "or container is demaged.");
		}

		byte[] result = new byte[length];
		int jump = (image.length - offset)/(length*8);


		//loop through each byte of text
		for(int b=0; b<result.length; ++b )
		{
			//loop through each bit within a byte of text
			for(int i=0; i<8; ++i, offset+=jump)
			{
				//assign bit: [(new byte value) << 1] OR [(text byte) AND 1]
				result[b] = (byte)((result[b] << 1) | (image[offset] & 1));
			}
		}
		return result;
	}


}
