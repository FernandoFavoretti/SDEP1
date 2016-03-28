/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerSide;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author F8064196
 */
public class PartRepository extends UnicastRemoteObject implements PartRepositoryInterface{
  
    private String repository;  //name of repository
    
      public PartRepository(String repository)  throws RemoteException {
          this.repository = repository;
      }
    

    /**
     * @return the repository
     */
    public String getRepository() {
        return repository;
    }

    /**
     * @param repository the repository to set
     */
    public void setRepository(String repository) {
        this.repository = repository;
    }

    @Override
    public Part addPart(String cod, String name, String desc, boolean isPrimitive, ArrayList<Map<String, Integer>> subPart)  throws RemoteException  {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Part lookUp(String cod)  throws RemoteException  { 
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<String> search(String repository)  throws RemoteException { 
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
    
}
