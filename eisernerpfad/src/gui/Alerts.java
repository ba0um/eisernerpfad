package gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
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
		newAlert.initStyle(StageStyle.UNDECORATED);

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
			// TODO is this case in use? if yes -> make it work by changing the infoexpert locs
			newAlert.setAlertType(AlertType.CONFIRMATION);
			newAlert.setHeaderText("Sicher?");
			// makes sure, isConfirmed is actually null (maybe redundant)
			// InformationExpert.setIsConfirmed(null);

			Optional<ButtonType> queryResult = newAlert.showAndWait();
			if (queryResult.get() == ButtonType.OK){
			//	InformationExpert.setIsConfirmed(true);	
			}
			else {
			//	InformationExpert.setIsConfirmed(false);	
			}
			break;							
		default:
			createNewAlert("Kein gültiger Alert-Typ!", 3);
			break;
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
