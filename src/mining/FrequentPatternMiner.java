package mining;
import data.Attribute;
import data.Data;
import data.DiscreteAttribute;
import data.EmptySetException;
import utility.*;
public class FrequentPatternMiner {

	public static LinkList frequentPatternDiscovery(Data data, float minSup) throws EmptySetException{
		Queue fpQueue = new Queue();
		LinkList outputFP = new LinkList();
		for (int i = 0; i < data.getNumberOfAttributes(); i++) {
			Attribute currentAttribute = data.getAttribute(i);
			for (int j = 0; j < ((DiscreteAttribute) currentAttribute).getNumberOfDistinctValues(); j++) {
				DiscreteItem item = new DiscreteItem((DiscreteAttribute) currentAttribute,
						((DiscreteAttribute) currentAttribute).getValue(j));
				FrequentPattern fp = new FrequentPattern();
				fp.addItem(item);
				fp.setSupport(FrequentPatternMiner.computeSupport(data, fp));
				if (fp.getSupport() >= minSup) { // 1-FP CANDIDATE
					fpQueue.enqueue(fp);
					// System.out.println(fp);
					outputFP.add(fp);
				}

			}

		}
		try{
		outputFP = expandFrequentPatterns(data, minSup, fpQueue, outputFP);
		}catch (EmptyQueueException e) {
			System.err.println(e.getMessage());
		}
		if (outputFP.isEmpty()){ //solleva l'eccezione se l'outputFP è vuoto
			throw new EmptySetException();
		}
		return outputFP;
	}

	private static LinkList expandFrequentPatterns(Data data, float minSup, Queue fpQueue, LinkList outputFP) throws EmptySetException,EmptyQueueException{
		// TO DO
		//in outputFP ci sono i pattern di lunghezza 1 frequenti
		//1) crearsi un array in cui copia i riferimenti agli item 
		int t=0;
		for (Puntatore p = outputFP.firstList(); !outputFP.endList(p); p = outputFP.succ(p), t++);
		Item itemPerRaffinamento[]=new Item[t];
		t=0;
		Queue tempQueue=(Queue) fpQueue.clone();
		while (!tempQueue.isEmpty()) {
			FrequentPattern fp=(FrequentPattern) tempQueue.first();
			tempQueue.dequeue();
			itemPerRaffinamento[t]=((FrequentPattern)fp).getItem(0);
			t++;
		}
		
		
		// Per ogni pattern fp della coda
		// provo a cotruire i raffinamenti di fp e se sono frequenti li aggiungo sia alla coda sia alla lista
		// come trovo i raffinamenti di fp?
		// per ogni item contenuto nell'array itemPerRaffinamento, vedo se quell'item fa già parte di fp, se non dovesse far part, provo a costruire un nuovo
		// pattern newfp che colleziona tutti gli atem di fp + l'item che ha selazionato dall'array; si calcola supporto di newfp e se è maggiore di min sup aggiunge
		// newfp alla lista e alla coda
		
		
		while(!fpQueue.isEmpty()){
			FrequentPattern fp=(FrequentPattern) fpQueue.first();
			fpQueue.dequeue();
			for(int i=0;i<itemPerRaffinamento.length;i++){
				boolean elemItemUguali=false;
				for(int k=0;k<fp.getPatternLength();k++){
						String NameItem=fp.getItem(k).getAttribute().getName();
						String ValueItem=(String)fp.getItem(k).getValue();
						String NameitemPerRaffinamento=itemPerRaffinamento[i].getAttribute().getName();
						String ValueitemPerRaffinamento=(String)itemPerRaffinamento[i].getValue();
						if(itemPerRaffinamento[i].getAttribute().getName().equals((String)fp.getItem(k).getAttribute().getName())&&
						itemPerRaffinamento[i].getValue().equals((String)fp.getItem(k).getValue())){
							elemItemUguali=true;
						}
				}
				if(!elemItemUguali){
						FrequentPattern fpNew= (FrequentPattern) fp.clone();
						fpNew.addItem(itemPerRaffinamento[i]);
						fpNew.setSupport(FrequentPatternMiner.computeSupport(data, fpNew));
						if(fpNew.getSupport()>minSup){
							fpQueue.enqueue(fpNew);
							outputFP.add(fpNew);
						}
				}
			}
		}
		return outputFP;
	}

	// Aggiorna il supporto
	public static float computeSupport(Data data, FrequentPattern FP) {
		int suppCount = 0;
		// indice esempio
		for (int i = 0; i < data.getNumberOfExamples(); i++) {
			// indice item
			boolean isSupporting = true;
			for (int j = 0; j < FP.getPatternLength(); j++) {
				// DiscreteItem
				DiscreteItem item = (DiscreteItem) FP.getItem(j);
				DiscreteAttribute attribute = (DiscreteAttribute) item.getAttribute();
				// Extract the example value
				Object valueInExample = data.getAttributeValue(i, attribute.getIndex());
				if (!item.checkItemCondition(valueInExample)) {
					isSupporting = false;
					break; // the ith example does not satisfy fp
				}

			}
			if (isSupporting)
				suppCount++;
		}
		return ((float) suppCount) / (data.getNumberOfExamples());

	}

	public static FrequentPattern refineFrequentPattern(FrequentPattern FP, Item item) {
		// TO DO
		FrequentPattern temp = (FrequentPattern) FP.clone();
		temp.addItem(item);
		return temp;
	}

	// aggiunta
	private static boolean controlloEsistenza(LinkList list, FrequentPattern fp) {
		for (Puntatore p = list.firstList(); !list.endList(p); p = list.succ(p)) {
			FrequentPattern controlloFp = (FrequentPattern) p.link.elemento;
			if (controlloFp.getPatternLength() == fp.getPatternLength()) {
				int controllo = 0;
				for (int i = 0; i < controlloFp.getPatternLength(); i++) {
					for (int j = 0; j < fp.getPatternLength(); j++) {
						if (controlloFp.getItem(i).getAttribute() == fp.getItem(j).getAttribute()
								&& ((String) controlloFp.getItem(i).getValue()) == ((String) fp.getItem(j)
										.getValue())) {
							controllo++;
						}
					}

				}
				if (controllo == fp.getPatternLength()) {
					return true;
				}
			}
		}
		return false;
	}

}
