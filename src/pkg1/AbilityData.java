package pkg1;

import java.util.ArrayList;

public class AbilityData {
	protected int gilVal;
	protected String abilityName;
	protected ArrayList<Object> recipe = new ArrayList<Object>(2);
	
	
	public AbilityData(){
		gilVal = 0;
		abilityName = "";
		recipe = null;
	}
	
	public AbilityData(String gval, String aName, String rName, String q){
		int value = Integer.parseInt(gval);
		gilVal = value;
		abilityName = aName;
		int quantity = Integer.parseInt(q);
		recipe.add(quantity);
		recipe.add(rName);
	}
}
