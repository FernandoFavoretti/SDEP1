/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerSide;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author F8064196
 */
public interface PartRepositoryInterface extends Remote{
    
    public Part addPart(String cod, String name, String desc, boolean isPrimitive, ArrayList<Map<String, Integer>> subPart )  throws RemoteException;
    
    public Part lookUp(String cod)  throws RemoteException;
    
    public ArrayList<String> search(String repository)  throws RemoteException;
    
    
    
}
