package Parts;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PartRepository extends Remote{
	
	public void addPart(Part part) throws RemoteException;
	public Part selectPart(int cod) throws RemoteException;
	public void showAllParts() throws RemoteException;	
	public boolean clearParts() throws RemoteException;
	
	public String getConnection() throws RemoteException;
	public String getNamePR() throws RemoteException;
	public  ArrayList<Part> getPartsList() throws RemoteException;
	
	public void setConnection(String conn) throws RemoteException;
	public void setNamePR(String name) throws RemoteException;
	
	//teste RMI----------------------------------------------------------
	public String testPartRepository(String test) throws RemoteException;
		

}
