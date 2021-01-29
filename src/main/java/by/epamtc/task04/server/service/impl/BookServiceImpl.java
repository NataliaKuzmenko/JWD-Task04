package by.epamtc.task04.server.service.impl;

import by.epamtc.task04.server.dao.BookDAO;
import by.epamtc.task04.server.dao.DAOFactory;
import by.epamtc.task04.server.entity.Lexeme;
import by.epamtc.task04.server.entity.TextComponent;
import by.epamtc.task04.server.entity.TextComponentType;
import by.epamtc.task04.server.entity.TextComposite;
import by.epamtc.task04.server.service.BookService;
import by.epamtc.task04.server.service.exception.BookDAOException;

import java.util.*;

public class BookServiceImpl implements BookService {

    private BookDAO dao;

    public BookServiceImpl() {
        this.dao = DAOFactory.getInstance().getBookDAO();
    }

    //Task #1
    @Override
    public Integer getMaxCountOfSentencesWithRepeatedWord() {
        return null;
    }

    // Task #2
    @Override
    public String orderSentencesByWordsCount() {
        TextComponent book = takeBook();
        Map<Integer, Integer> sentenceAndCountPair = new LinkedHashMap<>();
        List<String> sentencesList = new ArrayList();
        int sentenceCount = 0;

        List<TextComponent> paragraphs = ((TextComposite) book).getChildren();
        for (TextComponent paragraph : paragraphs) {
            List<TextComponent> sentences = ((TextComposite) paragraph).getChildren();

            for (TextComponent sentence : sentences) {
                if (sentence.getType() != TextComponentType.CODE) {
                    int sentenceLength = sentence.text().length();
                    sentenceAndCountPair.put(sentenceCount++, sentenceLength);
                    sentencesList.add(sentence.text());
                }
            }
        }

        return printSentences(sentencesList, sentenceAndCountPair);
    }

    private String printSentences(List<String> sentences, Map<Integer, Integer> sentenceAndCountPair) {
        StringBuilder sortedSent = new StringBuilder();

        while (sentenceAndCountPair.size() > 0) {
            int maxSen = maxWords(sentenceAndCountPair);
            String text1 = sentences.get(maxSen);

            sortedSent.append(text1.trim()).append("\n");
            sentenceAndCountPair.remove(maxSen);
        }
        return sortedSent.toString();
    }

    private int maxWords(Map<Integer, Integer> sentenceAndCountPair) {
        int maxLength = 0;
        int maxSen = 0;

        for (Map.Entry<Integer, Integer> entry : sentenceAndCountPair.entrySet()) {
            if (maxLength < entry.getValue()) {
                maxLength = entry.getValue();
                maxSen = entry.getKey();
            }
        }

        return maxSen;
    }


    // Task #3
    @Override
    public TextComponent wordInFirstSentenceAbsentInAnother() {
        TextComponent book = takeBook();
        List<TextComponent> sentences = takeSentencesWithoutCode(book);
        TextComponent firstSentence = sentences.get(0);

        List<TextComponent> wordsInFirstSentence = ((TextComposite) firstSentence).getChildren();

        for (TextComponent child : wordsInFirstSentence) {
            if (!anyOtherSentencesContainWord(child)) {
                return child;
            }
        }

        return null;
    }


    private boolean anyOtherSentencesContainWord(TextComponent child) {
        TextComponent book = takeBook();
        List<TextComponent> sentences = takeSentences(book);

        for (int i = 1; i < sentences.size(); i++) {
            List<TextComponent> words = ((TextComposite) sentences.get(i)).getChildren();

            for (TextComponent word : words) {
                if (word.equals(child)) {
                    return true;
                }
            }
        }
        return false;
    }

    //Task #4
    @Override
    public Set<TextComponent> getWordsConcreteLengthInQuestions(int wordLength) {
        Set<TextComponent> wordsConcreteLength = new HashSet<>();
        List<TextComponent> questions = getQuestionsFromText();
        List<TextComponent> lexemes = new ArrayList<>();
        for (TextComponent question : questions) {
            List<TextComponent> words = ((TextComposite) question).getChildren();
            lexemes.addAll(words);

        }
        for (TextComponent lexeme : lexemes) {
            if (lexeme.text().length() == wordLength) {
                wordsConcreteLength.add(lexeme);
            }
        }
        return wordsConcreteLength;
    }

    private List<TextComponent> getQuestionsFromText() {
        TextComponent book = takeBook();
        List<TextComponent> sentences = takeSentences(book);
        List<TextComponent> questions = new ArrayList<>();
        for (TextComponent sentence : sentences) {
            if (sentence.getType() != TextComponentType.CODE) {
                if (sentence.toString().trim().contains("?")) {
                    questions.add(sentence);
                }
            }
        }
        return questions;
    }


    // Task #5
    @Override
    public TextComponent replaceFirstAndLastWordsInSentence() {
        TextComponent book = takeBook();
        List<TextComponent> sentences = takeSentences(book);

        for (TextComponent sentence : sentences) {
            if (sentence.getType() != TextComponentType.CODE) {
                replaceFirstWithLast(sentence);
            }
        }

        return book;
    }

    private void replaceFirstWithLast(TextComponent sentence) {
        List<TextComponent> lexemes = ((TextComposite) sentence).getChildren();

        for (int i = 0; i < lexemes.size(); i++) {
            if (lexemes.get(i).getType() == TextComponentType.WORD) {
                for (int j = lexemes.size() - 1; j >= 0; j--) {
                    if (lexemes.get(j).getType() == TextComponentType.WORD) {
                        TextComponent temp = lexemes.get(i);

                        lexemes.set(i, lexemes.get(j));
                        lexemes.set(j, temp);
                        break;
                    }
                }
                break;
            }
        }
    }

    //Task #6
    @Override
    public Set<TextComponent> allWordsAlphabetOrder() {
        TextComponent book = takeBook();
        Set<TextComponent> words = new TreeSet<>();
        List<TextComponent> sentences = takeSentences(book);
        List<TextComponent> lexemes = new ArrayList<>();
        for (TextComponent sentence : sentences) {
            List<TextComponent> wordsOfSentence = ((TextComposite) sentence).getChildren();
            lexemes.addAll(wordsOfSentence);

        }
        for (TextComponent lexeme : lexemes) {
            words.add(lexeme);
        }
        return words;
    }

    //Task #7
    @Override
    public List<TextComponent> getWordsByVowelPercentage() {
        return null;
    }

    //Task #8
    @Override
    public List<TextComponent> getWordsThatBeginsWithVowel() {
        return null;
    }

    //Task #9
    @Override
    public List<TextComponent> getWordsThatSortedByCountOfGivenLetterIncr(Character character) {
        return null;
    }

    //Task #10
    @Override
    public List<String> getWordsSortedByCountInSentences(List<String> words) {
        return null;
    }

    //Task #11
    @Override
    public TextComponent removeSubstringEverySentence() {
        return null;
    }

    //Task #12
    @Override
    public TextComponent removeWordsStartWithConsonant(int wordLength) {
        return null;
    }

    //Task #13
    @Override
    public List<TextComponent> getWordsThatSortedByCountOfGivenLetterDecr(Character character) {
        return null;
    }

    //Task #14
    @Override
    public TextComponent getPalindromeMaxLenght() {
        TextComponent palindromeMaxLenght;
        Set<TextComponent> palindromes = new HashSet<>();
        TextComponent book = takeBook();
        List<TextComponent> sentences = takeSentences(book);
        Set<TextComponent> lexemes = new HashSet<>();
        for (TextComponent sentence : sentences) {
            List<TextComponent> wordsOfSentence = ((TextComposite) sentence).getChildren();
            lexemes.addAll(wordsOfSentence);
        }
        for (TextComponent lexeme : lexemes) {
            String str = lexeme.toString();
            String reverse = new StringBuffer(str).reverse().toString();
            if (str.equalsIgnoreCase(reverse)) {
                palindromes.add(lexeme);
            }
        }
        List<TextComponent> palindr = new ArrayList<>();
        palindr.addAll(palindromes);

        int maxLenghtPalindrome = palindr.get(0).toString().length();
        int index = 0;

        for (int i = 0; i < palindr.size(); i++) {
            if (palindr.get(i).toString().length() > maxLenghtPalindrome) {
                maxLenghtPalindrome = palindr.get(i).toString().length();
                index = i;
            }
        }
        palindromeMaxLenght = palindr.get(index);
        return palindromeMaxLenght;
    }

    // Task #15
    @Override
    public TextComponent removeStartLettersFromWords() {
        return null;
    }


    // Task #16
    @Override
    public TextComponent replaceWordsConcreteLengthInSentence(int wordLength, int sentenceNumber, String text) {
        TextComponent book = takeBook();
        List<TextComponent> sentences = takeSentences(book);
        TextComponent concreteSentence = sentences.get(sentenceNumber);

        List<TextComponent> words = ((TextComposite) concreteSentence).getChildren();
        for (TextComponent word : words) {
            if (word.text().length() == wordLength) {
                ((Lexeme) word).setLexeme(text);
            }
        }

        return book;
    }

    private List<TextComponent> takeLexemes(TextComponent book) {
        List<TextComponent> lexemes = new ArrayList<>();
        List<TextComponent> sentences = takeSentences(book);
        for (TextComponent sentence : sentences) {
            List<TextComponent> wordsOfSentence = ((TextComposite) sentence).getChildren();
            lexemes.addAll(wordsOfSentence);

        }

        return lexemes;
    }

    private List<TextComponent> takeSentencesWithoutCode(TextComponent book) {
        List<TextComponent> sentences = new ArrayList<>();

        for (TextComponent paragraph : takeParagraphs(book)) {
            if (paragraph.getType() != TextComponentType.CODE) {
                sentences.addAll(((TextComposite) paragraph).getChildren());
            }
        }

        return sentences;
    }

    private List<TextComponent> takeSentences(TextComponent book) {
        List<TextComponent> sentences = new ArrayList<>();

        for (TextComponent paragraph : takeParagraphs(book)) {
            List<TextComponent> paragraphSentences = ((TextComposite) paragraph).getChildren();
            sentences.addAll(paragraphSentences);
        }

        return sentences;
    }

    private List<TextComponent> takeParagraphs(TextComponent book) {
        return ((TextComposite) book).getChildren();
    }

    private TextComponent takeBook() {
        TextComponent book = new TextComposite(TextComponentType.BOOK);

        try {
            book = dao.createBook();
        } catch (BookDAOException e) {
            e.printStackTrace();
        }

        return book;
    }
}
