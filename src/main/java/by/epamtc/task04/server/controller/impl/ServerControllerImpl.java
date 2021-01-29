package by.epamtc.task04.server.controller.impl;

import by.epamtc.task04.server.controller.ServerController;
import by.epamtc.task04.server.entity.TextComponent;
import by.epamtc.task04.server.service.BookService;
import by.epamtc.task04.server.service.BookServiceFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ServerControllerImpl implements ServerController {

    private final int port;
    private final BookService service;

    public ServerControllerImpl(int port) {
        this.port = port;
        this.service = BookServiceFactory.getInstance().getBookService();
    }

    public void run() {
        try (ServerSocket server = new ServerSocket(port)) {

            while (true) {
                Socket clientSocket = server.accept();

                new Thread(() -> {
                    try (ObjectOutput output = new ObjectOutputStream(clientSocket.getOutputStream());
                         ObjectInput input = new ObjectInputStream(clientSocket.getInputStream())
                    ) {
                        String request = input.readLine();

                        switch (request) {
                            case "Max count of sentences with repeated word.":
                                output.writeObject(getMaxCountOfSentencesWithRepeatedWord());
                                break;
                            case "Sentences by words count.":
                                output.writeObject(orderSentencesByWordsCount());
                                break;
                            case "Replace words concrete length in sentence.":
                                output.writeObject(replaceWordsConcreteLength());
                                break;
                            case "Word in first sentence absent in another.":
                                output.writeObject(wordInFirstSentenceAbsentInAnother());
                                break;
                            case "Words concrete length in questions.":
                                output.writeObject(getWordsConcreteLengthInQuestions());
                                break;
                            case "All words Alphabet order.":
                                output.writeObject(allWordsAlphabetOrder());
                                break;
                            case "Words by vowel percentage.":
                                output.writeObject(getWordsByVowelPercentage());
                                break;
                            case "Words that begins with vowel sorted to first consonant letter.":
                                output.writeObject(getWordsThatBeginsWithVowel());
                                break;
                            case "Words that sorted by count of given letter(increases).":
                                output.writeObject(getWordsThatSortedByCountOfGivenLetterIncr());
                                break;
                            case "Words sorted by count in sentences.":
                                output.writeObject(getWordsSortedByCountInSentences());
                                break;
                            case "Remove substring for every sentence.":
                                output.writeObject(removeSubstringEverySentence());
                                break;
                            case "Remove words that start with consonant.":
                                output.writeObject(removeWordsStartWithConsonant());
                                break;
                            case "Words that sorted by count of given letter(decreases).":
                                output.writeObject(getWordsThatSortedByCountOfGivenLetterDecr());
                                break;
                            case "Palindrom max lenght.":
                                output.writeObject(getPalindromeMaxLenght());
                                break;
                            case "Remove start letter from words.":
                                output.writeObject(removeStartLettersFromWords());
                                break;
                            case "Replace first and last words in sentence.":
                                output.writeObject(replaceFirstAndLastWordsInSentence());
                                break;
                        }
                        output.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            clientSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer getMaxCountOfSentencesWithRepeatedWord() {
        return service.getMaxCountOfSentencesWithRepeatedWord();
    }

    private String orderSentencesByWordsCount() {

        return service.orderSentencesByWordsCount();
    }

    private TextComponent replaceWordsConcreteLength() {
        return service.replaceWordsConcreteLengthInSentence(4, 2, "***");
    }

    private TextComponent wordInFirstSentenceAbsentInAnother() {

        return service.wordInFirstSentenceAbsentInAnother();
    }

    private Set<TextComponent> getWordsConcreteLengthInQuestions() {
        return service.getWordsConcreteLengthInQuestions(5);
    }

    private Set<TextComponent> allWordsAlphabetOrder() {

        return service.allWordsAlphabetOrder();
    }

    private List<TextComponent> getWordsByVowelPercentage() {

        return service.getWordsByVowelPercentage();
    }

    private List<TextComponent> getWordsThatBeginsWithVowel() {

        return service.getWordsThatBeginsWithVowel();
    }

    private List<TextComponent> getWordsThatSortedByCountOfGivenLetterIncr() {
        return service.getWordsThatSortedByCountOfGivenLetterIncr('a');
    }

    private List<String> getWordsSortedByCountInSentences() {
        return service.getWordsSortedByCountInSentences(new ArrayList<>());
    }

    private TextComponent removeSubstringEverySentence() {

        return service.removeSubstringEverySentence();
    }

    private TextComponent removeWordsStartWithConsonant() {

        return service.removeWordsStartWithConsonant(5);
    }

    private List<TextComponent> getWordsThatSortedByCountOfGivenLetterDecr() {
        return service.getWordsThatSortedByCountOfGivenLetterDecr('a');
    }

    private TextComponent getPalindromeMaxLenght() {

        return service.getPalindromeMaxLenght();
    }

    private TextComponent removeStartLettersFromWords() {
        return service.removeStartLettersFromWords();
    }

    private TextComponent replaceFirstAndLastWordsInSentence() {
        return service.replaceFirstAndLastWordsInSentence();
    }
}

