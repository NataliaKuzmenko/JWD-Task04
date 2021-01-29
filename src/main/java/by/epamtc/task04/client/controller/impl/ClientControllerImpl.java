package by.epamtc.task04.client.controller.impl;

import by.epamtc.task04.client.controller.ClientController;
import by.epamtc.task04.client.view.ConsoleMenu;
import by.epamtc.task04.client.view.ConsolePrinter;
import by.epamtc.task04.client.view.exception.MenuException;
import by.epamtc.task04.server.entity.TextComponent;

import java.io.*;
import java.net.Socket;

public class ClientControllerImpl implements ClientController {

    private final String host;
    private final int port;
    private final ConsoleMenu menu;
    private final ConsolePrinter printer;

    public ClientControllerImpl(String host, int port) {
        this.host = host;
        this.port = port;
        this.menu = new ConsoleMenu();
        this.printer = new ConsolePrinter();
    }

    @Override
    public void run() {
        while (true) {
            printer.showMenu(menu);

            String request;
            try {
                request = menu.selectAction();
            } catch (MenuException | IOException e) {
                printer.printError(e.getMessage());
                continue;
            }

            if (request.equalsIgnoreCase("Exit.")) {
                break;
            }

            String response = sendRequest(request);
            printer.printText("\nResponse:\n" + response + "\n");
        }
    }

    private String sendRequest(String request) {
        String response;

        try (Socket clientSocket = new Socket(host, port);
             ObjectInput input = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutput output = new ObjectOutputStream(clientSocket.getOutputStream())
        ) {
            output.writeBytes(request + "\n");
            output.flush();

            try {
                TextComponent responseComponent = (TextComponent) input.readObject();
                response = responseComponent.text();
            } catch (ClassNotFoundException e) {
                response = e.getMessage();
            }
        } catch (IOException e) {
            response = e.getMessage();
        }

        return response;
    }
}
