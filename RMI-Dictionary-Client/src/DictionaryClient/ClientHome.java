package DictionaryClient;

import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientHome {
    private JPanel panel;
    private JButton addButton;
    private JButton removeButton;
    private JButton searchButton;
    private JTextField wordStringField;
    private JTextArea wordDetailArea;
    private JPanel panel1;
    public static String wordDetails;

    public ClientHome() {

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                wordDetailArea.setText("");
                boolean isValidWord = true;
                String wordString = wordStringField.getText();
                if (wordString.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Enter a valid word string");
                    isValidWord = false;
                }
                if (isValidWord) {
                    try {
                        String response = DictionaryClient.dictionaryServerInterface.searchWord(wordString);

                        try {
                            JSONObject wordObject = new JSONObject(response);
                            String toDisplay = "";
                            toDisplay = toDisplay.concat("Word: " + wordObject.getString("word") + "\n");
                            toDisplay = toDisplay.concat("\n");
                            toDisplay = toDisplay.concat("Word Class: " + wordObject.getString("wordClass") + "\n");
                            toDisplay = toDisplay.concat("\n");
                            toDisplay = toDisplay.concat("Definition1: " + wordObject.getString("definition1") + "\n");
                            toDisplay = toDisplay.concat("\n");
                            toDisplay = toDisplay.concat("Definition2: " + wordObject.getString("definition2") + "\n");
                            wordDetailArea.setText(toDisplay);

                        } catch (JSONException ex) {
                            JOptionPane.showMessageDialog(null, response);
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.toString());
                    }
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                wordDetailArea.setText("");
                JFrame addNewFrame = new JFrame("Add New Word");
                addNewFrame.setContentPane(new AddNewWord().getPanel1());
                addNewFrame.setResizable(false);
                addNewFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                addNewFrame.pack();
                addNewFrame.setVisible(true);

            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                wordDetailArea.setText("");
                boolean isValidWord = true;

                String wordString = wordStringField.getText();

                if (wordString.equals("") || wordString == null) {
                    JOptionPane.showMessageDialog(null, "Please Enter a valid word string");
                    isValidWord = false;
                }
                System.out.println(isValidWord);
                if (isValidWord) {
                    try {
                        String response = DictionaryClient.dictionaryServerInterface.removeWord(wordString);
                        JOptionPane.showMessageDialog(null, response);
                    }
                    catch (Exception ex) {
                        System.out.println(ex.toString());
                    }
                }
            }
        });
    }

    public JPanel getPanel() {
        return panel1;
    }


}
