package interfaces;

import java.lang.IndexOutOfBoundsException;

/**
* creación de la interfaz List, basándonos en los comportamientos comunes de las listas.
* @author Angel Zurita
* @since 14/03/2024
* @version 1
*/
public interface List<E>{
	/**
	* Método que agrega un elemento al final de la lista.Actualiza el final de la misma.
	* @param element Dato que quieres que tenga el elemento a agregar.
	* @return true si se agregó con exito, false si no fue así.
	*/
	public boolean add(E element);
	
	/**
	* Método que agrega un elemento en un índice dado.
	* @param element Dato que quieres que tenga el elemento a agregar.
	* @param índice en donde quieres agregar el elemento. 
	* @return true si se agregó con exito, false si no fue así.
	*/
	public boolean add(E element,int index) throws IndexOutOfBoundsException;
	
	/**
	* Método que borra el elemento que está en un índice dado.
	* @param índice del elemento a borrar. 
	* @return el dato del elemento eliminado.
	*/
	public E remove(int index) throws IndexOutOfBoundsException;
	
	/**
	* Método que borra todos los elementos cuyo dato es el  dato dado.
	* @param E Dato que quieres que tengan los elementos a borrar.
	* @return true si se eliminó con exito, false si no fue así.
	*/
	public boolean removeAll(E element);
}
