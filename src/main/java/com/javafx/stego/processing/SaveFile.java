package com.javafx.stego.processing;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class SaveFile {

	 public static void saveImage(File file, BufferedImage content, String extension) {
		 try {
			file = new File(file+"."+extension);
			ImageIO.write(content, extension, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }


	public static void saveImage(File file, byte [] content, String extension) {
		try {
			OutputStream o = new FileOutputStream(file.getPath()+"."+extension);
			BufferedOutputStream bos = new BufferedOutputStream(o);
			bos.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



}
