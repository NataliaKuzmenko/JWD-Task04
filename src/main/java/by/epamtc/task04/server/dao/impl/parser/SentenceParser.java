package by.epamtc.task04.server.dao.impl.parser;

import by.epamtc.task04.server.dao.impl.AbstractParser;
import by.epamtc.task04.server.entity.TextComponent;
import by.epamtc.task04.server.entity.TextComponentType;
import by.epamtc.task04.server.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParser {

    private final Pattern pattern;
    private final String sentence = ".+?(([.!?]\\s?)|(\\n))";

    public SentenceParser(AbstractParser nextParser) {
        super(nextParser);
        this.pattern = Pattern.compile(sentence);
    }

    @Override
    public TextComponent parse(String text) {
        TextComposite paragraph = new TextComposite(TextComponentType.PARAGRAPH);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String sentence = matcher.group();
            TextComponent sentenceComponent = parseNext(sentence);

            paragraph.addChildren(sentenceComponent);
        }

        return paragraph;
    }
}
