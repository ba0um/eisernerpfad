package info;

import javafx.util.Pair;

public class CharInfo {
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	/*
	 * General information such as name, height, etc.
	 */
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	/**
	 * The chosen name of the character
	 */
	private String charName;
	
	/**
	 * The chosen sex of the character
	 */
	private boolean charSex;
	
	/**
	 * The chosen, or random height of the character.
	 */
	private int charHeight;
	
	/**
	 * The chosen, or random weight of the character.
	 */
	private int charWeight;
	
	/**
	 * The enum of all hair colors available.
	 */
	public enum charHairColorEnum{
		RED, 
		BROWN, BROWN_DARK,
		BLOND, BLOND_LIGHT, BLOND_DARK, BLOND_WHITE,
		BLACK, BLACK_BLUE
	}
	
	/**
	 * The chosen, or random hair color of the character;
	 */
	private String charHairColor;
	
	/**
	 * The enum of all eye colors available.
	 */
	public enum charEyeColorEnum{
		BLACK, GREY,
		BLUE,
		GREEN,
		BROWN, BROWN_LIGHT, BROWN_DARK,
		GOLD
	}
	
	/**
	 * The chosen, or random eye color of the character;
	 */
	private String charEyeColor;
	
	/**
	 * The chosen looks / decription of the character.
	 */
	private String charLooks;	
	
	/**
	 * The enum of all races available.
	 */
	public enum charRaceEnum{
		HALF_AETERNA,
		NEHRIM,
		ARAZEAL,
		KILE,
		QYRA,
		SKARAGG
	}
	
	/**
	 * The chosen race of the character.
	 */
	private String charRace;
	
	/**
	 * The enum of all cultures available.
	 */
	public enum charCultureEnum{
		// Nehrim options
		NEHRIM_FREE, NEHRIM_SOUTH, NEHRIM_MIDDLE,
		
		// Arazeal options
		ARAZEAL_FREE, ARAZEAL_CIVILIZED, 
		
		// Kile options
		KILE_UPPER, KILE_FOLKS,
		
		// Qyra options
		QYRA_FARM, QYRA_FOLKS,
		
		// Skaragg options
		SKARAGG_BIG, SKARAGG_SMALL		
	}
	
	/**
	 * The chosen culture of the character, depending on its race.
	 */
	private String charCulture;	
	
	/**
	 * The chosen profession of the character.
	 */
	private String charProfession;
	
	/**
	 * The enum of all paths available.
	 */
	public enum charPathEnum{
		SUBLIME, ERUDITE, MANUFACTURER, PATHLESS
	}
	
	/**
	 * The given path of the character, depending on its profession.
	 */
	private String charPath;	
	
	/**
	 * The enum of all classes available.
	 */
	public enum charClassEnum{
		// Rogue classes
		INFILTRATOR, VAGRANT, TRICKSTER,
		
		// Warrior classes
		VANDAL, KEEPER, BLADE_DACNER,
		
		// Mage classes
		ELEMENTALIST, THAUMATURGE, SINISTROPE,
		
		// Affinities
		/** oneOf(Elementalist/Thaumaturge/Sinistrope) & Trickster */
		ARCANE_ARCHER,
		
		/** Blade Dancer & Infiltrator */
		ASSASSIN,
		
		/** Keeper & Sinistrope */
		DARK_KEEPER,
		
		/** Blade Dancer & Vandal */
		BLADE_MASTER,
		
		/** Elementalist & oneOf(Vandal / Keeper) */
		BATTLEMAGE,
		
		/** Blade Dancer & Keeper */
		BLADE_BREAKER,
		
		/** Sinistrope & Infiltrator */
		SHADOW_DANCER,
		
		/** Elementalist & Sinistrope */
		BLACK_MAGE,
		
		/** Thaumaturge & oneOf(Keeper / Vandal) */
		SERAPH,
		
		/** Trickster & Vagrant */
		WELL_TRAVELLED_ONE
	}
	
	/**
	 * The given class of the character.
	 */
	private String charClass;
	
	/**
	 * The current experience points of the character.
	 */
	private int charExp;
	
	/**
	 * The current basis points of the character. 
	 */
	private int charBasisPoints;
	
	/**
	 * The current special points of the character.
	 */
	private int charSpecialPoints;
	
	/**
	 * The current memory points of the character.
	 */
	private int charMemoryPoints;
	
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	/*
	 * Information about attributes and base values
	 */
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	/**
	 * The current strength of the character.<br>
	 * abbr: <b>ST</b>
	 */
	private int charAttributeStrength;
	
	/**
	 * The current dexterity of the character.<br>
	 * abbr: <b>GE</b>
	 */
	private int charAttributeDexterity;
	
	/**
	 * The current intelligence of the character.<br>
	 * abbr: <b>IN</b>
	 */
	private int charAttributeIntelligence;
	
	/**
	 * The current health points (LP) of the character.<br>
	 * abbr: <b>LP</b>
	 */
	private int charAttributeHealth;
	
	/**
	 * The current stamina points of the character.<br>
	 * abbr: <b>AP</b>
	 */
	private int charAttributeStamina;
	
	/**
	 * The current mana points of the character.<br>
	 * abbr: <b>MP</b>
	 */
	private int charAttributeMana;
	
	/**
	 * The current speed of the character.<br>
	 * abbr: <b>GS</b>
	 */
	private int charAttributeSpeed;
	
	/**
	 * The current health regeneration of the character.
	 */
	private double charAttributeHealthReg;
	
	/**
	 * The current stamina regeneration of the character.
	 */
	private double charAttributeStaminaReg;
	
	/**
	 * The current mana regeneration of the character.
	 */
	private double charAttributeManaReg;
	
	/**
	 * The current initiative of the character.<br>
	 * abbr: <b>INI</b>
	 */
	private int charAttributeInitiative;
	
	/**
	 * The current protection of the character.<br>
	 * abbr: <b>RS</b>
	 */
	private int charAttributeProtection;
	
	/**
	 * The current magic resistance of the character.<br>
	 * abbr: <b>MR</b>
	 */
	private int charAttributeMagicResistance;
	
	/**
	 * The current level of arcane fever of the character.<br>
	 * abbr: <b>AF</b>
	 */
	private double charAttributeArcaneFever;
	
	/**
	 * The current number of advantages (key) and disadvantages (value).
	 */
	private Pair<Integer, Integer> charAdvDis;
	
	
	/**
	 * The bonuses a character gets from its chosen race. <br>
	 * {str, dex, int, lp, ap, mp, mr, <br>
	 * talents: <br>
	 * 925 = 2x 5 intBase <br>
	 * 952 = 5x 2 base <br>
	 * 9101 = 10x 1 base <br>
	 * <br>
	 * 8101 = 10x 1 special <br>
	 * 8151 = 15x 1 special <br>
	 * 8201 = 20x 1 intSpecial <br>
	 * 8251 = 25x 1 special <br>
	 * 81011 = 10x 1 bodySpecial <br>
	 * 81512 = 15x 1 socialSpecial <br>
	 * 81513 = 15x 1 knowledgeSpecial <br>
	 * 81514 = 15x 1 natureSpecial <br>
	 * }
	 */
	private int[] raceBonuses;
	
	/**
	 * The number of stat points the player can distribute at the creation of the char.
	 */
	private int raceStatsBonus;
	
	/**
	 * The bonus a character gets from its chosen culture. <br>
	 * all talents <br>
	 * 952 = 5x 2 base <br> 
	 * 8101 = 10x 1 special <br>
	 * 81511 = 10x 1 bodySpecial <br>
	 * 81512 = 15x 1 socialSpecial <br>
	 * 81513 = 15x 1 knowledgeSpecial <br>
	 * 81514 = 15x 1 natureSpecial
	 */
	private int cultureBonuses;
	

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	/*
	 * Auto getter and setter
	 */
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
	
	/**
	 * @return the charName
	 */
	public String getCharName() {
		return charName;
	}

	/**
	 * @param charName the charName to set
	 */
	public void setCharName(String charName) {
		this.charName = charName;
	}

	/**
	 * @return the charSex
	 */
	public boolean isCharSex() {
		return charSex;
	}

	/**
	 * @param charSex the charSex to set
	 */
	public void setCharSex(boolean charSex) {
		this.charSex = charSex;
	}

	/**
	 * @return the charHeight
	 */
	public int getCharHeight() {
		return charHeight;
	}

	/**
	 * @param charHeight the charHeight to set
	 */
	public void setCharHeight(int charHeight) {
		this.charHeight = charHeight;
	}

	/**
	 * @return the charWeight
	 */
	public int getCharWeight() {
		return charWeight;
	}

	/**
	 * @param charWeight the charWeight to set
	 */
	public void setCharWeight(int charWeight) {
		this.charWeight = charWeight;
	}

	/**
	 * @return the charHairColor
	 */
	public String getCharHairColor() {
		return charHairColor;
	}

	/**
	 * @param charHairColor the charHairColor to set
	 */
	public void setCharHairColor(String charHairColor) {
		this.charHairColor = charHairColor;
	}

	/**
	 * @return the charEyeColor
	 */
	public String getCharEyeColor() {
		return charEyeColor;
	}

	/**
	 * @param charEyeColor the charEyeColor to set
	 */
	public void setCharEyeColor(String charEyeColor) {
		this.charEyeColor = charEyeColor;
	}

	/**
	 * @return the charLooks
	 */
	public String getCharLooks() {
		return charLooks;
	}

	/**
	 * @param charLooks the charLooks to set
	 */
	public void setCharLooks(String charLooks) {
		this.charLooks = charLooks;
	}

	/**
	 * @return the charRace
	 */
	public String getCharRace() {
		return charRace;
	}

	/**
	 * @param charRace the charRace to set
	 */
	public void setCharRace(String charRace) {
		this.charRace = charRace;
	}

	/**
	 * @return the charCulture
	 */
	public String getCharCulture() {
		return charCulture;
	}

	/**
	 * @param charCulture the charCulture to set
	 */
	public void setCharCulture(String charCulture) {
		this.charCulture = charCulture;
	}

	/**
	 * @return the charProfession
	 */
	public String getCharProfession() {
		return charProfession;
	}

	/**
	 * @param charProfession the charProfession to set
	 */
	public void setCharProfession(String charProfession) {
		this.charProfession = charProfession;
	}

	/**
	 * @return the charPath
	 */
	public String getCharPath() {
		return charPath;
	}

	/**
	 * @param charPath the charPath to set
	 */
	public void setCharPath(String charPath) {
		this.charPath = charPath;
	}

	/**
	 * @return the charClass
	 */
	public String getCharClass() {
		return charClass;
	}

	/**
	 * @param charClass the charClass to set
	 */
	public void setCharClass(String charClass) {
		this.charClass = charClass;
	}

	/**
	 * @return the charExp
	 */
	public int getCharExp() {
		return charExp;
	}

	/**
	 * @param charExp the charExp to set
	 */
	public void setCharExp(int charExp) {
		this.charExp = charExp;
	}

	/**
	 * @return the charBasisPoints
	 */
	public int getCharBasisPoints() {
		return charBasisPoints;
	}

	/**
	 * @param charBasisPoints the charBasisPoints to set
	 */
	public void setCharBasisPoints(int charBasisPoints) {
		this.charBasisPoints = charBasisPoints;
	}

	/**
	 * @return the charSpecialPoints
	 */
	public int getCharSpecialPoints() {
		return charSpecialPoints;
	}

	/**
	 * @param charSpecialPoints the charSpecialPoints to set
	 */
	public void setCharSpecialPoints(int charSpecialPoints) {
		this.charSpecialPoints = charSpecialPoints;
	}

	/**
	 * @return the charMemoryPoints
	 */
	public int getCharMemoryPoints() {
		return charMemoryPoints;
	}

	/**
	 * @param charMemoryPoints the charMemoryPoints to set
	 */
	public void setCharMemoryPoints(int charMemoryPoints) {
		this.charMemoryPoints = charMemoryPoints;
	}

	/**
	 * @return the charAttributeStrength
	 */
	public int getCharAttributeStrength() {
		return charAttributeStrength;
	}

	/**
	 * @param charAttributeStrength the charAttributeStrength to set
	 */
	public void setCharAttributeStrength(int charAttributeStrength) {
		this.charAttributeStrength = charAttributeStrength;
	}

	/**
	 * @return the charAttributeDexterity
	 */
	public int getCharAttributeDexterity() {
		return charAttributeDexterity;
	}

	/**
	 * @param charAttributeDexterity the charAttributeDexterity to set
	 */
	public void setCharAttributeDexterity(int charAttributeDexterity) {
		this.charAttributeDexterity = charAttributeDexterity;
	}

	/**
	 * @return the charAttributeIntelligence
	 */
	public int getCharAttributeIntelligence() {
		return charAttributeIntelligence;
	}

	/**
	 * @param charAttributeIntelligence the charAttributeIntelligence to set
	 */
	public void setCharAttributeIntelligence(int charAttributeIntelligence) {
		this.charAttributeIntelligence = charAttributeIntelligence;
	}

	/**
	 * @return the charAttributeHealth
	 */
	public int getCharAttributeHealth() {
		return charAttributeHealth;
	}

	/**
	 * @param charAttributeHealth the charAttributeHealth to set
	 */
	public void setCharAttributeHealth(int charAttributeHealth) {
		this.charAttributeHealth = charAttributeHealth;
	}

	/**
	 * @return the charAttributeStamina
	 */
	public int getCharAttributeStamina() {
		return charAttributeStamina;
	}

	/**
	 * @param charAttributeStamina the charAttributeStamina to set
	 */
	public void setCharAttributeStamina(int charAttributeStamina) {
		this.charAttributeStamina = charAttributeStamina;
	}

	/**
	 * @return the charAttributeMana
	 */
	public int getCharAttributeMana() {
		return charAttributeMana;
	}

	/**
	 * @param charAttributeMana the charAttributeMana to set
	 */
	public void setCharAttributeMana(int charAttributeMana) {
		this.charAttributeMana = charAttributeMana;
	}

	/**
	 * @return the charAttributeSpeed
	 */
	public int getCharAttributeSpeed() {
		return charAttributeSpeed;
	}

	/**
	 * @param charAttributeSpeed the charAttributeSpeed to set
	 */
	public void setCharAttributeSpeed(int charAttributeSpeed) {
		this.charAttributeSpeed = charAttributeSpeed;
	}

	/**
	 * @return the charAttributeHealthReg
	 */
	public double getCharAttributeHealthReg() {
		return charAttributeHealthReg;
	}

	/**
	 * @param charAttributeHealthReg the charAttributeHealthReg to set
	 */
	public void setCharAttributeHealthReg(double charAttributeHealthReg) {
		this.charAttributeHealthReg = charAttributeHealthReg;
	}

	/**
	 * @return the charAttributeStaminaReg
	 */
	public double getCharAttributeStaminaReg() {
		return charAttributeStaminaReg;
	}

	/**
	 * @param charAttributeStaminaReg the charAttributeStaminaReg to set
	 */
	public void setCharAttributeStaminaReg(double charAttributeStaminaReg) {
		this.charAttributeStaminaReg = charAttributeStaminaReg;
	}

	/**
	 * @return the charAttributeManaReg
	 */
	public double getCharAttributeManaReg() {
		return charAttributeManaReg;
	}

	/**
	 * @param charAttributeManaReg the charAttributeManaReg to set
	 */
	public void setCharAttributeManaReg(double charAttributeManaReg) {
		this.charAttributeManaReg = charAttributeManaReg;
	}

	/**
	 * @return the charAttributeInitiative
	 */
	public int getCharAttributeInitiative() {
		return charAttributeInitiative;
	}

	/**
	 * @param charAttributeInitiative the charAttributeInitiative to set
	 */
	public void setCharAttributeInitiative(int charAttributeInitiative) {
		this.charAttributeInitiative = charAttributeInitiative;
	}

	/**
	 * @return the charAttributeProtection
	 */
	public int getCharAttributeProtection() {
		return charAttributeProtection;
	}

	/**
	 * @param charAttributeProtection the charAttributeProtection to set
	 */
	public void setCharAttributeProtection(int charAttributeProtection) {
		this.charAttributeProtection = charAttributeProtection;
	}

	/**
	 * @return the charAttributeMagicResistance
	 */
	public int getCharAttributeMagicResistance() {
		return charAttributeMagicResistance;
	}

	/**
	 * @param charAttributeMagicResistance the charAttributeMagicResistance to set
	 */
	public void setCharAttributeMagicResistance(int charAttributeMagicResistance) {
		this.charAttributeMagicResistance = charAttributeMagicResistance;
	}

	/**
	 * @return the charAttributeArcaneFever
	 */
	public double getCharAttributeArcaneFever() {
		return charAttributeArcaneFever;
	}

	/**
	 * @param charAttributeArcaneFever the charAttributeArcaneFever to set
	 */
	public void setCharAttributeArcaneFever(double charAttributeArcaneFever) {
		this.charAttributeArcaneFever = charAttributeArcaneFever;
	}

	/**
	 * @param race - the characters race as string
	 * @return the {@link CharInfo#raceBonuses}
	 */
	public int[] getRaceBonuses(String race) {
		switch (race) {
		case "HALF_AETERNA":
			int[] aeternaBonuses = {-1, 0, 1, 0, 0, 18, 10, 925, 8201, 0};
			raceBonuses = aeternaBonuses;
			break;
		case "NEHRIM":
			int[] nehrimBonuses = {1, 0, 0, 12, 0, 0, 0, 952, 81011, 8151};
			raceBonuses = nehrimBonuses;
			break;
		case "ARAZEAL":
			int[] arazealBonuses = {1, 0, -1, 12, 6, 0, 4, 9101, 8251, 0};
			raceBonuses = arazealBonuses;
			break;
		case "KILE":
			int[] kileBonuses = {0, 0, 1, 0, 6, 0, 0, 952, 81512, 8101};
			raceBonuses = kileBonuses;
			break;
		case "QYRA":
			int[] qyraBonuses = {0, -1, 1, 10, 0, 8, 10, 952, 81513, 8101};
			raceBonuses = qyraBonuses;
			break;			
		case "SKARAGG":
			int[] skaraggBonuses = {0, 1, -1, 8, 0, 10, 4, 952, 81514, 8101};
			raceBonuses = skaraggBonuses;
			break;		
		default:
			break;
		}		
		return raceBonuses;
	}

	/**
	 * @param raceBonuses the raceBonuses to set
	 */
	public void setRaceBonuses(int[] raceBonuses) {
		this.raceBonuses = raceBonuses;
	}

	/**
	 * @return the raceStatsBonus
	 */
	public int getRaceStatsBonus() {
		return raceStatsBonus;
	}

	/**
	 * @param raceStatsBonus the raceStatsBonus to set
	 */
	public void setRaceStatsBonus(int raceStatsBonus) {
		this.raceStatsBonus = raceStatsBonus;
	}

	/**
	 * @param the characters culture as a string
	 * @return the {@link #cultureBonuses}
	 */
	public int getCultureBonuses(String culture) {
		int i = 0;
		switch (culture) {
		case "NEHRIM_SOUTH":
			i = 81511;
			break;
		case "ARAZEAL_FREE":
			i = 952;
			break;
		case "KILE_UPPER":
			i = 81512;
			break;
		case "QYRA_FARM":
			i = 81513;
			break;
		case "SKARAGG_BIG":
			i = 81514;
			break;
		case "NEHRIM_MIDDLE":
		case "ARAZEAL_CIVILIZED":
		case "KILE_FOLKS":
		case "QYRA_FOLKS":
		case "SKARAGG_SMALL":
			i = 8101;
			break;
		default:
			break;
		}
		cultureBonuses = i;
		return cultureBonuses;
	}

	/**
	 * @param cultureBonuses the cultureBonuses to set
	 */
	public void setCultureBonuses(int cultureBonuses) {
		this.cultureBonuses = cultureBonuses;
	}

	/**
	 * @return the charAdvDis
	 */
	public Pair<Integer, Integer> getCharAdvDis() {
		return charAdvDis;
	}

	/**
	 * @param charAdvDis the charAdvDis to set
	 */
	public void setCharAdvDis(Pair<Integer, Integer> charAdvDis) {
		this.charAdvDis = charAdvDis;
	}
	
}
