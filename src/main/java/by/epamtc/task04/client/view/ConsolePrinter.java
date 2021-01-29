package by.epamtc.task04.client.view;

import java.util.Map;

public class ConsolePrinter {

    public void showMenu(ConsoleMenu menu) {
        System.out.println("Select number of action:");

        for (Map.Entry<Integer, String> entry : menu.getActions().entrySet()) {
            System.out.printf("%d. %s\n", entry.getKey(), entry.getValue());
        }
    }

    public void printError(String message) {
        System.err.println(message);
        System.err.flush();
    }

    public void printText(String text) {
        System.out.println(text);
        System.out.flush();
    }
}
