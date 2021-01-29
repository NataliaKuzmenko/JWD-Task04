package by.epamtc.task04.server.dao;


import by.epamtc.task04.server.entity.TextComponent;
import by.epamtc.task04.server.service.exception.BookDAOException;

public interface BookDAO {
    TextComponent createBook() throws BookDAOException;
}
