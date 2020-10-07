package DictionaryServer;

import ServerInterface.DictionaryServerInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.*;

public class DictionaryServer {

    public static void main(String[] args) {

        try {
            DictionaryServerInterface stub = (DictionaryServerInterface) new DictionaryServerService();
            Registry registry = LocateRegistry.createRegistry(1235);
            registry.bind("DictionaryService", stub);
            System.out.println("Server Ready");

        } catch (Exception ex)
        {
            System.err.println("Server Exception: " + ex.toString());
            ex.printStackTrace();
        }
    }
}
