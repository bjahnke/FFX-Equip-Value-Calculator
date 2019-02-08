//Author: Brian Jahnke
/*
 * Purpose: 
 * Automatically calculate weapon value
 * in Final Fantasy X Remastered (Don't know if there's a difference)
 * based on data and formulas provided by the FAQ found here:
 * https://gamefaqs.gamespot.com/ps2/197344-final-fantasy-x/faqs/16153
 */
package pkg1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;
import java.util.Hashtable;
import java.util.Set;
import java.util.ArrayList;

public class WeamorValueCalc {
	
	private static Hashtable<String, AbilityData> abilityTable = new Hashtable<String, AbilityData>();
	
	public static void main(String[] args) throws IOException {
		abilityTable = parseText();
	}
	
	public static Hashtable<String, AbilityData> parseText() throws IOException{
		Hashtable<String, AbilityData> aTable = new Hashtable<String, AbilityData>();
		BufferedReader br = new BufferedReader(new FileReader("abilityList.txt"));
		String aDataStr = "";
		Pattern p_gil = Pattern.compile("^[\\d]+");
		Pattern p_aName = Pattern.compile("(?<=[-]+)[A-Za-z ]+(?=[:])");
		Pattern p_rName = Pattern.compile("(?<=(: ))[A-Za-z ]+(?=( x))");
		Pattern p_rQ = Pattern.compile("\\d+$");
		
		Pattern isData = p_gil;
		
		while((aDataStr = br.readLine()) != null){
			Matcher dataMatch = isData.matcher(aDataStr);
			
			//if the line is data then we pattern match and store
			if(dataMatch.find()){
				Matcher m_gil = p_gil.matcher(aDataStr);
				Matcher m_aName = p_aName.matcher(aDataStr);
				Matcher m_rName = p_rName.matcher(aDataStr);
				Matcher m_rQ = p_rQ.matcher(aDataStr);
				
				if(m_gil.find()){
					String gil = m_gil.group();
					if(m_aName.find()){
						String aName = m_aName.group();
						if(m_rName.find()){
							String rName = m_rName.group();
							if(m_rQ.find()){
								String rQ = m_rQ.group();
								AbilityData abilityData = new AbilityData(gil, aName, rName, rQ);
								aTable.put(aName, abilityData);
							}
						}
					}
				}
			}
		}
		System.out.println(aTable.toString());
		return aTable;
	}

}
