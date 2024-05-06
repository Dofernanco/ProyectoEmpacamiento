package structures;

import java.lang.Math;

/**
* creación de la clase Node, en específico maneja nodos binarios.
* @author Angel Zurita
* @since 9/04/2024
* @version 1
*/
public class Node<E>{
	private E data;
	private Node<E> left;
	private Node<E> right;
	private int factor;
	private int height;
	
	/**
	* Constructor de un nodo.
	* @param data El dato que va a tener el nodo.
	*/
	public Node(E data){
		this.data=data;
		left=null;
		right=null;
	}
	
	/**
	* Constructor de un nodo.
	* @param data El dato que va a tener el nodo.
	* @param left El hijo izquierdo del nodo.
	* @param right El hijo derecho del nodo.
	*/
	public Node(E data,Node<E> left,Node<E> right){
		this.data=data;
		this.left=left;
		this.right=right;
	}
	
	/**
	* Constructor de un nodo.
	*/
	public Node(){
		data=null;
		left=null;
		right=null;
		
	}
	
	/**
	* Método que devuelve el dato de un nodo.
	* @return data
	*/
	public E getData(){
		return data;
	}
	
	/**
	* Método que permite modificar el dato de un nodo.
	* @param data El nuevo dato.
	*/
	public void setData(E data){
		this.data=data;
	}
	
	/**
	* Método que devuelve el hijo izquierdo de un nodo.
	* @return left
	*/
	public Node<E> getLeft(){
		return left;
	}
	
	/**
	* Método que permite modificar el hijo izquierdo de un nodo.
	* @param left El nuevo hijo izquierdo.
	*/
	public void setLeft(Node<E> left){
		this.left=left;
	}
	
	/**
	* Método que devuelve el hijo derecho de un nodo.
	* @return right
	*/
	public Node<E> getRight(){
		return right;
	}
	
	/**
	* Método que permite modificar el hijo derecho de un nodo.
	* @param right El nuevo hijo derecho.
	*/
	public void setRight(Node<E> right){
		this.right=right;
	}
	
	/**
	* Método que devuelve el factor de un nodo.
	* @return factor
	*/
	public int getFactor(){
		if((this.getLeft()==null)&&(this.getRight()==null)){
			return 0;
		}else if(this.getLeft()==null){
			return getRight().getLevel();
		}else if(this.getRight()==null){
			return -(getLeft().getLevel());
		}else{
			return (getRight().getLevel())-(getLeft().getLevel());
		}
		
	}
	
	/**
	* Método que devuelve el nivel de un nodo.
	* @return level
	*/
	public int getLevel(){
		if((this.getLeft()==null)&&(this.getRight()==null)){
			return 1;
		}else if(this.getLeft()==null){
			return 1+getRight().getLevel();
		}else if(this.getRight()==null){
			return 1+getLeft().getLevel();
		}else{
			return 1+Math.max(getLeft().getLevel(),getRight().getLevel());
		}
	}
	
	@Override
	public String toString(){
		return "("+ data.toString()+")";
	}
	
}
