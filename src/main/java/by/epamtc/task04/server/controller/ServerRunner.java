package by.epamtc.task04.server.controller;

import by.epamtc.task04.server.controller.impl.ServerControllerImpl;

public class ServerRunner {

    public static void main(String[] args) {
        int port = 5555;
        ServerController server = new ServerControllerImpl(port);
        server.run();
    }
}
