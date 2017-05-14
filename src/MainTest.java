import java.util.Iterator;
import java.util.LinkedList;

import keyboardinput.Keyboard;
import mining.AssociationRule;
import mining.AssociationRuleArchive;
import mining.AssociationRuleMiner;
import mining.FrequentPattern;
import mining.FrequentPatternMiner;
import mining.NoPatternExcemption;
import mining.OneLevelPatternException;

import data.Data;
import data.EmptySetException;





public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Data data= new Data();
		AssociationRuleArchive archive=new AssociationRuleArchive();
		float minSup=(float)0.0,minConf=(float)0.0;
		do{
			System.out.println("Inserisci minsup (in [0,1])");
			minSup=Keyboard.readFloat();
		}while (minSup<0 || minSup>1);
		
		do{
			System.out.println("Inserisci minconf (in [0,1])");
			minConf=Keyboard.readFloat();
		}while (minConf<0 || minConf>1);
		
		
		
		try{
			LinkedList<FrequentPattern> outputFP=FrequentPatternMiner.frequentPatternDiscovery(data,minSup);
			
			
			Iterator<FrequentPattern> it=outputFP.iterator();
			while(it.hasNext()){
				FrequentPattern FP=it.next();
				archive.put(FP);
								
				LinkedList<AssociationRule> outputAR=null;
				try {
					outputAR = AssociationRuleMiner.confidentAssociationRuleDiscovery(data,FP,minConf); 
					Iterator<AssociationRule> itRule=outputAR.iterator();
					while(itRule.hasNext()){
						archive.put(FP,itRule.next());
					}
									
				
				} catch (OneLevelPatternException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					
				} 
				
			}
		}
		catch(EmptySetException e){
			System.out.println(e.getMessage());
		}
		System.out.print(archive);
		
		
	}

}
