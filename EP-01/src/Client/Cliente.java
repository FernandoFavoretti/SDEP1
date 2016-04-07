package Client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


import Parts.Part;
import Parts.PartRepository;
import Server.Servidor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

class Cliente {
	
	public static int countParts = 0;
	public static Scanner scan = new Scanner (System.in);
	public static ArrayList<String> serversNames = new ArrayList<String>();
	public static ArrayList<PartRepository> serversPR = new ArrayList<PartRepository>();
	
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		String ynClient = "";
		String ynServer = "y";
		msgInitApp();
		msgInstruction();
		
		addServer();
		
		while(!ynServer.equals("n")) {
			System.out.print(">Criar outro Servidor? [y/n]: ");
			ynServer = scan.nextLine();
			if(!ynServer.equals("y") && !ynServer.equals("n")) {
				msgOptionNotRecognized();
			}
			else if(ynServer.equals("y")){
				addServer();
			}
		}
	
		System.out.println();
		while(!ynClient.equals("y") && !ynClient.equals("n")) {
			System.out.print(">Iniciar Cliente? [y/n]: ");
			ynClient = scan.nextLine();
			if(ynClient.equals("y")) {
				startClient();
				break;
			}
			else if(ynClient.equals("n")){
				break;
			}
			msgOptionNotRecognized();
		}
		
		scan.close();
	}
	
	public static void addServer() {
		String nameServer = "";
		System.out.println();
		System.out.print(">Insira o nome do Servidor: ");
		nameServer = scan.nextLine();
		serversNames.add(nameServer);
		runNewServer(nameServer);
		msgSucessServer();
	}
	
	public static void runNewServer(String nameServer){
		try {
			Runtime.getRuntime().exec("cmd.exe /c start java Server.Main " + nameServer);
		} catch(IOException iOException){
	    	iOException.printStackTrace();
	    }
	}
	
	public static void startConnection() throws MalformedURLException, RemoteException, NotBoundException {
	
		Iterator <String> iterator = serversNames.iterator();
		String auxS = null;
		while (iterator.hasNext()) {
			auxS = iterator.next();
			Part part = (Part) Naming.lookup("localhost/" + auxS);
			PartRepository partRep = (PartRepository) Naming.lookup("localhost/" + auxS);
			partRep.setConnection("localhost/" + auxS);
			partRep.setNamePR(auxS);
			serversPR.add(partRep);
		}
		
	}
	
	public static String selectServerName(int index) throws MalformedURLException, RemoteException, NotBoundException {
		return serversNames.get(index-1);
	}
	
	public static PartRepository selectServerPR() throws MalformedURLException, RemoteException, NotBoundException {
		int index;
		showAllServer();
		System.out.print(">Selecione Servidor: ");
		index = scan.nextInt();
		return serversPR.get(index-1);
	}
	
	public static void showAllServer() {
		
		Iterator <String> iterator = serversNames.iterator();
		String auxS = null;
		int i = 0;
		System.out.println("");
		System.out.println(">------------- Servidores -------------");
		while (iterator.hasNext()) {
			auxS = iterator.next();
			System.out.printf("> %d - " + auxS.toUpperCase() + "\n", i+1);
			i++;
		}
		System.out.println(">--------------------------------------");
	}
	
	public static void startClient() throws MalformedURLException, RemoteException, NotBoundException{
		
		startConnection();
		
		msgSucessConnection();
		
		int indexPart = 0;
		Part currentP = null;
		PartRepository currentPR = selectServerPR();
		msgCurrentClient(currentPR.getNamePR().toUpperCase(),currentPR.getConnection());
		
		String command = "";

		while(!command.equals("quit")) {
			System.out.print  ("// >Inserir comando: ");
			command = scan.next();
			System.out.println("//                                                  //");
			if(!command.equals("bind")) {
				
				//COMANDO GETP-------------------------------------------
				if(command.equals("getp")) { 
					indexPart = getp(currentPR);
					currentP = currentPR.getPartsList().get(indexPart);
				}
				//-------------------------------------------------------
				System.out.println("//==================================================//");
				switcher(command, currentPR, indexPart);
			}
			else {
				System.out.println("//==================================================//");
				
				//COMANDO BIND------------------------------------------
				currentPR = selectServerPR();
				//-------------------------------------------------------
								
				msgCurrentClient(currentPR.getNamePR().toUpperCase(),currentPR.getConnection());
			}
			
		}
		scan.close();
	}
	
	public static int getp(PartRepository partR) throws RemoteException {
		int codPart;
		int index = 0;
		System.out.print("//// >Insira o codigo da peca: ");
		codPart = scan.nextInt();
		
		Iterator <Part> iterator = partR.getPartsList().iterator();
		Part auxS = null;
		while (iterator.hasNext()) {
			auxS = iterator.next();
			if(codPart == auxS.getCod()) { 
				break;
			}
			index++;
		}
		return index;
	}
	
	public static void switcher(String s, PartRepository partR, int codPart) throws RemoteException {
		switch (s) {
		
			case "listp" : 
				Iterator <Part> iterator = partR.getPartsList().iterator();
				Part auxS = null;
				int i = 0;
				System.out.println("//// >------------- Lista de Parts -------------");
				while (iterator.hasNext()) {
					auxS = iterator.next();
					System.out.printf("//// > %d - " + auxS.getName().toUpperCase() + "\n", i+1);
					i++;
				}
				System.out.println("////>-----------------------------------------");
				break;
			
			case "showp" : 
				/*
				 *	IMPLEMENTAR 
				 */
				
				break;
			case "clearlist" : 
				/*
				 *	IMPLEMENTAR 
				 */
				
				break;
			
			case "addsubpart" : 
				/*
				 *	IMPLEMENTAR 
				 */
				
				break;
			
			//COMANDO ADDP------------------------------------------------------------	
			case "addp" :
				Part part = new Servidor();
				countParts++;
				String aux;
				
				part.setCod(countParts);
				
				System.out.print("//// >Insira o nome da Part: ");
				aux = scan.next();
				part.setName(aux);
				
				System.out.print("//// >Insira uma descricao da Part: ");
				aux = scan.next();
				part.setDescribe(aux);
				
				System.out.print("//// >Eh primitivo? [y/n]: ");
				aux = scan.next(); 
				part.setIsPrimitive(aux.equals("y"));
				
				partR.addPart(part);
				
				msgSucessCreatedPart();
			
				break;
			//------------------------------------------------------------------------
				
			case "quit" :
				msgEndApp();
				break;
			
			default : 
				System.out.println("BOBAGEM");
		}
	}
	
	public static void msgCurrentClient(String rep, String link) {
		System.out.println("");
		System.out.println("//================= Conexao Atual ==================//");
		System.out.printf ("// Repositorio: %s\n", rep);
		System.out.printf ("// Link: %s\n", link);
		System.out.println("// Pecas:");
		System.out.println("// Subpecas:");
		System.out.println("//==================================================//");
		System.out.println("//                                                  //");
	}
	
	public static void msgSucessCreatedPart() {
		System.out.println("////                                               //");
		System.out.println("//// > --------------------------------------      //");
		System.out.println("//// > | Part criada com sucesso!           |      //");
		System.out.println("//// > --------------------------------------      //");
		System.out.println("////                                               //");
		System.out.println("//=================================================//");
		System.out.println("//                                                 //");
	}
	
	public static void msgSucessServer() {
		System.out.println();
		System.out.println("> --------------------------------------");
		System.out.println("> | Servidor criado com sucesso!       |");
		System.out.println("> --------------------------------------");
		System.out.println();
	}
	
	public static void msgOptionNotRecognized() {
		System.out.println();
		System.out.println("> --------------------------------------");
		System.out.println("> | Opcao nao reconhecida!             |");
		System.out.println("> --------------------------------------");
		System.out.println();
	}
	
	public static void msgSucessConnection() {
		System.out.println();
		System.out.println("> --------------------------------------");
		System.out.println("> | Conexoes efetuadas com sucesso!    |");
		System.out.println("> --------------------------------------");
		System.out.println();
	}
	
	public static void msgEndApp() {
		System.out.println();
		System.out.println("> ===========================================");
		System.out.println("> ||     <~~~ HASTA LA VISTA BABY ~~~>	   ||");
		System.out.println("> ===========================================");
		System.out.println();
	}
	
	public static void msgInitApp() {
		System.out.println();
		System.out.println();
		System.out.println("> ===========================================");
		System.out.println("> ||     <~~~ WELCOME, WE ARE F2H ~~~>	   ||");
		System.out.println("> ===========================================");
		System.out.println();
	}
	
	public static void msgInstruction() {
		System.out.println(">INSTRUCOES:");
		System.out.println(">Crie servidores inserido seu nome");
		System.out.println(">Falta implementar os comandos:");
		System.out.println("> - showp");
		System.out.println("> - clearlist");
		System.out.println("> - addsubpart");
	}
	
}
