package com.javafx.stego;

import java.net.URL;

import com.google.inject.Guice;
import com.google.inject.Injector;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class WindowMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		final Injector injector = Guice.createInjector( new WindowModule() );
		URL urlRoot = WindowMain.class.getResource("/view/Root.fxml");
		Parent root = FXMLLoader.load(urlRoot, null, new JavaFXBuilderFactory(),(claz) -> injector.getInstance(claz));

		final Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.setWidth(800);
		primaryStage.setHeight(600);
		primaryStage.setTitle("St3g0");
		primaryStage.setResizable(false);

		primaryStage.show();

		URL urlMenu = WindowMain.class.getResource("/view/Menu.fxml");
		FXMLLoader loader = new FXMLLoader(urlMenu, null, new JavaFXBuilderFactory(),(claz) -> injector.getInstance(claz));
		final Parent menu = loader.load();
		((BorderPane) root).setCenter(menu);

	}

	public static void main(String[] args) { launch(args); }

}
