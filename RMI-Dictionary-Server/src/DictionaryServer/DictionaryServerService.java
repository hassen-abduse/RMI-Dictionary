package DictionaryServer;

import ServerInterface.DictionaryServerInterface;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

public class DictionaryServerService extends UnicastRemoteObject implements DictionaryServerInterface {
    public static File JSONFile = new File("./dictionary.json");

    protected DictionaryServerService() throws RemoteException {
        super();
    }

    @Override
    public synchronized String addWord(String wordJSON) throws RemoteException {
        System.out.println("Add request has come.");
        System.out.println("Add: " + wordJSON);
        String response = null;
        JSONObject wordObject = new JSONObject(wordJSON);
        try {
            if (FileHandler.searchWord(wordObject.getString("word"), FileHandler.readJSONFile(JSONFile)) == null) {
                String jsonString = FileHandler.addWord(wordObject, FileHandler.readJSONFile(JSONFile));
                FileHandler.writeJSONFile(jsonString, JSONFile);
                response = "Word Added Successfully!";
            }
            else {
                response = "Duplicate word. Couldn't Add!";
            }

        } catch (IOException ex) {
            System.err.println("Error opening the dictionary JSON file");
        }
        return response;

    }

    @Override
    public synchronized String removeWord(String word) throws RemoteException {
        System.out.println("Remove request has come.");
        System.out.println("Remove: " + word);
        JSONObject wordObject;
        String response = null;
        try {
            if (FileHandler.searchWord(word, FileHandler.readJSONFile(JSONFile)) == null) {
                response = "Word Not Found!";
            }
            else {
                wordObject = new JSONObject(FileHandler.searchWord(word, FileHandler.readJSONFile(JSONFile)));
                String jsonString = FileHandler.removeWord(wordObject, FileHandler.readJSONFile(JSONFile));
                FileHandler.writeJSONFile(jsonString, JSONFile);
                response = "Word Removed Succesfully!";
            }

        } catch (IOException ex) {
            System.err.println("Error opening the  dictionary JSON File.");
        }
        return response;
    }

    @Override
    public synchronized String searchWord(String word) throws RemoteException {
        System.out.println("Search request has come");
        System.out.println("Search: " + word);
        JSONObject wordObject;
        String response = null;
        try {
            if(FileHandler.searchWord(word, FileHandler.readJSONFile(JSONFile)) == null) {
                response =  "Word Not Found!";
            }
            else {
                wordObject = new JSONObject(Objects.requireNonNull(FileHandler.searchWord(word, FileHandler.readJSONFile(JSONFile))));
                response = wordObject.toString();
            }
        } catch (IOException ex) {
            System.err.println("Error opening the dictionary JSON File");
        }
        return response;
    }
}
