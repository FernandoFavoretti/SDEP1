/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerSide;

import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author F8064196
 */
public class Part extends UnicastRemoteObject implements PartInterface {
    Part() throws Exception{}
    private String cod;
    private String name;
    private String desc;
    private boolean isPrimitive;
    private ArrayList<Map<String, Integer>> subPart = new ArrayList<Map<String, Integer>>();

    /**
     * @return the cod
     */
    public String getCod() throws RemoteException {
        return cod;
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(String cod) throws RemoteException   {
        this.cod = cod;
    }

    /**
     * @return the name
     */
    public String getName() throws RemoteException {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) throws RemoteException {
        this.name = name;
    }

    /**
     * @return the desc
     */
    public String getDesc() throws RemoteException {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) throws RemoteException {
        this.desc = desc;
    }

    /**
     * @return the isPrimitive
     */
    public boolean isIsPrimitive() throws RemoteException {
        return isPrimitive;
    }

    /**
     * @param isPrimitive the isPrimitive to set
     */
    public void setIsPrimitive(boolean isPrimitive) throws RemoteException {
        this.isPrimitive = isPrimitive;
    }

    /**
     * @return the subPart
     */
    public ArrayList<Map<String, Integer>> getSubPart() throws RemoteException {
        return subPart;
    }

    /**
     * @param subPart the subPart to set
     */
    public void setSubPart(ArrayList<Map<String, Integer>> subPart) throws RemoteException {
        this.subPart = subPart;
    }
    
    
}
