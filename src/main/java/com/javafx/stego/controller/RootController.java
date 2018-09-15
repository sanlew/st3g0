package com.javafx.stego.controller;


import com.javafx.stego.alert.AlertPanel;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;


public class RootController {

	@FXML
	Pane rootPane;

	@FXML
    private void handleAbout() {

		String header = "St3g0 v.0.3  encrypt and decrypt information in images. Image should be in .png or .jpg format. "
        		+ "You can hide text or another file. Current version use only LSB algorithm.";
		String content = "Author: Sandra Lewandowska " + "https://sandralewandowska.pl";
		AlertPanel.showAlertInfo("St3g0", header, content);
    }

	 @FXML
	    private void handleExit() {
	        System.exit(0);
	    }


}
