package com.javafx.stego.processing;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javafx.stego.BufferedImageProcessing;
import com.javafx.stego.processing.tool.BinaryConversion;


public class DataProcessing {

    private Logger log = LoggerFactory.getLogger(DataProcessing.class);
    private static int INT_LENGTH = 32;

	public  BufferedImage encryptImage(File file, byte [] text){

		BufferedImageProcessing bIP = new BufferedImageProcessing();
		BufferedImage image = bIP.getBufferedImage(file);
		WritableRaster raster   = image.getRaster();
		DataBufferByte buffer = (DataBufferByte)raster.getDataBuffer();

		byte[] imageByte =  buffer.getData();
		log.info("imageByte length: " + imageByte.length );

		byte msg[] = text;
		byte len[]  = BinaryConversion.toByteArray(msg.length);
		try
		{

			LSB.encodeText(imageByte, len,  0); //0 first position
			LSB.encodeText(imageByte, msg, INT_LENGTH); //4 bytes of space for length: 4bytes*8bit = 32 bits
		}
		catch(Exception e)
		{
			log.error("Target File cannot hold message!", e);
		}
		return image;
	}


}
