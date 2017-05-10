/*Definiamo la classe astratta Attribute che modella un generico attributo discreto o continuo.*/
public class Attribute {
private String name;  //nome simbolico dell'attributo
private int index;  //identificativo numerico dell'attributo

//name e index sono private perchè variabili dell'oggetto

public Attribute(String name, int index){ 
	//Questo metodo inizializza i valori dei membri name e index
	//assume come input valori per nome simbolico e identificativo numerico dell'attributo
	this.name=name;
	this.index=index;
}

public String getName(){
	return name;
}

public int getIndex(){
	return index;
}

public String toString(){
	return name;
}
}