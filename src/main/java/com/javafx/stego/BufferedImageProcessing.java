package com.javafx.stego;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javafx.stego.processing.DataProcessing;



public class BufferedImageProcessing {

	 private static Logger log = LoggerFactory.getLogger(DataProcessing.class);


	public BufferedImage getBufferedImage(File file)
	{
		BufferedImage bufferedImage = null;

		try
		{
			bufferedImage = ImageIO.read(file);
		}
		catch(Exception e)
		{
			   log.error("Image could not be read!", e);
		}
		return bufferedImage;
	}

}
