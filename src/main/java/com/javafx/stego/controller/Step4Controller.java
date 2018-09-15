package com.javafx.stego.controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.javafx.stego.alert.AlertPanel;
import com.javafx.stego.annotation.Submit;
import com.javafx.stego.model.WindowData;
import com.javafx.stego.processing.EncryptImage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

public class Step4Controller {

    private Logger log = LoggerFactory.getLogger(Step4Controller.class);

    @FXML
    Button generateButton;

    @FXML
    Label pathLabel;

    @Inject
    WindowData model;


    @Submit
    public void submit() throws Exception {
            log.debug("[STEP 4] Completed");
    }

    public void importBaseFile(){


        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);

        File file = fileChooser.showSaveDialog(generateButton.getScene().getWindow());
                    if (file != null) {
                    	model.setOutputFile(file);
                    	generate();

                    }
                    else{
                    	pathLabel.setText("Operation aborted. The file has not been selected." );
                    }
       }

	private static void configureFileChooser(
	        final FileChooser fileChooser) {
	            fileChooser.setTitle("Select file");
	            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	            fileChooser.getExtensionFilters().addAll(
	                new FileChooser.ExtensionFilter("All Images", "*.*")
	            );
	    }


    @FXML
    public void generate(){
    	try{
    		EncryptImage eI = new EncryptImage();
    		eI.encryptFile(model);
    		AlertPanel.showAlertInfo("Success!", "New file was generated!",  "");
    	}
    	catch(Exception e){
    		log.error("Generate file aborted.", e);
    	}
    }


}
