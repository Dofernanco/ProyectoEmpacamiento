package structures;

import interfaces.BinaryTree;

import java.lang.Comparable;
import java.lang.Math;

/**
* creación de la clase AVLTree, basándonos en su TDA.
* @author Angel Zurita
* @since 11/04/2024
* @version 1
*/
public class AVLTree<E extends Comparable< ? super E>> implements BinaryTree<E>{
	//Atributo.
	private Node<E> root;
	
	/**
	* Constructor de un árbol AVL.
	*/
	public AVLTree(){
		root=null;
	}
	
	/**
	* Constructor de un árbol AVL.
	* @param element El dato que va a tener la raíz del árbol.
	*/
	public AVLTree(E element){
		root=new Node(element);
	}
	
	/**
	* Constructor de un árbol AVL.
	* @param root La raíz del árbol.
	*/
	public AVLTree(Node<E> root){
		this.root=root;
	}
	
	@Override
	public boolean insert(E element) {
		Node<E> tmp;
		Node<E> tmp2;
		AVLTree<E> tmp3;
		if(this.height()==0){
			root=new Node<E>(element);
		}else{
			tmp=root;
			while(true){
				if(element.compareTo(tmp.getData())<=0){
					if(tmp.getLeft()!=null){
						tmp=tmp.getLeft();
					}else{
						tmp.setLeft(new Node<E>(element));
						break;
					}
				}else{
					if(tmp.getRight()!=null){
						tmp=tmp.getRight();
					}else{
						tmp.setRight(new Node<E>(element));
						break;
					}
				}
			}
		}
		
		
		while(this.countsBalanced()>0){ //equilibrar el árbol
			tmp2=this.analize();
			if(tmp2==null){
				this.balance(this.root,0);
			}else{
				tmp3=new AVLTree<E>(tmp2.getLeft());
				if(tmp3.countsBalanced()>0){
					this.balance(tmp2,-1);
				}else{
					this.balance(tmp2,1);
				}
			}
		}
		return true;
	}
	
	@Override
	public boolean delete(E element){
		Node<E> tmp=root;
		Node<E> tmp2=null;
		Node<E> tmp3;
		Node<E> tmp4=null;
		Node<E> tmp5;
		AVLTree<E> tmp6;
		int side=0;
		while((tmp!=null) && (!tmp.getData().equals(element))){
			if(element.compareTo(tmp.getData())<0){
				tmp2=tmp;
				tmp=tmp.getLeft();
				side=-1;
			}else{
				tmp2=tmp;
				tmp=tmp.getRight();
				side=1;
			}
		}
		if(tmp!=null){
			if((tmp.getLeft()==null)&&(tmp.getRight()==null)){ //se borra una hoja
				if(tmp==root){
					root=null;
				}else{
					if(side<0)
						tmp2.setLeft(null);
					if(side>0)
						tmp2.setRight(null);
				}
			}else if(tmp.getLeft()==null){ //se borra un nodo que solo tiene hijo derecho
				if(tmp==root){
					root=tmp.getRight();
				}else{
					if(side<0)
						tmp2.setLeft(tmp.getRight());
					if(side>0)
						tmp2.setRight(tmp.getRight());
				}
			}else if(tmp.getRight()==null){ //se borra un nodo que solo tiene hijo izquierdo
				if(tmp==root){
					root=tmp.getLeft();
				}else{
					if(side<0)
						tmp2.setLeft(tmp.getLeft());
					if(side>0)
						tmp2.setRight(tmp.getLeft());
				}
			}else{ //se borra un nodo con dos hijos
				tmp3=tmp.getLeft();
				while(tmp3.getRight()!=null){
					tmp4=tmp3;
					tmp3=tmp3.getRight();
				}
				if(tmp3==tmp.getLeft()){
					if(tmp==root){
						tmp3.setRight(tmp.getRight());
						this.root=tmp3;
					}else{
						if(side<0){
							tmp3.setRight(tmp.getRight());
							tmp2.setLeft(tmp3);
						}
						if(side>0){
							tmp3.setRight(tmp.getRight());
							tmp2.setRight(tmp3);
						}
					}
				}else{
					if(tmp==root){
						tmp4.setRight(tmp3.getLeft());
						tmp3.setRight(tmp.getRight());
						tmp3.setLeft(tmp.getLeft());
						this.root=tmp3;
					}else{
						if(side<0){
							tmp4.setRight(tmp3.getLeft());
							tmp3.setRight(tmp.getRight());
							tmp3.setLeft(tmp.getLeft());
							tmp2.setLeft(tmp3);
						}
						if(side>0){
							tmp4.setRight(tmp3.getLeft());
							tmp3.setRight(tmp.getRight());
							tmp3.setLeft(tmp.getLeft());
							tmp2.setRight(tmp3);
						}
					}
				}
			}
			
			while(this.countsBalanced()>0){ //equilibrar el árbol
				tmp5=this.analize();
				if(tmp5==null){
					this.balance(this.root,0);
				}else{
					tmp6=new AVLTree<E>(tmp5.getLeft());
					if(tmp6.countsBalanced()>0){
						this.balance(tmp5,-1);
					}else{
						this.balance(tmp5,1);
					}
				}
			}
			
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public boolean isEmpty(){
		return (root==null);
	}
	
	@Override
	public int height(){
		if(root==null){
			return 0;
		}else {
			return root.getLevel();
		}
	}
	
	/*public boolean rotateLeft(Node<E> node){
		return true;
	}
	
	
	public boolean rotateRight(Node<E> node){
		return true;
	}*/
	
	/**
	* Método que realiza una rotación a la izquierda.
	* @param node El padre del nodo desbalanceado. Si el nodo desvalanceado no tiene padre, entonces nodo_desvalanceado=raiz, en ese caso node=nodo_desbalanceado=raiz.
	* @param side <0 si el nodo desvalanceado es hijo izquierdo de node, >0 si el nodo desvalanceado es hijo derecho de node, =0 si el nodo desbalanceado es la raíz(node=raiz=node_desbalanceado).
	* @return true si la rotación se realizó con éxito, false en caso contrario.
	*/
	public boolean rotateLeft(Node<E> node,int side){ 
		Node<E> pivot,tmp;
		if(side<0){
			tmp=node.getLeft();
			pivot=tmp.getRight();
			tmp.setRight(pivot.getLeft());
			pivot.setLeft(tmp);
			node.setLeft(pivot);
		}else if(side>0){
			tmp=node.getRight();
			pivot=tmp.getRight();
			tmp.setRight(pivot.getLeft());
			pivot.setLeft(tmp);
			node.setRight(pivot);
		}else{
			pivot=node.getRight();
			node.setRight(pivot.getLeft());
			pivot.setLeft(node);
			this.root=pivot;
		}
		return true;
	}
	
	/**
	* Método que realiza una rotación a la derecha.
	* @param node El padre del nodo desbalanceado. Si el nodo desvalanceado no tiene padre, entonces nodo_desvalanceado=raiz, en ese caso node=nodo_desbalanceado=raiz.
	* @param side Se pide unnúmero <0 si el nodo desvalanceado es hijo izquierdo de node, >0 si el nodo desvalanceado es hijo derecho de node, =0 si el nodo desbalanceado es la raíz(node=raiz=node_desbalanceado).
	* @return true si la rotación se realizó con éxito, false en caso contrario.
	*/
	public boolean rotateRight(Node<E> node,int side){ 
		Node<E> pivot,tmp;
		if(side<0){
			tmp=node.getLeft();
			pivot=tmp.getLeft();
			tmp.setLeft(pivot.getRight());
			pivot.setRight(tmp);
			node.setLeft(pivot);
		}else if(side>0){
			tmp=node.getRight();
			pivot=tmp.getLeft();
			tmp.setLeft(pivot.getRight());
			pivot.setRight(tmp);
			node.setRight(pivot);
		}else{
			pivot=node.getLeft();
			node.setLeft(pivot.getRight());
			pivot.setRight(node);
			this.root=pivot;
		}
		return true;
	}
	
	/**
	* Método que recibe un elemento y regresa el nodo que contiene ese elemento.
	* @param element El elemento a buscar.
	* @return El nodo que contiene al elemento dado. Regresa null, si el elemento dado no se encuentra en el árbol.
	*/
	public Node<E> search(E element) {
		Node<E> tmp;
		int mark=0;
		if(this.height()==0){
			return null;
		}else{
			tmp=root;
			while(true){
				if(element.compareTo(tmp.getData())<0){
					if(tmp.getLeft()!=null){
						tmp=tmp.getLeft();
					}else{
						break;
					}
				}else if(element.compareTo(tmp.getData())==0){
					mark=1;
					break;
				}else{
					if(tmp.getRight()!=null){
						tmp=tmp.getRight();
					}else{
						break;
					}
				}
			}
			if(mark==1){
				return tmp;
			}else{
				return null;
			}
		}
	}
	
	/**
	* Método que balancea al árbol AVL. Es un método auxiliar para los métodos  insert(E element) y delete(E element).
	* Analiza si se debe hacer rotación doble o simple, y hacia qué lado serán esas rotaciones. 
	* Luego realiza las rotaciones necesarias.
	* @param node El padre del nodo desbalanceado. Si el nodo desvalanceado no tiene padre, entonces nodo_desvalanceado=raiz, en ese caso node=nodo_desbalanceado=raiz.
	* @param side Se pide unnúmero <0 si el nodo desvalanceado es hijo izquierdo de node, >0 si el nodo desvalanceado es hijo derecho de node, =0 si el nodo desbalanceado es la raíz(node=raiz=node_desbalanceado).
	*/
	private void balance(Node<E> node,int side){
		Node<E>  tmp;
		if(side<0){
			tmp=node.getLeft(); //tmp es la raiz del subarbol desbalanceado
			if(tmp.getFactor()>0){
				if(tmp.getRight().getFactor()>=0){// rot simple izq
					this.rotateLeft(node,-1);
				}else{ //rot doble der izq
					this.rotateRight(tmp,1);
					this.rotateLeft(node,-1);
				}
			}else{
				if(tmp.getLeft().getFactor()<=0){// rot simple der
					this.rotateRight(node,-1);
				}else{ //rot doble izq der
					this.rotateLeft(tmp,-1);
					this.rotateRight(node,-1);
				}
			}
		}else if(side>0){
			tmp=node.getRight(); //tmp es la raiz del subarbol desbalanceado
			if(tmp.getFactor()>0){
				if(tmp.getRight().getFactor()>=0){// rot simple izq
					this.rotateLeft(node,1);
				}else{ //rot doble der izq
					this.rotateRight(tmp,1);
					this.rotateLeft(node,1);
				}
			}else{
				if(tmp.getLeft().getFactor()<=0){// rot simple der
					this.rotateRight(node,1);
				}else{ //rot doble izq der
					this.rotateLeft(tmp,-1);
					this.rotateRight(node,1);
				}
			}
		}else{
			if(node.getFactor()>0){
				if(node.getRight().getFactor()>=0){// rot simple izq
					this.rotateLeft(node,0);
				}else{ //rot doble der izq
					this.rotateRight(node,1);
					this.rotateLeft(node,0);
				}
			}else{
				if(node.getLeft().getFactor()<=0){// rot simple der
					this.rotateRight(node,0);
				}else{ //rot doble izq der
					this.rotateLeft(node,-1);
					this.rotateRight(node,0);
				}
			}
		}
	}
	
	/*
	* Método que busca el subárbol desbalanceado más pequeño.
	* Es un método auxiliar para los métodos  insert(E element) y delete(E element). 
	* @return El padre de la raíz del subárbol desbalanceado más pequeño. null si el árbol ya está balanceado.
	*/
	private Node<E> analize(){
		AVLTree<E> tmp=new AVLTree<E>(this.root);
		Node<E> tmp2=null;
		if(tmp.countsBalanced()==0){
			return null;
		}else{
			while(tmp.subLeft().countsBalanced()>0||tmp.subRight().countsBalanced()>0){
				if(tmp.subLeft().countsBalanced()<1){
					tmp2=tmp.root;
					tmp=tmp.subRight();
				}else{
					tmp2=tmp.root;
					tmp=tmp.subLeft();
				}
			}
			return tmp2;
		}	
	}
	
	/*
	* Método recursivo que devuelve la cantidad de nodos desbalanceados que tiene el árbol.
	* Es un método auxiliar para los métodos  insert(E element) y delete(E element). 
	* @return La cantidad de nodos desbalanceados que tiene el árbol.
	*/
	private int countsBalanced(){
		if(root==null){
			return 0;
		}else{
			if((this.root.getLeft()==null)&&(this.root.getRight()==null)){
				return 0;
			}else if(this.root.getLeft()==null){
				if((root.getFactor()<(-1))||(root.getFactor()>1)){
					return (subRight().countsBalanced())+1;
				}else{
					return subRight().countsBalanced();
				}
			}else if(this.root.getRight()==null){
				if((root.getFactor()<(-1))||(root.getFactor()>1)){
					return (subLeft().countsBalanced())+1;
				}else{
					return subLeft().countsBalanced();
				}
			}else{
				if((root.getFactor()<(-1))||(root.getFactor()>1)){
					return (subLeft().countsBalanced())+(subRight().countsBalanced())+1;
				}else{
					return (subLeft().countsBalanced())+(subRight().countsBalanced());
				}
			}
		}
	}
	
	/*
	* Método que devuelve el subárbol izquierdo del árbol dado.
	* Es un método auxiliar para los métodos  insert(E element) y delete(E element). 
	* @return El subárbol izquierdo.
	*/
	private AVLTree<E> subLeft(){
		return new AVLTree(this.root.getLeft());
	}
	
	/*
	* Método que devuelve el subárbol derecho del árbol dado.
	* Es un método auxiliar para los métodos  insert(E element) y delete(E element). 
	* @return El subárbol derecho.
	*/
	private AVLTree<E> subRight(){
		return new AVLTree(this.root.getRight());
	}
	
	@Override
	public String toString(){
		 return "\n"+this.toString(0)+"\n";
	}
	
	/*
	* Método recursivo que devuelve la representación en String del árbol.
	* Es un método auxiliar para el método toString().
	* @param n Número de tabulaciones que quieres que tenga la raíz del arbol dado.
	* @return La representación en String del árbol.
	*/
	private String toString(int n){
		String tmp="";
		for(int i=0;i<n;i++)
			tmp+="\t";
		if(this.subRight().root==null && this.subLeft().root==null)
			return tmp+this.root.toString();
		else if(this.subRight().root==null)
			return tmp+this.root+"\n"+this.subLeft().toString(n+1);
		else if(this.subLeft().root==null)
			return "\n"+this.subRight().toString(n+1)+"\n"+tmp+this.root;
		else
			return ""+this.subRight().toString(n+1)+"\n"+tmp+this.root+"\n"+this.subLeft().toString(n+1);
	}
	
}
