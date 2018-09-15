package com.javafx.stego.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.javafx.stego.alert.AlertPanel;
import com.javafx.stego.annotation.Submit;
import com.javafx.stego.annotation.Validate;
import com.javafx.stego.model.AlgorithmType;
import com.javafx.stego.model.ContainerType;
import com.javafx.stego.model.WindowData;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class Step1Controller {

    private Logger log = LoggerFactory.getLogger(Step1Controller.class);

    @FXML
    Button openButton;

    @FXML
    Label localizationInputFileLabel;


    @FXML
    RadioButton imageRadioButton;

    @FXML
    SplitMenuButton splitMenu;

    @FXML
    MenuItem option1;

    @Inject
    WindowData model;

    @FXML
    ImageView imagePreview;



    @Validate
    public boolean validate(){

        if(splitMenu.getText().equals("--select--")){
        	AlertPanel.showAlertError("Step 1", "Selecting an algorithm is required.", "Select algorithm.");
        	return false;
        }

        if(StringUtils.isEmpty(localizationInputFileLabel.getText()) || localizationInputFileLabel.getText().equals("No file selected")) {
        	AlertPanel.showAlertError("Step 1", "Selecting an image is required.", "Select image.");
        	return false;
        }

        return true;
    }


    public void importBaseFile(){

        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);


        File file = fileChooser.showOpenDialog(openButton.getScene().getWindow());
                    if (file != null) {
                    	localizationInputFileLabel.setText(file.getPath().toString());
                    	model.setPathFileInput(file.getPath().toString());
                    	model.setInputFile(file);
                    	Image image = new Image(file.toURI().toString());
                    	imagePreview.setImage(image);

                		 String fileType = "Undetermined";

                		    try
                		    {
                		        fileType = Files.probeContentType(file.toPath());
                		        if (!(fileType.toUpperCase().equals("IMAGE/JPEG") || fileType.toUpperCase().equals("IMAGE/PNG"))) {
                				    throw new IllegalArgumentException(String.format("%s is not supported", fileType));
                				  }
                		    }
                		    catch (IOException ioException)
                		    {
                		        log.error(
                		                "Unable to determine file type for " + file.getName()
                		                        + " due to exception " + ioException);
                		    }
                			log.info("mimetype: " + fileType);

                    }
                    else{
                    	localizationInputFileLabel.setText("No file selected");
                    }
       }




	private static void configureFileChooser(
	        final FileChooser fileChooser) {
	            fileChooser.setTitle("Select file");
	            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	            fileChooser.getExtensionFilters().addAll(
	            		new FileChooser.ExtensionFilter(
	            			    "Images", "*.png", "*.jpg", "*.jpeg", "*.PNG", "*.JPG", "*.JPEG"));


	    }
    public void clickRadioButton(ActionEvent event){
    	if(event.getSource() == imageRadioButton){
    		imageRadioButton.setSelected(true);
    		model.setContainerType(ContainerType.IMAGE.getCode());
    	}

    }

    public void setAlgorithm(ActionEvent event){
    	MenuItem source = (MenuItem) event.getSource();
    	if(event.getSource() == option1){
    		model.setAlgorithm(AlgorithmType.LSB.getCode());

    	}
    	splitMenu.setText(source.getText());
    }

    @Submit
    public void submit(){
         log.debug("[STEP 1] Completed");
    }

}
