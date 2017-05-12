/* Definiamo la classe Cella che modella un elemento singolo della struttura dati lista linkata*/
package utility;
public class Cella {
		
		private Object elemento;
		
		Puntatore successivo=null; 

		public Cella(Object e){
			elemento = e;
		}
		//aggiunta
		public Object getElemento(){
			return elemento;
		}
	}
