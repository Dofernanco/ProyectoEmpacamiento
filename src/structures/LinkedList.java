package structures;

import interfaces.List;
import structures.Cell;
import java.lang.IndexOutOfBoundsException;

/**
* creación de la clase LinkedList, basándonos en su TDA
* @author Angel Zurita
* @since 14/03/2024
* @version 1
*/
public class LinkedList<E> implements List<E>{
	//Atributos.
	private Cell<E> head;
	private Cell<E> end;
	private int size;
	
	/**
	* Constructor de una lista ligada.
	* Inicializa head y end como null; y size como 0.
	*/
	public LinkedList(){
		head=null;
		end=null;
		size=0;
	}
	
	/**
	* Método que indica si una lista está vacía.
	* @return true si la lista no tiene elementos, false en caso contrario.
	*/
	public boolean isEmpty(){
		return size==0;
	}
	
	/**
	* Método que devuelve el número de elementos que tiene una lista.
	* @return size.
	*/
	public int size(){
		return size;
	}
	
	@Override
	public boolean add(E element){
		boolean flag=false;
		if(head==null){
			head= new Cell<E>(element);
			end=head;
		}else{
			Cell<E> cs= new Cell<E>(element);
			end.setNext(cs);
			end=cs;
		}
		size++;
		flag=true;
		return flag;
	}
	
	@Override
	public boolean add(E element,int index) throws IndexOutOfBoundsException{
		boolean flag=false;
		if((index<=size) &&(index>=0)){
			if(index==0){
				Cell<E> tmp3=new Cell<E>(element);
				tmp3.setNext(head);
				head=tmp3;
				if(size==0){
					end=head;
				}
				size++;
			}else if(index==size){
				this.add(element);
			}else {
				Cell<E> tmp=head;
				Cell<E> tmp2=new Cell<E>(element);
				for(int i=1;i<(index);i++){
					tmp=tmp.getNext();
				}
				tmp2.setNext(tmp.getNext());
				tmp.setNext(tmp2);
				size++;
			}
			flag=true;
		}else{
			throw new IndexOutOfBoundsException("Índice "+index+ " fuera de límites para el tamaño "+size);
		}
			
		return flag;
	}
	
	@Override
	public E remove(int index) throws IndexOutOfBoundsException{
		E tmp2=null;
		if((index<size) &&(index>=0)){
			if(index==0){	
				tmp2=(E)(head.getData());
				head=head.getNext();
				
				if(size==1){
					this.clean();
					size++;
				}
			}else{
				Cell<E> tmp=head;
				for(int i=1;i<(index);i++){
					tmp=tmp.getNext();
				}
				tmp2=(E)(tmp.getNext().getData());
				
				tmp.setNext(tmp.getNext().getNext());
				
				if(index==(size-1)){
					end=tmp;
				}
			}
			size--;
		}else {
			throw new IndexOutOfBoundsException("Índice "+index+ " fuera de límites para el tamaño "+size);
		}
		return tmp2;
	}
	
	@Override
	public boolean removeAll(E element){
		int before=size;
		this.elimina(element);
		int after=size;
		return ((before-after)!=0);
	}
	
	/**
	* Método que devuelve una lista parecida a la dada, pero sin la cabeza.  
	* Es un método auxiliar para que el método removeAll(E element) funcione.
	* @return La lista dada sin la cabeza.
	*/
	private LinkedList<E> makeSubList(){
		LinkedList<E> tmp=new LinkedList<E>();
		Cell<E> ctmp2=this.head;
		for(int i=0;i<(this.size-1);i++){
			tmp.add(ctmp2.getNext().getData(),i);
			ctmp2=ctmp2.getNext();
		}
		return tmp;
	}
	
	/**
	* Método que modifica una lista dada, de manera que conserva la cabeza;
	* pero el resto de la lista se vuelve otra lista dada(segundaLista).
	* Es un método auxiliar para que el método removeAll(E element) funcione.
	* @param segundaLista Lista que queremos que sea el nuevo cuerpo de nuestra otra lista. 
	*/
	private void join(LinkedList<E> segundaLista){
		if(segundaLista.head!=null){
			head.setNext(segundaLista.head);
			end=segundaLista.end;
			size=(segundaLista.size+1);
		}else{
			E tmp=head.getData();
			this.clean();
			this.add(tmp);
			
		}
	}
	
	/**
	* Método recursivo que borra todas las apariciones de un elemento dado.  
	* Es un método auxiliar para que el método removeAll(E element) funcione.
	* @param element El elemento que queremos eliminar.
	* @return La lista dada sin la cabeza, a lo que se le aplicará este mismo método.
	*/
	private LinkedList<E> elimina(E element){
		if(size==1){
			if(this.head.getData()==element){
				this.clean();
			}
		}
		if(size>1){
			if(this.head.getData()==element){
				this.remove(0);
				this.elimina(element);
			}else{
				this.join(this.makeSubList().elimina(element));
			}
		}
		return this;
	}
	
	
	/**
	* Método que muestra qué elemento está en la celda en índice dado.
	* @param index ïndice del que queremos saber su elemento.
	* @return El dato de la celda en el índice dado
	*/
	public E getElement(int index) throws IndexOutOfBoundsException{
		Cell<E> tmp=null;
		if((index<size) &&(index>=0)){
			tmp=head;
			for(int i=1;i<=index;i++){
				tmp=tmp.getNext();
			}
		}else {
			throw new IndexOutOfBoundsException("Índice "+index+ " fuera de límites para el tamaño "+size);
		}
		return tmp.getData();
	}
	
	/**
	* Método que muestra en qué índice está la primera aparición de un elemento dado.
	* @param element El elemento del que queremos conocer su índice.
	* @return El índice de la primera aparición del elemento dado. -1 si no ha aparecido el eloemento dado.
	*/
	public int getIndex(E element){
		int index=-1;
			if(head!=null){
			Cell<E> tmp=head;
				for(int i=0;i<size;i++){
					if(tmp.getData().equals(element)){
						index=i;
						break;
					}
					tmp=tmp.getNext();
				}
			}
		return index;
	}
	
	/**
	* Método que vacía una lista.
	*/
	public void clean(){
		head=null;
		end=null;
		size=0;
	}
	
	@Override
	public String toString(){
		Cell<E> cellTmp=head;
		String tmp="[";
		for(int i=0;i<(size-1);i++){
			tmp+=cellTmp.getData().toString()+",";
			cellTmp=cellTmp.getNext();
		}
		
		if(end!=null){
			tmp+=end.getData().toString()+"]";
		}else{
			tmp+="]";
		}
		
		return tmp;
	}
	
	
}
