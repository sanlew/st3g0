package com.javafx.stego.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.javafx.stego.model.WindowData;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MenuController {


	@Inject
	Injector injector;

	@Inject
	WindowData model;

	@FXML
	Pane pane;

	@FXML
	Button encryptButton;

	@FXML
	Button decryptButton;

	public void startOperation(ActionEvent event){

		Logger log = LoggerFactory.getLogger(MenuController.class);

		if(event.getSource().equals(encryptButton)){
			model.setMode(0);
		}
		if(event.getSource().equals(decryptButton)){
			model.setMode(1);
		}

		FXMLLoader window = new FXMLLoader(getClass().getResource("/view/Window.fxml"), null, new JavaFXBuilderFactory(),
				(clazz) -> injector.getInstance(clazz));

		VBox w;

		try {

			w = window.load( );
			Parent s = pane.getScene().getRoot();
			((BorderPane) s).setCenter(w);

		} catch (IOException e) {
			log.error("Loading view error. ", e);
		}


	}


}
