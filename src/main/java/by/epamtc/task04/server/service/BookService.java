package by.epamtc.task04.server.service;

import by.epamtc.task04.server.entity.TextComponent;

import java.util.List;
import java.util.Set;

public interface BookService {

    Integer getMaxCountOfSentencesWithRepeatedWord();

    String orderSentencesByWordsCount();

    TextComponent wordInFirstSentenceAbsentInAnother();

    Set<TextComponent> getWordsConcreteLengthInQuestions(int wordLength);

    TextComponent replaceFirstAndLastWordsInSentence();

    Set<TextComponent> allWordsAlphabetOrder();

    List<TextComponent> getWordsByVowelPercentage();

    List<TextComponent> getWordsThatBeginsWithVowel();

    List<TextComponent> getWordsThatSortedByCountOfGivenLetterIncr(Character character);

    List<String> getWordsSortedByCountInSentences(List<String> words);

    TextComponent removeSubstringEverySentence();

    TextComponent removeWordsStartWithConsonant(int wordLength);

    List<TextComponent> getWordsThatSortedByCountOfGivenLetterDecr(Character character);

    TextComponent getPalindromeMaxLenght();

    TextComponent removeStartLettersFromWords();

    TextComponent replaceWordsConcreteLengthInSentence(int wordLength, int sentenceNumber, String text);
}
