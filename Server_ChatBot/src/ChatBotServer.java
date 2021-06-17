import Handler.CustomClientHandler;
import Utils.Logger;

import java.io.IOException;
import java.net.ServerSocket;

public class ChatBotServer {

    public static void createServer(int port){
        Logger.info("Tentativo di creazione del server in corso.");
        try {
            ServerSocket server = new ServerSocket(port);
            Logger.info("Tentativo riuscito, server in ascolto sulla porta " + port);
            Logger.core("In attesa di connessioni...");

            while(true){
                new CustomClientHandler(server.accept()).start();
            }
        }catch (IOException exp){
            Logger.error("Errore nella creazione del server \n\t\t -" + exp.getMessage());
        }
    }

    public static void main(String[] args) {
        ChatBotServer.createServer(4444);
    }


}
