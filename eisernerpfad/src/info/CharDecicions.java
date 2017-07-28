package info;

import info.CharInfo.charRaceEnum;

import java.util.ArrayList;

import info.CharInfo.charCultureEnum;

public class CharDecicions {

	public CharInfo info = new CharInfo();	
	private ArrayList<charCultureEnum> charCultureOptions;

	public ArrayList<charCultureEnum> charCultureOptions(charRaceEnum charRace){
		switch (charRace) {
		case HALF_AETERNA:
			charCultureOptions.add(charCultureEnum.NEHRIM_FREE);
			charCultureOptions.add(charCultureEnum.NEHRIM_MIDDLE);
			charCultureOptions.add(charCultureEnum.NEHRIM_SOUTH);
			charCultureOptions.add(charCultureEnum.ARAZEAL_CIVILIZED);
			charCultureOptions.add(charCultureEnum.ARAZEAL_FREE);
			charCultureOptions.add(charCultureEnum.KILE_FOLKS);
			charCultureOptions.add(charCultureEnum.KILE_UPPER);
			charCultureOptions.add(charCultureEnum.QYRA_FARM);
			charCultureOptions.add(charCultureEnum.QYRA_FOLKS);
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
		case KILE:
			charCultureOptions.add(charCultureEnum.KILE_FOLKS);
			charCultureOptions.add(charCultureEnum.KILE_UPPER);
			break;
		case QYRA:
			charCultureOptions.add(charCultureEnum.QYRA_FARM);
			charCultureOptions.add(charCultureEnum.QYRA_FOLKS);
			break;		
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
