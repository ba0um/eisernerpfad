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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import gui.ToggleSet;

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
	private int curStr;
	private Label labelCurDex;
	private int curDex;
	private Label labelCurInt;
	private int curInt;
	private int availableBonuses;
	private int adv;
	private int dis;
	private int costsAdv;
	private int costsDis;
	private ArrayList<Integer> listChosenAdv;
	private ArrayList<Integer> listChosenDis;
	private boolean isAGiftSelected;

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

		Label labelCharName = new Label("W�hle deinen Namen!");
		labelCharName.setId("labelCharName");

		Label labelCharSex = new Label("W�hle dein Geschlecht!");
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
		ToggleButton toggleSexMale = new ToggleButton("M�nnlich");
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
						alerts.createNewAlert("Name und Geschlecht w�hlen!", 2);
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
						alerts.createNewAlert("Rasse und Klasse w�hlen!", 2);
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
							alerts.createNewAlert("Diese Gr��e ist f�r deine Rasse nicht erlaubt", 2);
							return;
						}
					}
					else{
						alerts.createNewAlert("Alle Optionen w�hlen!", 2);
						return;
					}
					System.out.println("Haarfarbe: " + info.getCharHairColor() + "\n" + "Augenfarbe: " + info.getCharEyeColor()
					+"\nGr��e: " + info.getCharHeight() + "\nGewicht: " + info.getCharWeight());
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
						alerts.createNewAlert("Pfad und Profession w�hlen!", 2);
						return;
					}
					System.out.println("Pfad: " + info.getCharPath() + "\n" + "Beruf: " + info.getCharProfession());
					break;
				case 4:
					int str = curStr;
					int dex = curDex;
					int intell = curInt;
					if(availableBonuses == 0){
						info.setCharAttributeStrength(str);
						info.setCharAttributeDexterity(dex);
						info.setCharAttributeIntelligence(intell);
					}
					else{
						alerts.createNewAlert("Bitte alle Punkte ausgeben!", 2);
						return;
					}
					System.out.println("St�rke: " + info.getCharAttributeStrength() + "\n" + "Gewandtheit: " + info.getCharAttributeDexterity()
					+ "\nIntelligenz: " + info.getCharAttributeIntelligence());
					break;
				case 5:
					ArrayList<String> selectedAdv = new ArrayList<>();
					ArrayList<String> selectedDis = new ArrayList<>();					
					if(adv-dis == 0 && dis >= 0){
						for(Integer i: listChosenAdv){
							selectedAdv.add(info.getAdvantages()[i].getKey());
						}
						for(Integer i: listChosenDis){
							selectedDis.add(info.getDisadvantages()[i].getKey());
						}
					}
					else{
						alerts.createNewAlert("Du hast noch Vorteile �brig, oder zu viele Nachteile ausgew�hlt. Bitte beginne von vorne.", 2);
						sceneCount = 4;
					}
					System.out.println("Gew�hlte Vorteile: " + selectedAdv + "\nGew�hlte Nachteile: " + selectedDis);
					break;
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
			Label labelRace = new Label("W�hle deine Rasse:");
			ObservableList<String> raceList = FXCollections.observableArrayList(
					"HALF_AETERNA",	"NEHRIM", "ARAZEAL",
					"KILE",	"QYRA", "SKARAGG"
					);
			raceMenu = new ComboBox<>();
			raceMenu.setPromptText("W�hle deine Rasse");
			raceMenu.setItems(raceList);

			raceMenu.valueProperty().addListener(new ChangeListener<String>(){

				@Override
				public void changed(ObservableValue val, String old_race, String new_race) {
					cultureMenu.setItems(decisions.changeCultureList(new_race));					
				}

			});
			raceMenu.setLayoutX(140);

			Label labelCulture = new Label ("W�hle deine Kultur:");
			labelCulture.setLayoutY(30);
			cultureMenu = new ComboBox<>();
			cultureMenu.setPromptText("W�hle deine Kultur");
			ObservableList<String> cultureList = FXCollections.observableArrayList(
					"Rasse zuerst w�hlen"
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
			Label labelHair = new Label("W�hle deine Haarfarbe:");
			hairList = decisions.getColorList(true);
			hairMenu = new ComboBox<>();
			hairMenu.setPromptText("W�hle deine Haarfarbe");
			hairMenu.setItems(hairList);
			hairMenu.setLayoutX(140);

			Button buttonRandomHair = new Button("Zuf�llig");
			buttonRandomHair.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					hairMenu.getSelectionModel().select(decisions.randomizeColorList());
				}				
			});

			buttonRandomHair.setLayoutX(320);

			Label labelEye = new Label("W�hle deine Augenfarbe:");
			labelEye.setLayoutY(30);
			eyeList = decisions.getColorList(false);
			eyeMenu = new ComboBox<>();
			eyeMenu.setPromptText("W�hle deine Augenfarbe");
			eyeMenu.setItems(eyeList);
			eyeMenu.setLayoutX(140);
			eyeMenu.setLayoutY(30);

			Button buttonRandomEye = new Button("Zuf�llig");
			buttonRandomEye.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					eyeMenu.getSelectionModel().select(decisions.randomizeColorList());
				}				
			});			
			buttonRandomEye.setLayoutX(320);
			buttonRandomEye.setLayoutY(30);

			Label labelHeight = new Label("K�rpergr��e in Finger:");	
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

			textFieldHeight.setPromptText("Deine Gr��e");
			textFieldHeight.setLayoutX(140);
			textFieldHeight.setLayoutY(60);
			textFieldHeight.textProperty().addListener((obs, oldString, newString) ->{
				weight = decisions.calculateWeight(textFieldHeight.getText());
				labelWeight.setText("Gewicht in Stein: " + weight);
			});

			Button buttonRandomHeight = new Button("Zuf�llig");
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
			Label labelPath = new Label("W�hle deinen Pfad");
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
			togglePathless.setLayoutX(320);

			Label labelProf = new Label("W�hle deinen Beruf");
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
			int[] raceBonus = info.getRaceBonuses(info.getCharRace());
			availableBonuses = info.getRaceStatsBonus();

			Label labelBase = new Label("W�hle deine Basiswerte:");
			Label labelStr = new Label("St�rke:");
			Label labelDex = new Label("Gewandtheit:");
			Label labelInt = new Label("Intelligenz:");
			Label labelBonus = new Label("Punkte �brig:");
			Label labelBonusValue = new Label(Integer.toString(availableBonuses));
			labelBonus.setLayoutX(250);
			labelBonusValue.setLayoutX(300);
			labelBonusValue.setLayoutY(20);
			labelStr.setLayoutY(30);
			labelDex.setLayoutY(60);
			labelInt.setLayoutY(90);			

			curStr = info.getCharAttributeStrength() + raceBonus[0];
			labelCurStr = new Label(Integer.toString(curStr));
			curDex = info.getCharAttributeDexterity() + raceBonus[1];
			labelCurDex = new Label(Integer.toString(curDex));
			curInt = info.getCharAttributeIntelligence() + raceBonus[2];
			labelCurInt = new Label(Integer.toString(curInt));
			labelCurStr.setLayoutX(85);
			labelCurStr.setLayoutY(30);
			labelCurDex.setLayoutX(85);
			labelCurDex.setLayoutY(60);
			labelCurInt.setLayoutX(85);
			labelCurInt.setLayoutY(90);

			Button buttonStrPlus = new Button(" + ");
			buttonStrPlus.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					if(availableBonuses > 0){
						curStr++;
						labelCurStr.setText(Integer.toString(curStr));	
						availableBonuses--;
						labelBonusValue.setText(Integer.toString(availableBonuses));
					}
					else{
						alerts.createNewAlert("Alle Boni aufgebraucht", 2);
					}
				}				
			});	
			Button buttonStrMinus = new Button(" - ");
			buttonStrMinus.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					if(curStr > info.getCharAttributeStrength() + raceBonus[0]){
						curStr--;
						labelCurStr.setText(Integer.toString(curStr));	
						availableBonuses++;
						labelBonusValue.setText(Integer.toString(availableBonuses));
					}
					else{
						alerts.createNewAlert("Nicht unter den Startwert", 2);
					}
				}				
			});
			Button buttonDexPlus = new Button(" + ");
			buttonDexPlus.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					if(availableBonuses > 0){
						curDex++;
						labelCurDex.setText(Integer.toString(curDex));	
						availableBonuses--;
						labelBonusValue.setText(Integer.toString(availableBonuses));
					}
					else{
						alerts.createNewAlert("Alle Boni aufgebraucht", 2);
					}
				}				
			});
			Button buttonDexMinus = new Button(" - ");
			buttonDexMinus.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					if(curDex > info.getCharAttributeDexterity() + raceBonus[1]){
						curDex--;
						labelCurDex.setText(Integer.toString(curDex));	
						availableBonuses++;
						labelBonusValue.setText(Integer.toString(availableBonuses));
					}
					else{
						alerts.createNewAlert("Nicht unter den Startwert", 2);
					}
				}				
			});
			Button buttonIntPlus = new Button(" + ");
			buttonIntPlus.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					if(availableBonuses > 0){
						curInt++;
						labelCurInt.setText(Integer.toString(curInt));	
						availableBonuses--;
						labelBonusValue.setText(Integer.toString(availableBonuses));
					}
					else{
						alerts.createNewAlert("Alle Boni aufgebraucht", 2);
					}
				}				
			});
			Button buttonIntMinus = new Button(" - ");
			buttonIntMinus.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					if(curInt > info.getCharAttributeIntelligence() + raceBonus[2]){
						curInt--;
						labelCurInt.setText(Integer.toString(curInt));	
						availableBonuses++;
						labelBonusValue.setText(Integer.toString(availableBonuses));
					}
					else{
						alerts.createNewAlert("Nicht unter den Startwert", 2);
					}
				}				
			});

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
			nodes.add(labelBonus);
			nodes.add(labelBonusValue);
			nodes.add(labelCurStr);
			nodes.add(labelCurDex);
			nodes.add(labelCurInt);
			nodes.add(buttonStrPlus);
			nodes.add(buttonStrMinus);
			nodes.add(buttonDexPlus);
			nodes.add(buttonDexMinus);
			nodes.add(buttonIntPlus);
			nodes.add(buttonIntMinus);		
			buttonNextStep.setLayoutY(120);
			nodes.add(buttonNextStep);
			break;
		// adv / dis
		// TODO: Stufe �bergeben (f�r die Ausgabe)
		// TODO: Gaben abw�hlen fixen
		// TODO: allg. abw�hlen fixen, so dass die Kosten stimmen
		case 5:
			adv = info.getCharAdvDis().getKey();
			if(info.getCharCulture().equals("NEHRIM_FREE")){
				adv++;
			}
			String path = info.getCharPath();
			if(path.equals("Die Schaffenden")){
				adv += 3;
			}
			else if(path.equals("Die Belesenen")){
				adv += 2;
			}
			else if(path.equals("Die Erhabenen")){
				adv++;
			}
			dis = info.getCharAdvDis().getValue();
			
			Label labelAdv = new Label("Vorteile:");
			Label labelAdvValue = new Label(Integer.toString(adv));
			Label labelDis = new Label("Nachteile:");
			Label labelDisValue = new Label(Integer.toString(dis));
			labelAdvValue.setLayoutX(60);
			labelDis.setLayoutX(400);
			labelDisValue.setLayoutX(460);		

			ScrollPane scrollAdv = new ScrollPane();
			scrollAdv.setLayoutY(60);
			scrollAdv.setMaxHeight(500);
			scrollAdv.setMinWidth(290);
			VBox vBoxAdv = new VBox();
			scrollAdv.setContent(vBoxAdv);
			Pair<String, Integer>[] listAdv = info.getAdvantages();
			ToggleSet<ToggleButton> toggleSetAdv = new ToggleSet<>(adv);
			for (int i = 0; i < listAdv.length; i++){
				String name = listAdv[i].getKey();
				costsAdv = listAdv[i].getValue();
				while (costsAdv > 9) {
					costsAdv = costsAdv / 10;
				}				
				ToggleButton toggleButton = new ToggleButton();
				toggleButton.setText(name + " (" + costsAdv + ")");			
				toggleButton.setUserData(i);
				
				toggleButton.selectedProperty().addListener((obs, wasChecked, isNowChecked) -> {
					int cash = listAdv[(int) toggleButton.getUserData()].getValue();
					if (isNowChecked) {
						if(cash > 3){
							int getCash = alerts.createNewAlert(cash);
							switch (getCash) {
							case 0:
								return;
							case 4:
								cash = 3;
								adv++;
							case 29:
								if(isAGiftSelected){
									alerts.createNewAlert("Nur eine Gabe erlaubt!", 2);
									adv -= 29;
									toggleButton.setSelected(false);
									return;
								}
								else{
									isAGiftSelected = true;
									cash = 2;
									break;
								}
							case 39:
								if(isAGiftSelected){
									alerts.createNewAlert("Nur eine Gabe erlaubt!", 2);
									adv -= 39;
									toggleButton.setSelected(false);
									return;
								}
								else{
									isAGiftSelected = true;
									cash = 3;
									break;
								}
							default:
								cash = getCash;
								break;
							}
						}
						toggleSetAdv.addToggle(toggleButton);
						adv -= cash;                  
					} else {
						if(cash > 3){
							int getCash = alerts.createNewAlert(cash);
							switch (getCash) {
							case 0:
								return;
							case 4:
								cash = 3;
								adv++;
							case 29:
								cash = 2;
								isAGiftSelected = false;
								break;
							case 39:
								cash = 3;
								isAGiftSelected = false;
								break;
							default:
								cash = getCash;
								break;
							}
						}
						toggleSetAdv.removeToggle(toggleButton);
						adv += cash;
					}
					labelAdvValue.setText(Integer.toString(adv));
					listChosenAdv = new ArrayList<Integer>();
					for (Node n: toggleSetAdv.getSelectedToggles()) {
						listChosenAdv.add((Integer) n.getUserData());
					}
				});
				vBoxAdv.getChildren().add(toggleButton);
			}

			ScrollPane scrollDis = new ScrollPane();
			scrollDis.setLayoutY(60);
			scrollDis.setLayoutX(400);
			scrollDis.setMaxHeight(500);
			scrollDis.setMinWidth(290);
			VBox vBoxDis = new VBox();
			scrollDis.setContent(vBoxDis);
			Pair<String, Integer>[] listDis = info.getDisadvantages();
			ToggleSet<ToggleButton> toggleSetDis = new ToggleSet<>(dis);
			for (int i = 0; i < listDis.length; i++){
				String name = listDis[i].getKey();
				costsDis = listDis[i].getValue();
				while (costsDis > 9) {
					if(i == 0) {
					}
					costsDis = costsDis / 10;
				}	
				
				ToggleButton toggleButton = new ToggleButton();
				toggleButton.setUserData(i);
				toggleButton.setText(name + " (" + costsDis + ")");

				toggleButton.selectedProperty().addListener((obs, wasChecked, isNowChecked) -> {
					int cash = listDis[(int) toggleButton.getUserData()].getValue();
					if (isNowChecked) {
						if(cash > 3){
							int getCash = alerts.createNewAlert(cash);
							switch (getCash) {
							case 0:
								return;
							case 4:
								cash = 3;
								adv++;							
							default:
								cash = getCash;
								break;
							}
						}
						toggleSetDis.addToggle(toggleButton);
						dis -= cash;	             
					} else {
						if(cash > 3){
							int getCash = alerts.createNewAlert(cash);
							switch (getCash) {
							case 0:
								return;
							case 4:
								cash = 3;
								adv++;							
							default:
								cash = getCash;
								break;
							}
						}
						toggleSetDis.removeToggle(toggleButton);
						dis += cash;
					}
					labelDisValue.setText(Integer.toString(dis));
					listChosenDis = new ArrayList<Integer>();
					for (Node n: toggleSetDis.getSelectedToggles()) {
						listChosenDis.add((Integer) n.getUserData());
					}
				});
				vBoxDis.getChildren().add(toggleButton);	           
			}
			nodes.add(labelAdv);
			nodes.add(labelAdvValue);
			nodes.add(labelDis);
			nodes.add(labelDisValue);
			nodes.add(scrollAdv);
			nodes.add(scrollDis);
			
			buttonNextStep.setLayoutX(320);
			nodes.add(buttonNextStep);
			break;
		default:
			break;
		}
		return nodes;
	}
}
