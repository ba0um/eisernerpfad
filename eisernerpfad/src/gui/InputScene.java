package gui;


import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import info.CharDecicions;
import info.CharInfo;
import info.CharInitialize;
import info.NewCharacter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class InputScene {
	
	private NewCharacter newChar = new NewCharacter();	
	private CharDecicions decisions = newChar.getDecisions();
	private CharInfo info = newChar.getInfo();
	private CharInitialize init = newChar.getInit();
	private Alerts alerts = new Alerts();

	private Scene inputScene;
	private AnchorPane rootPane = new AnchorPane();
	
	/*
	 * Variables needed in the whole class
	 */
	Button buttonNextStep;
	private ComboBox<String> cultureMenu;	
	private ComboBox<String> raceMenu;
	private ObservableList<String> hairList;
	private ComboBox<String> hairMenu;
	private ObservableList<String> eyeList;
	private ComboBox<String> eyeMenu;
	private TextField textFieldHeight;
	private int weight;
	private ToggleGroup groupPaths;
	private TextField textProf;
	private Label labelCurStr;
	private Label labelCurDex;
	private Label labelCurInt; 
	

	/**
	 * Number of input scenes: <br>
	 * 0 = name/sex <br>
	 * 1 = race/culture <br>
	 */
	private int sceneCount = 0;

	public Scene createInputScene(){
		
		/*
		 * Initialize the new character, before creating the scene.
		 */
		init.initializeNewChar();
		
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

		buttonNextStep = new Button("Weiter");
		buttonNextStep.setId("buttonNextStep");
		buttonNextStep.setLayoutX(120);
		buttonNextStep.setLayoutY(60);
		buttonNextStep.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switch (sceneCount) {
				case 0:
					String charName = textfieldName.getText();
					boolean charSex = toggleSexMale.isSelected();					
					if(!charName.trim().isEmpty() && toggleGroupSex.getSelectedToggle() != null){
						if(charName.length() > 2){
							info.setCharName(charName);
							info.setCharSex(charSex);
						}
						else{
							alerts.createNewAlert("Name zu kurz!", 2);
							return;
						}

					}	
					else{
						alerts.createNewAlert("Name und Geschlecht wählen!", 2);
						return;
					}
					System.out.println("Name: " + info.getCharName() + "\n" + "Geschlecht: " + info.isCharSex());					
					break;
				case 1:					
					String race = raceMenu.getValue();
					String culture = cultureMenu.getValue();
					if(race != null && culture != null){
						info.setCharRace(race);
						info.setCharCulture(culture);
					}	
					else{
						alerts.createNewAlert("Rasse und Klasse wählen!", 2);
						return;
					}
					System.out.println("Rasse: " + info.getCharRace() + "\n" + "Kultur: " + info.getCharCulture());
					break;
				case 2:
					String hair = hairMenu.getValue();
					String eye = eyeMenu.getValue();
					String height = textFieldHeight.getText();
					if(hair != null && eye != null && !height.trim().isEmpty()){
						if(decisions.isHeightAllowed(height)){
							info.setCharHairColor(hair);
							info.setCharEyeColor(eye);
							info.setCharHeight(Integer.parseInt(height));
							info.setCharWeight(weight);
						}
						else{
							alerts.createNewAlert("Diese Größe ist für deine Rasse nicht erlaubt", 2);
							return;
						}
					}
					else{
						alerts.createNewAlert("Alle Optionen wählen!", 2);
						return;
					}
					System.out.println("Haarfarbe: " + info.getCharHairColor() + "\n" + "Augenfarbe: " + info.getCharEyeColor()
										+"\nGröße: " + info.getCharHeight() + "\nGewicht: " + info.getCharWeight());
					break;
				case 3:
					String path = "Dein Pfad";
					String prof = textProf.getText();
					if(prof.length() > 3 && groupPaths.getSelectedToggle() != null){
						path = groupPaths.getSelectedToggle().getUserData().toString();
						info.setCharPath(path);
						info.setCharProfession(prof);
					}
					else {
						alerts.createNewAlert("Pfad und Profession wählen!", 2);
						return;
					}
					System.out.println("Pfad: " + info.getCharPath() + "\n" + "Beruf: " + info.getCharProfession());
				default:
					break;
				}

				sceneCount++;
				List<Node> listNodes = newNodes(sceneCount);
				rootPane.getChildren().clear();
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

	/**
	 * Creates all nodes of the current scene.
	 * @param sceneCount - number of the current scene
	 * @return list of nodes
	 */
	private List<Node> newNodes(int sceneCount){
		List<Node> nodes = new ArrayList<>();

		switch (sceneCount) {
		
		// race / culture
		case 1:
			Label labelRace = new Label("Wähle deine Rasse:");
			ObservableList<String> raceList = FXCollections.observableArrayList(
					"HALF_AETERNA",	"NEHRIM", "ARAZEAL",
					"KILE",	"QYRA", "SKARAGG"
					);
			raceMenu = new ComboBox<>();
			raceMenu.setPromptText("Wähle deine Rasse");
			raceMenu.setItems(raceList);

			raceMenu.valueProperty().addListener(new ChangeListener<String>(){

				@Override
				public void changed(ObservableValue val, String old_race, String new_race) {
					cultureMenu.setItems(decisions.changeCultureList(new_race));					
				}

			});
			raceMenu.setLayoutX(140);

			Label labelCulture = new Label ("Wähle deine Kultur:");
			labelCulture.setLayoutY(30);
			cultureMenu = new ComboBox<>();
			cultureMenu.setPromptText("Wähle deine Kultur");
			ObservableList<String> cultureList = FXCollections.observableArrayList(
					"Rasse zuerst wählen"
					);
			cultureMenu.setItems(cultureList);
			cultureMenu.setLayoutX(140);
			cultureMenu.setLayoutY(30);

			nodes.add(labelRace);
			nodes.add(raceMenu);
			nodes.add(labelCulture);
			nodes.add(cultureMenu);
			nodes.add(buttonNextStep);
			break;
			
			// hair / eye / height
		case 2:
			Label labelHair = new Label("Wähle deine Haarfarbe:");
			hairList = decisions.getColorList(true);
			hairMenu = new ComboBox<>();
			hairMenu.setPromptText("Wähle deine Haarfarbe");
			hairMenu.setItems(hairList);
			hairMenu.setLayoutX(140);

			Button buttonRandomHair = new Button("Zufällig");
			buttonRandomHair.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					hairMenu.getSelectionModel().select(decisions.randomizeColorList());
				}				
			});

			buttonRandomHair.setLayoutX(320);

			Label labelEye = new Label("Wähle deine Augenfarbe:");
			labelEye.setLayoutY(30);
			eyeList = decisions.getColorList(false);
			eyeMenu = new ComboBox<>();
			eyeMenu.setPromptText("Wähle deine Augenfarbe");
			eyeMenu.setItems(eyeList);
			eyeMenu.setLayoutX(140);
			eyeMenu.setLayoutY(30);

			Button buttonRandomEye = new Button("Zufällig");
			buttonRandomEye.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					eyeMenu.getSelectionModel().select(decisions.randomizeColorList());
				}				
			});			
			buttonRandomEye.setLayoutX(320);
			buttonRandomEye.setLayoutY(30);

			Label labelHeight = new Label("Körpergröße in Finger:");	
			labelHeight.setLayoutY(60);
			Label labelWeight = new Label("Gewicht:");	
			labelWeight.setLayoutY(90);
			textFieldHeight = new TextField();
			
			// filters all non-integer characters
			UnaryOperator<Change> integerFilter = change -> {
			    String input = change.getText();
			    if (input.matches("[0-9]*")) { 
			        return change;
			    }
			    return null;
			};
			textFieldHeight.setTextFormatter(new TextFormatter<String>(integerFilter));
			
			textFieldHeight.setPromptText("Deine Größe");
			textFieldHeight.setLayoutX(140);
			textFieldHeight.setLayoutY(60);
			textFieldHeight.textProperty().addListener((obs, oldString, newString) ->{
				weight = decisions.calculateWeight(textFieldHeight.getText());
				labelWeight.setText("Gewicht in Stein: " + weight);
			});

			Button buttonRandomHeight = new Button("Zufällig");
			buttonRandomHeight.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					textFieldHeight.setText(String.valueOf(decisions.randomizeHeight()));
				}				
			});	
			buttonRandomHeight.setLayoutX(320);
			buttonRandomHeight.setLayoutY(60);

			nodes.add(labelHair);
			nodes.add(hairMenu);
			nodes.add(buttonRandomHair);
			nodes.add(labelEye);
			nodes.add(eyeMenu);
			nodes.add(buttonRandomEye);
			nodes.add(labelHeight);
			nodes.add(textFieldHeight);
			nodes.add(buttonRandomHeight);
			nodes.add(labelWeight);
			buttonNextStep.setLayoutY(120);
			nodes.add(buttonNextStep);
			break;
			
		// path / role / profession	
		case 3:
			Label labelPath = new Label("Wähle deinen Pfad");
			ToggleButton toggleSublime = new ToggleButton("Die Erhabenen");
			toggleSublime.setUserData("Die Erhabenen");			
			ToggleButton toggleErudite = new ToggleButton("Die Belesenen");
			toggleErudite.setUserData("Die Belesenen");
			ToggleButton toggleManufacturer = new ToggleButton("Die Schaffenden");
			toggleManufacturer.setUserData("Die Schaffenden");
			ToggleButton togglePathless = new ToggleButton("Die Wegelosen");
			togglePathless.setUserData("Die Wegelosen");
			groupPaths = new ToggleGroup();
			toggleSublime.setToggleGroup(groupPaths);
			toggleErudite.setToggleGroup(groupPaths);
			toggleManufacturer.setToggleGroup(groupPaths);
			togglePathless.setToggleGroup(groupPaths);
			toggleSublime.setLayoutY(30);
			toggleErudite.setLayoutY(30);
			toggleErudite.setLayoutX(100);
			toggleManufacturer.setLayoutY(30);
			toggleManufacturer.setLayoutX(200);
			togglePathless.setLayoutY(30);
			togglePathless.setLayoutX(300);
			
			Label labelProf = new Label("Wähle deinen Beruf");
			textProf = new TextField();
			textProf.setPromptText("Dein Beruf");			
			labelProf.setLayoutY(60);
			textProf.setLayoutX(140);
			textProf.setLayoutY(60);
			
			nodes.add(labelPath);
			nodes.add(toggleSublime);
			nodes.add(toggleErudite);
			nodes.add(toggleManufacturer);
			nodes.add(togglePathless);
			nodes.add(labelProf);
			nodes.add(textProf);
			buttonNextStep.setLayoutY(90);
			nodes.add(buttonNextStep);
			break;
			
		// base stats	
		case 4:
			Label labelBase = new Label("Wähle deine Basiswerte:");
			Label labelStr = new Label("Stärke:");
			Label labelDex = new Label("Gewandtheit:");
			Label labelInt = new Label("Intelligenz:");
			labelStr.setLayoutY(30);
			labelDex.setLayoutY(60);
			labelInt.setLayoutY(90);
			labelCurStr = new Label();
			labelCurDex = new Label();
			labelCurInt = new Label();
			labelCurStr.setText(Integer.toString(info.getCharAttributeStrength()));
			labelCurDex.setText(Integer.toString(info.getCharAttributeDexterity()));
			labelCurInt.setText(Integer.toString(info.getCharAttributeIntelligence()));
			labelCurStr.setLayoutX(50);
			labelCurStr.setLayoutY(30);
			labelCurDex.setLayoutX(50);
			labelCurDex.setLayoutY(60);
			labelCurInt.setLayoutX(50);
			labelCurInt.setLayoutY(90);
			
			Button buttonStrPlus = new Button(" + ");
			buttonStrPlus.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					int newBase = decisions.changeBaseStat("Str", true);
					labelCurStr.setText(Integer.toString(newBase));
				}				
			});	
			Button buttonStrMinus = new Button(" - ");
			Button buttonDexPlus = new Button(" + ");
			Button buttonDexMinus = new Button(" - ");
			Button buttonIntPlus = new Button(" + ");
			Button buttonIntMinus = new Button(" - ");
			buttonStrPlus.setLayoutX(100);
			buttonStrPlus.setLayoutY(30);
			buttonStrMinus.setLayoutX(150);
			buttonStrMinus.setLayoutY(30);
			buttonDexPlus.setLayoutX(100);
			buttonDexPlus.setLayoutY(60);
			buttonDexMinus.setLayoutX(150);
			buttonDexMinus.setLayoutY(60);
			buttonIntPlus.setLayoutX(100);
			buttonIntPlus.setLayoutY(90);
			buttonIntMinus.setLayoutX(150);
			buttonIntMinus.setLayoutY(90);			
			
			nodes.add(labelBase);
			nodes.add(labelStr);
			nodes.add(labelDex);
			nodes.add(labelInt);
			nodes.add(buttonStrPlus);
			nodes.add(buttonStrMinus);
			nodes.add(buttonDexPlus);
			nodes.add(buttonDexMinus);
			nodes.add(buttonIntPlus);
			nodes.add(buttonIntMinus);			
			buttonNextStep.setLayoutY(120); // TODO change value
			nodes.add(buttonNextStep);
			break;
		default:
			break;
		}
		return nodes;
	}
}
