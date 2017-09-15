package gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import javafx.animation.Timeline;
import javafx.application.Application;
import java.util.Optional;

import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.event.*;

/**
 * Controller for alert dialogs. <br>
 * Provides use of different types of alerts and dialogues all over the application.
 * @see Alert
 */
public class Alerts extends Application {

	/**
	 * Shows alert dialog with given string and set titel "Der Eiserne Pfad".
	 * @param alertMessage - will be displayed as text
	 * @param alertType - determines which buttons are displayed <br>
	 * - 0 = info <br>
	 * - 1 = info (automatically close after 2 sec) <br>
	 * - 2 = warning <br>
	 * - 3 = error <br>
	 * - 4 = (confirmation)
	 */
	void createNewAlert(String alertMessage, int alertType){
		Alert newAlert = new Alert(AlertType.INFORMATION);
		newAlert.setTitle("Der Eiserne Pfad");
		newAlert.setHeaderText("Information");
		newAlert.setContentText(alertMessage);
		//newAlert.initStyle(StageStyle.UNDECORATED); // makes it so, the alert box has no frame

		switch(alertType){
		case 0: 
			newAlert.showAndWait();
			break;

		case 1:	
			Timeline timeline = new Timeline(new KeyFrame( Duration.seconds(2), 
					new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event){
					newAlert.hide();
				}
			}));
			timeline.setCycleCount(1);
			timeline.play();					
			newAlert.show();
			break;

		case 2: 
			newAlert.setAlertType(AlertType.WARNING);
			newAlert.setHeaderText("Warnung!");
			newAlert.showAndWait();
			break;

		case 3: 
			newAlert.setAlertType(AlertType.ERROR);
			newAlert.setHeaderText("Da ist etwas schief gelaufen! :(");
			newAlert.showAndWait();
			break;

		case 4: 
			newAlert.setAlertType(AlertType.CONFIRMATION);
			newAlert.setHeaderText("Sicher?");
			// makes sure, isConfirmed is actually null (maybe redundant)
			info.CharDecicions.setIsConfirmed(null);

			Optional<ButtonType> queryResult = newAlert.showAndWait();
			if (queryResult.get() == ButtonType.OK){
				info.CharDecicions.setIsConfirmed(true);	
			}
			else {
				info.CharDecicions.setIsConfirmed(false);	
			}
			break;							
		default:
			createNewAlert("Kein gültiger Alert-Typ!", 3);
			break;
		}
	}	

	/**
	 * Chose the stage of the adv / dis
	 * @param value of the adv / dis
	 */
	public int createNewAlert(int value) {
		Alert newAlert = new Alert(AlertType.INFORMATION);
		newAlert.setTitle("Der Eiserne Pfad");
		newAlert.setHeaderText("Wähle deine Stufe!");
		newAlert.setContentText("Welche Stufe möchtest du?");

		ButtonType buttonStageOne = new ButtonType("Stufe 1");
		ButtonType buttonStageTwo = new ButtonType("Stufe 2");
		ButtonType buttonStageThree = new ButtonType("Stufe 3");

		Optional<ButtonType> choiceResult;

		switch (value) {
		case 12:
			newAlert.getButtonTypes().setAll(buttonStageOne, buttonStageTwo, ButtonType.CANCEL);
			choiceResult = newAlert.showAndWait();
			if (choiceResult.get() == buttonStageOne){				 
				return 1;	
			}
			else if (choiceResult.get() == buttonStageTwo){				 
				return 2;	
			}
			else{
				return 0;
			}
		case 123:
			newAlert.getButtonTypes().setAll(buttonStageOne, buttonStageTwo, buttonStageThree, ButtonType.CANCEL);
			choiceResult = newAlert.showAndWait();
			if (choiceResult.get() == buttonStageOne){				 
				return 1;	
			}
			else if (choiceResult.get() == buttonStageTwo){				 
				return 2;	
			}
			else if (choiceResult.get() == buttonStageThree){				 
				return 3;	
			}
			else{
				return 0;
			}
		case 23:
			newAlert.getButtonTypes().setAll(buttonStageTwo, buttonStageThree, ButtonType.CANCEL);
			choiceResult = newAlert.showAndWait();
			if (choiceResult.get() == buttonStageTwo){				 
				return 2;	
			}
			else if (choiceResult.get() == buttonStageThree){				 
				return 3;	
			}
			else{
				return 0;
			}
		case 31:
			newAlert.getButtonTypes().setAll(buttonStageThree, ButtonType.CANCEL);
			choiceResult = newAlert.showAndWait();			
			if (choiceResult.get() == buttonStageThree){				 
				return 4;	
			}
			else{
				return 0;
			}
		case 29:
			newAlert.setContentText("Diese Gabe wählen?");
			newAlert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
			choiceResult = newAlert.showAndWait();
			if (choiceResult.get() == ButtonType.OK){
				return 29;	
			}
			else{
				return 0;
			}
		case 39:
			newAlert.setContentText("Diese Gabe wählen?");
			newAlert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
			choiceResult = newAlert.showAndWait();
			if (choiceResult.get() == ButtonType.OK){
				return 39;	
			}
			else{
				return 0;
			}
		case 239:
			newAlert.setContentText("Diese Gabe wählen? Welche Stufe?");
			newAlert.getButtonTypes().setAll(buttonStageTwo, buttonStageThree, ButtonType.CANCEL);
			choiceResult = newAlert.showAndWait();
			if (choiceResult.get() == buttonStageTwo){
				return 29;	
			}
			if (choiceResult.get() == buttonStageThree){
				return 39;	
			}
			else{
				return 0;
			}
		default:
			return 0;
		}
	}

	/**
	 * Since this class extends application, 
	 * this method needs to exist for the compiling process to work. Please ignore it.
	 * @deprecated - should never be used, if everything works
	 */
	@Override
	public void start(Stage arg0){}


}
