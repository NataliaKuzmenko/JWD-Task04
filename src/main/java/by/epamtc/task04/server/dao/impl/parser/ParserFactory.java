package by.epamtc.task04.server.dao.impl.parser;

import by.epamtc.task04.server.dao.impl.AbstractParser;

public final class ParserFactory {

    private static final ParserFactory instance = new ParserFactory();
    private AbstractParser parser = new ParagraphParser(new SentenceParser(new LexemeParser(null)));

    private ParserFactory() {
    }

    public static ParserFactory getInstance() {
        return instance;
    }

    public AbstractParser getParser() {
        return parser;
    }
}
