package Handler;

import Utils.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class ClientHandler extends Thread{

    private final Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    protected final String id;
    protected boolean isConnected = true;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        id = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);

            Logger.core("Connesso correttamente con " + id);
        }catch (IOException exp){
            Logger.error("Errore nell'inizializzazione della connessione" + exp.getMessage());
            isConnected = false;
        }

    }

    protected abstract void greet();

    protected abstract void handleClientRequest(String request);

    private String readFromClient() throws IOException {
        String cn;
        if (isConnected) {
            if (!socket.isInputShutdown()) {
                if ((cn = in.readLine()) != null) {
                    return cn;
                }
            }
        }
        return null;
    }

    protected void writeToClient(String content) {
        if (isConnected) {
            if (!socket.isOutputShutdown()) {
                out.println(content);
            }
        }
    }

    private void close(){
        try {
            Logger.core("Tentativo di chiusura in corso per " + id);
            socket.close();
            Logger.core("Tentativo di chiusura riuscito per " + id);
        }catch (IOException exp){
            Logger.error("Errore nello shutdown del socket " + id + "\n\t\t - forse gia' chiuso?");
        }
    }

    @Override
    public void run() {
        try{
            if(isConnected) greet();
            while(isConnected){
                String cmd = readFromClient();
                if(cmd != null) handleClientRequest(cmd);
            }
        }catch (IOException exp){
            Logger.error(id + " Errore sconosciuto, chiusura connessione in corso");
        }
        close();
    }
}
