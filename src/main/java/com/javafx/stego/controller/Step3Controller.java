package com.javafx.stego.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.javafx.stego.alert.AlertPanel;
import com.javafx.stego.annotation.Submit;
import com.javafx.stego.annotation.Validate;
import com.javafx.stego.model.WindowData;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;

public class Step3Controller {

    private Logger log = LoggerFactory.getLogger(Step3Controller.class);

    @FXML
    PasswordField passField;

    @FXML
    PasswordField passConfirmationField;

    @FXML
    CheckBox passCheckBox;

    @FXML
    GridPane passPanel;

    @Inject
    WindowData model;

    @FXML
    public void checkField(){
    	if(passCheckBox.isSelected()){
    		passPanel.setVisible(true);
    	}
    	else{
    		passPanel.setVisible(false);
    		passField.setText("");
    		passConfirmationField.setText("");
    	}
    	model.setWithPassword(passCheckBox.isSelected());
    }

    @FXML
    public void initialize() {
    	passField.textProperty().bindBidirectional(model.getPasswordProperty());
    	passConfirmationField.textProperty().bindBidirectional(model.getPasswordConfirmationProperty());
    	model.setWithPassword(true);

    }

    @Validate
    public boolean validate(){
    	String pass = passField.getText();
    	String pass2 = passConfirmationField.getText();

    			if(passCheckBox.isSelected() && (StringUtils.isEmpty(pass) || StringUtils.isEmpty(pass2) || !pass.equals(pass2))){
    	        	AlertPanel.showAlertError("Step 3", "Passwords are different,", "You shoud enter correct password.");
    	        	return false;
    			}
        return true;
    }

    @Submit
    public void submit(){
        log.debug("[STEP 3] Completed");
    }
}


