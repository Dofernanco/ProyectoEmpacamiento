package src;

import java.lang.Comparable;

public class Package implements Comparable<Package>{
    
    private String name;
    private String description;
    private Double weight;
    private int id;
	
    public Package(String name,String description,double weight,int id){
	this.name = name;
	this.description = description;
	this.weight = weight;
	this.id = id;
    }
	
    public void setName(String name){
	this.name = name;
    }
	
    public void setDescription(String description){
	this.description = description;
    }
	
    public void setWeight(double weight){
	this.weight = weight;
    }
	
    public void setId(int id){
	this.id = id;
    }
	
    public String getName(){
	return name;
    }
	
    public String getDescription(){
	return description;
    }
	
    public double getWeight(){
	return weight;
    }
	
    public int getId(){
	return id;
    }

    @Override
    public String toString(){
	return name + id;
    }

    @Override
    public int compareTo(Package p){
	return this.weight.compareTo(p.weight);
    }

    
}
