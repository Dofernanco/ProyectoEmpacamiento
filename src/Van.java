package src;

import structures.LinkedList;

public class Van{
	private String id;
	private LinkedList<Package> packages;
	private double maxWeight;
	
	public Van(String id,LinkedList<Package> packages,double maxWeight){
		this.id=id;
		this.packages=packages;
		this.maxWeight=maxWeight;
	}
	
	public Van(String id,double maxWeight){
		this.id=id;
		packages=new LinkedList<Package>();
		this.maxWeight=maxWeight;
	}
	
	public void setId(String id){
		this.id=id;
	}
	
	public void setPackages(LinkedList<Package> packages){
		this.packages=packages;
	}
	
	public void setMaxWeight(double maxWeight){
		this.maxWeight=maxWeight;
	}
	
	public String getId(){
		return id;
	}
	
	public LinkedList<Package> getPackages(){
		return packages;
	}
	
	public double getMaxWeight(){
		return maxWeight;
	}
	
	@Override
	public String toString(){
		String tmp= "Camioneta"+ id;
		for (int i=0;i<packages.size();i++){
			tmp+=packages.getElement(i).toString();
			tmp+="\n";
		}
		return tmp;
	}
}
