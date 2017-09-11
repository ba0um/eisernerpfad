package info;

import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class CharDecicions {

	public CharInfo info = new CharInfo();	
	private ObservableList<String> cultureList;
	private ObservableList<String> hairList;
	private ObservableList<String> eyeList;


	/**
	 * Changes the options for the culture drop-down-menu according to the chosen race.
	 * @param race - expects the currently chosen race
	 * @return - an observableArrayList consisting of all culture options allowed
	 */
	public ObservableList<String> changeCultureList(String race) {
		switch (race) {
		case "HALF_AETERNA":
			cultureList = FXCollections.observableArrayList(
					"NEHRIM_FREE", "NEHRIM_SOUTH", "NEHRIM_MIDDLE",	
					"ARAZEAL_FREE", "ARAZEAL_CIVILIZED", 
					"KILE_UPPER", "KILE_FOLKS",	
					"QYRA_FARM", "QYRA_FOLKS"
					);			
			break;
		case "NEHRIM":
			cultureList = FXCollections.observableArrayList(
					"NEHRIM_FREE", "NEHRIM_SOUTH", "NEHRIM_MIDDLE"
					);
			break;
		case "ARAZEAL":
			cultureList = FXCollections.observableArrayList(
					"ARAZEAL_FREE", "ARAZEAL_CIVILIZED"
					);
			break;			
		case "KILE":
			cultureList = FXCollections.observableArrayList(
					"KILE_UPPER", "KILE_FOLKS"
					);
			break;			
		case "QYRA":
			cultureList = FXCollections.observableArrayList(
					"QYRA_FARM", "QYRA_FOLKS"
					);
			break;			
		case "SKARAGG":
			cultureList = FXCollections.observableArrayList(
					"SKARAGG_BIG", "SKARAGG_SMALL"		
					);
			break;			
		default:
			break;
		}
		return cultureList;	
	}
	
	/**
	 * Works for both hair and eye color.
	 * @param isHair - expects true for hair and false for eye
	 * @return List of Strings - all allowed color options of the current race and selection (hair or eye)
	 */
	public ObservableList<String> getColorList(boolean isHair){
		String currentRace = info.getCharRace();
		switch (currentRace) {
		case "HALF_AETERNA":
			hairList = FXCollections.observableArrayList(
					"Rot", "Braun", "Dunkelblond", "Hellblond",
					"Weißblond", "Schwarz", "Blauschwarz"
					);
			eyeList = FXCollections.observableArrayList(
					"Schwarz", "Grau", "Blau", "Grün",
					"Dunkelbraun", "Hellbraun", "Goldgesprenkelt"
					);
			break;		
		case "NEHRIM": // same as Arazeal
		case "ARAZEAL":
			eyeList = FXCollections.observableArrayList(
					"Dunkelbraun", "Braun", "Blau",
					"Grau", "Grün", "Schwarz"
					);
			if (!isHair){ // breaks only if eyeList should be printed, gets the hairlist from skaragg (since it's the same)
				break;
			}
		case "SKARAGG":
			hairList = FXCollections.observableArrayList(
					"Schwarz", "Braun", "Dunkelblond",
					"Blond", "Weißblond", "Rot"
					);
			eyeList = FXCollections.observableArrayList(
					"Grün", "Braun", "Grau",
					"Blau", "Dunkelbraun", "Schwarz"
					);
			break;		
		case "KILE":
			hairList = FXCollections.observableArrayList(
					"Schwarz", "Dunkelbraun", "Braun", "Blond"
					);
			eyeList = FXCollections.observableArrayList(
					"Dunkelbraun", "Schwarz", "Braun", "Grau"
					);
			break;			
		case "QYRA":
			hairList = FXCollections.observableArrayList(
					"Schwarz", "Dunkelbraun", "Braun"
					);	
			eyeList = hairList;
		default:
			break;
		}
		if(isHair){
			return hairList;		
		}
		else{
			return eyeList;
		}
	}
	

	/**
	 * 
	 * @param rolls - number of throws of the die
	 * @param sides - number of sides of the die
	 * @return
	 */
	public int[] rollDices(int rolls, int sides) {
		int[] diceValues = new int[rolls];
		Random r = new Random();
		for(int i = 0; i < rolls; i++){
			diceValues[i] = r.nextInt(sides)+1;
		}
		return diceValues;
	}

	/**
	 * Works for both eyes and hair color
	 * @return index of the current selected item
	 */
	public int randomizeColorList() {
		int diceValue = rollDices(1, 20)[0];
		int currentSelection = 0;
		String currentRace = info.getCharRace();
		switch (currentRace) {
		case "HALF_AETERNA":
			if(diceValue == 20){
				currentSelection = 6;
			}
			else if(diceValue >= 18){
				currentSelection = 5;
			}
			else if(diceValue >= 16){
				currentSelection = 4;
			}
			else if(diceValue >= 11){
				currentSelection = 3;
			}
			else if(diceValue >= 7){
				currentSelection = 2;
			}
			else if(diceValue >= 4){
				currentSelection = 1;
			}
			else{
				currentSelection = 0;
			}
			break;
		case "NEHRIM": // same values
		case "ARAZEAL":
		case "SKARAGG":
			if(diceValue >= 19){
				currentSelection = 5;
			}
			else if(diceValue >= 17){
				currentSelection = 4;
			}
			else if(diceValue >= 13){
				currentSelection = 3;
			}
			else if(diceValue >= 7){
				currentSelection = 2;
			}
			else if(diceValue >= 3){
				currentSelection = 1;
			}
			else{
				currentSelection = 0;
			}
			break;		
		case "KILE":
			if(diceValue == 20){
				currentSelection = 3;
			}
			else if(diceValue >= 17){
				currentSelection = 2;
			}
			else if(diceValue >= 11){
				currentSelection = 1;
			}
			else{
				currentSelection = 0;
			}
			break;			
		case "QYRA":
			if(diceValue == 20){
				currentSelection = 2;
			}
			else if(diceValue >= 16){
				currentSelection = 1;
			}
			else{
				currentSelection = 0;
			}
			break;
		default:
			break;
		}
		return currentSelection;		
	}

	public int randomizeHeight() {
		int height = 0;
		int[] diceRolling = rollDices(2, 20);
		int diceValue = diceRolling[0] + diceRolling[1];
		String race = info.getCharRace();
		switch (race) {
		case "HALF_AETERNA":
			height = diceValue + 168;
			break;
		case "NEHRIM": // same height
		case "KILE":
		case "QYRA":
			height = diceValue + 160;
			break;
		case "ARAZEAL":
			height = diceValue + 165;
			break;		
		case "SKARAGG":
			height = diceValue + 155;
			break;			
		default:
			break;
		}		
		return height;
	}

	public int calculateWeight(String height) {
		int weight = Integer.parseInt(height);
		String race = info.getCharRace();
		switch (race) {
		case "HALF_AETERNA":
			weight = weight - 120;
			break;
		case "NEHRIM": // same weight
		case "KILE":
		case "QYRA":
			weight = weight - 100;
			break;
		case "ARAZEAL":
			weight = weight - 95;
			break;		
		case "SKARAGG":
			weight = weight - 110;
			break;			
		default:
			break;
		}		
		return weight;
	}
	
	public boolean isHeightAllowed(String heightString){
		boolean isAllowed = false;
		int height = Integer.parseInt(heightString);
		String race = info.getCharRace();
		switch (race) {
		case "HALF_AETERNA":
			isAllowed = (height >= 170 && height <= 208); 
			break;
		case "NEHRIM": // same height
		case "KILE":
		case "QYRA":
			isAllowed = (height >= 162 && height <= 200); 
			break;
		case "ARAZEAL":
			isAllowed = (height >= 167 && height <= 205); 
			break;		
		case "SKARAGG":
			isAllowed = (height >= 157 && height <= 195); 
			break;			
		default:
			break;
		}		
		return isAllowed;
	}	
}
