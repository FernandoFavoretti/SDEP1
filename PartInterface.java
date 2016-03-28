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
public interface PartInterface extends Remote {
   
    public String getCod() throws RemoteException;
    public void setCod(String cod) throws RemoteException;
    public String getName() throws RemoteException;
    public void setName(String name) throws RemoteException;
    public String getDesc() throws RemoteException;
    public void setDesc(String desc) throws RemoteException;
    public boolean isIsPrimitive() throws RemoteException;
    public void setIsPrimitive(boolean isPrimitive) throws RemoteException;
    public ArrayList<Map<String, Integer>> getSubPart() throws RemoteException;
    public void setSubPart(ArrayList<Map<String, Integer>> subPart) throws RemoteException;

}
