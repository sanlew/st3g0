package com.javafx.stego.controller;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.javafx.stego.annotation.Submit;
import com.javafx.stego.annotation.Validate;
import com.javafx.stego.model.WindowData;

import javafx.beans.binding.When;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Callback;

public class WindowController {

	private final int INDICATOR_RADIUS = 10;

	private final String CONTROLLER_KEY = "controller";

	private Logger log = LoggerFactory.getLogger(WindowController.class);

	@FXML
	VBox contentVBox;

	@FXML
	HBox stepsHBox;

	@FXML
	Button nextButton;

	@FXML
	Button backButton;

	@FXML
	Button cancelButton;

	@Inject
	Injector injector;

	@Inject
	WindowData model;

	private final List<Parent> steps = new ArrayList<>();

	private final IntegerProperty currentStep = new SimpleIntegerProperty(-1);

	@FXML
	public void initialize() throws Exception {

		buildSteps();
		initButtons();
		buildIndicatorCircles();
		setInitialContent();
	}

	private void initButtons() {
		backButton.disableProperty().bind( currentStep.lessThanOrEqualTo(0) );
		nextButton.disableProperty().bind( currentStep.greaterThanOrEqualTo(steps.size()-1) );
		cancelButton.textProperty().bind(
				new When(
						currentStep.lessThan(steps.size()-1)
				)
						.then("Cancel")
						.otherwise("Start Over")
		);

	}

	private void setInitialContent() {
		currentStep.set( 0 );
		contentVBox.getChildren().add( steps.get( currentStep.get() ));
	}

	private void buildIndicatorCircles() {
		for( int i=0; i<steps.size(); i++ ) {
			stepsHBox.getChildren().add( createIndicatorCircle(i));
		}
	}

	private void buildSteps() throws java.io.IOException {

		final JavaFXBuilderFactory bf = new JavaFXBuilderFactory();

		final Callback<Class<?>, Object> cb = (clazz) -> injector.getInstance(clazz);


		FXMLLoader fxmlLoaderStep1 = new FXMLLoader( getClass().getResource("/view/Step1.fxml"), null, bf, cb);
		Parent step1 = fxmlLoaderStep1.load( );
		step1.getProperties().put( CONTROLLER_KEY, fxmlLoaderStep1.getController() );

		if(model.getMode() == 0){

		FXMLLoader fxmlLoaderStep2 = new FXMLLoader( getClass().getResource("/view/Step2.fxml"), null, bf, cb );
		Parent step2 = fxmlLoaderStep2.load();
		step2.getProperties().put( CONTROLLER_KEY, fxmlLoaderStep2.getController() );

		FXMLLoader fxmlLoaderStep3 = new FXMLLoader(getClass().getResource("/view/Step3.fxml"), null, bf, cb );
		Parent step3 = fxmlLoaderStep3.load( );
		step3.getProperties().put( CONTROLLER_KEY, fxmlLoaderStep3.getController() );

		FXMLLoader fxmlLoaderStep4 = new FXMLLoader(getClass().getResource("/view/Step4.fxml"), null, bf, cb);
		Parent step4 = fxmlLoaderStep4.load(  );
		step4.getProperties().put( CONTROLLER_KEY, fxmlLoaderStep4.getController() );

		steps.addAll( Arrays.asList(
				step1, step2, step3, step4
					));
		}

		if(model.getMode() == 1){
			FXMLLoader fxmlLoaderStep2 = new FXMLLoader(getClass().getResource("/view/Step2Decrypt.fxml"), null, bf, cb );
			Parent step2 = fxmlLoaderStep2.load();
			step2.getProperties().put( CONTROLLER_KEY, fxmlLoaderStep2.getController() );
			steps.addAll( Arrays.asList(
					step1, step2
						));
		}
	}

	private Circle createIndicatorCircle(int i) {

		Circle circle = new Circle(INDICATOR_RADIUS, Color.WHITE);
		circle.setStroke(Color.BLACK);

		circle.fillProperty().bind(
				new When(
						currentStep.greaterThanOrEqualTo(i))
						.then(Color.DODGERBLUE)
						.otherwise(Color.WHITE));

		return circle;
	}

	@FXML
	public void next() {

		Parent p = steps.get(currentStep.get());
		Object controller = p.getProperties().get(CONTROLLER_KEY);


		Method v = getMethod( Validate.class, controller );
		if( v != null ) {
			try {
				Object retval = v.invoke(controller);
				if( retval != null && ((Boolean)retval) == false ) {
					return;
				}

			} catch (IllegalAccessException | InvocationTargetException e) {
				log.error("Navigation next error", e);
			}
		}


		Method sub = getMethod( Submit.class, controller );
		if( sub != null ) {
			try {
				sub.invoke(controller);
			} catch (IllegalAccessException | InvocationTargetException e) {
				log.error("Navigation next error", e);
			}
		}

		if( currentStep.get() < (steps.size()-1) ) {
			contentVBox.getChildren().remove( steps.get(currentStep.get()) );
			currentStep.set( currentStep.get() + 1 );
			contentVBox.getChildren().add( steps.get(currentStep.get()) );
		}
	}

	@FXML
	public void back() {

		if( currentStep.get() > 0 ) {
			contentVBox.getChildren().remove( steps.get(currentStep.get()) );
			currentStep.set( currentStep.get() - 1 );
			contentVBox.getChildren().add( steps.get(currentStep.get()) );
		}
	}

	@FXML
	public void cancel() {

		contentVBox.getChildren().remove( steps.get(currentStep.get()) );
		currentStep.set(0);  // first screen
		contentVBox.getChildren().add( steps.get(currentStep.get()) );
		Parent s = contentVBox.getScene().getRoot();
		FXMLLoader menu = new FXMLLoader(getClass().getResource("/view/Menu.fxml"), null, new JavaFXBuilderFactory(),
				(clazz) -> injector.getInstance(clazz));

		Pane p;

		try {
			p = menu.load( );
			((BorderPane) s).setCenter(p);

		} catch (IOException ioException) {
			log.error("Error return to main menu. ", ioException);
		}

		model.reset();
	}

	private Method getMethod(Class<? extends Annotation> an, Object obj) {

		if( an == null ) {
			return null;
		}

		if( obj == null ) {
			return null;
		}

		Method[] methods = obj.getClass().getMethods();
		if( methods != null && methods.length > 0 ) {
			for( Method m : methods ) {
				if( m.isAnnotationPresent(an)) {
					return m;
				}
			}
		}
		return null;
	}
}
