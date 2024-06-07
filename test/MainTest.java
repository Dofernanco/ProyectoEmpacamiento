package test;

import entity.Van;
import entity.Package;
import control.Packer;
import control.LinkedList;

public class MainTest{
	public static void main(String[] args){
		Van cam1=new Van("1",50);
		Van cam2=new Van("2",60);
		Van cam3=new Van("3",20);
		/*Van cam4=new Van("4",15);
		Van cam5=new Van("5",55);
		Van cam6=new Van("6",40);
		Van cam7=new Van("7",30);
		Van cam8=new Van("8",70);
		Van cam9=new Van("9",45);
		Van cam10=new Van("10",10);
		Van cam11=new Van("11",17);
		
		cam11.getPackages().add(new Package("0","",1000,0));*/
		
		Package pack1=new Package("1","",29,1);
		Package pack2=new Package("2","",55,2);
		Package pack3=new Package("3","",45,3);
		
		Packer packer=new Packer();
		packer.getVans().add(cam1);
		packer.getVans().add(cam2);
		packer.getVans().add(cam3);
		/*packer.getVans().add(cam4);
		packer.getVans().add(cam5);
		packer.getVans().add(cam6);
		packer.getVans().add(cam7);
		packer.getVans().add(cam8);
		packer.getVans().add(cam9);
		packer.getVans().add(cam10);
		packer.getVans().add(cam11);*/
		
		
		packer.getPackages().add(pack1);
		packer.getPackages().add(pack2);
		packer.getPackages().add(pack3);
		
		System.out.println(packer.getPackages());
		System.out.println(packer.getVans());
		
		packer.pack();
		
		System.out.println(packer.getPackages());
		System.out.println(packer.getVans());
	}
}
