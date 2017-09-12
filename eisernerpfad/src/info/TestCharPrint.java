package info;

import gui.InputScene;

public class TestCharPrint {
	
	private NewCharacter newChar = InputScene.getNewChar();
	private CharInitialize init = newChar.init;
	private CharInfo info = newChar.info;
	private String testChar;

	public String printChar(){
		init.initializeNewChar();
		testChar = 
				"St�rke: " + info.getCharAttributeStrength() + "\n"
			+ 	"Gewandtheit: " + info.getCharAttributeDexterity() + "\n"
			+ 	"Intelligenz: " + info.getCharAttributeIntelligence() + "\n"
			;
		return testChar;
	}
	
}
