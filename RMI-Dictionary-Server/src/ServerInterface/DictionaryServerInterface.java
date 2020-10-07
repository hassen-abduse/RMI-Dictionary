package ServerInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DictionaryServerInterface extends Remote {
    public String addWord(String word) throws RemoteException;

    public String removeWord(String word) throws RemoteException;

    public String searchWord(String word) throws RemoteException;

}