package mining;

import java.util.LinkedList;
import java.util.List;

public class FrequentPattern implements Cloneable{

	private Item fp[];
	private float support;
	
	FrequentPattern(){ 
			fp=new Item[0];
	}
	
	//aggiunge un nuovo item al pattern
	public void addItem(Item item)
	{
		int length =fp.length;
		
		Item temp []=new Item[length+1];
		System.arraycopy(fp, 0, temp, 0, length);
		temp [length]=item;
		fp=temp;
		
		
	}
	
	public Item getItem(int index){
		return fp[index];
	}
	
	public float getSupport(){
		return support;
	}
	
	public int getPatternLength(){
		return fp.length;
	}
	
	public void setSupport(float support){
		this.support=support;
	}
	
	public String toString(){
		String value="";
		for(int i=0;i<fp.length-1;i++)
			value+=fp[i] +" AND ";
		if(fp.length>0){
			value+=fp[fp.length-1];
			value+="["+support+"]";
		}
		
		return value;
	}

	//AGGUNGI MEDOTO CLONEBOLE
	public Object clone(){
		Object o=null;
		try {
			o=super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	return o;
	}
}
