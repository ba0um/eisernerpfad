package info;

import info.CharInfo.charRaceEnum;

import java.util.ArrayList;

import info.CharInfo.charCultureEnum;

public class CharDecicions {

	private CharInfo info = new CharInfo();	
	private ArrayList<charCultureEnum> charCultureOptions;

	public ArrayList<charCultureEnum> charCultureOptions(charRaceEnum charRace){
		switch (charRace) {
		case HALF_AETERNA:
			charCultureOptions.add(charCultureEnum.NEHRIM_FREE);
			charCultureOptions.add(charCultureEnum.NEHRIM_MIDDLE);
			charCultureOptions.add(charCultureEnum.NEHRIM_SOUTH);
			charCultureOptions.add(charCultureEnum.ARAZEAL_CIVILIZED);
			charCultureOptions.add(charCultureEnum.ARAZEAL_FREE);
			// TODO
			break;
		case NEHRIM:
			charCultureOptions.add(charCultureEnum.NEHRIM_FREE);
			charCultureOptions.add(charCultureEnum.NEHRIM_MIDDLE);
			charCultureOptions.add(charCultureEnum.NEHRIM_SOUTH);
			break;
		case ARAZEAL:
			charCultureOptions.add(charCultureEnum.ARAZEAL_CIVILIZED);
			charCultureOptions.add(charCultureEnum.ARAZEAL_FREE);
			break;
			// TODO
		
		case SKARAGG:
			charCultureOptions.add(charCultureEnum.SKARAGG_BIG);
			charCultureOptions.add(charCultureEnum.SKARAGG_SMALL);
			break;
		
		default:
			break;
		}
		
		
		return charCultureOptions;
	}

}
