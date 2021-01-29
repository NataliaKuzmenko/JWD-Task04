package by.epamtc.task04.server.dao.impl;

import by.epamtc.task04.server.dao.BookDAO;
import by.epamtc.task04.server.dao.impl.parser.ParserFactory;
import by.epamtc.task04.server.entity.TextComponent;
import by.epamtc.task04.server.entity.TextComponentType;
import by.epamtc.task04.server.entity.TextComposite;
import by.epamtc.task04.server.service.exception.BookDAOException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class FileBookDAO implements BookDAO {

    private static final String BOOK_FILENAME = "book_example.txt";

    private FileBookReader reader;
    private AbstractParser parser;

    public FileBookDAO() {
        ClassLoader loader = getClass().getClassLoader();
        String filePath = Objects.requireNonNull(loader.getResource(BOOK_FILENAME)).getFile();
        this.reader = new FileBookReader(new File(filePath));
        this.parser = ParserFactory.getInstance().getParser();
    }

    @Override
    public TextComponent createBook() throws BookDAOException {
        TextComposite book = new TextComposite(TextComponentType.BOOK);

        try {
            String text = reader.readAllText();
            TextComponent bookPart = parser.parse(text);
            addAllParts(book, (TextComposite) bookPart);
        } catch (IOException e) {
            throw new BookDAOException("Error of reading file: ", e);
        }

        return book;
    }

    private void addAllParts(TextComposite book, TextComposite parse) {
        List<TextComponent> children = parse.getChildren();

        for (TextComponent child : children) {
            book.addChildren(child);
        }
    }
}
