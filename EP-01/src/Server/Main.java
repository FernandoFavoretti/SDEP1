package Server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		
		Servidor server = new Servidor();
		//rebind(nome do servico a ser disponibilizado, referencia do objeto)
		String conn = "localhost/" + args[0];
		
		Naming.rebind(conn, server);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
	
		System.out.println();
		System.out.println("//==================================================//");
		System.out.printf ("//      	  ~~~ SERVER %s ~~~\n", args[0].toUpperCase());
		System.out.println("//                                                  //");
		System.out.printf ("// HOST: %s\n", conn);
		System.out.println("// DESCRIBE: Repositorio de partes.");
		System.out.print  ("// CREATED BY: "); System.out.println(dateFormat.format(date));
		System.out.print  ("// CREATED IN: "); System.out.println(System.getProperty("user.name"));
		System.out.println("//                                                  //");
		System.out.println("//==================================================//");
	
	}
}
