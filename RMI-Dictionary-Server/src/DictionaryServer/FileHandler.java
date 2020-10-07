package DictionaryServer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

public class FileHandler {


    public static String addWord(JSONObject wordObject, String dictionaryJSON) {
        boolean found = false;
        JSONArray newWords = new JSONArray();
        JSONArray words = new JSONArray((dictionaryJSON));

        for (int i = 0; i < words.length(); i++) {
            JSONObject currentWord = words.getJSONObject(i);
            if (currentWord.getString("word").equals(wordObject.getString("word"))) {
                found = true;

            }
            newWords.put(currentWord);

        }

        if (found) {
            System.out.println("Duplicate");
        } else {
            newWords.put(wordObject);
            System.out.println("Word Added");
        }

        return newWords.toString();
    }

    public static String removeWord(JSONObject wordObject, String dictionaryJSON) {
        boolean found = false;

        JSONArray newWords = new JSONArray();
        JSONArray words = new JSONArray((dictionaryJSON));

        for (int i = 0; i < words.length(); i++) {
            JSONObject currentWord = words.getJSONObject(i);
            if (currentWord.getString("word").equals(wordObject.getString("word"))) {
                found = true;
                continue;
            }
            newWords.put(currentWord);

        }

        if (found) {
            System.out.println("Removed");
        } else {
            System.out.println("Not Found");
        }

        return newWords.toString();

    }

    public static String searchWord(String word, String dictionaryJSON) {
        boolean found = false;
        JSONObject currentWord = new JSONObject();
        JSONArray words = new JSONArray(dictionaryJSON);
        for (int i = 0; i < words.length(); i++) {
            currentWord = words.getJSONObject(i);
            if (currentWord.getString("word").equals(word)) {
                found = true;
            }

        }
        if (found) {
            return currentWord.toString();
        } else {
            return null;
        }
    }

    public static String readJSONFile(File JSONFile) throws IOException {
        StringBuilder jsonString = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(JSONFile));
        String sCurrentLine;
        while ((sCurrentLine = bufferedReader.readLine()) != null) {
            jsonString.append(sCurrentLine);
        }
        return jsonString.toString();

    }

    public static void writeJSONFile(String jsonString, File JSONFile) throws IOException {

        String fileName = JSONFile.getAbsolutePath();
        JSONFile.delete();
        File newJSONFile = new File(fileName);
        FileWriter fileWriter = new FileWriter(newJSONFile);
        fileWriter.write(jsonString);
        fileWriter.close();
    }


}
