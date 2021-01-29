package by.epamtc.task04.client.controller;

import by.epamtc.task04.client.controller.impl.ClientControllerImpl;

public class ClientRunner {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 5555;

        ClientController service = new ClientControllerImpl(host, port);
        service.run();
    }
}
