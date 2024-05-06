package structures;

/**
* creación de la clase Cell, en específico maneja celdas simples.
* @author Angel Zurita
* @since 14/03/2024
* @version 1
*/
public class Cell<T>{
	//atributos
	private T data;
	private Cell<T> next;
	
	/**
	* Constructor de una celda.
	* @param data El dato que va a tener la celda.
	*/
	public Cell(T data){
		this.data=data;
	}
	
	/**
	* Método que devuelve el dato de una celda.
	* @return data
	*/
	public T getData(){
		return data;
	}
	
	/**
	* Método que permite modificar el dato de una celda.
	* @param data El nuevo dato.
	*/
	public void setData(T data){
		this.data=data;
	}
	
	/**
	* Método que devuelve la celda a la se hace referencia.
	* @return next
	*/
	public Cell<T> getNext(){
		return next;
	}
	
	/**
	* Método que permite modificar la referencia que una celda hace.
	* @param next La nueva referencia de la celda.
	*/
	public void setNext(Cell<T> next){
		this.next=next;
	}
	
	@Override
	public String toString(){
		String temp= "La celda con dato "+ data.toString()+".";
		return temp;
	}
	
	@Override 
	public boolean equals(Object obj){
		boolean flag=false;
		if (obj instanceof Cell && obj!=null){
			Cell temp=(Cell)(obj);
			if (temp.data.equals(this.data)){
				if (temp.next==this.next)
					flag=true;
			}
		}
		return flag;
	}
}
