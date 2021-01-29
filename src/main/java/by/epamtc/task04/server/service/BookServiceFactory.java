package by.epamtc.task04.server.service;

import by.epamtc.task04.server.service.impl.BookServiceImpl;

public class BookServiceFactory {

    private static final BookServiceFactory instance = new BookServiceFactory();
    private final BookService bookService = new BookServiceImpl();

    private BookServiceFactory() {
    }

    public static BookServiceFactory getInstance() {
        return instance;
    }

    public BookService getBookService() {
        return bookService;
    }
}
