package boundary;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

import control.LinkedList;
import control.Packer;
import entity.Package;
import entity.Van;

public class Main{
	
	public static void muestraMenu(){
		System.out.println("¿Qué quieres hacer?:\n Escribe\n"
				+"\t 1\tpara ingresar camioneta\n"
				+"\t 2\tpara ingresar paquete\n"
				+"\t 3\tpara empaquetar\n"
				+"\t 4\tpara salir");
	}
	
	public static void continuar(){
		System.out.println("¿Deseas continuar?:\n Escribe\n"
				+"\t 1\tpara continuar\n"
				+"\t 4\t para salir");
	}
	
	public static void clean(){
		System.out.print("\033[H\033[2J");  
		System.out.flush();  
	}

	public static void write(String line,String path) throws Exception{
		FileWriter fw=new FileWriter(path,true);
		BufferedWriter bw= new BufferedWriter(fw);
		
		bw.write(line+"\n");
		bw.close();
	}
	
	public static void removeAll(String path) throws Exception{
		FileWriter fw=new FileWriter(path);
		BufferedWriter bw= new BufferedWriter(fw);
		
		bw.write("");
		bw.close();
	}
	
	public static void fillPacker(Packer packer,String path) throws FileNotFoundException{
		File file=new File(path); 
		Scanner sc=new Scanner(file);
		String line;
		String[] array;
		
		packer.getVans().clean();
		packer.getPackages().clean();
		
		System.out.println("Se comienza a procesar la ruta");
		while(sc.hasNextLine()){
			line= sc.nextLine();
			
			array=line.split("[/\\|]");
			if(array[0].equals("*")){
				packer.getVans().add(new Van(array[1],Double.parseDouble(array[2])));
			}else if(array[0].equals("#")){
				packer.getPackages().add(new Package(array[1]
							,array[2]
							,Double.parseDouble(array[3])
							,Integer.parseInt(array[4])));
			}
		}
	}
	
	public static void main(String[] args){
		Scanner sc=new Scanner (System.in);
		int opcion=0;
		Packer packer=new Packer();
		
		String id;
		double maxWeight;
		
		String name,description;
		int id2;
		double weight;
		
		while(opcion!=4){
			clean();
			muestraMenu();
			opcion=sc.nextInt();
			
			switch(opcion) {
				case 1: //ingresar camioneta
					System.out.println("Ingresa ID de la camioneta");
					sc.nextLine();
					id=sc.nextLine();
					System.out.println("Ingresa capacidad de la camioneta");
					maxWeight=sc.nextDouble();
					try{
						write("*|"+id+"|"+maxWeight,"resources/data.txt");
					}catch (Exception e){
						System.out.println("Archivo no encontrado");
					}
					
					break;
				case 2: //ingresar paquete
					System.out.println("Ingresa nombre del paquete");
					sc.nextLine();
					name=sc.nextLine();
					System.out.println("Ingresa descripción del paquete");
					description=sc.nextLine();
					System.out.println("Ingresa peso del paquete");
					weight=sc.nextDouble();
					System.out.println("Ingresa id del paquete");
					id2=sc.nextInt();
					try{
						write("#|"+name+"|"+description+"|"+weight+"|"+id2,"resources/data.txt");
					}catch (Exception e){
						System.out.println("Archivo no encontrado");
					}
					
					break;
				case 3: //empaquetar
					try{
						removeAll("resources/delivery.txt");
					}catch (Exception e){
						System.out.println("Archivo no encontrado");
					}
					
					try{
						fillPacker(packer,"resources/data.txt");
					}catch (FileNotFoundException e){
						System.out.println("Archivo no encontrado");
					}
					packer.pack();
					//se llena resources/delivery.txt
					try{
						write(packer.toString(),"resources/delivery.txt");
					}catch (Exception e){
						System.out.println("Archivo no encontrado");
					}
					
					continuar();
					opcion=sc.nextInt();
					break;
				case 4:
					break;
				default:
					System.out.println("Esa opción no es válida :( ");
					
					continuar();
					opcion=sc.nextInt();
			}
		}
		
		clean();
		System.out.println("Vuelva pronto");
		
	}
}
