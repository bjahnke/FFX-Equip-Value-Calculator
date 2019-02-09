package pkg1;

import java.io.PrintWriter;
import java.util.ArrayList;
import static pkg1.Equip.*;

public class AbilityData {
	protected int gilVal;
	protected String abilityName;
	protected ArrayList<Object> recipe = new ArrayList<Object>(2);
	protected Equip equipType;
	
	
	public AbilityData(){
		gilVal = 0;
		abilityName = "";
		recipe = null;
	}
	
	public AbilityData(String gval, String aName, String rName, String q, Equip eType){
		int value = Integer.parseInt(gval);
		gilVal = value;
		abilityName = aName;
		int quantity = Integer.parseInt(q);
		equipType = eType;
		recipe.add(quantity);
		recipe.add(rName);
		
	}
	
	@Override
	public String toString() {
		return abilityName + ": " + gilVal + ", " + recipe + ", " + equipType;
	}
}
