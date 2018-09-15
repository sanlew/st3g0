package com.javafx.stego.processing;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;

import org.apache.commons.lang3.StringUtils;

import com.javafx.stego.BufferedImageProcessing;
import com.javafx.stego.alert.AlertPanel;
import com.javafx.stego.model.WindowData;
import com.javafx.stego.processing.tool.PasswordProcessing;
import com.javafx.stego.processing.tool.StringProcessing;


public class DecryptImage {


	public String decryptFile(WindowData windowData) {

		File file = windowData.getInputFile();
		File outputFile = windowData.getOutputFile();
		int algorithmType = windowData.getAlgorithm();
		String text = "";
		byte[] result = null;
		String pass = windowData.getPassword();

		if(algorithmType == 0){

			BufferedImageProcessing bIP = new BufferedImageProcessing();
			BufferedImage image = bIP.getBufferedImage(file);
			WritableRaster raster   = image.getRaster();
			DataBufferByte buffer = (DataBufferByte)raster.getDataBuffer();
			byte[] imageByte = buffer.getData();
			result = LSB.decodeText(imageByte);

			if(StringUtils.isNotEmpty(pass)){
				result = PasswordProcessing.decryptMessageBytes(result, pass);
			}

			if(windowData.getKindHideData()==0){
				text =  StringProcessing.decodeUTF8(result);
			}
			else{
				SaveFile.saveImage(outputFile,result, "file");
				AlertPanel.showAlertInfo("Success!", "New file was generated!", "");
			}
		}

		return text;
	}


}
