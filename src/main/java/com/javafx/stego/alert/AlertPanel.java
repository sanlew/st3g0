package com.javafx.stego.alert;

import javafx.scene.control.Alert;

public class AlertPanel {

	public static void showAlertError(String title, String header, String content) {
    	Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
	}

	public static void showAlertInfo(String title, String header, String content) {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
	}

}
