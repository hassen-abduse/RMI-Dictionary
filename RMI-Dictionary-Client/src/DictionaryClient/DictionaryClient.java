package DictionaryClient;


import ServerInterface.DictionaryServerInterface;

import javax.swing.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DictionaryClient {

    public static DictionaryServerInterface dictionaryServerInterface;
    private DictionaryClient() {}
    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(1235);

            dictionaryServerInterface = (DictionaryServerInterface) registry.lookup("DictionaryService");
            System.out.println(dictionaryServerInterface);

            JFrame homeFrame = new JFrame("Home");
            homeFrame.setContentPane(new ClientHome().getPanel());
            homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            homeFrame.setResizable(false);
            homeFrame.pack();
            homeFrame.setVisible(true);
            JOptionPane.showMessageDialog(null, "Connected!");

        }catch (Exception ex) {
            System.err.println("Client Exception: " + ex.toString());
            ex.printStackTrace();
        }

    }
}
