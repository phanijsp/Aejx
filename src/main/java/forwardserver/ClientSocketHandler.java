package forwardserver;

import forwardserver.flows.ClientServerSocketFlow;
import forwardserver.flows.ServerClientSocketFlow;
import utils.ApplicationConfig;

import java.io.IOException;
import java.net.Socket;
import static utils.ApplicationUtils.log;

public class ClientSocketHandler extends Thread{

    private final Socket clientSocket;

    public ClientSocketHandler(Socket clientSocket){

        this.clientSocket = clientSocket;

    }

    public void run(){

        try {

            Socket serverSocket = new Socket(ApplicationConfig.getInstance().getForwardIp(), ApplicationConfig.getInstance().getForwardPort());

            new ClientServerSocketFlow(clientSocket, serverSocket)
                    .start();

            new ServerClientSocketFlow(clientSocket, serverSocket)
                    .start();

        } catch (IOException e) {
            log(e.getMessage());
            e.printStackTrace();
        }
    }
}
