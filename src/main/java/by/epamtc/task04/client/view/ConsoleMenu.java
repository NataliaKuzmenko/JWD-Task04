package by.epamtc.task04.client.view;

import by.epamtc.task04.client.view.exception.MenuException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConsoleMenu {

    private final Map<Integer, String> actions = new LinkedHashMap<>();
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    {
        actions.put(1, "Max count of sentences with repeated word.");
        actions.put(2, "Sentences by words count.");
        actions.put(3, "Word in first sentence absent in another.");
        actions.put(4, "Words concrete length in questions.");
        actions.put(5, "Replace first and last words in sentence.");
        actions.put(6, "All words Alphabet order.");
        actions.put(7, "Words by vowel percentage.");
        actions.put(8, "Words that begins with vowel sorted to first consonant letter.");
        actions.put(9, "Words that sorted by count of given letter(increases).");
        actions.put(10, "Words sorted by count in sentences.");
        actions.put(11, "Remove substring for every sentence.");
        actions.put(12, "Remove words that start with consonant.");
        actions.put(13, "Words that sorted by count of given letter(decreases).");
        actions.put(14, "Palindrom max lenght.");
        actions.put(15, "Remove start letter from words.");
        actions.put(16, "Replace words concrete length in sentence.");
        actions.put(0, "Exit.");
    }

    public String selectAction() throws MenuException, IOException {
        String str = reader.readLine();
        int number = Integer.parseInt(str);

        if (!actions.containsKey(number)) {
            throw new MenuException("Wrong number of action: " + number);
        } else {
            return actions.get(number);
        }
    }

    public Map<Integer, String> getActions() {
        return actions;
    }
}
