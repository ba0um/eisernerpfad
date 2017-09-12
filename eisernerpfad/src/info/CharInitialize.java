package info;

import gui.InputScene;

/**
 * Sets all base values and attributes.
 */
public class CharInitialize {	
	
	
	private NewCharacter newChar = InputScene.getNewChar();
	private CharInfo info = newChar.getInfo();
	
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	/*
	 * May be changed later when in need of balancing
	 */
	
	// current multipliers
	private final int LIFE_MULTIPLIER = 9;
	private final int STAMINA_MULTIPLIER = 11;
	private final int MANA_MULTIPLIER = 8;
	
	// current starting values
	private final int STR_BASE = 5;
	private final int DEX_BASE = 5;
	private final int INT_BASE = 5;	
	private final int LIFE_REG_BASE = 10;
	private final int STAMINA_REG_BASE = 10;
	private final int MANA_REG_BASE = 10;
	private final int INI_BASE = 10;
	private final int PROTECTION_BASE = 0;
	private final int MAGIC_RES_BASE = 0;
	private final int ARCANE_FEVER_BASE = 0;
	
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	
	
	/**
	 * Runs this to set the starting values for a new char.
	 */
	public void initializeNewChar(){
		
		// set base values
		info.setCharAttributeStrength(STR_BASE);
		info.setCharAttributeDexterity(DEX_BASE);
		info.setCharAttributeIntelligence(INT_BASE);
		
		info.setCharAttributeHealth(STR_BASE * LIFE_MULTIPLIER); 
		info.setCharAttributeStamina(DEX_BASE * STAMINA_MULTIPLIER);
		info.setCharAttributeMana(INT_BASE * MANA_MULTIPLIER);
		
		info.setCharAttributeManaReg(MANA_REG_BASE);
		info.setCharAttributeHealthReg(LIFE_REG_BASE);
		info.setCharAttributeStaminaReg(STAMINA_REG_BASE);
		
		info.setCharAttributeInitiative(INI_BASE);
		info.setCharAttributeProtection(PROTECTION_BASE);
		info.setCharAttributeMagicResistance(MAGIC_RES_BASE);
		info.setCharAttributeArcaneFever(ARCANE_FEVER_BASE);
	}

}
