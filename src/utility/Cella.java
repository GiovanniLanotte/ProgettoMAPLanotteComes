/* Definiamo la classe Cella che modella un elemento singolo della struttura dati lista linkata*/
package utility;
public  class Cella {
	
	public Object elemento;
	
	public Puntatore successivo=null; 

	public Cella(Object e){
		elemento = e;
	}

}

