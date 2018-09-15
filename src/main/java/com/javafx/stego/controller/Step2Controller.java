package com.javafx.stego.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.javafx.stego.alert.AlertPanel;
import com.javafx.stego.annotation.Submit;
import com.javafx.stego.annotation.Validate;
import com.javafx.stego.model.WindowData;
import com.javafx.stego.processing.tool.StringProcessing;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

public class Step2Controller {

    private Logger log = LoggerFactory.getLogger(Step2Controller.class);
    private static int INT_LENGTH = 32;

    @FXML
    TextArea encryptTextArea;

    @FXML
    RadioButton secretTextRadioButton;

    @FXML
    RadioButton secretFileRadioButton;

    @FXML
    Label pathLabel;

    @FXML
    Button chooseSecretFileButton;

    @FXML
    Label encryptLabel;

    @Inject
    WindowData model;

    @FXML
    public void initialize() {
        encryptTextArea.textProperty().bindBidirectional(model.getHideTextProperty());
    }
    public void clickRadioButton(ActionEvent event){
    	if(event.getSource() == secretTextRadioButton){
    		secretFileRadioButton.setSelected(false);
    		chooseSecretFileButton.setVisible(false);
    		encryptLabel.setVisible(true);
    		encryptTextArea.setVisible(true);
    		pathLabel.setText("");
    		pathLabel.setVisible(false);
    		model.setKindHideData(0);
    	}

    	if(event.getSource() == secretFileRadioButton){
    		secretTextRadioButton.setSelected(false);
    		chooseSecretFileButton.setVisible(true);
    		encryptLabel.setVisible(false);
    		encryptTextArea.setVisible(false);
    		pathLabel.setVisible(true);
    		model.setKindHideData(1);

    	}

    }

    public void importFile(){

        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);


        File file = fileChooser.showOpenDialog(chooseSecretFileButton.getScene().getWindow());
                    if (file != null) {
                    	pathLabel.setText(file.getPath().toString());
                    	model.setPathHideFile(file.getPath().toString());
                    	model.setHideFile(file);

                    }
                    else{
                    	pathLabel.setText("No file seected");
                    }
       }

    private static void configureFileChooser(
	        final FileChooser fileChooser) {
	            fileChooser.setTitle("Select file");
	            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	            fileChooser.getExtensionFilters().addAll(
	                new FileChooser.ExtensionFilter("All Files", "*.*")	);

	    }

    @Validate
    public boolean validate(){
    	int lengthInputFile = 0;
    	int lengthHideFile = 0;


    	Path pathInputFile = Paths.get(model.getInputFile().getPath());
		try {
			lengthInputFile = Files.readAllBytes(pathInputFile).length;
		} catch (IOException ioException) {
			log.error("Cant read input file.", ioException);
		}

    	if(model.getKindHideData()==0){
    		lengthHideFile = StringProcessing.encodeUTF8(model.getHideText()).length;
		}
		else{
			Path pathHideFile = Paths.get(model.getHideFile().getPath());
			try {
				lengthHideFile = Files.readAllBytes(pathHideFile).length;
			} catch (IOException ioException) {
				log.error("Cant read input file.", ioException);
			}
		}
    	log.debug("lengthHideFile: "+ lengthHideFile+" lengthInputFile: "+ lengthInputFile*3);

    	if(lengthHideFile + INT_LENGTH > lengthInputFile*3)
		{
			AlertPanel.showAlertError("Error","File not long enough!","Select bigger container file or smaller data to hide.");
			return false;

		}

        return true;
    }

    @Submit
    public void submit(){
        log.debug("[STEP 2] Completed");
    }
}
