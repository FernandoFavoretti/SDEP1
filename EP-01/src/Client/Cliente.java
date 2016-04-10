package Client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import Parts.AmountSubComponents;
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
	public static PartRepository currentPR = null;
	public static Part currentP = null;
	
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
	
	public  static String getNameWithBarsSplit(String in, int index) {
		String[] out = in.split("/");
		return out[index];
	}
	
	public static void startConnection() throws MalformedURLException, RemoteException, NotBoundException {
		
		String[] names = Naming.list("");
		String auxS = null;
		for (int i = 0; i < names.length; i++) {
			auxS = names[i];
			Part part = (Part) Naming.lookup(auxS);
			PartRepository partRep = (PartRepository) Naming.lookup(auxS);
			partRep.setNamePR(getNameWithBarsSplit(auxS, 4));
			partRep.setConnection(getNameWithBarsSplit(auxS, 3) + "/" +getNameWithBarsSplit(auxS, 4));
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
	
	public static void showAllServer() throws RemoteException, MalformedURLException {
		
		String[] names = Naming.list("localhost");
		System.out.println("");
		System.out.println(">------------- Servidores -------------");
		for (int i = 0; i < names.length; i++){
			System.out.printf("> %d  - " + getNameWithBarsSplit(names[i], 4).toUpperCase() + "\n", i+1);
			serversNames.add(getNameWithBarsSplit(names[i], 4).toUpperCase());
		}
		System.out.println(">--------------------------------------");
		

	}
	
	public static void startClient() throws MalformedURLException, RemoteException, NotBoundException{
		
		startConnection();
		
		msgSucessConnection();
		
		int indexPart = 0;
		currentPR = selectServerPR();
		msgCurrentClient(currentPR.getNamePR().toUpperCase(),currentPR.getConnection());
		
		String command = "";

		while(!command.equals("quit")) {
			System.out.print  ("// >Inserir comando: ");
			command = scan.next();
			System.out.println("//                                                  //");
			if(!command.equals("bind")) {

				if(command.equals("getp")) { 
					if(currentPR.getPartsList().size()!=0){
						indexPart = getp(currentPR);
						currentP = currentPR.getPartsList().get(indexPart);
						System.out.println("//// > "+ currentP.getName().toUpperCase());
					}else
						System.out.println("Nao existe pecas nesse repositorio  ");
				}else{

					System.out.println("//==================================================//");
					switcher(command, currentPR, indexPart);
				}
			}
			else {
				System.out.println("//==================================================//");

				currentPR = selectServerPR();
				if(currentPR.getPartsList().size() != 0)
					currentP = currentPR.getPartsList().get(0);
				else currentP = null;
								
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
				System.out.println("//// >------------- Lista de Parts -------------");
				while (iterator.hasNext()) {
					auxS = iterator.next();
					System.out.printf("//// > "+ auxS.getCod() +" - " + auxS.getName().toUpperCase() + "\n");
				}
				System.out.println("////>-----------------------------------------");
				break;
			
			case "showp" : 
				if(currentP == null){
					System.out.println("Nao existe peca corrente no momento");
					break;
				}
				System.out.print("//// >A peca atual: " + currentP.getName().toUpperCase() + " possui as seguitnes caracteristicas");
				System.out.println("//// > - " + currentP.getDescribe().toUpperCase());
				Iterator<AmountSubComponents> subiterator = currentP.getComponents().iterator();
				AmountSubComponents auxsubiterator = null;
					if(subiterator.hasNext())
						System.out.println("//// > - Contem as seguintes subParts: \\Nome \\Quantidade \\ Servidor");
							
					else
						System.out.println("//// > - Nao contem nenhuma subPart ");

						while(subiterator.hasNext()){
							auxsubiterator = subiterator.next();
							System.out.println("//// > - \\" + auxsubiterator.getSubComponent().getName().toUpperCase() + " \\ "+ auxsubiterator.getAmount()+" \\ "+auxsubiterator.getServer());

							}

						if(currentP.getIsPrimitive())
							System.out.printf("//// > - Esta chave é primitiva. \n");
						else
							System.out.printf("//// > - Esta chave não é primitiva. \n");

						break;

			case "clearlist" : 
				if(currentP == null){
					System.out.println("Nao existe peca corrente no momento");
					break;
				}
				currentP.getComponents().clear();
				System.out.println("//// > A lista foi limpa com sucesso. ");
				break;
			
			case "addsubpart" : 
				if(currentP == null){
					System.out.println("Nao existe peca corrente no momento");
					break;
				}
				System.out.print("//// >A peca atual: " + currentP.getName().toUpperCase());
				if(!currentP.getIsPrimitive())
					System.out.println(" \\ nao é primitiva. ");
				else{
					System.out.println(" \\ é primitiva. ");
					break;
				}
				String auxsubpart;
				System.out.print("//// >Insira o nome da subParte que sera inserida: ");
				scan.nextLine();
				auxsubpart = scan.nextLine();
				auxsubpart = auxsubpart.replaceAll(" ", "_").toLowerCase();
				System.out.print("//// >Insira a quantidade: ");
				int auxsubqnt;
				auxsubqnt = Integer.parseInt(scan.next());
				Part auxsubpart2 = null;
				
				for(int servers = 0; servers < serversPR.size() ; servers++){
					PartRepository auxrepo = serversPR.get(servers);
					Iterator <Part> iterator4 = auxrepo.getPartsList().iterator();
					while(iterator4.hasNext()){
						auxsubpart2 = iterator4.next();
						if(auxsubpart.toUpperCase().equals(auxsubpart2.getName().toUpperCase())){
							currentP.addSubComponents(auxsubpart2, auxsubqnt, serversNames.get(servers));
							System.out.printf("//// > - a Parte: " + auxsubpart2.getName() + " foi adicionada com sucesso! \n");

						}
					}
				}
				
				break;

			case "addp" :
				Part part = new Servidor();
				countParts++;
				String aux;
				
				part.setCod(countParts);
				
				System.out.print("//// >Insira o nome da Part: ");
				scan.nextLine();
				aux = scan.nextLine();
				

				aux = aux.replaceAll(" ", "_").toLowerCase();
				part.setName(aux);
				
				System.out.print("//// >Insira uma descricao da Part: ");
				aux = scan.nextLine();
				
				aux = aux.replaceAll(" ", "_").toLowerCase();
				part.setDescribe(aux);
				
				System.out.print("//// >Eh primitivo? [y/n]: ");
				while(!aux.equals("y") && !aux.equals("n")){
					aux = scan.next(); 
					if(aux.equals("y")){
						part.setIsPrimitive(true);
					}
					if(aux.equals("n")){
						part.setIsPrimitive(false);

					}
					if(!aux.equals("y") && !aux.equals("n")){
						System.out.println("//// > Entrada invalida. Responda com 'y' ou 'n' ");
						
					}
				}
				partR.addPart(part);
				currentP = part;
				
				msgSucessCreatedPart();
			
				break;
			//------------------------------------------------------------------------
				
			case "quit" :
				msgEndApp();
				break;
			
			default : 
				System.out.println("Comando Invalido \\  Fernando sexisi");
		}
	}
	
	public static void msgCurrentClient(String rep, String link) throws RemoteException {
		System.out.println("");
		System.out.println("//================= Conexao Atual ==================//");
		System.out.printf ("// Repositorio: %s\n", rep);
		System.out.printf ("// Link: %s\n", link);
		System.out.println("// Quantidade de Pecas:" + currentPR.getPartsList().size());
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
		System.out.println(">Crie servidores inserido seu nome.");
		System.out.println(">Nome de servidor nao podera ser nome composto!!! ");

	}
	
}
