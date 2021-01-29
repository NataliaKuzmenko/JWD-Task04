package by.epamtc.task04.server.dao.impl.parser;

import by.epamtc.task04.server.dao.impl.AbstractParser;
import by.epamtc.task04.server.entity.Lexeme;
import by.epamtc.task04.server.entity.TextComponent;
import by.epamtc.task04.server.entity.TextComponentType;
import by.epamtc.task04.server.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractParser {

    private final Pattern pattern;
    private final String lexeme = "(([-\\w]+)|([-.=()'%,\":;])|(\\s+))";
    private final String punctuation = "[-.=()'%,\":;]";
    private final String space = "\\s+";

    public LexemeParser(AbstractParser nextParser) {
        super(nextParser);
        this.pattern = Pattern.compile(lexeme);
    }

    @Override
    public TextComponent parse(String text) {
        TextComposite sentence = new TextComposite(TextComponentType.SENTENCE);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String word = matcher.group();

            if (word.matches(punctuation)) {
                sentence.addChildren(new Lexeme(word, TextComponentType.PUNCTUATION));
            } else if (word.matches(space)) {
                sentence.addChildren(new Lexeme(word, TextComponentType.SPACE));
            } else {
                sentence.addChildren(new Lexeme(word, TextComponentType.WORD));
            }
        }

        return sentence;
    }
}
