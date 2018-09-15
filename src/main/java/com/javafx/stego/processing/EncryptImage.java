package com.javafx.stego.processing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.javafx.stego.model.WindowData;
import com.javafx.stego.processing.tool.PasswordProcessing;
import com.javafx.stego.processing.tool.StringProcessing;



public class EncryptImage {


	BufferedImage outputImage;

	public void encryptFile(WindowData windowData) {
		File inputFile = windowData.getInputFile();
		File outputFile = windowData.getOutputFile();
		byte[] text = null;

		if(windowData.getKindHideData()==0){
			text = StringProcessing.encodeUTF8(windowData.getHideText());
		}
		else{
			Path path = Paths.get(windowData.getHideFile().getPath());
			try {
				text = Files.readAllBytes(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(windowData.getWithPassword()){
			text = PasswordProcessing.encryptMessageBytes(text, windowData.getPassword());
		}

		if(windowData.getAlgorithm() == 0){
			DataProcessing dataProcessing = new DataProcessing();
	        outputImage = dataProcessing.encryptImage(inputFile, text);
	        SaveFile.saveImage(outputFile, outputImage, "png");
		}

	}


}
