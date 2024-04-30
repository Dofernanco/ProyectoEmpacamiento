

public class Package{
	private String name;
	private String description;
	private double weight;
	private int id;
	
	public Package(String name,String description,double weight,int id){
		this.name=name;
		this.description=description;
		this.weight=weight;
		this.id=id;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public void setDescription(String description){
		this.description=description;
	}
	
	public void setWeight(double weight){
		this.weight=weight;
	}
	
	public void setId(int id){
		this.id=id;
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
}