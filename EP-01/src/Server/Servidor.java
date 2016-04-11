package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;

import Parts.AmountSubComponents;
import Parts.Part;
import Parts.PartRepository;

public class Servidor extends UnicastRemoteObject implements Part , PartRepository {

	private static final long serialVersionUID = 1L;
	
	//variaveis de Part
	public int codPart = 0;
	public String namePart  = "";
	public String describePart = "";
	public ArrayList<AmountSubComponents> componentsPart = new ArrayList<AmountSubComponents>();
	public boolean isPrimitive = true; 
	
	//variaveis de PartRepository
	public ArrayList<Part> partsList = new ArrayList<Part>();
	public String connection = "";
	public String namePR = "";
	
	//variaveis do Servidor
	public String nameServer = "";
	
	
	public Servidor() throws RemoteException {
		super();
	}
	
	/***************************************************************************/
	//							Metodos de Part									/
	/***************************************************************************/
	
	@Override
	public int getCod() throws RemoteException { 
		return this.codPart; 
	}
	
	@Override
	public String getName() throws RemoteException { 
		return this.namePart; 
	}
	
	@Override
	public String getDescribe() throws RemoteException { 
		return this.describePart; 
	}	
	
	@Override
	public ArrayList<AmountSubComponents> getComponents() throws RemoteException { 
		return this.componentsPart; 
	}
	@Override
	public boolean addSubComponents(Part part, int ammount, String server) throws RemoteException{
		AmountSubComponents subComponent = new AmountSubComponents(part,ammount,server);
		this.componentsPart.add(subComponent);
		
		return true;
	}
	
	@Override
	public boolean getIsPrimitive() throws RemoteException { 
		return this.isPrimitive; 
	}
	
	@Override
	public void setCod(int cod) throws RemoteException { 
		this.codPart = cod; 
	}	
	
	@Override
	public void setName(String name) throws RemoteException { 
		this.namePart = name; 
	}	
	
	@Override
	public void setDescribe(String describe) throws RemoteException { 
		this.describePart = describe;
	}	
	
	@Override
	public void setComponents() throws RemoteException { 
		this.componentsPart.clear();
	}
	
	@Override
	public void setIsPrimitive(boolean primitive) throws RemoteException {	
		this.isPrimitive = primitive; 
	}
		
	
	/***************************************************************************/
	//						Metodos de PartRepository							/
	/***************************************************************************/
	
	@Override
	public void addPart(Part part) throws RemoteException {
		partsList.add(part);		
	}
	
	@Override
	public Part selectPart(int cod) throws RemoteException {
		Iterator <AmountSubComponents> iterator = componentsPart.iterator();
		AmountSubComponents auxSubPart = null;
		while (iterator.hasNext()) {
			auxSubPart = iterator.next();
			if(cod == auxSubPart.getSubComponent().getCod()) {
				break;
			}
		}
		return auxSubPart.getSubComponent();
	}
	
	@Override
	public void showAllParts() throws RemoteException{
		Iterator <Part> iterator = partsList.iterator();
		Part auxPart = null;
		System.out.println("------ Lista de todas as Parts ------");
		while (iterator.hasNext()) {
			auxPart = iterator.next();
			System.out.printf("Codigo: %i\n", auxPart.getCod());
			System.out.printf("Nome: %s\n", auxPart.getName());
			System.out.printf("Descricao: %i\n", auxPart.getDescribe());
			System.out.println();
		}
		System.out.println("-------------------------------------");
	}
	
	public boolean clearParts() throws RemoteException{
		partsList.clear();
		return true;
	}


	@Override
	public String getConnection() throws RemoteException {
		return this.connection;		
	}
	
	@Override
	public String getNamePR() throws RemoteException {
		return this.namePR;
	}
	
	@Override
	public ArrayList<Part> getPartsList() throws RemoteException {
		return this.partsList;
	}
		
	@Override
	public void setConnection(String conn) throws RemoteException {
		this.connection = conn;
	}
	
	@Override
	public void setNamePR(String name) throws RemoteException {
		this.namePR = name;
	}
	


}

