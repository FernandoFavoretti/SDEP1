package Parts;

import java.util.ArrayList;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Part extends Remote {
	
	public int getCod() throws RemoteException;
	public String getName() throws RemoteException;
	public String getDescribe() throws RemoteException;
	public ArrayList<AmountSubComponents> getComponents() throws RemoteException;
	public boolean addSubComponents(Part part, int ammount, String server) throws RemoteException;
	public boolean getIsPrimitive() throws RemoteException;
	
	public void setCod(int cod) throws RemoteException;
	public void setName(String name) throws RemoteException;
	public void setDescribe(String describe) throws RemoteException;
	public void setComponents() throws RemoteException;
	public void setIsPrimitive(boolean primitive) throws RemoteException;
	
	//teste RMI----------------------------------------------------------
	public String testPart(String test) throws RemoteException;
	
}
