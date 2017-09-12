package info;

/**
 * Knows everything about a char. Semi-interface.
 *
 */
public class NewCharacter {
	
	private CharInfo info = new CharInfo();
	public CharInfo getInfo(){
		return info;		
	}
	private CharDecicions decisions = new CharDecicions();
	public CharDecicions getDecisions(){
		return decisions;		
	}
	private CharInitialize init = new CharInitialize();
	public CharInitialize getInit(){
		return init;
	}
	
}
