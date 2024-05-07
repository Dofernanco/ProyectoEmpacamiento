package interfaces;

/**
* creación de la interfaz BinaryTree, basándonos en los comportamientos comunes de los árboles binarios.
* @author Angel Zurita
* @since 11/04/2024
* @version 1
*/
public interface BinaryTree<E>{
	/**
	* Método que agrega un nodo al árbol binario.
	* Si ya existe un nodo que contiene al elemento dado, lanzará error.
	* @param element Dato que quieres que tenga el nodo a agregar.
	* @return true si se agregó con exito, false si no fue así.
	*/
	public boolean insert(E element);
	
	/**
	* Método que borra un elemento del árbol binario.
	* @param element Elemento que quieres borrar
	* @return true si se agregó con exito, false si no fue así(o si no existe el elemento).
	*/
	public boolean delete(E element);
	
	/**
	* Método que indica si un árbol binario está vacío.
	* @return true si el árbol no tiene elementos, false en caso contrario.
	*/
	public boolean isEmpty();
	
	/**
	* Método que devuelve la altura de un árbol binario
	* @return La altura del árbol binario.
	*/
	public int height();
}
