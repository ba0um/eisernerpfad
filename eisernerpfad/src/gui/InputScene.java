package gui;


import java.util.ArrayList;
import java.util.List;

import info.CharDecicions;
import info.CharInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class InputScene {
	
	private CharDecicions decicions = new CharDecicions();
	private CharInfo info = decicions.info;
	
	private Scene inputScene;
	private AnchorPane rootPane = new AnchorPane();
	
	/**
	 * Number of input scenes: <br>
	 * 0 = name/sex <br>
	 * 1 = race/culture <br>
	 */
	private int sceneCount = 0;
	
	public Scene createInputScene(){
		
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
				List<Node> listNodes = newNodes(sceneCount);
				rootPane.getChildren().removeAll(groupLabels, groupTextfields);
				listNodes.stream()
					.forEach(x -> rootPane.getChildren().add(x));				
			}
		});
		
		groupButtons.getChildren().addAll(buttonNextStep);
		
		if(sceneCount == 0) {
			rootPane.getChildren().addAll(groupLabels, groupTextfields, groupButtons);	
		}
		
		
		inputScene = new Scene(rootPane);
		return inputScene;
		
	}
	
	private List<Node> newNodes(int sceneCount){
		List<Node> nodes = new ArrayList<>();
		
		switch (sceneCount) {
		case 1:
			Label labelRace = new Label("Wähle deine Rasse:");
			TextField textfieldRace = new TextField();
			textfieldRace.setLayoutX(140);
			Label labelCulture = new Label ("Wähle deine Kultur:");
			labelCulture.setLayoutY(30);
			ComboBox cultureMenu = new ComboBox<>();
			cultureMenu.setLayoutX(140);
			cultureMenu.setLayoutY(30);
			
			nodes.add(labelRace);
			nodes.add(textfieldRace);
			nodes.add(labelCulture);
			nodes.add(cultureMenu);
			
			break;

		default:
			break;
		}
		return nodes;
	}
}
