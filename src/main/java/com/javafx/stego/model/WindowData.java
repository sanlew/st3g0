package com.javafx.stego.model;

import java.io.File;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class WindowData {


    private Integer mode;
	private IntegerProperty containerType = new SimpleIntegerProperty(); //0-IMAGE;
	private IntegerProperty algorithm = new SimpleIntegerProperty(); // 0-LSB
    private StringProperty pathFileInput = new SimpleStringProperty();
    private File inputFile;
    private IntegerProperty kindHideData = new SimpleIntegerProperty(); //0-TEXT; 1-FILE
    private StringProperty pathHideFile = new SimpleStringProperty();
    private File hideFile;
    private StringProperty hideText = new SimpleStringProperty();
    private BooleanProperty withPassword = new SimpleBooleanProperty();
    private StringProperty password = new SimpleStringProperty();
    private StringProperty passwordConfirmation = new SimpleStringProperty();
    private StringProperty pathFileOutput = new SimpleStringProperty();
    private File outputFile;

	public IntegerProperty getContainerTypeProperty() {
		return containerType;
	}
	public void setContainerType(Integer containerType) {
		this.containerType.set(containerType);
	}

	public Integer getContainerType() {
		return containerType.get();
	}

	public IntegerProperty getAlgorithmProperty() {
		return algorithm;
	}
	public void setAlgorithm(Integer algorithm) {
		this.algorithm.set(algorithm);
	}

	public Integer getAlgorithm() {
		return algorithm.get();
	}


	public IntegerProperty getKindHideDataProperty() {
		return kindHideData;
	}
	public void setKindHideData(Integer kindHideData) {
		this.kindHideData.set(kindHideData);
	}

	public Integer getKindHideData() {
		return kindHideData.get();
	}

	public BooleanProperty getWithPasswordProperty() {
		return withPassword;
	}
	public void setWithPassword(Boolean withPassword) {
		this.withPassword.set(withPassword);
	}

	public Boolean getWithPassword() {
		return withPassword.get();
	}

	public StringProperty getPathFileInputProperty() {
		return pathFileInput;
	}
	public void setPathFileInput(String pathFileInput) {
		this.pathFileInput.set(pathFileInput);
	}

	public String getPathFileInput() {
		return pathFileInput.get();
	}

	public StringProperty getPathHideFileProperty() {
		return pathHideFile;
	}
	public void setPathHideFile(String pathHideFile) {
		this.pathHideFile.set(pathHideFile);
	}

	public String getPathHideFile() {
		return pathHideFile.get();
	}

	public StringProperty getHideTextProperty() {
		return hideText;
	}
	public void setHideText(String hideText) {
		this.hideText.set(hideText);
	}

	public String getHideText() {
		return hideText.get();
	}

	public StringProperty getPasswordProperty() {
		return password;
	}
	public void setPassword(String password) {
		this.password.set(password);
	}

	public String getPassword() {
		return password.get();
	}

	public StringProperty getPasswordConfirmationProperty() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation.set(passwordConfirmation);
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation.get();
	}

	public StringProperty getPathFileOutputProperty() {
		return pathFileOutput;
	}
	public void setPathFileOutput(String pathFileOutput) {
		this.pathFileOutput.set(pathFileOutput);
	}

	public String getPathFileOutput() {
		return pathFileOutput.get();
	}



    public void reset() {
    	containerType.set(-1);
    	algorithm.set(-1);
    	kindHideData.set(0);
    	withPassword.set(false);
        pathFileInput.set("");
        pathHideFile.set("");
        hideText.set("");
        password.set("");
        passwordConfirmation.set("");
        pathFileOutput.set("");
        setHideFile(null);
        setInputFile(null);
        setOutputFile(null);

    }

	public File getInputFile() {
		return inputFile;
	}
	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}
	public File getHideFile() {
		return hideFile;
	}
	public void setHideFile(File hideFile) {
		this.hideFile = hideFile;
	}
	public File getOutputFile() {
		return outputFile;
	}
	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}
	public Integer getMode() {
		return mode;
	}
	public void setMode(Integer mode) {
		this.mode = mode;
	}


}
