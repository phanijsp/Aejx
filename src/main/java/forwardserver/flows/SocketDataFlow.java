package forwardserver.flows;

import java.net.Socket;

public abstract class SocketDataFlow extends Thread{
    Socket clientSocket;
    Socket serverSocket;
    public SocketDataFlow(Socket clientSocket, Socket serverSocket){
        this.clientSocket = clientSocket;
        this.serverSocket = serverSocket;
    }

    public void run(){
        begin();
    }

    protected abstract void begin();

}
