/*Definiamo la classe Queue che modella una struttura coda che è poi  usata come contenitore a modalità FIFO per i 
 * pattern frequenti scoperti a livello k da usare per generare i pattern candidati a livello k+1*/
class Queue implements Cloneable {

		private Record begin = null;

		private Record end = null;
		
		private class Record {

	 		public Object elem;

	 		public Record next;

			public Record(Object e) {
				this.elem = e; 
				this.next = null;
			}
		}
		

		public boolean isEmpty() {
			return this.begin == null;
		}

		public void enqueue(Object e) {
			if (this.isEmpty())
				this.begin = this.end = new Record(e);
			else {
				this.end.next = new Record(e);
				this.end = this.end.next;
			}
		}


		public Object first(){
			return this.begin.elem;
		}

		public void dequeue(){
			if(this.begin==this.end){
				if(this.begin==null)
				System.out.println("The queue is empty!");
				else
					this.begin=this.end=null;
			}
			else{
				begin=begin.next;
			}
			
		}
		
		public Object clone(){
			Object o = null;
			try {
				o= super.clone();
			} catch (CloneNotSupportedException e) {
			
				System.err.println("La clonazione non è avvenuta correttamente");
			}
			return o;
		}

	}