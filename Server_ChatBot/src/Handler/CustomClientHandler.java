package Handler;

import java.net.Socket;

public class CustomClientHandler extends ClientHandler {

    public CustomClientHandler(Socket socket) {
        super(socket);
    }

    @Override
    protected void greet(){

    }

    @Override
    protected void handleClientRequest(String command){

    }
}
