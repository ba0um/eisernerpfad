package gui;

import info.CharInfo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class InputScene {
	
	private CharInfo info = new info.CharInfo();
	
	private Scene inputScene;
	
	/**
	 * Number of input scenes: <br>
	 * 0 = name/sex <br>
	 * 1 = race/culture <br>
	 */
	private int sceneCount = 0;
	
	public Scene createInputScene(){
		
		AnchorPane rootPane = new AnchorPane();
		
		/*
		 * Labels
		 */
		Group groupLabels = new Group();
		
		Label labelCharName = new Label("Wähle deinen Namen!");
		labelCharName.setId("labelCharName");
		
		Label labelCharSex = new Label("Wähle dein Geschlecht!");
		labelCharSex.setId("labelCharSex");
		labelCharSex.setLayoutY(30);
		
		groupLabels.getChildren().addAll(labelCharName, labelCharSex);
		
		
		
		/*
		 * Textfields, etc.
		 */
		Group groupTextfields = new Group();
		
		TextField textfieldName = new TextField();
		textfieldName.setId("textfieldName");
		textfieldName.setLayoutX(140);
		
		ToggleGroup toggleGroupSex = new ToggleGroup();
		ToggleButton toggleSexMale = new ToggleButton("Männlich");
		toggleSexMale.setToggleGroup(toggleGroupSex);
		toggleSexMale.setId("textfieldSex");
		toggleSexMale.setLayoutX(140);
		toggleSexMale.setLayoutY(30);
		ToggleButton toggleSexFemale = new ToggleButton("Weiblich");
		toggleSexFemale.setToggleGroup(toggleGroupSex);
		toggleSexFemale.setSelected(true);
		toggleSexFemale.setId("textfieldSex");
		toggleSexFemale.setLayoutX(210);
		toggleSexFemale.setLayoutY(30);
		
		groupTextfields.getChildren().addAll(textfieldName, toggleSexMale, toggleSexFemale);
				
		
		/*
		 * Buttons
		 */
		Group groupButtons = new Group();
		
		Button buttonNextStep = new Button("Weiter");
		buttonNextStep.setId("buttonNextStep");
		buttonNextStep.setLayoutX(120);
		buttonNextStep.setLayoutY(60);
		buttonNextStep.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (sceneCount == 0) {				

					String charName = textfieldName.getText();
					info.setCharName(charName);
					
					boolean charSex = toggleSexMale.isSelected();
					info.setCharSex(charSex);
					
					System.out.println("Name: " + info.getCharName() + "\n" + "Geschlecht: " + info.isCharSex());
					
				}
				sceneCount++;
				groupTextfields.getChildren().removeAll(groupLabels, groupTextfields);
				// TODO change position of button
				// TODO switch scene 
			}
		});
		
		groupButtons.getChildren().addAll(buttonNextStep);
		
		
		rootPane.getChildren().addAll(groupLabels, groupTextfields, groupButtons);
		
		
		inputScene = new Scene(rootPane);
		return inputScene;
		
	}

}
