/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerSide;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author F8064196
 */
public class Server implements PartRepositoryInterface, PartInterface {

    public static void main(String[] args) {

        try {
            System.setSecurityManager(null);
            Scanner s = new Scanner(System.in);
            System.out.println("Entre com o nome do Servidor:");
            String repository = s.nextLine().trim();

            PartRepository server = new PartRepository(repository);
            
            Naming.rebind("rmi://localhost/ABC", server);

            System.out.println("[System] Chat Remote Object is ready:");
            
        } catch (Exception e) {
            System.out.println("Server failed: " + e);
        }
    }

    @Override
    public Part addPart(String cod, String name, String desc, boolean isPrimitive, ArrayList<Map<String, Integer>> subPart) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Part lookUp(String cod) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<String> search(String repository) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getCod() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setCod(String cod) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getName() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setName(String name) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getDesc() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setDesc(String desc) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isIsPrimitive() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setIsPrimitive(boolean isPrimitive) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Map<String, Integer>> getSubPart() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSubPart(ArrayList<Map<String, Integer>> subPart) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
