package com.javafx.stego.controller;

import java.io.File;

import com.google.inject.Inject;
import com.javafx.stego.model.WindowData;
import com.javafx.stego.processing.DecryptImage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.stage.FileChooser;



public class Step2DecryptController {

	@FXML
	Button decryptButton;

	@FXML
	RadioButton secretTextRadioButton;

	@FXML
	RadioButton secretFileRadioButton;

	@FXML
	Label decryptLabel;

	@FXML
	Label messageReceivedLabel;

	@FXML
	CheckBox passCheckBox;

	@FXML
	ScrollPane messageScrollPane;

	@FXML
	PasswordField passField;

	@Inject
	WindowData model;

	 public void clickRadioButton(ActionEvent event){
	    	if(event.getSource() == secretTextRadioButton){
	    		secretFileRadioButton.setSelected(false);
	    		model.setKindHideData(0);

	    	}

	    	if(event.getSource() == secretFileRadioButton){
	    		secretTextRadioButton.setSelected(false);
	    		model.setKindHideData(1);
	    	}

	    }

	 @FXML
	    public void checkField(){
	    	if(passCheckBox.isSelected()){
	    		passField.setVisible(true);
	    	}
	    	else{
	    		passField.setVisible(false);
	    		passField.setText("");
	    	}
	 }

	 private void chooseFile(){



	        FileChooser fileChooser = new FileChooser();
	        configureFileChooser(fileChooser);


	        File file = fileChooser.showSaveDialog(decryptButton.getScene().getWindow());
	                    if (file != null) {
	                    	model.setOutputFile(file);

	                    }

	       }



		private static void configureFileChooser(
		        final FileChooser fileChooser) {
		            fileChooser.setTitle("Save as");
		            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		            fileChooser.getExtensionFilters().addAll(
		                new FileChooser.ExtensionFilter("All Images", "*.*")

		            );

		    }

	@FXML
	    private void decrypt(){


		model.setPassword(passField.getText());

		if(model.getKindHideData()==1){
			chooseFile();

		}
	    	DecryptImage eI = new DecryptImage();
	    	String text = eI.decryptFile(model);
	    	if(secretTextRadioButton.isSelected()){
	    		messageScrollPane.setVisible(true);
	    		messageReceivedLabel.setVisible(true);
	    		decryptLabel.setText(text);
	    	}

	    }


}
