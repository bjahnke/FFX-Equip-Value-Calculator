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
import java.util.ArrayList;

public class WeamorValueCalc {
	
	private static Hashtable<String, AbilityData> abilityTable = new Hashtable<String, AbilityData>();
	
	public static void main(String[] args) throws IOException {
		abilityTable = parseText();
		double value = calcValue();
		System.out.println("Value: " + value);
	}
	
	public static double calcValue() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("equipment.txt"));
		String str = br.readLine();
		Pattern pSlots = Pattern.compile("(?<=(^(Slots: )))[1-4]$");
		Matcher mSlots = pSlots.matcher(str);
		int totalSlots = 0;
		int usedSlots = 0;
		int abilityValueSum = 0;
		
		
		if(mSlots.find()){
			totalSlots = Integer.parseInt(mSlots.group());
		}
		else{
			//ERROR: number of slots not found 
			//OR
			//Total slots = number of abilities listed?
		}
		
		System.out.println("Abilities:");
		while((str = br.readLine()) != null){
			if(abilityTable.containsKey(str)){
				AbilityData data = abilityTable.get(str);
				System.out.println("\t"+data.abilityName);
				abilityValueSum += data.gilVal;
				usedSlots++;
			}
		}
		for(int i = (totalSlots-usedSlots); i > 0; i--){
			System.out.print("\t*empty slot*\n");
		}
		br.close();
		
		return applyFormula(abilityValueSum, totalSlots, usedSlots);
		
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
		br.close();
		System.out.println(aTable.toString());
		return aTable;
	}
	
	//FORMULA
	
	public static double applyFormula(int x, int tSlots, int uSlots){
		double result = x + 12; //default 1 slot
		if(tSlots == 2){
			result = 1.2*x + 18;
		}
		if(tSlots == 3){
			result = 4.5*x + 56;
			if(uSlots > 1 ){
				result = result/1.5;
			}
		}
		if(tSlots == 4){
			result = 15*x + 187;
			if(uSlots == 2){
				result = result/2;
			}
			else if(uSlots > 2){
				result = result/3;
			}
		}
		return result;
	}
	
	
}
