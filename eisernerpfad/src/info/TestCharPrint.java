package info;

public class TestCharPrint {
	
	private CharInitialize newChar = new CharInitialize();
	private CharInfo info = newChar.info;
	private String testChar;

	public String printChar(){
		newChar.initialize();
		testChar = 
				"Stärke: " + info.getCharAttributeStrength() + "\n"
			+ 	"Gewandtheit: " + info.getCharAttributeDexterity() + "\n"
			+ 	"Intelligenz: " + info.getCharAttributeIntelligence() + "\n"
			;
		return testChar;
	}
	
}
