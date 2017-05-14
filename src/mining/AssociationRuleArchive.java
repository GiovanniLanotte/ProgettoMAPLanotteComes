package mining;

import java.util.HashMap;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class AssociationRuleArchive {

	private HashMap<FrequentPattern, TreeSet<AssociationRule>> archive;
	
	public AssociationRuleArchive() {
		archive= new HashMap<FrequentPattern, TreeSet<AssociationRule>>();
	}
	
	public void put(FrequentPattern fp){
		if(!archive.containsKey(fp)){
			TreeSet<AssociationRule> value= new  TreeSet<AssociationRule>();
			archive.put(fp,value);
		}
			
	}
	
	public void put(FrequentPattern fp, AssociationRule rule){
		if(!archive.containsKey(fp)){
			TreeSet<AssociationRule> value= new  TreeSet<AssociationRule>();
			value.add(rule);
			archive.put(fp, value);
		}
		else{
			TreeSet<AssociationRule> value= archive.get(fp);
			value.add(rule);
			archive.put(fp,value);
		}
	}
	
	public TreeSet<AssociationRule> getRules(FrequentPattern fp){
		return archive.get(fp);
	}
	public String toString(){
		String stringa="";
		Set<FrequentPattern> keys=archive.keySet();
		int i=1;
		for(FrequentPattern fp: keys){
			Set<AssociationRule>value=archive.get(fp);
			stringa+= new Integer(i).toString()+"." + archive.get(fp)+value.toString()+"\n";
		}
		return stringa;
		
	}

}